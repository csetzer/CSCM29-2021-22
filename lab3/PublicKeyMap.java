package lab3;

import java.util.HashMap;
import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import java.util.Set;


public class PublicKeyMap {

    /** defines maps between names of users (strings) and public keys 
        we define them for convenience and efficiency in both direction,
        and when only using operations in  this class consistency 
	will be maintained */

    /* the underlying hashmaps */
    
    private HashMap<String,PublicKey> user2PublicKey;
    private HashMap<PublicKey,String> publicKey2User;


    /* constructing the empty map */
    
    public PublicKeyMap(){
	this.user2PublicKey = new HashMap<String,PublicKey> ();
	this.publicKey2User = new HashMap<PublicKey,String>();
    }	

    /* constructing a map from existing data 
       assumes that this data is consistent 
       
       An improved version would first check that these are indeep consistent
       and then add it
 
    */

    public PublicKeyMap(HashMap<String,PublicKey> user2PublicKey,
			HashMap<PublicKey,String> publicKey2User) {       
	this.user2PublicKey = new HashMap<String,PublicKey> (user2PublicKey);
	this.publicKey2User = new HashMap<PublicKey,String>(publicKey2User);
    }

    /* the copying constructor */

    public PublicKeyMap(PublicKeyMap publicKeyMap) {       
	this.user2PublicKey = new HashMap<String,PublicKey> (publicKeyMap.getUser2PublicKey());
	this.publicKey2User = new HashMap<PublicKey,String>(publicKeyMap.publicKey2User());
    }

    /* adding an entry to the map */

    public void addKey(String user,PublicKey publicKey){
	user2PublicKey.put(user,publicKey);
	publicKey2User.put(publicKey,user);
    }

	


    /* retrieve the  underlying hash maps */
    
    public HashMap<String,PublicKey> getUser2PublicKey(){
	return new HashMap<String,PublicKey>(user2PublicKey);
    }

    

    public HashMap<PublicKey,String> publicKey2User(){
	return new HashMap<PublicKey,String>(publicKey2User);
    }    

    /*  look up a  user in the map from the public key */


    
    
    public String getUser(PublicKey pbk) {
	return publicKey2User.get(pbk);
    }

    /*  look up a  public key in the map */    

    public PublicKey getPublicKey(String user) {
	return user2PublicKey.get(user);
    }
    /*  obtain public key in a readable format as a string */

    public String getPublicKeyString(String user)throws NoSuchAlgorithmException {
	return KeyUtils.publicKeyToString(getPublicKey(user));
    };



    /* get list of users */
    
    public Set<String> getUsers(){
	return user2PublicKey.keySet();
    }

    /* add a public Key map 
       this allows to combine the public key maps into one 
       The presupposition is that the keymaps have different public Keys and users,
       otherwise the publicKeyMap becomes inconsistent.
       An improved version would first make a check that there is no overlap 
    */

    public void addPublicKeyMap(PublicKeyMap keys){
	for (String user : keys.getUsers()){
	    addKey(user,keys.getPublicKey(user));
	}
    }


}    
