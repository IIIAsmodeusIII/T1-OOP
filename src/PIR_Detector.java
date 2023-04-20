public class PIR_Detector extends Sensor{

    public PIR_Detector(float x, float y, int direction_angle, int sensing_angle, int sensing_range){
        this.x = x;
        this.y = y;
        this.direction_angle = direction_angle;
        this.sensing_angle = sensing_angle;
        this.sensing_range = sensing_range;
    }{
        id = nextId++;
    }

    public int getDirectionAngle(){ return direction_angle; }

    public int getSensingAngle(){ return sensing_angle; }
    
    public int getSensingRange(){ return sensing_range; }
    
    public float[] getPosition() {
        float[] array = {x, y};
        return array;

    }public String getHeader(){
        return "Pir"+id;
    }

    private float x;
    private float y;
    private int direction_angle;
    private int sensing_angle;
    private int sensing_range;
    private final int id;
    private static int nextId;
    static {
        nextId = 0;
    }
}
