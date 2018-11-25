
import java.util.Arrays;

public class UserField extends Node {

    String employee_name;
    String user_id;
    String domain;
    String email;
    String role;

    public UserField(String employee_name, String user_id,
            String domain, String email, String role)
    {
        this.employee_name = employee_name;
        this.user_id = user_id;
        this.domain = domain;
        this.email = email;
        this.role = role;
    }

    public UserField(LDAPLog log)
    {
        this.employee_name = log.employee_name;
        this.domain = log.domain;
        this.email = log.email;
        this.role = log.role;
    }
    public UserField(String user_id)
    {
        this.user_id = user_id;
    }

    public String toString()
    {
        StringBuilder string = new StringBuilder(employee_name + " " + user_id);
        string.append(" Histogram : ").append(Arrays.toString(histogram));
        for(Node node : this.children){
           string.append("\n").append(node);
        }
        return string.toString();
    }

    public void updateFields(String employee_name,
            String domain, String email, String role)
    {
        boolean missingInfo;

        missingInfo = this.employee_name == null |this.domain == null | this.email == null | this.role == null;


        if(missingInfo){
            this.employee_name = employee_name;
            this.domain = domain;
            this.email = email;
            this.role = role;
        }
    }
}
