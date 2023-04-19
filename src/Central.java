import java.util.ArrayList;

public class Central {
    public Central(ArrayList<Door> doors, ArrayList<Window> windows){
        zone0 = new ArrayList<Sensor>();
        zone1 = new ArrayList<Sensor>();
        zone0_state = true;
        zone1_state = true;
        flag = true;
        isArmed = false;
        siren = null;

        zone0.add(doors.get(0).getSensor());

        for (int i = 1; i < doors.size(); i++){
            zone1.add(doors.get(i).getSensor());
        }
    }
    public void arm(String param) {
        if (param.equals("d")){
            disarm();
        }
        else {
            checkZone();
            if (flag == true) {
                isArmed = true;
                System.out.println("Armado exitoso!");
            } else {
                System.out.print("Zonas abiertas: ");
                if (zone0_state == false) {
                    System.out.print("Zona 0 ");
                }
                if (zone1_state == false) {
                    System.out.print("Zona 1 ");
                }
                System.out.println("");
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
        if(zone0.get(0).toString() == "0"){
            zone0_state = false;
            flag = false;
            return false;
        }
        for (int i = 1; i < zone1.size(); i++){
            if(zone1.get(i).toString() == "0"){
                zone1_state = false;
                flag = false;
                return false;
            }
        }
        flag = true;
        return true;
    }
    public String getHeader(){
        return "Central";
    }
    public boolean getState(){
        return isArmed?true:false;
    }
    private ArrayList<Sensor> zone0;
    private ArrayList<Sensor> zone1;
    private boolean isArmed;
    private boolean zone0_state;
    private boolean zone1_state;
    private boolean flag;
    private Siren siren;
}


