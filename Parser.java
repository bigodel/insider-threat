import java.util.ArrayList;
import java.util.Date;

public class Parser {
   
    public Parser() {}
    
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

    }
}
