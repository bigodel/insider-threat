import java.util.Scanner;
import java.io.*;
public class Main {

    public static void main(String[] args) {
        
        try{
            Scanner sc = new Scanner(new File("r1/device.csv"));
            // skipping first line. it's useless.
            if(sc.hasNextLine()){
                sc.nextLine(); 
            }
            /** Este loop é a função para transformar a string em Data, mover
             * para a classe parser posteriormente */
            while(sc.hasNextLine()){
            
                String [] line = sc.nextLine().split(",");
                String data = line[1].split(" ")[0]; 
                String hora = line[1].split(" ")[1];
                
                String [] dataNumbers = data.split("/");
                String [] hourNumbers = hora.split(":");
                
                StringBuilder all = new StringBuilder(dataNumbers[0] + " " + dataNumbers[1] + " "+ dataNumbers[2] + " ");
                all.append(hourNumbers[0] + " " + hourNumbers[1] + " " + hourNumbers[2]);
                System.out.println(hourNumbers[1]);
                System.out.println(all);
            }
        }
        
        catch(FileNotFoundException e) { System.exit(0);
        }
    }
}
