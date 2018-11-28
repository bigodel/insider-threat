import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Date;

public class Tree
{
    private enum LogType
    {
        Http, Device, Login, LDAP;
    }
    
    private int [] AllUsersHistogram = new int[24];
    private LogType currentLogType;
    private Node root;
    int amountOfUsers;
    
    public Tree()
    {
        root = new Node ();
    }
    
    public void addLogEntry(LogEntry log)
    {
        if (log instanceof HttpLog) {
            currentLogType = LogType.Http;
        }
        else if (log instanceof LoginLog) {
            currentLogType = LogType.Login;
        }
        else if (log instanceof DeviceLog) {
            currentLogType = LogType.Device;
        }
        else if (log instanceof LDAPLog) {
            currentLogType = LogType.LDAP;
        }
        
        switch (currentLogType) {
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
        
        if (!root.getChildren().isEmpty()) {
            ArrayList<Node> children = root.getChildren();
            for (Node node : children) {
                UserField userNode = (UserField) node;
                
                if (userNode.getUser_id() != null &&
                    userNode.getUser_id().contains(log.getUser_id())) {
                    
                    user = userNode;
                }
            }
        }
        
        if (user == null) {
            user = new UserField(log);
            root.addChildren(user);
            amountOfUsers += 1;
        }
        else {
            user.updateFields(log.getEmployee_name(), log.getDomain(),
                log.getEmail(), log.getRole());
        }
    }
    
    private void addCommonLog(CommonLog log)
    {
        UserField user = null;
        ArrayList<Node> children = root.getChildren();
        
        if (!children.isEmpty()) {
            for (Node node : children) {
                UserField userNode = (UserField) node;
                
                if (userNode.getUser_id().equals(log.getUser())) {
                    user = userNode;
                }
            }
        }
        
        if (user == null) {
            user = new UserField(log.getUser());
            root.addChildren(user);
            amountOfUsers += 1;
        }
        
        // this is a stub version, using a undefined timewindow
        TimeWindow timeWindow = null;
        if (user.getChildren().isEmpty()){
            timeWindow = new TimeWindow();
            user.addChildren(timeWindow);
        }
        else {
            timeWindow = (TimeWindow) user.getChildren().get(0);
        }
        
        ArrayList<Node> TimeChildren = timeWindow.getChildren();
        
        Computer pc = null;
        for (Node node : TimeChildren) {
            Computer computerNode = (Computer) node;
            if (computerNode.getUser_pc().equals(log.getPc())) {
                pc = computerNode;
            }
        }
        
        if (pc == null) {
            pc = new Computer(log.getPc());
            timeWindow.addChildren(pc);
        }
        
        ArrayList<Node> PcChildren = pc.getChildren();
        
        Activity activity = null;
        if (PcChildren.isEmpty()) {
            activity = new Activity();
            PcChildren.add(activity);
        }
        else {
            activity = (Activity) PcChildren.get(0);
        }
        
        Action action;
        boolean succesfullInsertion = false;
        
        switch (currentLogType) {
            case Http:
                action = new Url(log.getActivity(), log.getDate());
                
                if (activity.addAction(action)) {
                    succesfullInsertion = true;
                }
                
                break;
            case Device:
                action = new Usb(log.getActivity(), log.getDate());
                
                if (activity.addAction(action)) {
                    succesfullInsertion = true;
                }
                
                break;
            case Login:
                action = new Login(log.getActivity(), log.getDate());
                
                if (activity.addAction(action)) {
                    succesfullInsertion = true;
                }
                
                break;
        }
        if (succesfullInsertion) {
            Date dateOfAction = log.getDate();
            user.incrementHistogram(dateOfAction);
            pc.incrementHistogram(dateOfAction);
            activity.incrementHistogram(dateOfAction);
            AllUsersHistogram[dateOfAction.getHours()]+=1;
        }
    }
    
    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder("Tree \n");
        
        ArrayList<Node> children = root.getChildren();
        for (Node node : children) {
            string.append(node).append("\n");
        }
        
        return string.toString();
    }

    public String findMostSuspiciousEmployee(){
        int [] mean = new int[24];
        int [] usersDistances = new int[amountOfUsers];
        int currentUserIndex = 0;

        for (int i = 0; i < 23; i++) {
            mean[i] =  AllUsersHistogram[i]/amountOfUsers;
        }

        ArrayList<Node> children = root.getChildren();
        if (!children.isEmpty()) {
            for (Node node : children) {
                UserField userNode = (UserField) node;
                int [] currentUserHistogram =  userNode.getHistogram();
                int difference = 0;

                for (int i = 0; i < 23; i++) {
                    difference = currentUserHistogram[i] - mean[i];
                    difference = difference * difference;
                }
                usersDistances[currentUserIndex] = (int) sqrt(difference);
                currentUserIndex += 1;
                difference = 0;
            }
        }

        /* finding the most suspect user*/
        int suspiciousIndex = 0;
        int max = usersDistances[0];
        for (int i = 0; i < amountOfUsers; i++) {
           if(usersDistances[i] > max){
                suspiciousIndex = i;
                max = usersDistances[i];
           }
        }

        /* todo print a better representation */
        return root.getChildren().get(suspiciousIndex).toString();
    }
    
    public String findUserById(String user_id)
    {
        UserField user = null;
        ArrayList<Node> children = root.getChildren();
        
        if (!children.isEmpty()) {
            for (Node node : children) {
                UserField userNode = (UserField) node;
                
                if (userNode.getUser_id().equals(user_id)) {
                    user = userNode;
                }
            }
        }
        if(user != null) return user.toString();

        else return "User Not Found";
    }
    
    public String findUserByName(String user_name)
    {
        UserField user = null;
        ArrayList<Node> children = root.getChildren();
        
        if (!children.isEmpty()) {
            for (Node node : children) {
                UserField userNode = (UserField) node;
                
                if (userNode.getEmployee_name().equals(user_name)) {
                    user = userNode;
                }
            }
        }
        if(user != null) return user.toString();

        else return "User Not Found";
    }
}
