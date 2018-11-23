import java.util.Date;

public class TimeWindow extends Node{
   
    Date beginTime;
    Date endTime;
    
    public TimeWindow() {}

    public TimeWindow(Date beginTime, Date endTime)
    {
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public Date getBeginTime()
    {
        return beginTime;
    }
    
    public Date getEndTime()
    {
        return endTime;
    }

    public String toString()
    {
        StringBuilder string = new StringBuilder("Pcs");
        for(Node node : this.children){
           string.append("\n").append(node);
        }
        return string.toString();
    }
}
