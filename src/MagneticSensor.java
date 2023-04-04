public class MagneticSensor extends Sensor {
    public MagneticSensor(){}
    public void moveMagnetAwayFromSwitch() {
        SwitchState state = SwitchState.OPEN;
        setState(state);
    }
    public void putMagnetNearSwitch() {
        SwitchState state = SwitchState.CLOSE;
        setState(state);
    }
}
