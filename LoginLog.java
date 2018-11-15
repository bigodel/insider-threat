import java.util.Date;

public class LoginLog extends CommonLog {
   
    String login;

    public LoginLog(String id, Date date,String user , String pc, String login)
    {
        super(id,date,user,pc);
        this.login = login;
    }
}
