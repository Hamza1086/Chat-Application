import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class MethodBody implements Runnable{

    int index;
    boolean seen;
    String name;
    MsgSystem ms;
    Scanner sc = new Scanner(System.in);
    Runnable r1;
    static final int PORT = 8080;
    static PrintWriter out;
    static Scanner in;
    static String IP = "localhost";

    MethodBody(MsgSystem ms){
        this.ms = ms;
        this.r1 = this;
    }

    public int getReceiverIndex(String recName) {
        Iterator<Contacts> iterate = ms.contacts.iterator();
        while(iterate.hasNext()){
            Contacts c = iterate.next();
            if(c.getName().equalsIgnoreCase(recName)){
                return ms.contacts.indexOf(c);
            }
        }
        return -1;
    }

    public void displayMsgs(List<Message> tempMessages, int index, boolean status) {

        tempMessages.sort((m1,m2)->{
            if(Integer.parseInt(m1.getMessageId()) > Integer.parseInt(m2.getMessageId())){
                return -1;
            } else if (Integer.parseInt(m1.getMessageId()) < Integer.parseInt(m2.getMessageId())) {
                return 1;
            }
            return 0;
        });

        for (Message message : tempMessages) {
            System.out.println(message);
            if (status) {
                message.setStatus(true);
            }
        }
        if (status) {
            System.out.println(ms.contacts.get(index).getName() + "'s" + " Messages" + " seen");
        }
    }

    public void methodSendMsg(String recNo, String content, boolean status)  {

        int i = getReceiverIndex(recNo);
        if (i != -1) {
            if(ms.contacts.get(i).getName().equalsIgnoreCase("Muzamil")){
                index = getReceiverIndex(recNo);
                seen = status;
                name = recNo;
                r1.run();
            }
            ms.myMsg.get(i).add(new Message(recNo,content,status));
            ms.count.set(i,ms.count.get(index)+1);
            System.out.println("Message sent .. ");
        } else {
            System.out.println("Incorrect Receiver Name ");
        }
    }

    public void methodContactList(){
        System.out.println("-----------------------------\n");
        System.out.printf("%-12s %-15s\n", "Name:", "Number:");
        for (int i = 0; i < ms.contacts.size(); i++) {
            System.out.printf("%-12s %-15s%n", ms.contacts.get(i).getName(),ms.contacts.get(i).getPhoneNo());
        }
        System.out.println("\n-----------------------------");
    }

    public void methodReciverMsgs(){
        boolean update = true;
        while (update) {
            System.out.print("\n\nView Options:-\nEnter 1 to display all the Receiver Messages\n" +
                    "Enter 2 to view the specific receiver Messages\nEnter 3 to exit from it: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (int i = 0; i < ms.contacts.size(); i++) {
                        System.out.println("\nReceiver: " + ms.contacts.get(i).getName() + " Messages");
                        List<Message> tempList = new ArrayList<>();
                        for (int j = 0; j < ms.count.get(i); j++) {
                            tempList.add(ms.myMsg.get(i).get(j));
                        }
                        displayMsgs(tempList, i, true);
                    }
                    break;
                case 2:
                    methodContactList();
                    System.out.print("\nEnter a name from the list above: ");
                    sc.nextLine();
                    String name = sc.nextLine();

                    for (int i = 0; i < ms.contacts.size(); i++) {
                        if (name.equalsIgnoreCase(ms.contacts.get(i).getName())) {
                            List<Message> tempList = new ArrayList<>();
                            for (int j = 0; j < ms.count.get(i); j++) {;
                                tempList.add(ms.myMsg.get(i).get(j));
                            }
                            displayMsgs(tempList, i, true);
                        }
                    }
                    break;
                case 3:
                    update = false;
                    break;
                default:
                    break;
            }
        }
    }

    public void methodStatusHistory(){

        boolean update = true;
        while (update) {
            String name;
            int index;
            System.out.print("\nEnter 1 to view all the seen messages\nEnter 2 to view the all unseen messages\nEnter 3 to view the the specific person's seen messages\nEnter 4 to view the specific person's unseen messages\nEnter 5 to exit: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    for (int i = 0; i < ms.contacts.size(); i++) {
                        System.out.println("\nReceiver: " + ms.contacts.get(i).getName() + " Messages");
                        List<Message> tempList = new ArrayList<>();
                        for (int j = 0; j < ms.count.get(i); j++) {
                            if (ms.myMsg.get(i).get(j).getStatus()) {
                                tempList.add(ms.myMsg.get(i).get(j));
                            }
                        }
                        displayMsgs(tempList, i, false);
                    }
                    break;

                case 2:
                    for (int i = 0; i < ms.contacts.size(); i++) {
                        System.out.println("\nReceiver: " + ms.contacts.get(i).getName() + " Messages");
                        List<Message> tempList = new ArrayList<>();
                        for (int j = 0; j < ms.count.get(i); j++) {
                            if (!ms.myMsg.get(i).get(j).getStatus()) {
                                tempList.add(ms.myMsg.get(i).get(j));
                            }
                        }
                        displayMsgs(tempList, i, false);
                    }
                    break;
                case 3:
                    methodContactList();
                    System.out.print("\nEnter the name : ");
                    sc.nextLine();
                    name = sc.nextLine();
                    index = getReceiverIndex(name);
                    if (index != -1) {
                        System.out.println("\nReceiver: " + ms.contacts.get(index).getName() + " Messages");
                        List<Message> tempList = new ArrayList<>();
                        for (int j = 0; j < ms.count.get(index); j++) {
                            if (ms.myMsg.get(index).get(j).getStatus()) {
                                tempList.add(ms.myMsg.get(index).get(j));
                            }
                        }
                        displayMsgs(tempList, index, false);
                    } else {
                        System.out.println("Incorrect Name ");
                    }
                    break;
                case 4:
                    methodContactList();
                    System.out.print("\nEnter the name : ");
                    sc.nextLine();
                    name = sc.nextLine();
                    index = getReceiverIndex(name);

                    if (index != -1) {
                        System.out.println("\nReceiver: " + ms.contacts.get(index).getName() + " Messages");
                        List<Message> tempList = new ArrayList<>();
                        for (int j = 0; j < ms.count.get(index); j++) {
                            if (!ms.myMsg.get(index).get(j).getStatus()) {
                                tempList.add(ms.myMsg.get(index).get(j));
                            }
                        }
                        displayMsgs(tempList, index, false);
                    }
                    else {
                        System.out.println("Incorrect Name ");
                    }
                    break;

                case 5:
                    update = false;
                    break;
                default:
                    break;

            }
        }
    }

    public void methodAddContact(){
        System.out.print("Enter the new contact Name: ");
        String name = sc.nextLine();
        System.out.print("Enter the new contact Number: ");
        String number = sc.nextLine();

        ms.contacts.add(new Contacts(number,name));
        ms.count.add(0);
        ms.myMsg.add(new ArrayList<>());

        System.out.println("Contact Added Successfully .. ");
    }

    public void methodDeleteContact(){
        methodContactList();
        System.out.print("Enter the contact name: ");
        String name = sc.nextLine();
        int index = getReceiverIndex(name);

        if (index != -1) {
            ms.contacts.remove(index);
            ms.count.remove(index);
            ms.myMsg.remove(index);
            System.out.println("Contact deleted Successfully .. ");
        } else {
            System.out.println("Incorrect Name ");
        }
    }
    public void run() {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Waiting for connection...");
            Socket ss = serverSocket.accept();
            System.out.println("Client Connected");

            out = new PrintWriter(ss.getOutputStream(), true);
            in = new Scanner(ss.getInputStream());
            sc = new Scanner(System.in);

            new Thread(() -> {
                int i = 0;
                while (true) {
                    if (in.hasNextLine()) {
                        String clientMessage = in.nextLine();
                        ms.myMsg.get(index).add(new Message(name, clientMessage, seen));
                        ms.count.set(index,ms.count.get(index)+1);
                        if(i==0){
                            System.out.println("");
                            i++;
                        }
                        System.out.println("Message from client: " + clientMessage);
                        if (clientMessage.equalsIgnoreCase("bye")) {
                            System.out.println("Client disconnected.");
                            break;
                        }
                    }
                }
            }).start();

            while (true) {
                System.out.print("Message from server: ");
                String serverMessage = sc.nextLine();
                out.println(serverMessage); // Automatically flushes

                if (serverMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Ending communication.");
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void methodRunClient() {

        try {
            Socket socket = new Socket(IP, PORT);
            System.out.println("Connected to server\nStart communication");
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
            sc = new Scanner(System.in);
            new Thread(() -> {
                while (true) {
                    if (in.hasNextLine()) {
                        String serverMessage = in.nextLine();
                        System.out.println("\nMessage from server: " + serverMessage);
                        if (serverMessage.equalsIgnoreCase("bye")) {
                            System.out.println("Server disconnected.");
                            break;
                        }
                    }
                }
            }).start();
            while (true) {
                System.out.print("Message from client: ");
                String clientMessage = sc.nextLine();
                out.println(clientMessage);

                if (clientMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Ending communication.");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

