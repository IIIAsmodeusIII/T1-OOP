import java.util.ArrayList;

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
        this.Cuadrantes_cono();
    }{
        id = nextId++;
    }
    
    public String getHeader(){
        return "Pir"+id;
    }

    public boolean detectarPunto(float x, float y){
        if (rectaSup.get_m() * rectaInf.get_m() < 0) return (rectaSup.Eval(x, y) == rectaInf.Eval(x, y)) && circulo.Eval(this.x, this.y, x, y) && in_cuadrante(x, y);
        return (rectaSup.Eval(x, y) != rectaInf.Eval(x, y)) && circulo.Eval(this.x, this.y, x, y) && in_cuadrante(x, y);
    }

    public void Cuadrantes_cono(){
        int alfa = direction_angle+(sensing_angle/2);
        int beta = direction_angle-(sensing_angle/2);
        if (alfa >= 0 && alfa <= 90 || beta >= 0 && beta <= 90 ) cuadrantes.add(1);
        if (alfa > 90 && alfa <= 180 || beta > 90 && beta <= 180 ) cuadrantes.add(2);
        if (alfa > 180 && alfa <= 270 || beta > 180 && beta <= 270 ) cuadrantes.add(3);
        if (alfa > 270 && alfa < 360 || beta > 270 && beta < 360 ) cuadrantes.add(4);

    }

    public boolean in_cuadrante(float x, float y){
        if (this.x < x && this.y < y && cuadrantes.contains(1)) return true;
        if (this.x > x && this.y < y && cuadrantes.contains(2)) return true;
        if (this.x > x && this.y > y && cuadrantes.contains(3)) return true;
        return this.x < x && this.y > y && cuadrantes.contains(4);
    }

    private Recta rectaSup;
    private Recta rectaInf;
    private float x;
    private float y;
    private int direction_angle;
    private int sensing_angle;
    private int sensing_range;
    private ArrayList<Integer> cuadrantes;

    private Circulo circulo;
    private final int id;
    private static int nextId;
    static {
        nextId = 0;
    }
}
