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

//ボタン押下時に呼び出される処理
const checkOnClick = (inputAssociativeArray, numberInputIdArray, inputType) => {
	const checkTarget = new InputCheckOnClick(inputAssociativeArray, numberInputIdArray, inputType);
	checkTarget.checkEmpty();
	checkTarget.checkNumeric();
	checkTarget.setFinalErrorMessage();
	return checkTarget.formControl();
}

const checkOnInput = (inputId) => {
	const checkTarget = new InputCheckOnInput(inputId);
	checkTarget.changeOfBackAndFontColor();
}

/**
 * 送信ボタン押下入力チェッククラス
 */
class InputCheckOnClick {
	constructor(inputAssociativeArray, numberInputIdArray, inputType) {
		this.inputAssociativeArray = inputAssociativeArray;
		this.numberInputIdArray = numberInputIdArray;
		this.errorMessageArray = {};
		this.finalErrorFlag = false;
		this.finalErrorMessage = new String;
		this.inputType = inputType;
	}

	//エラー発生時、指定したid名の背景色を変更する処理
	changeOfBackgroundColorInError(id){
		document.getElementById(id).style.backgroundColor = COLOR_BG_ERROR;
	}

	//エラーがない時、指定したid名の背景色を変更する処理
	changeOfBackgroundColorInNonError(id){
		document.getElementById(id).style.backgroundColor = COLOR_BG_NORMAL;
	}

	//指定したid名の文字色を指定した色に変更する処理
	changeOfFontColor(id, color){
		document.getElementById(id).style.color = color;
	}

	//空文字チェック(空文字の場合trueを返す)
	isEmpty(id){
		return document.getElementById(id).value === "";
	}

	//数値の形式チェック(合致しない場合trueを返す)
	isNotMatchNumberFormat(id){
		const inputNumber = document.getElementById(id).value;
		return !(/^([1-9][0-9]*)$/.test(inputNumber));
	}

	//数値の最大チェック(999を超える場合場合trueを返す)
	isInNumberRange(id){
		return document.getElementById(id).value > NUMBER_MAX;
	}

	//空文字チェックを行い、エラーがあればerrorMessageArrayフィールドにエラー文を挿入
	checkEmpty(){
		Object.keys(this.inputAssociativeArray).forEach((inputId) => {
			if(this.isEmpty(inputId)){
				//エラー有り
				this.finalErrorFlag = true;
				//エラーメッセージの挿入
				this.errorMessageArray[inputId] = this.inputAssociativeArray[inputId] + EMPTY_ERROR_MESSAGE;
				//背景色の変更(エラー時)
				this.changeOfBackgroundColorInError(inputId);
			}else{
				//背景色の変更(正常時)
				this.changeOfBackgroundColorInNonError(inputId);
			}
		});
	}

	//数値チェックを行い、エラーがあればerrorMessageArrayフィールドにエラー文を挿入
	checkNumeric(){
		Object.keys(this.inputAssociativeArray).forEach((inputId) => {
			this.numberInputIdArray.some((numberInputId) => {
				if (inputId === numberInputId) {
					if (this.isNotMatchNumberFormat(inputId)) {
						this.finalErrorFlag = true;
						this.errorMessageArray[inputId] = this.inputAssociativeArray[inputId] + NUMBER_FORMAT_ERROR_MESSAGE;
						this.changeOfBackgroundColorInError(inputId);
						return true;
					}

					if(this.isInNumberRange(inputId)){
						this.finalErrorFlag = true;
						this.errorMessageArray[inputId] = this.inputAssociativeArray[inputId] + NUMBER_RANGE_ERROR_MESSAGE;
						this.changeOfBackgroundColorInError(inputId);
					}else {
						this.changeOfBackgroundColorInNonError(inputId);
					}
				}
			});
		});
	}

	//最終的なエラーメッセージをfinalErrorMessageフィールドにセットする処理
	setFinalErrorMessage(){
		Object.keys(this.inputAssociativeArray).forEach((inputId) => {
			if(!(this.errorMessageArray[inputId] === undefined)){
				this.finalErrorMessage += this.errorMessageArray[inputId];
			}
		});
	}

	//ダイアログを制御する処理
	formControl(){
		if(this.finalErrorFlag){
			setTimeout(() => {alert(this.finalErrorMessage)},1);
			return false;
		}else{
			const executeNext = confirm(this.inputType + "しますか?");
			return executeNext;
		}
	}
}

/**
 *リアルタイムの型チェッククラス
 */
class InputCheckOnInput {
	constructor(inputId) {
		this.inputId = inputId;
	}
	//エラー発生時、指定したid名の背景色を変更する処理
	changeOfBackgroundColorInError(id) {
		document.getElementById(id).style.backgroundColor = COLOR_BG_ERROR;
	}

	//エラーがない時、指定したid名の背景色を変更する処理
	changeOfBackgroundColorInNonError(id) {
		document.getElementById(id).style.backgroundColor = COLOR_BG_NORMAL;
	}

	//指定したid名の文字色を指定した色に変更する処理
	changeOfFontColor(id, color) {
		document.getElementById(id).style.color = color;
	}
	//数値の形式チェック(合致しない場合trueを返す)
	isNotMatchNumberFormat(id) {
		const inputNumber = document.getElementById(id).value;
		return !(/^([1-9][0-9]*|)$/.test(inputNumber));
	}
	//数字の形式に合致しない時、背景色と文字色を変える処理
	changeOfBackAndFontColor() {
		//数値が入力されているかチェック
		if (this.isNotMatchNumberFormat(this.inputId)) {
			//正規表現に合致しない場合、背景色を黄色に、文字色を赤に変更
			this.changeOfBackgroundColorInError(this.inputId);
			this.changeOfFontColor(this.inputId, COLOR_FONT_ERROR);
		} else {
			//正規表現に合致する場合、背景色を白に、文字色を黒に変更
			this.changeOfBackgroundColorInNonError(this.inputId);
			this.changeOfFontColor(this.inputId, COLOR_FONT_NORMAL);
		}
	}
}