package app;

import org.jfugue.player.Player;
import org.jfugue.theory.Note;

public class TextToSoundConverter {
    // Attributes
    private String rawText;

    // Constructors
    public TextToSoundConverter() {
    }

    // Methods
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