import java.util.ArrayList;
import java.util.Date;

public class Parser {
   
    public Parser() {}
    public Date parseDate(String input){
   
//        String [] line = sc.nextLine().split(",");
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

        //       System.out.println(hourNumbers[1]);
//        System.out.println(date);
 //       System.out.println(infoToDate.get(2) + " " + infoToDate.get(1) + " " + infoToDate.get(0) + " " + 
//                infoToDate.get(3) + " " + infoToDate.get(4) + " " + infoToDate.get(5));
        infoToDate.clear();

        return date;


    }
}
