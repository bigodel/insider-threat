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

    //    root.addChildren(

    }

    private void addCommonLog(CommonLog log)
    {
        Node user = null;

        for(Node node : root.children){
            UserField userNode = (UserField) node;
            if(userNode.user_id.equals(log.user)){
                user = userNode;
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

        Node pc = null;
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
        
        Activity act = null;
        if(pc.children.size() == 0){
            act = new Activity();
        }

        else{
            act = (Activity) pc.children.get(0);
        }

        switch(currentLogType){
        
            case Http:
                act.actions.get(0).add( new Action (log.getActivity(),log.getDate()) );
            case Device:
                act.actions.get(1).add( new Action (log.getActivity(),log.getDate()) );
            case Login:
                act.actions.get(2).add( new Action (log.getActivity(),log.getDate()) );
        }


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
