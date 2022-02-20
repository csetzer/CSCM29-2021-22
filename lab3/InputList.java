package lab3;

import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.util.ArrayList;

/** InputList
 *  defines a list of Inputs  of a  transaction
 *
 */

public class InputList{


    /** 
      * underlying list of inputs
      */
    
    private ArrayList<Input> inputList;

    /** 
      * add an Input to the list
      */
    

    public void addEntry(Input input){
	inputList.add(input);
    }

    /** 
      * add a input given by sender amount and signature
      */
    
    public void addEntry(PublicKey sender,int amount,byte[] signature){
	inputList.add(new Input(sender,amount,signature));
    }



    /** 
      * add an entry given by sender as a string, amount, with signature created 
      *   using outputList, wallet
      */
    

    public void addEntry(String sender,int amount, OutputList outputList,Wallet wallet)
	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {	
	inputList.add(new Input(sender, amount,outputList,wallet));
    }


    /** 
      * add an entry given by sender as a publicKey, amount, with signature created 
      *   using outputList, wallet
      */
    

    public void addEntry(PublicKey sender,int amount, OutputList outputList,Wallet wallet)
	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {	
	inputList.add(new Input(sender, amount,outputList,wallet));
    }    
    
    

    /** 
      * constructor constructing the empty InputList
      */
    
    public InputList(){
	inputList =  new ArrayList<Input>();
    }

    /** 
      * constructor constructing a list containing one entry
          consisting of a sender, an amount, and a signature
      */    
    
    public InputList(PublicKey sender,int amount,byte[] signature){
	inputList = new ArrayList<Input>();
	addEntry(sender,amount,signature);
    }




    /** 
     * If we have a Wallet covering the sender
     * and an outputList
     * then we can compute the signature by signing the transaction to be signed consisting
     *    of the public key and input amount and the outputList
     *    using the private key of the sender
     */    



    public InputList(String sender,int amount, OutputList outputList,Wallet wallet)
	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
	inputList = new ArrayList<Input>();
	addEntry(sender, amount,outputList,wallet);
    }    

    /*  as before but referring to the sender by the public key rather than the string */
    
    public InputList(PublicKey sender,int amount, OutputList outputList,Wallet wallet)
	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
	inputList = new ArrayList<Input>();
	addEntry(sender, amount,outputList,wallet);
    }    
    

    

    /** 
      * constructor constructing a list containing two entries
          each consisting of a sender, an amount, and a signature
          note that it is not verifiedthat the signatures are correct
      */        

    public InputList(PublicKey sender1,int amount1,byte[] signature1,
		       PublicKey sender2,int amount2,byte[] signature2){
	inputList = new ArrayList<Input>();
	addEntry(sender1,amount1,signature1);
	addEntry(sender2,amount2,signature2);
    }

    /**  as before but for 2 users, using outputList and wallet **/
    
    public InputList(String sender1,int amount1,
		       String sender2,int amount2,
		       OutputList outputList,Wallet wallet)
	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
	inputList = new ArrayList<Input>();
	addEntry(new Input(sender1, amount1,outputList,wallet));
	addEntry(new Input(sender2, amount2,outputList,wallet));	
    }    


    /**  as before but for 2 users using outputList and wallet,
         this time senders are given by public keys  **/
    
    public InputList(PublicKey sender1,int amount1,
		       PublicKey sender2,int amount2,
		       OutputList outputList,Wallet wallet)
	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
	inputList = new ArrayList<Input>();
	addEntry(new Input(sender1, amount1,outputList,wallet));
	addEntry(new Input(sender2, amount2,outputList,wallet));	
    }    
    
	

    /** 
      * obtain the underlying list
      */
    
    public ArrayList<Input> toList(){
	return(inputList);
    };


    /* obtain the number of entries */
    
    public int size(){
	return(toList().size());
    }


    /* get one entry by its index */
    
    public Input get(int index){
	return (toList()).get(index);
    }

    /** 
      * compute the sum of entries in the list
      */

    public int toSum(){
	int result = 0;
	for (Input  entry : toList()){
	    result += entry.getAmount();
		};
	return result;
    };	


    /** 
      * when checking that InputList can be deducted
      *   from an ledger
      *   it is not enough to check that each single item can be deducted
      *   since for the same sender several items might occur
      *   
      *   in order to check that the InputList can be deducted
      *    we first create an ledger containing for each user the
      *    sum of amounts to be deducted
      *
      *   then we can check whether each entry in the original ledger is
      *     greater the sum of items for each user to be deducted
      */

    public Ledger toLedger(){
	Ledger result = new Ledger();
	for (Input  entry : toList()){
	    result.addBalance(entry.getSender(),entry.getAmount());
	};
	return result;
    }    



    /**   function  to print all items in the InputList
     *    in the form 
     *      word1  <sender> word2 <amount>  

     *
     *   we use the pubKeyMap in order to look up the names for each public key
     */
    
    public void print(String word1, String word2,PublicKeyMap pubKeyMap) {
	for (Input entry : inputList) {
	    entry.print(word1,word2,pubKeyMap);
	}
    }

    /**  Task 2
         Check the signatures of each element of the InputList is correct
         w.r.t. the OutputList given
         You can make use of the checkSignature method of individual Input
         (elements of Input).

         In order for the code to compile it has been defined as True
         but that should be adapted.
    **/

    public boolean checkSignature(OutputList outList){
	// you need to replace then next line by the correct statement	
	return true;
    }



    /** 
     * Default way of printing out the InputList
     */

    public void print(PublicKeyMap pubKeyMap) {
	print("Sender: "," value:  ",pubKeyMap);
    }

    /** 
     * Generic Test cases, providing a headline
     *    printing out the InputList
     *    and printing out the sum of amounts in the InputList
     */            
    

    public void testCase(String header,PublicKeyMap pubKeyMap){
	System.out.println(header);
	print(pubKeyMap);
	System.out.println("Sum of Amounts = " + toSum());	
	System.out.println();	
    };

    /** 
     * Test cases
     */            
    
    public static void test()
		throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
	Wallet wallet = SampleWallet.generate(new String[]{ "Alice", "Bob", "Carol", "David"});
	PublicKeyMap pubKeyMap = wallet.toPublicKeyMap();	
	byte[] sampleMessage1 = KeyUtils.integer2ByteArray(1);
	byte[] signedMessage1 = wallet.signMessage(sampleMessage1,"Alice");
	PublicKey pubKeyA =	pubKeyMap.getPublicKey("Alice");
	PublicKey pubKeyB =	pubKeyMap.getPublicKey("Bob"); 			
	(new InputList(pubKeyA,10,signedMessage1)).testCase("Test Alice 10",pubKeyMap);

	(new InputList(pubKeyB,20,signedMessage1)).testCase("Test Bob 20",pubKeyMap);
	
	(new InputList(pubKeyA,10,signedMessage1,
			 pubKeyA,10,signedMessage1)).testCase("Alice twice 10",pubKeyMap);

	InputList l = new InputList(pubKeyA,10,signedMessage1,
					pubKeyB,20,signedMessage1);
	l.testCase("Test Alice 10 and Bob  20",pubKeyMap);
	
	System.out.println("Same List but with words Sender and sends");	
	l.print("Sender "," sends ",pubKeyMap);		
	
    }
    

    /** 
     * main function running test cases
     */            
    
    public static void main(String[] args)
    		throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
	InputList.test();
    }    

};    
