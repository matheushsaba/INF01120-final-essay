package app;

import org.jfugue.player.Player;
import org.jfugue.theory.Note;

public class TextToSoundConverter {
    // Attributes
    private String rawText;
    final int ASCII_CODE_A = 65;
    final int ASCII_CODE_G = 71;
    final int ASCII_SPACE = 32;
    final String testText = "HGASHSDHTRYK&$#¨#53745745JRFJGMGF";


    // Constructors
    public TextToSoundConverter() {
    }

    // Methods
    public int convertCharacterStringToAsciiCode(String characterText) {
        char character = characterText.charAt(0);
        
        return (int) character;
    }
    public boolean IsCharacterNoteOrSpace(String characterText) {
        String upperCaseCharacterText = characterText.toUpperCase();
        int characterAsciiCode = convertCharacterStringToAsciiCode(upperCaseCharacterText);
        boolean isCharacterAsciiValidNote = characterAsciiCode >= ASCII_CODE_A && characterAsciiCode <= ASCII_CODE_G;
        boolean isCharacterAsciiSpace = characterAsciiCode == ASCII_SPACE;

        return (isCharacterAsciiValidNote || isCharacterAsciiSpace) ? true : false;
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