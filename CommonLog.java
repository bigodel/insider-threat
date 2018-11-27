import java.util.Date;

/**
 * Abstract class representing the most common types of logs
 * @author cyborg
 */
abstract public class CommonLog extends LogEntry {
    
    private Date date;
    private String user;
    private String pc;

    /**
     *
     * Default Constructor * 
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

    /**
     *
     * @return Returns the activity string associated with the node
     */
    public abstract String getActivity();
    
    /**
     *
     * @return returns the date of the respective log
     */
    public Date getDate() { 
        return date;
    }

    /**
     *
     * @return returns the user to which the log is related
     */
    public String getUser() {
        return user;
    }

    /**
     *
     * @return returns the pc to which the log is related
     */
    public String getPc() {
        return pc;
    }
}
