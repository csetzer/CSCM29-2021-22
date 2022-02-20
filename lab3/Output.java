package lab3;

import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;


/** Output
     *  specifies a recipient and the amount to be send
     *  will be used in a transaction as one arrow going out
     *    of one transaction
     */


public class Output{

    /** The amount to be transfered  */
    private int amount;

    /** The recipient */
    private PublicKey recipient;

    /** 
     * Create new Output from a public key for the recipient and an amount
     */

    public Output(PublicKey recipient,int amount){
	this.amount  = amount;
	this.recipient = recipient;
    }


    /** 
     * Get the recipient 
     */

    public PublicKey getRecipient(){
	return recipient;
    };

    /* look up the name of the recipient in the pubKeyMap */

    public String getRecipientName(PublicKeyMap pubKeyMap){
	return pubKeyMap.getUser(recipient);
    };
    
    

    /** 
     * Get the  amount 
     */    

    public int getAmount(){
	return amount;
    }


    /** 
     * Print the entry in the form word1  <recipient> word2 <amount>
     *   we use the pubKeyMap in order to look up the names for each public key
     */
    
    public void print(String word1, String word2,PublicKeyMap pubKeyMap)  {
	System.out.println(word1 + getRecipientName(pubKeyMap) + word2 + getAmount());
    }

    /** 
     * Default way of printing out the Output
     */        

    public void print(PublicKeyMap pubKeyMap) {
	print("Recipient: "," value:  ",pubKeyMap);
    }

    /** 
     * Test cases
     */            

    public static void test() throws NoSuchAlgorithmException {
	Wallet wallet = SampleWallet.generate(new String[]{ "Alice", "Bob", "Carol", "David"});
	PublicKeyMap pubKeyMap = wallet.toPublicKeyMap();
	PublicKey pubKeyA =	pubKeyMap.getPublicKey("Alice");
	PublicKey pubKeyB =	pubKeyMap.getPublicKey("Bob"); 			
	System.out.println();
	System.out.println("Test Alice 10");
	(new Output(pubKeyA,10)).print(pubKeyMap);
	System.out.println();
	System.out.println("Test Bob 20");
	(new Output(pubKeyB,20)).print(pubKeyMap);	
    };


    /** 
     * main function running test cases
     */            
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
	Output.test();
    }        

    
}
    

    
