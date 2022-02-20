package lab3;

import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.InvalidKeyException;


/** 
 *   Ledger defines an ledger in the ledger model of bitcoins
 *     it extends UserAmount
 */

public class Ledger extends UserAmount {



    /* 
     *  Task 4: Fill in the method checkUserAmountDeductable 
     *                             checkEntryListDeductable
     *                             subtractEntryList 
     *                             addEntryList 
     *                             processTransaction 
     *  in the same way as you did for lab 3
     *
     *  NOTE  processTransaction appears AFTER Task 5
     *
     *  This is essentially the same as Tasks 1 - 3 + Task 5 of lab 2
     */

    public boolean checkUserAmountDeductable(UserAmount userAmountCheck){
	return true;
    };


    /** 
     *  next part of Task 4
     *  Check that a list of publicKey amounts can be deducted from the 
     *     current ledger
     *
     *   done by first converting the list of publicKey amounts into an ledger
     *     and then checking that the resulting ledger can be deducted.
     *   
     */    


    public boolean checkInputListDeductable(InputList inputList){
	// you need to replace then next line by the correct statement	
	return true;
    };


    /** 
     *  next part of Task 4
     * Subtract a list of Input from the ledger
     *
     *   requires that the list to be deducted is deductable.
     *   
     */    
    

    public void subtractInputList(InputList inputList){
	// fill in Body	
	}
    


    
    /** 
     *  next part of Task 4
     * Adds a list of output of a transaction to the current ledger
     *
     */    

    public void addOutputList(OutputList outputList){
	// fill in Body		
	}
    


   /** 
     *
     *  Task 5 Check a transaction is valid.
     *
     *  this means that 
     *    the sum of outputs is less than or equal the sum of inputs
     *    all signatures are valid
     *    and the inputs can be deducted from the ledger.

     *    This method has been set to true so that the code compiles - that should
     *    be changed
     */    

    public boolean checkTransactionValid(Transaction tx){
	// you need to replace then next line by the correct statement	
	return true;	
    };


    /** 
     * Task 4, last part. Fill in the method processTransaction
     *
     * Process a transaction
     *    by first deducting all the inputs
     *    and then adding all the outputs.
     *
     */    
    
    public void processTransaction(Transaction tx){
	// fill in Body			
    };

    
    /** 
     * Prints the current state of the ledger. 
     */

    public void print(PublicKeyMap pubKeyMap) {
	for (PublicKey publicKey : publicKeyList ) {
	    Integer value = getBalance(publicKey);
	    System.out.println("The balance for " +
			       pubKeyMap.getUser(publicKey) + " is " + value); 
	}

    }



    /** 
     * Testcase
     */

    public static void test()
	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

	Wallet exampleWallet = SampleWallet.generate(new String[]{ "Alice"});
	byte[] exampleMessage = KeyUtils.integer2ByteArray(1);
	byte[] exampleSignature = exampleWallet.signMessage(exampleMessage,"Alice");


	/***   Task 6
               add  to the test case the test as described in the lab sheet
                
               you can use the above exampleSignature, when a sample signature is needed
	       which cannot be computed from the data.

	**/

               
	
    }

    /** 
     * main function running test cases
     */            

    public static void main(String[] args)
	throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
	Ledger.test();
    }
}
