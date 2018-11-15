import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
public class Main {

    public static void main(String[] args) {
        
        try{
            Scanner sc = new Scanner(new File("r1/device.csv"));
            Parser parse = new Parser();
            // skipping first line. it's useless.
             if(sc.hasNextLine()){
                sc.nextLine(); 
             }
            /** Este loop é a função para transformar a string em Data, mover
             * para a classe parser posteriormente */
            while(sc.hasNextLine()){
                
                LogEntry log = parse.parseDeviceLogLine(sc.nextLine());

                /* To implement
                 * Tree.add(log);
                 */
            }
        }
        catch(FileNotFoundException e) { System.exit(0);
        }
    }
}
