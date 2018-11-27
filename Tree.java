public class Tree {
    
    
    private enum LogType {
        Http, Device, Login, LDAP;
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
        else if (log instanceof LDAPLog){

           currentLogType = LogType.LDAP;
        }

        switch(currentLogType) {
            case LDAP:
            addLDAPLog((LDAPLog) log);
            break;
            
            default:
            addCommonLog((CommonLog) log);
        }
    }

    private void addLDAPLog(LDAPLog log)
    {
        UserField user = null;

        if (!root.children.isEmpty()){
            for(Node node : root.children){
                UserField userNode = (UserField) node;
                if(userNode.getUser_id() != null &&
                    userNode.getUser_id().contains(log.getUser_id())){
                   
                    user = userNode;
                }
            }
        }
    
        if(user == null){
            user = new UserField(log);
            root.children.add(user);
        }

        else{
            user.updateFields(log.getEmployee_name(), log.getDomain(), log.getEmail(), log.getRole());
        }
    }

    private void addCommonLog(CommonLog log)
    {
        UserField user = null;
        
        if (!root.children.isEmpty()){
            for(Node node : root.children){
                UserField userNode = (UserField) node;
                if(userNode.getUser_id().equals(log.getUser())){
                    user = userNode;
                }
            }
        }
        
        if(user == null){
            user = new UserField(log.getUser());
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
            if(computerNode.getUser_pc().equals(log.getPc())){
                pc = computerNode;
            }
        }
        
        if(pc == null)
        {
            pc = new Computer(log.getPc());
            timeWindow.children.add(pc);
        }
        
        Activity activity = null;
        if(pc.children.size() == 0){
            activity = new Activity();
            pc.children.add(activity);
        }
        
        else{
            activity = (Activity) pc.children.get(0);
        }
        
        Action action;
        boolean succesfullInsertion = false;
        
        switch(currentLogType){
            
            case Http:
                action = new Url(/*log.id, */log.getActivity(), log.getDate());
                if(activity.addAction(action)){
                    succesfullInsertion = true;
                }
                break;
            case Device:
                
                action = new Usb(/*log.id, */ log.getActivity(), log.getDate());
                if(activity.addAction(action)){
                    succesfullInsertion = true;
                }
                break;
            case Login:
                action = new Login(/*log.id, */ log.getActivity(), log.getDate());
                if(activity.addAction(action)){
                    succesfullInsertion = true;
                }
                break;
        }
        if(succesfullInsertion){
            int hourOfAction = log.getDate().getHours();
            user.histogram[hourOfAction] += 1;
            pc.histogram[hourOfAction] += 1;
            activity.histogram[hourOfAction] += 1;
        }
    }

    @Override
    public String toString()
    {

        StringBuilder string = new StringBuilder("Tree \n");
        for(Node node : root.children ){
            string.append(node).append("\n");
        }
        return string.toString();
    }
   /* 
    private final class ActivityIndexes{
        public static final int Http = 0;
        public static final int USB = 1;
        public static final int LOGIN = 2;
    }*/
}
