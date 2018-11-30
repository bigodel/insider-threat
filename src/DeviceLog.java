import java.util.Date;

/**
 *
 * @author cyborg
 */
public class DeviceLog extends CommonLog {
   
    private String activity;

    /**
     * Default Constructor
     * @param id action id
     * @param date moment the action took place
     * @param user User that made the action
     * @param pc Pc where the action was made
     * @param activity activity made in the current log
     */
    public DeviceLog(String id, Date date,String user , String pc, String activity)
    {
        super(id,date,user,pc);
        this.activity = activity;
    }
    
    public String getActivity()
    {
        return this.activity;
    }
}
