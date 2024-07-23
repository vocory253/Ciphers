
import java.util.*;

// Class comment: This class represents the CaesarKey encrpytion scheme and extends the
// Substitution class.
public class CaesarKey extends Substitution {
    private String key;

    // Behavior: Takes in a key and checks if it is valid. If so, the key will be stored.
    // Exceptions: If a key is not given, or it contains a duplicate or contains characters that
    // lie outside the encodable range then an IllegalArgumentException() will be thrown.
    // Parameters: String key represents the key you want to apply to the encrpytion/decryption.
    public CaesarKey(String key){
        super();
        if(key.isEmpty() || validator(key)){
            throw new IllegalArgumentException();
        }
        this.key = key;
    }

    // Behavior: Takes in an input and applies the CaesarKey encryption scheme on it.
    // Return: Gives back the CaesarKey encrypted String.
    // Parameters: String input represents the String you want to encrypt.
    public String encrypt(String input){
        arrayListSetter();
        return super.encrypt(input);
    }

    // Behavior: Takes in an input and reverses the CaesarKey encyption scheme on it.
    // Return: Gives back the CaesarKey decrypted String.
    // Parameters: String input represents the String you want to decrypt.
    public String decrypt(String input){
        arrayListSetter();
        return super.decrypt(input);
    }

    // Behavior: Creates what the shifter will look like after having already applied the key.
    public void arrayListSetter(){
        List<Character> storage = initialPopulation();
        for(int i = 0; i < storage.size(); i++){
            Character curr = storage.get(i);
            if(key.indexOf(curr) >= 0){
                storage.remove(i);
                i--;
            }
        }
        for(int j = 0; j < key.length(); j++){
            storage.add(j, key.charAt(j));
        }
        setShifter(stringConcatenation(storage));
    }    
}
