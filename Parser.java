import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.*;
import java.util.NoSuchElementException;

public class Parser {
   
    private enum LogType {
        Device, Http, Logon, LDAP
    }

    private Scanner sc;
    private LogType logType;

    
    public void setFile( File path ){
        try {
            sc = new Scanner(path);
            
            if(path.getParent().contains("LDAP")){
                logType = LogType.LDAP;
            }

            else switch(path.getName()){
                case "device.csv":
                    // skips unused line
                    sc.nextLine();
                    logType = LogType.Device;
                    break;

                case "http.csv":
                    logType = LogType.Http;
                    break;
                
                case "logon.csv":
                    // skips unused line
                    logType = LogType.Logon;
                    break;

                default:
                    throw new IllegalArgumentException("Not a log file");
            } 
             
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public LogEntry parseNextLine(){
        
        if(sc.hasNextLine()){
        
            if(logType == LogType.LDAP)
                return parseLDAPLogLine(sc.nextLine());

            else return parseCommonLogLine(sc.nextLine());

        }
        else throw new NoSuchElementException("There aren't any lines to be read anymore");
    }

    public Parser() {}
   
    public ParseFile(File path){
        this.setFile(path);
    }

    public boolean hasNextLine(){
        return sc.hasNext();
    }

    private Date parseDate(String input){
   
        String data = input.split(" ")[0]; 
        String hora = input.split(" ")[1];

        String [] dataNumbers = data.split("/");
        String [] hourNumbers = hora.split(":");

        ArrayList<Integer> infoToDate = new ArrayList();

        for(String number : dataNumbers){
            infoToDate.add(Integer.parseInt(number));
        }
        for(String horas : hourNumbers){
            infoToDate.add(Integer.parseInt(horas));
        }
        Date date = new Date(infoToDate.get(2) - 1900,infoToDate.get(0),infoToDate.get(1),
                    infoToDate.get(3),infoToDate.get(4),infoToDate.get(5));

        infoToDate.clear();

        return date;
    }

    
    public LDAPLog parseLDAPLogLine(String logLine){

        String [] line = logLine.split(",");

        return new LDAPLog (line[0],line[1],line[2],line[3],line[4]);

    }

    public CommonLog parseCommonLogLine(String logLine){

        String [] line = logLine.split(",");

        String id = line[0].substring(1, line[0].length()-1);
        Date date = parseDate(line[1]);

        switch (logType){
        
                case Device:
                    
                    return new DeviceLog (id,date,line[2],line[3],line[4]);

                case Http:
                    return new HttpLog (id,date,line[2],line[3],line[4]);
                
                case Logon:
                    return new LoginLog (id,date,line[2],line[3],line[4]);

                default:
                    throw new IllegalArgumentException("Not a log file");

        }

    }
/* Deprecated functions below
    public LoginLog parseLoginLogLine(String logLine){

        String [] line = logLine.split(",");

        String id = line[0].substring(1, line[0].length()-1);
        Date date = parseDate(line[1]);

        return new LoginLog (id,date,line[2],line[3],line[4]);

    }

    public HttpLog parseHttpLogLine(String logLine){

        String [] line = logLine.split(",");

        String id = line[0].substring(1, line[0].length()-1);
        Date date = parseDate(line[1]);

        return new HttpLog (id,date,line[2],line[3],line[4]);

    }
    public DeviceLog parseDeviceLogLine(String logLine){

        String [] line = logLine.split(",");

        String id = line[0].substring(1, line[0].length()-1);
        Date date = parseDate(line[1]);

        return new DeviceLog (id,date,line[2],line[3],line[4]);

    }*/
}
