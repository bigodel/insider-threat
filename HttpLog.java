import java.util.Date;

/**
 * Log class for internet access
 * @author cyborg
 */
public class HttpLog extends CommonLog {
   
    private String url;

    /**
     * Default Constructor
     * @param id action id
     * @param date moment the action took place
     * @param user User that made the action
     * @param pc Pc where the action was made
     * @param url url accessed in the current log
     */
    public HttpLog(String id, Date date,String user , String pc, String url)
    {
        super(id,date,user,pc);
        this.url = url;
    }

    public String getActivity()
    {
        return this.url;
    }
}
