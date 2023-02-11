package app;

public class Sound {
    // Attributes
    private int volume;
    private int BPM;
    private Instrument instrument;
    private int octave;

    // Enumerations
    public enum Instrument{
        Guitar,
        Bass,
        Piano,
        Keyboard
    }

    // Constructors

    // Methods
    public void play() {
        System.out.println("Playing sound");
    }

    public void stop() {
        System.out.println("Stopping sound");
    }

    public void pause() {
        System.out.println("Pausing sound");
    }

    public void resume() {
        System.out.println("Resuming sound");
    }

    public void repeatNote() {
        System.out.println("Repeating note");
    }

    public void playRingingTelephone() {
        System.out.println("Playing ringing telephone");
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public void setBPM(int BPM) {
        this.BPM = BPM;
    }

    public void playNote(String note) {
        System.out.println("Playing note");
    }
}
