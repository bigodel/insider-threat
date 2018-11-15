import java.util.Date;

public class LDAPLog extends LogEntry {
   
    String employee_name;
    String domain;
    String email;
    String role;
    
    public LDAPLog(String employee_name, String id,String email , String domain, String role)
    {
        super(id);
        this.employee_name = employee_name;
        this.domain = domain;
        this.email = email;
        this.role = role;
    }
}
