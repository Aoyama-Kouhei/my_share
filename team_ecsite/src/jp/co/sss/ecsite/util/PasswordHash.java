package jp.co.sss.ecsite.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {
/**
 * パスワードのハッシュ化をするクラス
 * @author tkodama
 * @throws NoSuchAlgorithmException
 *
 */
	public static byte[] passwordHash(String password) throws NoSuchAlgorithmException {
		// ハッシュ化
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] b = digest.digest(password.getBytes());
		return b;
	}
}
