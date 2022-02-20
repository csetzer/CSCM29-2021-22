package lab3;

import java.security.PublicKey;
import java.util.ArrayList;


/* Class for defining messages to be signed by private keys */

public class SigData{

    private ArrayList<Byte> sigData;

    /* the empty message */
    
    public SigData(){
	sigData = new ArrayList<Byte>();
    }

    /* adds a byte array */
    
    public void addByteArray(byte[] bytes){
	for (int i = 0; i < bytes.length;i++)
	    sigData.add(bytes[i]);
    }

    /* adds an integer to the current message */
    
    public void addInteger(int number){
	addByteArray(KeyUtils.integer2ByteArray(number));
    }
    /* adds a public key to the current message */    

    public void addPublicKey(PublicKey pubkey){
	addByteArray(pubkey.getEncoded());
    }


    /* returns the underlying byte array which will then be signed */
    
    public byte[] toArray(){
	byte[] sigD = new byte[sigData.size()];
        int i = 0;
        for (Byte sb : sigData)
	sigD[i++] = sb; 
        return sigD;
    }
};
    
	
