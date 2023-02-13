package app;

import java.util.ArrayList;
import java.util.List;

import org.jfugue.player.Player;
import org.jfugue.theory.Note;

public class TextToSoundParser {
    // Attributes
    private String rawText;
    private final int ASCII_CODE_A = 65;
    private final int ASCII_CODE_B = 66;
    private final int ASCII_CODE_G = 71;
    private final int ASCII_CODE_I = 73;
    private final int ASCII_CODE_O = 79;
    private final int ASCII_CODE_U = 85;
    private final int ASCII_CODE_R = 82;
    private final int ASCII_NEW_LINE = 10;
    private final int ASCII_SPACE = 32;
    private final int ASCII_PLUS = 43;
    private final int ASCII_MINUS = 45;
    private final int ASCII_SEMICOLON = 59;
    private final int ASCII_QUESTION_MARK = 63;
    private final String testText = "fdahf-dhd&$ #¨#5e3745 745J?RFJGbMGF  g42cwgR+43dsa+dsBPM+gsfdR-dgsggsdg;dsvsd";


    // Constructors
    public TextToSoundParser() {
    }

    // Methods
    private int convertCharacterStringToAsciiCode(String characterText) {
        if (characterText.length() > 1)
            throw new IllegalArgumentException("The characterText parameter must have only one character");
       
        char character = characterText.charAt(0);
        
        return (int) character;
    }
    private boolean isCharacterNote(int characterAsciiCode) {
        boolean isCharacterAsciiValidNote = characterAsciiCode >= ASCII_CODE_A && characterAsciiCode <= ASCII_CODE_G;

        return (isCharacterAsciiValidNote) ? true : false;
    }
    private boolean isCharacterSpace(int characterAsciiCode){
        return (characterAsciiCode == ASCII_SPACE) ? true : false;
    }
    private boolean isCharacterPlus(int characterAsciiCode){
        return (characterAsciiCode == ASCII_PLUS) ? true : false;
    }
    private boolean isCharacterMinus(int characterAsciiCode){
        return (characterAsciiCode == ASCII_MINUS) ? true : false;
    }
    private boolean isCharacterNonNoteVowel(int characterAsciiCode){
        return (characterAsciiCode == ASCII_CODE_I || characterAsciiCode == ASCII_CODE_O || characterAsciiCode == ASCII_CODE_U) ? true : false;
    }
    private boolean isCharacterR(int characterAsciiCode){
        return (characterAsciiCode == ASCII_CODE_R) ? true : false;
    }
    private boolean isIncreaseOctaveTextSegment(String rawText, int rCharacterIndex){
        boolean isTextSegmentRPlus = rawText.substring(rCharacterIndex, rCharacterIndex + 1) == "R+";
        
        return isTextSegmentRPlus ? true : false;
    }
    private boolean isDecreaseOctaveTextSegment(String rawText, int rCharacterIndex){
        boolean isTextSegmentRMinus = rawText.substring(rCharacterIndex, rCharacterIndex + 1) == "R-";
        
        return isTextSegmentRMinus ? true : false;
    }
    private boolean isCharacterQuestionMark(int characterAsciiCode){
        return (characterAsciiCode == ASCII_QUESTION_MARK) ? true : false;
    }
    private boolean isCharacterNewLine(int characterAsciiCode){
        return (characterAsciiCode == ASCII_NEW_LINE) ? true : false;
    }
    private boolean isIncreaseBPMTextSegment(int characterAsciiCode, String rawText, int firstCharacterIndex){
        if (isCharacterB(characterAsciiCode)){
            if (rawText.substring(firstCharacterIndex, firstCharacterIndex + 3) == "BPM+"){
                return true;
            }
        }

        return false;
    }
    private boolean isCharacterB(int characterAsciiCode){
        return (characterAsciiCode == ASCII_CODE_B) ? true :  false;
    }
    private boolean isCharacterSemicolon(int characterAsciiCode){
        return (characterAsciiCode == ASCII_SEMICOLON) ? true : false;
    }



    private void checkCharacterAction(String characterText, String rawText, int firstCharacterIndex){
        String upperCaseCharacterText = characterText.toUpperCase();
        int characterAsciiCode = convertCharacterStringToAsciiCode(upperCaseCharacterText);

        if (isCharacterNote(characterAsciiCode)){
            if (isIncreaseBPMTextSegment(characterAsciiCode, rawText, firstCharacterIndex)){
                //change bpm
            }
            //play note
        }
        else if (isCharacterSpace(characterAsciiCode)){
            //play pause
        }
        else if (isCharacterPlus(characterAsciiCode)){
            //increase volume
        }
        else if (isCharacterMinus(characterAsciiCode)){
            //return volume to default
        }
        else if (isCharacterNonNoteVowel(characterAsciiCode)){
            //verify if last character is a note and play a sound accordingly 
        }
        else if (isCharacterR(characterAsciiCode)){
            if (isIncreaseOctaveTextSegment(rawText, firstCharacterIndex)){
                //increase octave
                //skip 2 characters in string
            }
            else if (isDecreaseOctaveTextSegment(rawText, firstCharacterIndex)){
                //decrease octave
                //skip 2 characters in string
            }
            else{
                //ignore
            }
        }
        else if (isCharacterQuestionMark(characterAsciiCode)){
            //plays random note
        }
        else if (isCharacterNewLine(characterAsciiCode)){
            //change instrument
        }
        else if (isCharacterSemicolon(characterAsciiCode)){
            //randomize bpm
        }
        else{
            //does nothing
        }
        
    }

    

    public void convertTextToSound() {
        System.out.println("Converting text to sound");
    }

    public void mapText(String text) {
        System.out.println("Mapping text");
    }

    public void convertNonNoteVowelToSound() {
        System.out.println("Converting non-note vowel to sound");
    }

    public Note convertTextToNote(String text) {
        Note note = new Note(text);

        return note;
    }
}