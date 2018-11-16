import java.util.Date;

/**
 *
 * @author cyborg
 */
public class LoginLog extends CommonLog {
   
    String login;

    /**
     * Default Constructor
     * @param id action id
     * @param date moment the action took place
     * @param user User that made the action
     * @param pc Pc where the action was made
     * @param login Login or logoff action
     */
    public LoginLog(String id, Date date,String user , String pc, String login)
    {
        super(id,date,user,pc);
        this.login = login;
    }
}
