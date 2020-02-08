package cityOffAir;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CrypterEnc {

	public void CrypterEnc(String fileName, ArrayList<Object> submission) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
				IllegalBlockSizeException, IOException, ClassNotFoundException, BadPaddingException {

		//You may use any combination, but you should use the same for writing and reading
		SecretKey key64 = new SecretKeySpec( new byte[] {0x49, 0x73,0x61,0x69, 0x61, 0x68, 0x34, 0x30, 0x3a, 0x33, 0x31}, "Blowfish" );
		Cipher cipher = Cipher.getInstance( "Blowfish" );

		//Code to write your object to file
		cipher.init( Cipher.ENCRYPT_MODE, key64 );
		SealedObject sealedObject = new SealedObject( submission, cipher);
		CipherOutputStream cipherOutputStream = new CipherOutputStream( new BufferedOutputStream( new FileOutputStream( fileName ) ), cipher );
		ObjectOutputStream outputStream = new ObjectOutputStream( cipherOutputStream );
		outputStream.writeObject( sealedObject );
		outputStream.close();
	}

}
