
import java.util.Arrays;

/**
 *
 * @author cyborg
 */
public class Computer extends Node {
    
    private String user_pc;

    /**
     *
     * @param user_pc Pc used by the current user
     */
    public Computer (String user_pc)
    {
        this.user_pc = user_pc;
    }

    public String toString()
    {
        StringBuilder string = new StringBuilder("used_pc : " + this.user_pc + "\n");

        string.append("Histogram : ").append(Arrays.toString(histogram));
        for(Node node : this.children){
           string.append("\n").append(node);
        }
        return string.toString();
    }

    /**
     *
     * @return The pc used by the user
     */
    public String getUser_pc() {
        return user_pc;
    }
}
