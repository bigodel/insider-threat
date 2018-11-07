import java.util.Date;
abstract public class CommonLogs extends LogEntry {
    
    Date date;
    String user;
    String pc;

    public CommonLogs(String id , Date date, String user , String pc){
        super(id);
        this.date = date;
        this.user = user;
        this.pc = pc;

    }
}
