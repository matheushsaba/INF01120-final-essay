package app;

import java.util.ArrayList;
import java.util.HashSet;
import org.jfugue.pattern.Pattern;

public class Music {
    // Constants
    final int MIN_BPM = 40;
    final int MAX_BPM = 240;
    final int MIN_OCTAVE = 1;
    final int MAX_OCTAVE = 8;
    final int MIN_VOLUME = 0;
    final int MAX_VOLUME = 100;
    final int DEFAULT_VOLUME = 50;
    //final int DEFAULT_BPM = 120;
    //final int DEFAULT_OCTAVE = 4;

    private final int maximumVolumeValue = 100;
    private final int defaultVolumeValue = 50;
    private final int minimumVolumeValue = 0;

    private final int defaultBPMValue = 120;
    private final int minimumBPMValue = 0;

    private final int maximumOctaveValue = 10;
    private final int defaultOctaveValue = 5;
    private final int minimumOctaveValue = 1;

    // Properties
    private ArrayList<Pattern> sequentialPatterns;
    private PatternBuilder actualPatternBuilder;


    // Constructor
    public Music(){
        this.sequentialPatterns = new ArrayList<Pattern>();
        this.actualPatternBuilder = new PatternBuilder(defaultVolumeValue, defaultBPMValue, PatternBuilder.Instrument.GUITAR, defaultOctaveValue);
    }


    // Methods
    public void playText(String rawText){

        for (int i = 0; i < rawText.length(); i++) {
            SegmentAction segmentAction = TextSegmentToActionMapper.checkSegmentAction(rawText, i);
        }
    }

   
    
    
}