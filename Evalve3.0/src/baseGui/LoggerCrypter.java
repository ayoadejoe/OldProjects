package baseGui;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
public class LoggerCrypter {
	
	public LoggerCrypter(File toEncrypt) {
		try {
			//("Received "+toEncrypt+" for encryption");
			String key = "Isaiah40:31" ; // needs to be at least 8 characters for DES
			FileInputStream fis = new FileInputStream( toEncrypt );
			FileOutputStream fos = new FileOutputStream( toEncrypt+"+locked" );
			encrypt(key, fis, fos);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
	}
	
	public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance( "DES" );
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance( "DES" ); // DES/ECB/PKCS5Padding for SunJCE
	if (mode == Cipher.ENCRYPT_MODE) {
		cipher.init(Cipher.ENCRYPT_MODE, desKey);
		CipherInputStream cis = new CipherInputStream(is, cipher);
		doCopy(cis, os);
	} else if (mode == Cipher.DECRYPT_MODE) {
		cipher.init(Cipher.DECRYPT_MODE, desKey);
		CipherOutputStream cos = new CipherOutputStream(os, cipher);
		doCopy(is, cos);
	}
	}
	public static void doCopy(InputStream is, OutputStream os) throws IOException {
		byte [] bytes = new byte [64];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}
	}