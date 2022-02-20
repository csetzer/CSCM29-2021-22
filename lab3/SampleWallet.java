package lab3;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Set;
import lab3.LabUtils.CryptoUtils;

public class SampleWallet {

    /* generates a Wallet for a given list of keyNames in a random way */
    
   public static Wallet generate(String[] keyNames) throws NoSuchAlgorithmException {
	Wallet wallet = new  Wallet();
        byte[] initialKey = new byte[32];
	for (int i = 0; i < initialKey.length; i++){
            initialKey[i] = (byte)i;
        }
        SecureRandom prg = new SecureRandom(initialKey);
        int numSizeBits = 2048;
	for (String keyName : keyNames){	
            byte[] key = new byte[32];
            prg.nextBytes(key);
            System.out.println("Generating key pair for keyName " + keyName);
            KeyPair rp = CryptoUtils.generateKeyPair(numSizeBits);
	    wallet.addKey(keyName,rp.getPrivate(),rp.getPublic());
        }
	return wallet;
    }


    /* test case */
    
    public static void test() throws NoSuchAlgorithmException {
	String[] names = new String[]{ "Alice", "Bob", "Carol", "David"};
	Wallet keys = generate(names);
	Set<String> keyNames = keys.getKeyNames();
	for (String keyName  : keyNames){
	    System.out.println("KeyName = " + keyName + " public key = " + keys.getPublicKeyString(keyName));
	}
    }


    public static void main(String[] args) throws NoSuchAlgorithmException{
	test();
    }        


    
}
