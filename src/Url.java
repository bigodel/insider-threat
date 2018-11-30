
import java.util.Date;

public class Url extends Action {
   

    public Url(String action, Date date)
    {
        super(action,date);
    }

    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append(super.toString());
        return string.toString();
    }
}
