
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Random;
import java.util.Scanner;
import java.net.Socket;

public class Demo implements Runnable {

    static final int PORT = 8080;
    static Scanner scanner = new Scanner(System.in);
    static PrintWriter out;
    static Scanner sc;
    static Scanner in;

    public static void main(String[] args) {

        MsgSystem m1 = new MsgSystem();
        DefaultMsgs d1 = new DefaultMsgs();
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int choice ;
        d1.defMsgs(m1);
        Runnable r1 = new Demo();

        while(true){
            
        System.out.print("\nEnter 1 to view the contact List\n" +
                "Enter 2 to Send Message to Receiver\nEnter 3 to view the Messages\nEnter 4 to visiting Status based Messages\nEnter 5 to add Contact\nEnter 6 to delete Contact: ");
        choice = sc.nextInt();
        sc.nextLine();

            switch (choice) {

                case 0:
                    System.exit(0);
                    break;
                case 1:
                    m1.contactList();
                    break;
                case 2:
                    m1.contactList();
                    System.out.print("Enter the receiver name: ");
                    String name = sc.nextLine();
                    m1.sendMsg(name,"Default",false);

                    if(name.equals("Muzamil")) {
                        r1.run();
                    }

                    break;  
                case 3:
                    m1.receiverMsgs();
                    break;
                case 4:
                    m1.statusHistory();
                    break;
                case 5:
                    m1.addContact();
                    break;          
                case 6:
                    m1.deleteContact();
                    break;    
                default:
                    break;
            }
        }
    }

    public void run() {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Waiting for connection... ");
            Socket ss = serverSocket.accept();
            System.out.println("Client Connected ");

            out = new PrintWriter(ss.getOutputStream(), true);
            in = new Scanner(ss.getInputStream());
            sc = new Scanner(System.in);

            while (true) {

                if (in.hasNextLine()) {
                    String clientMessage = in.nextLine();
                    System.out.println("Message from client: " + clientMessage);

                    if (clientMessage.equalsIgnoreCase("bye")) {
                        System.out.println("Client disconnected.");
                        break;
                    }

                }

                System.out.print("Message from server: ");
                String serverMessage = sc.nextLine();
                out.println(serverMessage);

                if (serverMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Ending communication.");
                    break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

