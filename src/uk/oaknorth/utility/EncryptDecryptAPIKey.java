package uk.oaknorth.utility;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

public class EncryptDecryptAPIKey {
	private final String fileName;
	private final String propKeyName;
	private final String isPropKeyEncrypted;
	final String decryptedAPIKey;
	
	public String getDecryptedAPIKey() {
		return decryptedAPIKey;
	}
	public EncryptDecryptAPIKey(String fileName, String propKeyName,String isPropKeyEncrypted) throws Exception{
	
		this.fileName = fileName;
        this.propKeyName = propKeyName;
        this.isPropKeyEncrypted = isPropKeyEncrypted;
        try {
            encryptPropertyValue();
        } catch (IOException e) {
            throw new Exception("Problem encountered during encryption process",e);
        }
        decryptedAPIKey = decryptPropertyValue();
	}
	 private void encryptPropertyValue() throws IOException   {
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
	        Properties props = new EncryptableProperties(encryptor);  
	        //props.load(new FileInputStream(fileName));
	        props.load(EncryptDecryptAPIKey.class.getClassLoader().getResourceAsStream(fileName));
	        //Retrieve boolean properties value to see if password is already encrypted or not
	        String isEncrypted = props.getProperty(isPropKeyEncrypted);
	        OutputStream output = null;
	       
            System.out.println(isEncrypted+propKeyName+isPropKeyEncrypted);
          
	        //Check if password is encrypted?
	        if("false".equals(isEncrypted)){
	            String tmpKey = props.getProperty(propKeyName);
	            output = new FileOutputStream(fileName);
	            encryptor.setPassword("jasypt");
	            String encryptedPassword = encryptor.encrypt(tmpKey);
	            System.out.println("Encryption done and encrypted password is : " + encryptedPassword ); 
	            props.setProperty(propKeyName, encryptedPassword);
	            // Set the boolean flag to true to indicate future encryption operation that password is already encrypted
	            props.setProperty(isPropKeyEncrypted,"true");
	            props.store(output, null);
	        }else{
	             System.out.println("User password is already encrypted.\n ");
	        }
	    }
	 
	    private String decryptPropertyValue() throws IOException{
	         System.out.println("Starting decryption");
	         InputStream input = null;
	         //input = new FileInputStream(EncryptDecryptAPIKey.class.getClassLoader().getResourceAsStream(fileName));
	         Properties prop = new Properties();
	         prop.load(EncryptDecryptAPIKey.class.getClassLoader().getResourceAsStream(fileName));
	        String encryptedPropertyValue = prop.getProperty(propKeyName);
	        //System.out.println(encryptedPropertyValue); 
	 
	        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
	        encryptor.setPassword("jasypt");
	        String decryptedPropertyValue = encryptor.decrypt(encryptedPropertyValue);
	        return decryptedPropertyValue;
	    }
	    
	    public static void main(String[] s) throws Exception{
	    	EncryptDecryptAPIKey e = new EncryptDecryptAPIKey("apikey.properties",
	    			"apikey", "keyencrypted");
	    	
	    }
	
}
