public class Tree {
    
    
    private enum LogType {
        Http, Device, Login, LDAPLog;
    }
    LogType currentLogType;
    Node root;

    public Tree(){
        root = new Node ();
    }

    public void addLogEntry(LogEntry log){

        if (log instanceof HttpLog){
           currentLogType = LogType.Http;
        }
        else if (log instanceof LoginLog){
           currentLogType = LogType.Login;
        }
        else if (log instanceof DeviceLog){

           currentLogType = LogType.Device;
        }
        /*else if (log instanceof LDAPLog){

           currentLogType = LogType.LDAPLog;
        }*/
        
        addCommonLog((CommonLog) log);
    }

    private void addCommonLog(CommonLog log)
    {
        UserField user = null;
        
        if (!root.children.isEmpty()){
            for(Node node : root.children){
                UserField userNode = (UserField) node;
                if(userNode.user_id.equals(log.user)){
                    user = userNode;
                }
            }
        }
        
        if(user == null){
            user = new UserField(log.user);
            root.children.add(user);
        }
        
        // this is a stub version, using a undefined timewindow
        TimeWindow timeWindow = null;
        if (user.children.size() == 0){
            timeWindow = new TimeWindow();
            user.children.add(timeWindow);
        }
        else {
            timeWindow = (TimeWindow) user.children.get(0);
        }
        
        Computer pc = null;
        for(Node node : timeWindow.children){
            Computer computerNode = (Computer) node;
            if(computerNode.user_pc.equals(log.pc)){
                pc = computerNode;
            }
        }
        
        if(pc == null)
        {
            pc = new Computer(log.pc);
            timeWindow.children.add(pc);
        }
        
        Activity activity = null;
        if(pc.children.size() == 0){
            activity = new Activity();
        }
        
        else{
            activity = (Activity) pc.children.get(0);
        }
        
        Action action = new Action(log.id, log.getActivity(), log.getDate());
        boolean succesfullInsertion = false;
        
        switch(currentLogType){
            
            case Http:
                if (!activity.actions.get(0).contains(action)){
                    activity.actions.get(0).add(action);
                    succesfullInsertion = true;
                }
                break;
            case Device:
                
                if (!activity.actions.get(1).contains(action)){
                    activity.actions.get(1).add(action);
                    succesfullInsertion = true;
                    break;
                }
            case Login:
                if (!activity.actions.get(2).contains(action)){
                    activity.actions.get(2).add(action);
                    succesfullInsertion = true;
                }
                break;
        }
        if(succesfullInsertion){
            int hourOfAction = log.date.getHours();
            user.histogram[hourOfAction] += 1;
            pc.histogram[hourOfAction] += 1;
            activity.histogram[hourOfAction] += 1;
        }
    }

    public String toString()
    {
        StringBuilder string = new StringBuilder("Tree \n");
        for(Node node : root.children ){
            string.append(node).append("\n");
        }
        return string.toString();
    }

    
    private final class TreeLevels{
    
        public static final int root = 0;
        public static final int user = 1;
        public static final int timeWindow = 2;
        public static final int pc = 3;
        public static final int activity = 4;

    }

    private final class ActivityIndexes{
        public static final int Http = 0;
        public static final int USB = 1;
        public static final int LOGIN = 2;
    }
}
