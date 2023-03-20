package app;

public class ChangePropertyAction extends SegmentAction {
    // Attributes
    public ActionOnProperty propertyToChange;
    public int propertyNewValue;

    // Constructors
    public ChangePropertyAction(ActionOnProperty propertyToChange, int propertyNewValue) {
        super(ActionType.CHANGE_PROPERTY);
        this.propertyToChange = propertyToChange;
        this.propertyNewValue = propertyNewValue;
    }

    // Enumerations
    public enum ActionOnProperty {
        ADD_VALUE_TO_INSTRUMENT,
        SET_VALUE_TO_INSTRUMENT, 
        RAISE_OCTAVE, 
        SET_VALUE_TO_BPM, 
        DOUBLE_VOLUME
    }
    
}
