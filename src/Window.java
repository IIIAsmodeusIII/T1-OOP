public class Window {
    public Window() {
        magneticSensor = new MagneticSensor();
        this.state = State.CLOSE;
        magneticSensor.putMagnetNearSwitch();
    }
    {
        id = nextId++;
    }
    public void open() {
        this.magneticSensor.moveMagnetAwayFromSwitch();
        this.state = State.OPEN;
    }
    public void close() {
        this.magneticSensor.putMagnetNearSwitch();
        this.state = State.CLOSE;
    }
    public String getHeader(){
        return "w"+id;
    }
    public int getState(){
        if (this.state == State.CLOSE)
            return 1;
        else
            return 0;
    }
    public final MagneticSensor magneticSensor;
    private State state;
    private final int id;
    private static int nextId=0;
}
