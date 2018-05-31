package encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAEncrypt {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String str = "This is a Base64 test.";
		
		System.out.println(SHA256(str));
		
	}
	
	public static String SHA256(String input) throws NoSuchAlgorithmException{
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		byte[] result = mDigest.digest(input.getBytes());
		
		StringBuffer sb = new StringBuffer();
		for (int i =0; i < result.length; i++){
			sb.append(Integer.toString((result[i] & 0xFF) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
