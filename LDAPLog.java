import java.util.Date;

/**
 *
 * @author cyborg
 */
public class LDAPLog extends LogEntry {
   
    String employee_name;
    String domain;
    String email;
    String role;
    
    /**
     *
     * @param employee_name Name of the employee
     * @param id employee's id 
     * @param email employee's
     * @param domain employee's domain of work
     * @param role employee's role of work
     */
    public LDAPLog(String employee_name, String id,String email , String domain, String role)
    {
        super(id);
        this.employee_name = employee_name;
        this.domain = domain;
        this.email = email;
        this.role = role;
    }
}
