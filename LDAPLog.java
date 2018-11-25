import java.util.Date;

/**
 *
 * @author cyborg
 */
public class LDAPLog extends LogEntry {
   
    public String employee_name;
    public String user_id;
    public String domain;
    public String email;
    public String role;
    
    /**
     *
     * @param employee_name Name of the employee
     * @param user_id employee's id
     * @param email employee's
     * @param domain employee's domain of work
     * @param role employee's role of work
     */
    public LDAPLog(String employee_name, String user_id,String domain , String email, String role)
    {
        super(null);
        this.employee_name = employee_name;
        this.user_id = user_id;
        this.domain = domain;
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        string.append(employee_name);
        string.append(user_id);
        string.append(domain);
        string.append(email);
        string.append(role);


        return string.toString();
    }
}
