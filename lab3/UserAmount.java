package lab3;

import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.util.Hashtable;
import java.util.Set;
import java.util.ArrayList;


/** 
 *   UserAmount defines a map from users to the amount they have
     users are given as public keys
 */

public class UserAmount {

    /** 
     * The current balance of each user, with each account's name mapped to its 
     *    current balance.
     */

    protected Hashtable<PublicKey, Integer> userAmountBase;

    /**
     *  In order to print out the userAmount in a good order
     *  we maintain a list of public Keys,
     *  which will be the set of public keys maped by it in the order
     *  they were added
     **/

    protected ArrayList<PublicKey> publicKeyList;


    /** 
     * Creates an empty UserAmount
     */
    public UserAmount() {
	userAmountBase = new Hashtable<PublicKey, Integer>();
	publicKeyList = new ArrayList<PublicKey>();
	
    }

    /** 
     * Creates a UserAmount from a map from publicKeys to integers
     */
    
    public UserAmount(Hashtable<PublicKey, Integer> userAmountBase) {
	this.userAmountBase = userAmountBase;
	publicKeyList = new ArrayList<PublicKey>();	
	for (PublicKey pbk : userAmountBase.keySet()){
	    publicKeyList.add(pbk);
	}
    }


    /**
     * Constructor for creating UserAmount from an InputList
     * by adding for each input the amount to the user.
     * InputList could have more than one input for the same user
     * in which case the amounts are added up
     *
     * This is used when when checking that a InputList can be deducted
     *   from  an accountbalance given as an element of Ledger
     *    we first create using this constructor a UserAmount 
     *    which determines for each user the sum of amounts to be deducted
     *
     *   then we can check whether each input in the original ledger is
     *     greater the sum of items for each user to be deducted
     */



    public UserAmount(InputList inputList) {
	this.userAmountBase = new Hashtable<PublicKey, Integer>();
	for (Input  input : inputList.toList()){
	    this.addBalance(input.getSender(),input.getAmount());
	};
    }    

    

    /** obtain the underlying Hashtable from string to integers
     */   
    
    public Hashtable<PublicKey,Integer> getUserAmountBase(){
	return userAmountBase;
    };

    /** 
      * obtain the list of publicKeys in the tree map
      */   
    
    public Set<PublicKey> getPublicKeys(){
	return getUserAmountBase().keySet();
    };

    /** 
      * obtain the list of publicKeys in the order they were added
      */   

    public ArrayList<PublicKey> getPublicKeysOrdered(){
	return publicKeyList;
    };        

    
    

    /** 
     * Adds a mapping from new account's name {@code publicKey} to its 
     * account balance {@code balance} into the UserAmount
     *
     * if there was an entry it is overridden.  
     */

    public void addAccount(PublicKey publicKey, int balance) {
	userAmountBase.put(publicKey, balance);
	if (! publicKeyList.contains(publicKey)){
	    publicKeyList.add(publicKey);
	}
    }

    /** 
     * @return true if the {@code publicKey} exists in the UserAmount
     */
    
    public boolean hasPublicKey(PublicKey publicKey) {
	return userAmountBase.containsKey(publicKey);
    }


    /** 
     * @return the balance for this account {@code account}
     *
     *  if there was no entry, return zero
     *
     */
    
    public int getBalance(PublicKey publicKey) {
	if (hasPublicKey(publicKey)){
		return userAmountBase.get(publicKey);
	    } else
	    {  return 0;
	    }
    }


    /** 
     * set the balance for {@code publicKey} to {@code amount}
     */

    
    public void setBalance(PublicKey publicKey, int amount){
	userAmountBase.put(publicKey,amount);
	if (! publicKeyList.contains(publicKey)){
	    publicKeyList.add(publicKey);
	}	
	    };
	

    /** 
     * Imcrements Adds amount to balance for {@code publicKey}
     * 
     *  if there was no entry for {@code publicKey} add one with 
     *       {@code balance}
     */
    
    public void addBalance(PublicKey publicKey, int amount) {
	setBalance(publicKey,getBalance(publicKey) + amount);
    }


    /** 
     * Subtracts amount from balance for {@code publicKey}
     */
    
    public void subtractFromBalance(PublicKey publicKey, int amount) {
	setBalance(publicKey,getBalance(publicKey) - amount);
    }


    /** 
     * Check balance has at least amount for {@code publicKey}
     */
    public boolean checkBalance(PublicKey publicKey, int amount) {
	return (getBalance(publicKey) >= amount);
    }







    public static void test()
	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

	
    }

    /** 
     * main function running test cases
     */            

    public static void main(String[] args)
	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
	UserAmount.test();
    }
}
