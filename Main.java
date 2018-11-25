import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Parser parser = new Parser();
        //parser.setFile(new File("r1/device.csv"));
        ArrayList<File> files = new ArrayList<File>();
        files.add(new File("r1/device.csv"));
        files.add(new File("r1/http.csv"));
        files.add(new File("r1/LDAP/2009-12.csv"));
        files.add(new File("r1/LDAP/2010-01.csv"));
        files.add(new File("r1/LDAP/2010-02.csv"));
        files.add(new File("r1/LDAP/2010-03.csv"));
        files.add(new File("r1/LDAP/2010-04.csv"));
        files.add(new File("r1/LDAP/2010-05.csv"));
        files.add(new File("r1/LDAP/2010-06.csv"));
        files.add(new File("r1/LDAP/2010-07.csv"));
        files.add(new File("r1/LDAP/2010-08.csv"));
        files.add(new File("r1/LDAP/2010-09.csv"));
        files.add(new File("r1/LDAP/2010-10.csv"));
        files.add(new File("r1/LDAP/2010-11.csv"));
        files.add(new File("r1/LDAP/2010-12.csv"));
        files.add(new File("r1/LDAP/2011-01.csv"));
        files.add(new File("r1/LDAP/2011-02.csv"));
        files.add(new File("r1/LDAP/2011-03.csv"));
        files.add(new File("r1/LDAP/2011-04.csv"));
        files.add(new File("r1/LDAP/2011-05.csv"));
        Tree tree = new Tree();
        //        parser.setFile(new File("r1/LDAP/2009-12.csv"));

        for(File file : files){
            int line = 0;
            parser.setFile(file);

            while (parser.hasNextLine()) {

                LogEntry log = parser.parseNextLine();

                //System.out.println(log);
                tree.addLogEntry(log);
//                System.out.println("line : " + " " + ++line);
            }

        }
        System.out.println(tree);
    }
}
