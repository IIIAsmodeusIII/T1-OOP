public class PIR_Detector extends Sensor{

    public PIR_Detector(float x, float y, int direction_angle, int sensing_angle, int sensing_range){
        this.x = x;
        this.y = y;
        this.direction_angle = direction_angle;
        this.sensing_angle = sensing_angle;
        this.sensing_range = sensing_range;
        this.rectaSup = new Recta(false, x, y, direction_angle, sensing_angle);
        this.rectaInf = new Recta(true, x, y, direction_angle, sensing_angle);
        this.circulo = new Circulo(sensing_range);
    }{
        id = nextId++;
    }
    
    public String getHeader(){
        return "Pir"+id;
    }

    public boolean detectarPunto(float x, float y){
        return (rectaSup.Eval(x, y) != rectaInf.Eval(x, y)) && circulo.Eval(this.x, this.y, x, y);
    }

    private Recta rectaSup;
    private Recta rectaInf;
    private float x;
    private float y;
    private int direction_angle;
    private int sensing_angle;
    private int sensing_range;

    private Circulo circulo;
    private final int id;
    private static int nextId;
    static {
        nextId = 0;
    }
}
