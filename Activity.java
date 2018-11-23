import java.util.ArrayList;
import java.util.Date;

public class Activity extends Node{

    /*private enum Indexes{
        Http = 0, USB = 1, LOGIN = 2;
    }*/

    public ArrayList < ArrayList<Action> > actions; 

    public Activity () 
    {
        actions = new ArrayList< ArrayList < Action >> ();
        actions.add(new ArrayList<Action> ());
        actions.add(new ArrayList<Action> ());
        actions.add(new ArrayList<Action> ());
    }
    
    public String toString()
    {
        StringBuilder string = new StringBuilder("Http ");
        string.append(actions.get(0).toString() + "\n");
        string.append("USB ");
        string.append(actions.get(1).toString() +"\n");
        string.append("LOGIN ");
        string.append(actions.get(2).toString()+"\n");
        return string.toString();
    }
}
