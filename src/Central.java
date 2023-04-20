import java.util.ArrayList;
import java.util.Objects;

public class Central {
    public Central(ArrayList<Door> doors, ArrayList<Window> windows, ArrayList<PIR_Detector> pirs){
        zone0 = new ArrayList<>();
        zone1 = new ArrayList<>();
        zone2 = new ArrayList<>();
        personas = new ArrayList<>();
        zone0_state = true;
        zone1_state = true;
        zone2_state = true;
        flag = true;
        isArmed = false;
        siren = null;
        modo = null;

        zone0.add(doors.get(0).getSensor());

        for (int i = 1; i < doors.size(); i++){
            zone1.add(doors.get(i).getSensor());
        }
        for (int i = 1; i < windows.size(); i++){
            zone1.add(windows.get(i).getSensor());
        }

        for (int i = 1; i < pirs.size(); i++){
            zone2.add(pirs.get(i));
        }
    }
    public void setPeople(ArrayList<Person> people){
        personas = people;
    }
    public void arm(String param) {
        modo = param;
        if (param.equals("d")){
            disarm();
        }
        else {
            checkZone();
            if (flag) {
                isArmed = true;
                System.out.println("Armado exitoso!");
            } else {
                System.out.print("Zonas abiertas: ");
                if (!zone0_state) {
                    System.out.print("Zona 0 ");
                }
                if (!zone1_state) {
                    System.out.print("Zona 1 ");
                }
                System.out.println("\n");
            }
        }
    }
    public void disarm() {
        isArmed = false;
    }
    public void setSiren(Siren s) {
        siren = s;
    }
    public boolean checkZone(){
        if(Objects.equals(zone0.get(0).toString(), "0")){
            zone0_state = false;
            flag = false;
            return false;
        }
        for (int i = 0; i < zone1.size(); i++){
            if(Objects.equals(zone1.get(i).toString(), "0")){
                zone1_state = false;
                flag = false;
                return false;
            }
        }
        if(Objects.equals(modo, "a")){
            for (int i = 0; i < zone2.size(); i++){
                for (int j = 0; j < personas.size(); j++){
                    if(zone2.get(i).detectarPunto(personas.get(j).getX(), personas.get(j).getY()) == false){
                        zone2_state = false;
                        flag = false;
                        return false;
                    }
                }
            }
        }
        flag = true;
        return true;
    }
    public String getHeader(){
        return "Central";
    }
    public boolean getState(){
        return isArmed;
    }

    public Siren getSiren(){
        return this.siren;
    }
    private ArrayList<Sensor> zone0;
    private ArrayList<Sensor> zone1;
    private ArrayList<PIR_Detector> zone2;
    private ArrayList<Person> personas;
    private boolean isArmed;
    private boolean zone0_state;
    private boolean zone1_state;
    private boolean zone2_state;
    private boolean flag;
    private Siren siren;
    private String modo;
}


