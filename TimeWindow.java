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
}
