package lab3;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

public class Crypto {

    
    /**
     * @return true if {@code signature} is a valid digital signature of {@code message} under the
     *         key {@code pubKey}. Internally, this uses RSA signature, but the student does not
     *         have to deal with any of the implementation details of the specific signature
     *         algorithm
     */
    public static boolean verifySignature(PublicKey pubKey, byte[] message, byte[] signature) {
        Signature sig = null;
        try {
            sig = Signature.getInstance("SHA256withRSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sig.initVerify(pubKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
	
        try {
            sig.update(message);
            return sig.verify(signature);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return false;

    } 
    
    
    /** 
     *  This sign method takes a secret key {@code privKey} and a messsage {@code msg} as inputs
     * @return a signature for msg under privKey
     */ 
    public static byte[] sign(PrivateKey privKey, byte[] msg)
			throws NoSuchAlgorithmException, SignatureException,
			InvalidKeyException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privKey);
        signature.update(msg);
        return signature.sign();	
        }
}
