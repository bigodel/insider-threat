import java.util.ArrayList;
import java.util.Date;

public abstract class Activity extends NodeField{

    /*private enum Indexes{
        Http = 0, USB = 1, LOGIN = 2;
    }*/

    public ArrayList < ArrayList<Action> > actions = 
        new ArrayList< ArrayList < Action >> (3);

    public Activity () 
    {
    }
}
