import java.util.Date;

public class TimeWindow extends Node{
   
    private Date beginTime;
    private Date endTime;
    
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

    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder("Pcs");
        for(Node node : this.getChildren()){
           string.append("\n").append(node);
        }
        return string.toString();
    }
}
