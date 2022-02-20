package lab3;

import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.util.Arrays;


/** Input
     *  specifies one input of a transaction
     %  given by the sender given by a public key, the amount to be transferred 
     *    and a signature
     */


public class Input{

    /** The sender */
    private PublicKey sender;

    /** The amount to be transfered  */
    private int amount;


    /** The signature produced to check validity */
    private byte[] signature;
    

    /** 
     * Create Input from sender, amount, signature
     */

    public Input(PublicKey sender,int amount,byte[] signature){
	this.amount  = amount;
	this.sender = sender;
	this.signature = Arrays.copyOf(signature,signature.length);	
    }


    /** 
     * If we have a Wallet covering the sender
     * and an outputList
     *
     * then we can compute the signature by signing the transaction to be signed consisting
     *    of the public key and input amount and the output list
     *  using the private key of the sender
     */



    public Input(String sender,int amount, OutputList outputList,Wallet wallet)
    	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
	this.amount = amount;
	this.sender = wallet.getPublicKey(sender);
	byte[] signatureTmp = wallet.getSignature(this.sender,amount,outputList);
	this.signature = Arrays.copyOf(signatureTmp,signatureTmp.length);
    };



    /*  as before but referring to the sender by the public key rather than the string */
    
    public Input(PublicKey sender,int amount, OutputList outputList,Wallet wallet)
    	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
	this.amount = amount;
	this.sender = sender;
	byte[] signatureTmp = wallet.getSignature(this.sender,amount,outputList);
	this.signature = Arrays.copyOf(signatureTmp,signatureTmp.length);	
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
     * Get the  signature
     */        

    public byte[] getSignature() {
	return signature;
    }

    /** Create the corresponding element of InputUnsigned
        which is obtained by omitting the signature.
    **/

    public InputUnsigned toInputUnsigned(){
	return new InputUnsigned(sender,amount);
    }

    /** 
        Task 1
        Check the signature is correct for a given OutputList.
           (The OutputList is needed to determine the message to be signed which
            consists of the sender, amount, and the public keys and amounts for
            each output.
            It is computed in the method getMessageToSign  of InputList.java) 

        This can be done by getting the underlying InputUnsigned
        and executing the method checkSignature for it referring to the OutputList
        and the signature which is a field of Input

        In order for the code to compile it has been defined in the questions as True
        but that should be replaced by the correct value.
    **/

    public boolean checkSignature(OutputList outList){
	// you need to replace then next line by the correct statement
	return true;
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
     * Default way of printing out the Input
     */        

    public void print(PublicKeyMap pubKeyMap) {
	print("Sender: "," Amount:  ",pubKeyMap);
    }

    /** 
     * Test cases
     */            

    public static void test()
	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
	Wallet wallet = SampleWallet.generate(new String[]{ "Alice", "Bob", "Carol", "David"});
	PublicKeyMap pubKeyMap = wallet.toPublicKeyMap();
	byte[] sampleMessage1 = KeyUtils.integer2ByteArray(1);
	byte[] signedMessage1 = wallet.signMessage(sampleMessage1,"Alice");
	System.out.println();
	PublicKey pubKeyA =	pubKeyMap.getPublicKey("Alice");
	PublicKey pubKeyB =	pubKeyMap.getPublicKey("Bob"); 		
	System.out.println("Test Alice 10");
	(new Input(pubKeyA,10,signedMessage1)).print(pubKeyMap);
	System.out.println();
	System.out.println("Test Bob 20");
	(new Input(pubKeyB,20,signedMessage1)).print(pubKeyMap);	
    };


    /** 
     * main function running test cases
     */            
    
    public static void main(String[] args)
	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
	Input.test();
    }        

    
}
    

    
