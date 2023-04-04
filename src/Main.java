import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        // Constants
        final String CONFIG_FILE_NAME = "src/config.txt";

        // Variables
        int nDoors;
        int nWindows;
        int nPIRs;

        // Temp
        float x;
        float y;
        int angle;
        int sensingDistance;
        int sensingAngle;

        // Pattern Regex
        Pattern pattern = Pattern.compile("(k|d|w|c|p)(\\d?)\s(.*)");
        Matcher matcher;

        String sirenSound;

        // Read file
        try {
            File fileObj = new File(CONFIG_FILE_NAME);
            Scanner reader = new Scanner(fileObj);

            // Get first line
            nDoors   = reader.nextInt();
            nWindows = reader.nextInt();
            nPIRs    = reader.nextInt();

            // Create arrays
            Door[] doors     = new Door[nDoors];
            Window[] windows = new Window[nWindows];
            Sensor[] pirs    = new Sensor[nPIRs];

            // Create doors
            doors[0] = new Door();
            System.out.println("[*] Puerta principal creada.");
            for (int i=0; i<nDoors-1; i++){
                doors[i+1] = new Door();
                System.out.println("[*] Puerta creada.");
            }

            // Create windows
            for (int i=0; i<nWindows; i++){
                windows[i] = new Window();
                System.out.println("[*] Ventana creada.");
            }

            // Create PIRs
            for (int i=0; i<nPIRs; i++){
                x = reader.nextFloat();
                y = reader.nextFloat();
                angle           = reader.nextInt();
                sensingDistance = reader.nextInt();
                sensingAngle    = reader.nextInt();
                System.out.format("[*] PIR creado en %f, %f, inclinacion %d con rango %d,%d.\n", x, y, angle, sensingDistance, sensingAngle);
                // Instanciate!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }

            sirenSound = reader.next();
            System.out.print("[*] Archivo de audio: ");
            System.out.println(sirenSound);
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("[*] Error durante la lectura del archivo de configuracion.");
            e.printStackTrace();
        }

        // Menu
        while(true){
            Scanner consoleReader = new Scanner(System.in);
            System.out.print("Ingrese comando (exit() para salir): ");
            String input = consoleReader.nextLine();  // Read user input

            if(input.equals("exit()")){
                System.out.println("[*] Fin del programa.");
                break;
            }

            matcher = pattern.matcher(input);
            if (matcher.find()) {
                System.out.println(matcher.group(0));
                System.out.println(matcher.group(1));
                System.out.println(matcher.group(2));
                System.out.println(matcher.group(3));
                // Call entity method !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
        }
    }
}