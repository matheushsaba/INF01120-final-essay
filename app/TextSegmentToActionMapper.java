package app;

public class TextSegmentToActionMapper {
    // Constants
    private final int ASCII_CODE_A = 65;
    private final int ASCII_CODE_B = 66;
    private final int ASCII_CODE_G = 71;
    private final int ASCII_CODE_I = 73;
    private final int ASCII_CODE_O = 79;
    private final int ASCII_CODE_U = 85;
    private final int ASCII_CODE_R = 82;
    private final int ASCII_CODE_Z = 90;
    private final int ASCII_NEW_LINE = 10;
    private final int ASCII_SPACE = 32;
    private final int ASCII_PLUS = 43;
    private final int ASCII_MINUS = 45;
    private final int ASCII_SEMICOLON = 59;
    private final int ASCII_QUESTION_MARK = 63;

    // Enumerations
    public enum SegmentAction {
        PLAY_NOTE, 
        PAUSE, 
        DOUBLE_VOLUME, 
        DEFAULT_VOLUME, 
        REPEAT_NOTE, 
        RING_TELEPHONE, 
        RAISE_OCTAVE, 
        LOWER_OCTAVE,
        PLAY_RANDOM_NOTE,
        CHANGE_INSTRUMENT,
        RAISE_BPM,
        RANDOMIZE_BPM,
        CONTINUE
    }

    // Methods
    private SegmentAction checkCharacterAction(String rawText, int characterIndex){
        String characterText = getCharacterAtIndex(rawText, characterIndex);
        int upperCaseCharacterAsciiCode = getCharacterUpperCaseAsciiCode(characterText);

        if (isCharacterLetter(upperCaseCharacterAsciiCode)){
            return getLetterAction(rawText, characterIndex);
        }

        return getSymbolAction(upperCaseCharacterAsciiCode);
    }
    private String getCharacterAtIndex(String rawText, int characterIndex){
        return rawText.substring(characterIndex, characterIndex + 1);
    }	
    private int getCharacterAsciiCode(String characterText){
        Character character = characterText.charAt(0);
        int characterAsciiCode = (int)character;

        return characterAsciiCode;
    }
    private int getCharacterUpperCaseAsciiCode(String characterText){
        String upperCaseCharacterText = characterText.toUpperCase();
        Character upperCaseCharacter = upperCaseCharacterText.charAt(0);
        int upperCaseCharacterAsciiCode = (int)upperCaseCharacter;

        return upperCaseCharacterAsciiCode;
    }
    
    private boolean isCharacterLetter(int upperCaseCharacterAsciiCode) {
        boolean isCharacterAsciiValidLetter = upperCaseCharacterAsciiCode >= ASCII_CODE_A && upperCaseCharacterAsciiCode <= ASCII_CODE_Z;

        return (isCharacterAsciiValidLetter) ? true : false;
    }
    private SegmentAction getLetterAction(String rawText, int characterIndex){
        String characterText = getCharacterAtIndex(rawText, characterIndex);
        int characterAsciiCode = getCharacterAsciiCode(characterText);
        int upperCaseCharacterAsciiCode = getCharacterUpperCaseAsciiCode(characterText);
        
        if (isCharacterNote(upperCaseCharacterAsciiCode)){
            return isRaiseBPMTextSegment(upperCaseCharacterAsciiCode, rawText, characterIndex) ? SegmentAction.RAISE_BPM : SegmentAction.PLAY_NOTE;
        }
        else if (isCharacterNonNoteVowel(upperCaseCharacterAsciiCode)){
            return isPreviousCharacterNote(rawText, characterIndex) ? SegmentAction.PLAY_NOTE : SegmentAction.RING_TELEPHONE;
        }
        else if (characterAsciiCode == ASCII_CODE_R){
            return getOctaveModifierIfValid(rawText, characterIndex);
        }

        return SegmentAction.CONTINUE;
    }
    private boolean isCharacterNote(int characterAsciiCode) {
        boolean isCharacterAsciiValidNote = characterAsciiCode >= ASCII_CODE_A && characterAsciiCode <= ASCII_CODE_G;

        return (isCharacterAsciiValidNote) ? true : false;
    }
    private boolean isRaiseBPMTextSegment(int characterAsciiCode, String rawText, int characterIndex){
        if (characterAsciiCode == ASCII_CODE_B){
            if (rawText.substring(characterIndex, characterIndex + 3) == "BPM+"){
                return true;
            }
        }

        return false;
    }
    private boolean isCharacterNonNoteVowel(int characterAsciiCode){
        return (characterAsciiCode == ASCII_CODE_I || characterAsciiCode == ASCII_CODE_O || characterAsciiCode == ASCII_CODE_U) ? true : false;
    }
    private boolean isPreviousCharacterNote(String rawText, int characterIndex){
        String previousCharacterText = getCharacterAtIndex(rawText, characterIndex - 1);
        int previousCharacterUpperCaseAsciiCode = getCharacterUpperCaseAsciiCode(previousCharacterText);

        return isCharacterNote(previousCharacterUpperCaseAsciiCode) ? true : false;
    }
    private SegmentAction getOctaveModifierIfValid(String rawText, int firstCharacterIndex){
        Character nextCharacter = rawText.charAt(firstCharacterIndex + 1);
        int nextCharacterAsciiCode = (int)nextCharacter;
        
        switch (nextCharacterAsciiCode){
            case ASCII_PLUS:
                return SegmentAction.RAISE_OCTAVE;
            case ASCII_MINUS:
                return SegmentAction.LOWER_OCTAVE;
            default:
                return SegmentAction.CONTINUE;
        }
    }
    
    private SegmentAction getSymbolAction(int upperCaseCharacterAsciiCode){
        switch (upperCaseCharacterAsciiCode){
            case ASCII_SPACE:
                return SegmentAction.PAUSE;
            case ASCII_PLUS:
                return SegmentAction.DOUBLE_VOLUME;
            case ASCII_MINUS:
                return SegmentAction.DEFAULT_VOLUME;
            case ASCII_QUESTION_MARK:
                return SegmentAction.PLAY_RANDOM_NOTE;
            case ASCII_NEW_LINE:
                return SegmentAction.CHANGE_INSTRUMENT;
            case ASCII_SEMICOLON:
                return SegmentAction.RANDOMIZE_BPM;
            default:
                return SegmentAction.CONTINUE;
        }
    }

    

    

}
