package lab3;

import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.InvalidKeyException;


/** InputUnsigned
     *  specifies one input of a transaction without the signature
     %  given by a public key, and the amount to be transferred 
     *  
     */


public class InputUnsigned{

    /** The amount to be transfered  */
    private int amount;

    /** The sender */
    private PublicKey sender;

    /** 
     * Create InputUnsigned from sender and amount
     */

    public InputUnsigned(PublicKey sender,int amount){
	this.amount  = amount;
	this.sender = sender;
    }


    /** 
     * Create InputUnsigned from an Input
     */


    public InputUnsigned(Input input){
	this.sender = input.getSender();
	this.amount =  input.getAmount();
    };


    /** 
     * Get the sender 
     */

    public PublicKey getSender(){
	return sender;
    };


    /* get the name of the sender 
       by looking it up in  a PublicKeyMap
    */

    public String getSenderName(PublicKeyMap pubKeys){
	return pubKeys.getUser(sender);
    };
    
    /** 
     * Get the  amount 
     */    

    public int getAmount(){
	return amount;
    }


    /** 
     * Print the entry in the form word1  <sender> word2 <amount>
     *
     *  we use pubKeyMap in order to lookup the sender's name for each public key
     */
    
    public void print(String word1, String word2,PublicKeyMap pubKeyMap) {
	System.out.println(word1 + getSenderName(pubKeyMap) + word2 + getAmount());
    }


    

    /** 
     * Default way of printing out the InputUnsigned
     */        

    public void print(PublicKeyMap pubKeyMap) {
	print("Sender: "," Amount:  ",pubKeyMap);
    }


    /** 
     * Create the message to be signed, when the input is the current InputUnsigned
     *    for a given outputlist
     *
     *  In Bitcoin the sender signs his input and all outputs
     *    so other inputs of the transaction are not included in the message to sign
     *
     */
    
    public byte[] getMessageToSign(OutputList outList){
	SigData sigData = new SigData();
	sigData.addPublicKey(sender);
	sigData.addInteger(amount);
        for (Output output : outList.toList()) {
	    sigData.addPublicKey(output.getRecipient());
	    sigData.addInteger(output.getAmount());
        }
	return sigData.toArray();
    }


    /* Check that a signature is correct for input given by the current sender and amount
       and a given outputList 
       Note that the message to be signed consist of the input for the user and
       all outputs.
    */

	
    public boolean checkSignature(OutputList outList,byte[] signature){
	return Crypto.verifySignature(sender,getMessageToSign(outList), signature);
    }



    /** 
     * Test cases
     */            

    public static void test()
	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
	Wallet wallet = SampleWallet.generate(new String[]{ "Alice", "Bob", "Carol", "David"});
	PublicKeyMap pubKeyMap = wallet.toPublicKeyMap();
	PublicKey pubKeyA =	pubKeyMap.getPublicKey("Alice");
	PublicKey pubKeyB =	pubKeyMap.getPublicKey("Bob"); 			

	System.out.println();
	System.out.println("Test Alice 10");
	(new InputUnsigned(pubKeyA,10)).print(pubKeyMap);
	System.out.println();
	System.out.println("Test Bob 20");
	(new InputUnsigned(pubKeyB,20)).print(pubKeyMap);	
    };


    /** 
     * main function running test cases
     */            
    
    public static void main(String[] args)
	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
	InputUnsigned.test();
    }        

    
}
    

    
