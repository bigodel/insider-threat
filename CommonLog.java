import java.util.Date;

/**
 * Abstract class representing the most common types of logs
 * @author cyborg
 */
abstract public class CommonLog extends LogEntry {
    
    Date date;
    String user;
    String pc;

    /**
     *
     * Default Constructor
     * @param id action id
     * @param date moment the action took place
     * @param user User that made the action
     * @param pc Pc where the action was made
     */
    public CommonLog(String id , Date date, String user , String pc){
        super(id);
        this.date = date;
        this.user = user;
        this.pc = pc;

    }
}
