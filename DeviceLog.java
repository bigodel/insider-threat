import java.util.Date;

public class DeviceLog extends CommonLog {
   
    String activity;

    public DeviceLog(String id, Date date,String user , String pc, String activity)
    {
        super(id,date,user,pc);
        this.activity = activity;
    }
}
