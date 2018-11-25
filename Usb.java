/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cyborg
*/
import java.util.Date;

public class Usb extends Action {

    public Usb(/*String id, */String action, Date date)
    {
        super(/*id,*/action,date);
    }
    
    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder("Usb ");
        string.append(super.toString());
        return string.toString();
    }
}