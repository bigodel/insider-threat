import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Parser parser = new Parser();
        //parser.setFile(new File("r1/device.csv"));
        ArrayList<File> files = new ArrayList<File>();
       // files.add(new File("r1/device.csv"));
        files.add(new File("r1/http.csv"));
        //files.add(new File("r1/logon.csv"));
        Tree tree = new Tree();
        //        parser.setFile(new File("r1/LDAP/2009-12.csv"));

        for(File file : files){
            int line = 0;
            parser.setFile(file);

            while (parser.hasNextLine()) {

                LogEntry log = parser.parseNextLine();

                //System.out.println(log);
                tree.addLogEntry(log);
                System.out.println("line : " + " " + ++line);
            }

        }
        System.out.println(tree);
    }
}
