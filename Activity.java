import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author cyborg
 */
public class Activity extends Node{

    /*private enum Indexes{
        Http = 0, USB = 1, LOGIN = 2;
    }*/

    //public ArrayList < ArrayList<Action> > actions; 
    //public ArrayList <Node> actions;

    /**
     *
     */

    public Activity () 
    {
       // actions = new ArrayList< ArrayList < Action >> ();
        children.add(new CommonActivity ());
        children.add(new CommonActivity ());
        children.add(new CommonActivity ());
    }

    /**
     *
     * @param action Adds action to the user's list of actions 
     * @return returns true if actions wasn't already stored and false otherwise
     */
    public boolean addAction(Action action){
        CommonActivity act = null;

        if(action instanceof Url){
            act = (CommonActivity) children.get(0);
        }
        else if(action instanceof Usb){
            act = (CommonActivity) children.get(1);
        }
        else if(action instanceof Login){
            act = (CommonActivity) children.get(2);
        }
        if(act != null && act.addAction(action)){
            histogram[action.getDate().getHours()]+=1;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("Http : ");
        string.append(children.get(0).toString()).append("\n");
        string.append("USB : ");
        string.append(children.get(1).toString()).append("\n");
        string.append("Login : ");
        string.append(children.get(2).toString()).append("\n");
        return string.toString();
    }
}
