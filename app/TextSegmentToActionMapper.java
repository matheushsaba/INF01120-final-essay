package app;

import app.ChangePropertyAction.ActionOnProperty;

public class TextSegmentToActionMapper {
    // Constants
    // Uppercase notes
    private static final int ASCII_CODE_A = 65;
    private static final int ASCII_CODE_G = 71;

    // Non note vowels
    private static final int ASCII_CODE_I = 73;
    private static final int ASCII_CODE_O = 79;
    private static final int ASCII_CODE_U = 85;
    private static final int ASCII_CODE_i = 105;
    private static final int ASCII_CODE_o = 111;
    private static final int ASCII_CODE_u = 117;
    
    // First and last letter
    private static final int ASCII_CODE_a = 97;
    private static final int ASCII_CODE_Z = 90;
    private static final int ASCII_CODE_z = 122;

    // Numbers
    private static final int ASCII_CODE_0 = 48;
    private static final int ASCII_CODE_9 = 57;

    // Symbols
    private static final int ASCII_CODE_EXCLAMATION = 33;
    private static final int ASCII_NEW_LINE = 10;
    private static final int ASCII_SPACE = 32;
    private static final int ASCII_SEMICOLON = 59;
    private static final int ASCII_COMMA = 44;
    private static final int ASCII_QUESTION_MARK = 63;
    private static final int ASCII_DOT = 46;


    // Methods
    public static SegmentAction checkSegmentAction(String rawText, int characterIndex){
        String textSegment = getSegmentAtIndex(rawText, characterIndex);
        int characterAsciiCode = getCharacterAsciiCode(textSegment);

        if (isCharacterletter(characterAsciiCode)){
            return getLetterAction(characterAsciiCode, rawText, characterIndex);
        }
        else if (isCharacterNumber(characterAsciiCode)){
            return new ChangePropertyAction(ActionOnProperty.ADD_VALUE_TO_INSTRUMENT, Integer.parseInt(textSegment));
        }

        return getSymbolAction(characterAsciiCode, rawText, characterIndex);
    }
    private static String getSegmentAtIndex(String rawText, int characterIndex){
        return rawText.substring(characterIndex, characterIndex + 1);
    }	
    private static int getCharacterAsciiCode(String characterText){
        Character character = characterText.charAt(0);
        int characterAsciiCode = (int)character;

        return characterAsciiCode;
    }
    
    private static boolean isCharacterletter(int characterAsciiCode) {
        return (characterAsciiCode >= ASCII_CODE_A && characterAsciiCode <= ASCII_CODE_Z) || (characterAsciiCode >= ASCII_CODE_a && characterAsciiCode <= ASCII_CODE_z);
    }
    private static SegmentAction getLetterAction(int characterAsciiCode, String rawText, int characterIndex){
        if (isCharacterUpperCaseNote(characterAsciiCode)){
            return new PlayNoteAction(characterAsciiCode);
        }
        else if (isCharacterNonNoteVowel(characterAsciiCode)){
            return new ChangePropertyAction(ActionOnProperty.SET_VALUE_TO_INSTRUMENT, 7); // 7 = Harpsicord
        }
        
        int previousCharacterAsciiCode = getPreviousCharacterAsciiCode(rawText, characterIndex);

        if (isCharacterUpperCaseNote(previousCharacterAsciiCode)){
            return new PlayNoteAction(previousCharacterAsciiCode); // Retorna a nota anterior
        }
        return new PlayNoteAction(ASCII_SPACE); // Retorna silêncio
    }
    private static boolean isCharacterUpperCaseNote(int characterAsciiCode) {
        return characterAsciiCode >= ASCII_CODE_A && characterAsciiCode <= ASCII_CODE_G;
    }
    private static boolean isCharacterNonNoteVowel(int characterAsciiCode) {
        boolean isCharacterUpperCaseNonNoteVowel = characterAsciiCode == ASCII_CODE_I || characterAsciiCode == ASCII_CODE_O || characterAsciiCode == ASCII_CODE_U;
        boolean isCharacterLowerCaseNonNoteVowel = characterAsciiCode == ASCII_CODE_i || characterAsciiCode == ASCII_CODE_o || characterAsciiCode == ASCII_CODE_u;

        return isCharacterUpperCaseNonNoteVowel || isCharacterLowerCaseNonNoteVowel;
    }
    private static int getPreviousCharacterAsciiCode(String rawText, int characterIndex){
        String previousCharacterText = getSegmentAtIndex(rawText, characterIndex - 1);
        int previousCharacterUpperCaseAsciiCode = getCharacterAsciiCode(previousCharacterText);

        return previousCharacterUpperCaseAsciiCode;
    }

    private static boolean isCharacterNumber(int characterAsciiCode) {
        return characterAsciiCode >= ASCII_CODE_0 && characterAsciiCode <= ASCII_CODE_9;
    }

    private static SegmentAction getSymbolAction(int characterAsciiCode, String rawText, int characterIndex){
        switch (characterAsciiCode){
            case ASCII_CODE_EXCLAMATION:
                return new ChangePropertyAction(ActionOnProperty.SET_VALUE_TO_INSTRUMENT, 114); // 114 = Agogo
            case ASCII_NEW_LINE:
                return new ChangePropertyAction(ActionOnProperty.SET_VALUE_TO_INSTRUMENT, 15); // 15 = Tubular Bells
            case ASCII_SEMICOLON:
                return new ChangePropertyAction(ActionOnProperty.SET_VALUE_TO_INSTRUMENT, 76); // 76 = Pan Flute
            case ASCII_COMMA:
                return new ChangePropertyAction(ActionOnProperty.SET_VALUE_TO_INSTRUMENT, 20); // 20 = Church Organ
            case ASCII_QUESTION_MARK:
                return new ChangePropertyAction(ActionOnProperty.RAISE_OCTAVE, 1);
            case ASCII_DOT:
                return new ChangePropertyAction(ActionOnProperty.RAISE_OCTAVE, 1);
            default:
                int previousCharacterAsciiCode = getPreviousCharacterAsciiCode(rawText, characterIndex);

                if (isCharacterUpperCaseNote(previousCharacterAsciiCode)){
                    return new PlayNoteAction(previousCharacterAsciiCode); // Retorna a nota anterior
                }
                return new PlayNoteAction(ASCII_SPACE); // Retorna silêncio
        }
    }
}
