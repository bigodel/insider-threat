import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Parser parser = new Parser();
        ArrayList<File> files = new ArrayList<File>();
        
        if(args.length == 0){
            try{
                files = readFolder(new File("r1"));
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else if (args.length == 1){
            try{
                files = readFolder(new File(args[0]));
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Proper usave is: java Main <folder> ");
            System.exit(0);
        }

        Tree tree = new Tree();

        for (File file : files) {
            System.out.println("Starting to parse " + file.getName());
           // int line = 0;
            parser.setFile(file);

            while (parser.hasNextLine()) {
                LogEntry log = parser.parseNextLine();

                // System.out.println(log);
                tree.addLogEntry(log);
            //    System.out.println("line : " + " " + ++line);
            }

        }
        
        List<Node> suspects = tree.findSuspiciousEmployees();
        System.out.println("Amount of suspects : " + suspects.size());
        for(Node node : suspects){
            User user = (User) node;
            System.out.println( user.getEmployee_name() + " " + user.getUser_id() );
            //System.out.println(node);
        }

        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a user's id if you want to check a suspects' profile.");
        System.out.println("Or Simply insert enter <quit> to do nothing(without brackets)");
        String id = sc.next();
        if(id.toUpperCase().equals("QUIT")){
            System.out.println("Sucessful exit");
            System.exit(0);
        }
        System.out.println(tree.findUserById(id));
    }

    public static ArrayList<File> readFolder(File folder) throws IOException { 
        ArrayList<File> filesToRead = new ArrayList<>();

        if(!folder.isDirectory()){
            throw new IOException("Not a valid folder");
        }
        for(File file : folder.listFiles()){
            if(file.isDirectory()){
                filesToRead.addAll(readFolder(file));
            }
            if(file.isFile()){
                if(file.getName().contains(".csv")){
                    filesToRead.add(file);
                }
            }
         
        }
        return filesToRead;
    }
}
