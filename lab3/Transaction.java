package lab3;

import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.InvalidKeyException;

/**   Transaction
 *    consisting of a list of inputs and
 *    a list of outputs
 */    

public class Transaction {

    /** The list of inputs  */
    private InputList inputs;

    /** The list of outputs  */
    private OutputList outputs;    


    /**
     * Creates a new transaction 
     */ 
    public Transaction(InputList inputs, OutputList outputs){
	this.inputs = inputs;
	this.outputs= outputs;
    }

    /**
     * return the list of inputs
     */ 
    
    public InputList toInputs(){
	return inputs;
    }


    /**
     * return the list of outputs
     */ 
    
    public OutputList toOutputs(){
	return outputs;
    }    


    /**
     * check the sum of inputs is >= the sum of outputs
     */
    
    public boolean checkTransactionAmountsValid (){
	return (toInputs().toSum() >= toOutputs().toSum());
    }


    /* Task 3
       check all signatures are valid 

       In order for the code to compile it has been defined as True
       but that should be adapted
    */
    
    public boolean checkSignaturesValid(){
	// you need to replace then next line by the correct statement	
	return true;
	}

	   

    /**
     * print the transaction
     */ 
    

    public void print(PublicKeyMap pubKeyMap) {
	System.out.println("Inputs:");
        toInputs().print("User: "," spends ",pubKeyMap);
	System.out.println("Outputs:");
        toOutputs().print("User: "," receives ",pubKeyMap);	
    }


    /** 
     * Generic Test cases, providing a headline
     *    printing out the transaction
     *    and printing out whether it is valid
     */            

    
    public void testCase(String header,PublicKeyMap pubKeyMap){
	System.out.println(header);
	print(pubKeyMap);
	System.out.println("Is valid regarding sums = " + checkTransactionAmountsValid());
	System.out.println("");
    }
	

    /** 
     * Test cases
     */            

    public static void test()
	throws NoSuchAlgorithmException, SignatureException,InvalidKeyException {
	Wallet wallet = SampleWallet.generate(new String[]{ "Alice", "Bob", "Carol", "David"});
	PublicKeyMap pubKeyMap = wallet.toPublicKeyMap();		
	byte[] sampleMessage1 = KeyUtils.integer2ByteArray(1);
	byte[] signedMessage1 = wallet.signMessage(sampleMessage1,"Alice");
	PublicKey pubKeyA =	pubKeyMap.getPublicKey("Alice");
	PublicKey pubKeyB =	pubKeyMap.getPublicKey("Bob");
	PublicKey pubKeyC =	pubKeyMap.getPublicKey("Carol"); 					
	Transaction tx = new Transaction(new InputList(),
					 new OutputList());	
	tx.testCase("Transaction null to null",pubKeyMap);
	tx = new Transaction(new InputList(pubKeyA,10,signedMessage1),
			     new OutputList(pubKeyB,5));
	tx.testCase("Transaction Alice 10  to Bob 5",pubKeyMap);


	tx = new Transaction(new InputList(pubKeyA,5,signedMessage1),
			     new OutputList(pubKeyB,10));
	tx.testCase("Transaction Alice 5  to Bob 10",pubKeyMap);
	
	tx = new Transaction(new InputList(pubKeyA,10,signedMessage1,pubKeyB,5,signedMessage1),
			     new OutputList(pubKeyA,7,pubKeyC,8));
        tx.testCase("Transaction Alice 10  Bob 5 to Alice 7 Carol 8",pubKeyMap);

	tx = new Transaction(new InputList(pubKeyA,10,signedMessage1,pubKeyB,5,signedMessage1),
			     new OutputList(pubKeyA,10,pubKeyC,8));
        tx.testCase("Transaction Alice 10  Bob 5 to Alice 10 Carol 8",pubKeyMap);

    }


    /** 
     * main function running test cases
     */            
    
    public static void main(String[] args)
	throws NoSuchAlgorithmException, SignatureException,InvalidKeyException {
	Transaction.test();
    }    
    
}

