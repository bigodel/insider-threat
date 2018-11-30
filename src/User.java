import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class User extends Node
{
    private String employee_name;
    private String user_id;
    private String domain;
    private String email;
    private String role;

    public User(String employee_name, String user_id,
                String domain, String email, String role)
    {
        this.employee_name = employee_name;
        this.user_id = user_id;
        this.domain = domain;
        this.email = email;
        this.role = role;
    }

    public User(LDAPLog log)
    {
        this.employee_name = log.getEmployee_name();
        this.user_id = "DTAA/" + log.getUser_id();
        this.domain = log.getDomain();
        this.email = log.getEmail();
        this.role = log.getRole();
    }

    public User(String user_id)
    {
        this.user_id = user_id;
    }

    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder(employee_name + " " + user_id);
        string.append(" Histogram : ").append(Arrays.toString(getHistogram()));

        LinkedList<Node> children = getChildren();
        for (Node node : children) {
            string.append("\n").append(node);
        }

        return string.toString();
    }

    public void updateFields(String employee_name, String domain,
                             String email, String role)
    {
        boolean missingInfo;

        missingInfo = this.employee_name == null || this.domain == null || this.email == null || this.role == null;

        if (missingInfo) {
            this.employee_name = employee_name;
            this.domain = domain;
            this.email = email;
            this.role = role;
        }
    }

    public String getEmployee_name()
    {
        return employee_name;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public String getDomain()
    {
        return domain;
    }

    public String getEmail()
    {
        return email;
    }

    public String getRole()
    {
        return role;
    }
}
