package app;

import java.util.ArrayList;
import java.util.HashSet;

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

    // Properties
    private HashSet<String> acceptedNotes;
    private ArrayList<Sound> sequentialSounds;

    // Constructor


    // Methods
    public void addSound(Sound sound) {
        sequentialSounds.add(sound);
    }

    public void removeSound(Sound sound) {
        sequentialSounds.remove(sound);
    }

    public void play() {
        for (Sound sound : sequentialSounds) {
            sound.play();
        }
    }

    public void stop() {
        for (Sound sound : sequentialSounds) {
            sound.stop();
        }
    }

    public void pause() {
        for (Sound sound : sequentialSounds) {
            sound.pause();
        }
    }

    public void resume() {
        for (Sound sound : sequentialSounds) {
            sound.resume();
        }
    }
    /* 
    public void doubleVolume() {
        for (Sound sound : sequentialSounds) {
            sound.doubleVolume();
        }
    }

    public void returnVolumeToDefault() {
        for (Sound sound : sequentialSounds) {
            sound.returnVolumeToDefault();
        }
    }

    public void changeOctave(int octave) {
        for (Sound sound : sequentialSounds) {
            sound.changeOctave(octave);
        }
    }

    public void changeInstrument(Instrument instrument) {
        for (Sound sound : sequentialSounds) {
            sound.changeInstrument(instrument);
        }
    }

    public void changeBPM(int bpm) {
        for (Sound sound : sequentialSounds) {
            sound.changeBPM(bpm);
        }
    }*/
    
    
}