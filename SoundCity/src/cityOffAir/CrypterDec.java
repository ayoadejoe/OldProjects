package cityOffAir;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Vector;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CrypterDec {

	public ArrayList<Object> CrypterDec(String fileName) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException {
		//Code to read your object from file
		SecretKey key64 = new SecretKeySpec( new byte[] {0x49, 0x73,0x61,0x69, 0x61, 0x68, 0x34, 0x30, 0x3a, 0x33, 0x31}, "Blowfish" );
		Cipher cipher = Cipher.getInstance("Blowfish");
				cipher.init( Cipher.DECRYPT_MODE, key64 );
				CipherInputStream cipherInputStream = new CipherInputStream( new BufferedInputStream( new FileInputStream( fileName ) ), cipher );
				ObjectInputStream inputStream = new ObjectInputStream( cipherInputStream );
				SealedObject sealedObject2 = (SealedObject) inputStream.readObject();
				ArrayList<Object> submissions = (ArrayList) sealedObject2.getObject( cipher );
				System.out.println("Decoded vector:"+submissions);
				return submissions;
	}
	
	public static void main(String[] args){
		File toKeep = new File("Syncs\\");
		String [] keep = toKeep.list();
		String filename = "Syncs\\"+keep[0];
		System.out.println("Filename to acquire:"+filename);
		try {
			new CrypterDec().CrypterDec(filename);
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | ClassNotFoundException
				| IllegalBlockSizeException | BadPaddingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
