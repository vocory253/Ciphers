
import java.util.*;

// Class comment: This class represents the Substitution encryption scheme and extends the
// Cipher class.
public class Substitution extends Cipher {
    // Field(s):
    private String shifter;
    private Map<Character, Character> tracker;

    // Behavior: Creates a Substitution cipher and since there is no given input, the shifter is set as empty.
    public Substitution(){
        shifter = "";
        tracker = new HashMap<>();
    }

    // Behavior: Creates a Substitution cipher and if there are no errors with the inputed shifter, it takes the
    // shifter and stores it.
    // Exception: If the given shifter has duplicates, does not match the number of characters within our encodable 
    // range or has a character that falls outside the encodable range, an IllegalArgumentException is thrown. 
    // Parameters: String shifter represents the shifter that aligns with your encodable range. How the encodable range
    // vertically aligns to the shifter will become the pairing of what that character corresponds too when 
    // encrypting/decrypting.
    public Substitution(String shifter){
        shiftSetting(shifter);
        tracker = new HashMap<>();
    }

    // Behavior: Takes a shifter and if there are errors with the shifter, it will be stored and become the new shifter.
    // Exception: If the given shifter has duplicates, does not match the number of characters within our encodable 
    // range or has a character that falls outside the encodable range, an IllegalArgumentException is thrown. 
    // Parameters: String shifter represents the shifter that aligns with your encodable range. How the encodable range
    // vertically aligns to the shifter will become the pairing of what that character corresponds too when 
    // encrypting/decrypting.
    public void setShifter(String shifter){
        shiftSetting(shifter);
    }

    // Behavior: Takes the given input and applies the Substitution cipher by shifting by the 
    // amount previously specified. 
    // Exception: If a shifter has not been given or is empty an IllegalStateException() is thrown.
    // Return: Gives back the final encrypted scheme applying the Substitution cipher.
    // Parameters: String input, represents the input the user wants to encryt.
    public String encrypt(String input) {
        if(shifter == null || shifter.isEmpty()){
            throw new IllegalStateException();
        }
        mapPopulator(true); // encrypt == true
        return concatenator(input);
    }

    // Behavior: Takes the given input and "un-Substitution" encryption schemes it. In other words,
    // it reverses the Substitution cipher.
    // Exception: If a shifter has not been given or is empty an IllegalStateException() is thrown.
    // Return: Gives back the final encrypted scheme applying the Substitution cipher.
    // Parameters: String input, represents the input the user wants to encryt.
    public String decrypt(String input){
        if(shifter == null || shifter.isEmpty()){
            throw new IllegalStateException();
        }
        mapPopulator(false);
        return concatenator(input);
    }

    // Behavior: Depending on whether you are trying to encrypt or decrypt, it will populate the 
    // tracker with the desired encryption scheme matching.
    // Parameters: boolean encryptOrDecrypt represents whether or not you want to encrypt(true) or
    // decrypt (false).
    public void mapPopulator(boolean encryptOrDecrypt){
        tracker.clear();
        for(int i = 0; i < TOTAL_CHARS; i++){
            if(encryptOrDecrypt){
                tracker.put((char) (MIN_CHAR + i), shifter.charAt(i));
            } else {
                tracker.put(shifter.charAt(i), (char) (MIN_CHAR + i));
            }
        }
    }

    // Behavior: Takes in the input the user wants to encrypt or decrypt and actually goes about
    // going through this process.
    // Return: Gives back the final encryption/decryption.
    // Parameters: String input, represents the input the user wants to encryt or decrypt. 
    public String concatenator(String input){
        String result = "";
        for(int i = 0; i < input.length(); i++){
            Character curr = input.charAt(i);
            result += tracker.get(curr);
        }
        return result;
    }

    // Behavior: Changes the current shifter if it is a valid input.
    // Exception: If the shifter does not equal the length of the encodable range or contains a
    // duplicate, an IllegalArgumentException() is thrown.
    // Parameters: String shifter represents the shifter that aligns with your encodable range.
    // How the encodable range vertically aligns to the shifter will become the pairing of what
    // that character corresponds too when encrypting/decrypting.
    private void shiftSetting(String shifter){
        if(shifter.length() != TOTAL_CHARS || validator(shifter)){
            throw new IllegalArgumentException();
        }
        this.shifter = shifter;
    }

    // Behavior: Checks whether or not the provided shifter is valid. It if invalid if the shifter
    // does not equal the length of the encodable range or contains a duplicate.
    // Return: Gives back true if the shifter was valid and false if it was not.
    // Parameters: String shifter represents the shifter that aligns with your encodable range.
    // How the encodable range vertically aligns to the shifter will become the pairing of what
    // that character corresponds too when encrypting/decrypting. 
    public boolean validator(String shifter){
        Set<Character> result = new HashSet<>();
        for(int i = 0; i < shifter.length(); i++){
            Character curr = shifter.charAt(i);
            if(curr < MIN_CHAR || curr > MAX_CHAR || !result.add(curr)){
                return true;
            }
        }
        return false;
    }

    // Behavior: Takes all of the data of a list and puts it into a single form / "word".
    // Return: Gives back this single form / "word".
    // Parameters: List<Character> storage, represents the list that you want to put into a
    // single word.
    public String stringConcatenation(List<Character> storage){
        String concatenated = "";
        // String concatentation
        for(int i = 0; i < storage.size(); i++){
            concatenated += storage.get(i);
        }
        return concatenated;
    }

    // Behavior: Populates a list with the encodable range.
    // Return: Gives back this list.
    public List<Character> initialPopulation(){
        List<Character> result = new ArrayList<>();
        for(int i = MIN_CHAR; i < MAX_CHAR + 1; i++){
            result.add((char) (i));
        }
        return result;
    }
}
