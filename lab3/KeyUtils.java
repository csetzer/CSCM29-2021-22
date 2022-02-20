package lab3;

import java.nio.ByteBuffer;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.util.Base64;


public class KeyUtils{

    /* this class defines some convenient operations for dealing with keys 
       (public and private keys, byte arrays) */

    

    /* this prints out a public key in a readable format */
    
    public static String publicKeyToString(PublicKey pbk) throws NoSuchAlgorithmException{
	return Base64.getEncoder().encodeToString(pbk.getEncoded());
    }


    /* Messages to be signed are formed from the amounts (which are integers)
         and public keys 

      The following operation converts an integer into a array of bytes which can
      be used for creating messages
    */

    
    public static byte[] integer2ByteArray (int i){
	ByteBuffer b = ByteBuffer.allocate(Integer.SIZE / 8); // Temporary memory
	b.putInt(i);
	return b.array();
    }


    /* operation for signing a message with a a private key */
    
    public static byte[] signMessage(byte[] message, PrivateKey privateKey)
	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException{
	return Crypto.sign(privateKey, message);
    }
}
