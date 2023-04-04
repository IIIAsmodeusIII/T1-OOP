public class Main {
    public static void main(String[] args) {
        MagneticSensor s = new MagneticSensor();
        System.out.println(s);
        s.putMagnetNearSwitch();
        System.out.println(s);
        s.moveMagnetAwayFromSwitch();
        System.out.println(s);
    }
}