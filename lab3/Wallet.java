package lab3;

import java.util.HashMap;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.util.Set;
import java.util.ArrayList;

/**  A Wallet is a map from keyNames (which are strings) to private keys and public keys
 **/

public class Wallet {

    /* the map determing the private key for every keyName */
    
    private HashMap<String,PrivateKey> keyName2PrivateKey;

    /* the map determing the public Keys */
    private PublicKeyMap  publicKeyMap;


    /* Constructor for the empty Wallet */
    
    public Wallet(){
        this.keyName2PrivateKey = new HashMap<String,PrivateKey> ();
        this.publicKeyMap = new PublicKeyMap();
    }   

    /* Constructor for the Wallet constructed from hashmaps */    

    public Wallet(HashMap<String,PrivateKey> keyName2PrivateKey,
                  HashMap<String,PublicKey> keyName2PublicKey,
                  HashMap<PublicKey,String> publicKey2KeyName) {       
        this.keyName2PrivateKey = new HashMap<String,PrivateKey> (keyName2PrivateKey);
        this.publicKeyMap = new PublicKeyMap(keyName2PublicKey,publicKey2KeyName);
    }

    /* adds a key given by a name, and a private and public key */

    public void addKey(String keyName,PrivateKey privateKey, PublicKey publicKey){
        keyName2PrivateKey.put(keyName,privateKey);
        publicKeyMap.addKey(keyName,publicKey);
    }

    /* returns the publicKeyMap */

    public PublicKeyMap toPublicKeyMap(){
        return new PublicKeyMap(publicKeyMap);
    }


    /* returns the map from names to private keys  */
    
    public HashMap<String,PrivateKey> getKeyName2PrivateKey(){
        return new HashMap<String,PrivateKey>(keyName2PrivateKey);
    }

    /* returns the map from names to public keys  */    
    public HashMap<String,PublicKey> getKeyName2PublicKey(){
        return publicKeyMap.getUser2PublicKey();
    }

    /* returns the map from public keys to names  */    
    public HashMap<PublicKey,String> publicKey2KeyName(){
        return publicKeyMap.publicKey2User();   
    }    


    /* obatin the keyName from a public key */

    public String getKeyName(PublicKey pbk) {
        return publicKeyMap.getUser(pbk);
    }
    /* obtain the publicKey from a keyName */

    public PublicKey getPublicKey(String keyName) {
        return publicKeyMap.getPublicKey(keyName);
    }

    /* obtain the publicKey as a string (for easy printing) from a keyName */    

    public String getPublicKeyString(String keyName)throws NoSuchAlgorithmException {
        return publicKeyMap.getPublicKeyString(keyName);
    };

    /* obtain the privateKey from a keyName */    

    public PrivateKey getPrivateKey(String keyName) {
        return keyName2PrivateKey.get(keyName);
    }

    /* obtain the set of keyNames */    
    public Set<String> getKeyNames(){
        return publicKeyMap.getUsers();
    }

    /* Creates a subwallet from a wallet, containing those
       keys for which the keyname is in the list keyNames */
    
    public Wallet toSubWallet(ArrayList<String> keyNames){
        Wallet result = new Wallet();
        for (String keyName : keyNames){
            result.addKey(keyName,getPrivateKey(keyName),getPublicKey(keyName));
        };
        return result;
    }

    /* sign a message using the key with name keyName */

    public byte[] signMessage(byte[] message,String keyName)
        throws NoSuchAlgorithmException, SignatureException, InvalidKeyException{
        return Crypto.sign(getPrivateKey(keyName),message);
    }

    /* 
         Create the signature for the message consisting of a outputList and
         the input given by sender and amount */
    

    public byte[] getSignature(PublicKey sender, int amount,OutputList outList)
        throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return signMessage(outList.getMessageToSign(sender,amount),getKeyName(sender));
            }

}    
