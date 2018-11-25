
import java.util.Date;

public class Url extends Action {
   

    public Url(/*String id,*/ String action, Date date)
    {
        super(/*id,*/action,date);
    }

    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder("Url ");
        string.append(super.toString());
        return string.toString();
    }
}
