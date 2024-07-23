
import java.util.List;

// Class comment: This class represents the implementor of the Substitution, CaesarShift, and
// CaesarKey encryption scheme and extends the Cipher class.
public class MultiCipher extends Cipher {
    private List<Cipher> ciphers;
    
    // Behavior: Checks whether the list is valid. If so, the list will be stored.
    // Exception: If there is nothing in the inputed List, then an IllegalArgumentException()
    // will be thrown.
    // Parameters: List<Cipher> ciphers represents a list the ciphers you want to be applied
    // through encryption/decryption.
    public MultiCipher(List<Cipher> ciphers){
        if(ciphers == null){
            throw new IllegalArgumentException();
        }
        this.ciphers = ciphers;
    }

    // Behavior: Takes in an input and applies all the encryption schemes that has been
    // specified in the previously provided list.
    // Return: Gives back the fully encrypted String.
    // Parameters: String input represents the String you want to encrypt.
    public String encrypt(String input){
        String output = ciphers.get(0).encrypt(input);
        for(int i = 1; i < ciphers.size(); i++){
            output = ciphers.get(i).encrypt(output);
        }
        return output;
    }

    // Behavior: Takes in an input and reverses all of the encryption schemes that has been
    // specified in the previously provided list.
    // Return: Gives back the fully decrypted String.
    // Parameters: String input represents the String you want to decrypt.
    public String decrypt(String input){
        String output = ciphers.get(ciphers.size() - 1).decrypt(input);
        for(int i = ciphers.size() - 2 ; i >= 0; i--){
            output = ciphers.get(i).decrypt(output);
        }
        return output;
    }
}
