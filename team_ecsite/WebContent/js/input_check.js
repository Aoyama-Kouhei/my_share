//空白時のエラーメッセージ
const EMPTY_ERROR_MESSAGE = "を入力してください\n";
//数値の形式不正時のエラーメッセージ
const NUMBER_FORMAT_ERROR_MESSAGE = "は数値で入力してください\n";
//数値範囲不正時のエラーメッセージ
const NUMBER_RANGE_ERROR_MESSAGE = "は1000未満を入力してください\n";
//エラー時の背景色
const COLOR_BG_ERROR = "yellow";
//正常時の背景色
const COLOR_BG_NORMAL = "white";
//エラー時の文字色
const COLOR_FONT_ERROR = "red";
//正常時の文字色
const COLOR_FONT_NORMAL = "black";
//数値の上限値
const NUMBER_MAX = 999;

//フォームの入力チェック
const inputCheck = () => {
	//各入力フォームに対応した名前を保存する連想配列
	const inputAssociativeArray = { productName: '商品名', price: '価格', explainText: '説明文', imageUrl: '画像URL' };
	//数値の入力チェックを行うフォームのid属性の値を格納した配列
	const numberInputIdArray = ["price"];
	//各入力フォームに対応したエラーメッセージを保存する連想配列
	let errorMessageArray = {};
	//最終的なエラーの有無を判別する変数(true:エラー有り, false:エラーなし)
	let finalErrorFlag = false;
	//エラーメッセージを結合して保存する変数
	let finalErrorMessage = new String();

	//フォームの数だけ入力チェックを行う
	Object.keys(inputAssociativeArray).forEach((inputId) => {
		//空文字チェック
		if (isEmpty(inputId)) {
			//空文字チェックにエラー有り
			finalErrorFlag = true;
			//空文字チェックのエラーメッセージを挿入
			errorMessageArray[inputId] = inputAssociativeArray[inputId] + EMPTY_ERROR_MESSAGE;
			//背景色を黄色に変更
			changeOfBackgroundColorInError(inputId);
			//空文字チェックに引っかかった場合、次の項目のチェックに移る
			return;
		}
		//数値項目の数だけ数値チェックを行う
		numberInputIdArray.some((numberInputId) => {
			//数値チェック項目か判別
			if (inputId === numberInputId) {
				//数値の形式チェック
				if (isNotMatchNumberFormat(inputId)) {
					//数値の形式チェックにエラー有り
					finalErrorFlag = true;
					//数値の形式チェックのエラーメッセージを挿入
					errorMessageArray[inputId] = inputAssociativeArray[inputId] + NUMBER_FORMAT_ERROR_MESSAGE;
					//背景色を黄色に変更
					changeOfBackgroundColorInError(inputId);
					//数値の形式チェックに引っかかった場合、後続の数値チェックを行わず、次の項目のチェックに移る
					return true;
				}
				//数値の最大値チェック
				if (isInNumberRange(inputId)) {
					//数値の最大値チェックにエラー有り
					finalErrorFlag = true;
					//数値の最大値チェックのエラーメッセージを挿入
					errorMessageArray[inputId] = inputAssociativeArray[inputId] + NUMBER_RANGE_ERROR_MESSAGE;
					//背景色を黄色に変更
					changeOfBackgroundColorInError(inputId);
				}else{
					//背景色を白色に変更
					changeOfBackgroundColorInNonError(inputId);
				}
			}else{
				//背景色を白色に変更
				changeOfBackgroundColorInNonError(inputId);
			}
		});
	});

	//各フォームのエラーメッセージを1つの変数にまとめる
	Object.keys(inputAssociativeArray).forEach((inputId) => {
		if(!(errorMessageArray[inputId] === undefined)){
			finalErrorMessage += errorMessageArray[inputId];
		}
	});

	//エラーの有無によるフォーム送信の判別
	if (finalErrorFlag) {
		//エラーメッセージをメッセージボックスに出力
		setTimeout(() => {alert(finalErrorMessage)},1);
		//フォーム送信のキャンセル
		return false;
	} else {
		//登録入力か編集入力か取得
		const inputType = document.getElementById("submit").value;
		//確認ダイアログの表示
		const executeNext = confirm(inputType + "しますか?");
		//確認ダイアログでOKを押した場合フォーム送信を実行
		return executeNext;
	}
};

//数字の形式に合致しない時、背景色と文字色を変える処理
const changeOfBackAndFontColor = (id) => {
	//数値が入力されているかチェック
	if (isNotMatchNumberFormat(id)) {
		//正規表現に合致しない場合、背景色を黄色に、文字色を赤に変更
		changeOfBackgroundColorInError(id);
		changeOfFontColor(id, COLOR_FONT_ERROR);
	} else {
		//正規表現に合致する場合、背景色を白に、文字色を黒に変更
		changeOfBackgroundColorInNonError(id);
		changeOfFontColor(id, COLOR_FONT_NORMAL);
	}
};

//エラー発生時、指定したid名の背景色を変更する処理
const changeOfBackgroundColorInError = (id) => {
	document.getElementById(id).style.backgroundColor = COLOR_BG_ERROR;
};

//エラーがない時、指定したid名の背景色を変更する処理
const changeOfBackgroundColorInNonError = (id) => {
	document.getElementById(id).style.backgroundColor = COLOR_BG_NORMAL;
};

//指定したid名の文字色を指定した色に変更する処理
const changeOfFontColor = (id, color) => {
	document.getElementById(id).style.color = color;
};

//空文字チェック(空文字の場合trueを返す)
const isEmpty = (id) => {
	return document.getElementById(id).value === "";
};

//数値の形式チェック(合致しない場合trueを返す)
const isNotMatchNumberFormat = (id) => {
	const inputNumber = document.getElementById(id).value;
	return !(/^([1-9][0-9]*|)$/.test(inputNumber));
};

//数値の最大チェック(999を超える場合場合trueを返す)
const isInNumberRange = (id) => {
	return document.getElementById(id).value > NUMBER_MAX;
};
