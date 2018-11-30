//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
import static java.lang.Math.sqrt;
import java.util.LinkedList;
import java.util.List;
import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.sort;
import java.util.Date;
import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.sort;

/**
 *
 * @author cyborg
 */
public class Tree /*implements Serializable*/
{
    private enum LogType { Http, Device, Login, LDAP; }
    private LogType currentLogType;
    private Node root;
    private int amountOfUsers;

    /**
     *
     */
    public Tree() 
    {
        root = new Node();
        amountOfUsers = 0;
    }

    /**
     *
     * @param log
     */
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
        User user = null;

        if (!root.getChildren().isEmpty()) {
            LinkedList<Node> children = root.getChildren();
            for (Node node : children) {
                User userNode = (User) node;

                if (userNode.getUser_id() != null &&
                    userNode.getUser_id().contains(log.getUser_id())) {

                    user = userNode;
                }
            }
        }

        if (user == null) {
            user = new User(log);
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
        User user = null;
        LinkedList<Node> children = root.getChildren();

        if (!children.isEmpty()) {
            for (Node node : children) {
                User userNode = (User) node;

                if (userNode.getUser_id().equals(log.getUser())) {
                    user = userNode;
                }
            }
        }

        if (user == null) {
            user = new User(log.getUser());
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

        LinkedList<Node> TimeChildren = timeWindow.getChildren();

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

        LinkedList<Node> PcChildren = pc.getChildren();

        Activities activity = null;
        if (PcChildren.isEmpty()) {
            activity = new Activities();
            PcChildren.add(activity);
        }
        else {
            activity = (Activities) PcChildren.get(0);
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
            root.incrementHistogram(dateOfAction);
        }
    }

/* Currently deprecated since with changes to the other nodes toString methods
    it would probably overflow 

    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder("Tree \n");

        LinkedList<Node> children = root.getChildren();
        for (Node node : children) {
            string.append(node).append("\n");
        }

        return string.toString();
    }
*/
    /**
     *
     * @return Returns the profile of the most suspicious employee
     */
    public String findMostSuspiciousEmployee()
    {
        int[] mean = new int[24];
        int[] usersDistances = new int[amountOfUsers];
        int currentUserIndex = 0;
        int[] thisHistogram = root.getHistogram();

        for (int i = 0; i < 23; i++) {
            mean[i] =  thisHistogram[i]/amountOfUsers;
        }


        LinkedList<Node> children = root.getChildren();
        if (!children.isEmpty()) {
            for (Node node : children) {
                User userNode = (User) node;
                int[] currentUserHistogram =  userNode.getHistogram();
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
            if (usersDistances[i] > max) {
                suspiciousIndex = i;
                max = usersDistances[i];
            }
        }

        /* todo print a better representation */
        return root.getChildren().get(suspiciousIndex).toString();
    }

    /**
     * @return Returns a list with the most suspicious Employees
     */
    public List<Node> findSuspiciousEmployees()
    {
        int[] mean = new int[24];
        int[] usersDistances = new int[amountOfUsers];
        int currentUserIndex = 0;
        int[] thisHistogram = root.getHistogram();

        for (int i = 0; i < 23; i++) {
            mean[i] =  thisHistogram[i]/amountOfUsers;
        }

        //System.out.println(Arrays.toString(mean));

        LinkedList<Node> children = root.getChildren();
        if (!children.isEmpty()) {
            for (Node node : children) {
                User userNode = (User) node;
                int[] currentUserHistogram =  userNode.getHistogram();
                double difference = 0;
                double currentDifference = 0;

                for (int i = 0; i < 23; i++) {
                    currentDifference = currentUserHistogram[i] - mean[i];
                    currentDifference = currentDifference * currentDifference;
                    difference += currentDifference;
                }

                usersDistances[currentUserIndex] = (int) sqrt(difference);
                currentUserIndex += 1;
            }
        }

        /* finding the most suspect users*/
        sort(usersDistances);

        int halfIndex = amountOfUsers/2;
        int[] firstHalf = copyOfRange(usersDistances,0,halfIndex); 
        int[] secondHalf = copyOfRange(usersDistances,halfIndex, amountOfUsers); 
        int Q1 = median(firstHalf, halfIndex);
        int Q3 = median(secondHalf, amountOfUsers - halfIndex);
        int IQR = Q3-Q1;

        int LowerBound = Q1 - (int) (1.5 * IQR);
        int UpperBound = Q3 + (int) (1.5 * IQR);
       /* System.out.println("Users distances : " + Arrays.toString(usersDistances));
        System.out.println("Q1: " + Q1);
        System.out.println("Q3: " + Q3);
        System.out.println("Lower Bound: " + LowerBound);
        System.out.println("Upper Bound: " + UpperBound);*/

        LinkedList<Node> suspiciousUsers = new LinkedList<>();

        for (int i = 0; i < amountOfUsers; i++) {
            if(usersDistances[i] > UpperBound || usersDistances[i] < LowerBound){
                suspiciousUsers.add(children.get(i));
            }
        }

        return suspiciousUsers;
    }

    private int median(int [] arr, int size){

        int halfIndex = size/2;
        int median;
        if(halfIndex % 2 == 0){
           median = (arr[halfIndex+1] + arr[halfIndex])/2;
        }
        else {
            median = arr[halfIndex+1];
        }

        return median;
    }

    /**
     *
     * @param user_id
     * @return Returns the user profile
     */
    public String findUserById(String user_id)
    {
        User user = null;
        LinkedList<Node> children = root.getChildren();

        if (!children.isEmpty()) {
            for (Node node : children) {
                User userNode = (User) node;

                if (userNode.getUser_id().contains(user_id)) {
                    user = userNode;
                }
            }
        }
        if(user != null) return user.toString();

        else return "User Not Found";
    }

    /**
     *
     * @param user_name
     * @return Returns the user profile
     */
    public String findUserByName(String user_name)
    {
        User user = null;
        LinkedList<Node> children = root.getChildren();

        if (!children.isEmpty()) {
            for (Node node : children) {
                User userNode = (User) node;

                if (userNode.getEmployee_name().equals(user_name)) {
                    user = userNode;
                }
            }
        }
        if(user != null) return user.toString();

        else return "User Not Found";
    }

    /* unused function
    public void saveTree() throws IOException{
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tree.ser"))) {
            out.writeObject(this);
            out.flush();
        }
    }
    */
}
