import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author cyborg
 */
public class Activities extends Node{

    /**
     *
     */

    public Activities () 
    {
       // actions = new ArrayList< ArrayList < Action >> ();
        addChildren(new CommonActivity ());
        addChildren(new CommonActivity ());
        addChildren(new CommonActivity ());
    }

    /**
     *
     * @param action Adds action to the user's list of actions 
     * @return returns true if actions wasn't already stored and false otherwise
     */
    public boolean addAction(Action action){
        CommonActivity act = null;

        if(action instanceof Url){
            act = (CommonActivity) getChildren().get(0);
        }
        else if(action instanceof Usb){
            act = (CommonActivity) getChildren().get(1);
        }
        else if(action instanceof Login){
            act = (CommonActivity) getChildren().get(2);
        }
        if(act != null && act.addAction(action)){
            incrementHistogram(action.getDate());
            return true;
        }
        return false;
    }
    
    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("Http : ");
        string.append(getChildren().get(0).toString()).append("\n");
        string.append("USB : ");
        string.append(getChildren().get(1).toString()).append("\n");
        string.append("Login : ");
        string.append(getChildren().get(2).toString()).append("\n");
        return string.toString();
    }
}
