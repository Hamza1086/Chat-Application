

import java.util.Random;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {

        MsgSystem m1 = new MsgSystem();
        DefaultMsgs d1 = new DefaultMsgs();
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int choice ;
        d1.defMsgs(m1);

        while(true){
            
        System.out.print("\nEnter 1 to view the contact List\n" +
                "Enter 2 to Send Message to Receiver\nEnter 3 to view the Messages\nEnter 4 to visiting Status based Messages" +
                "\nEnter 5 to add Contact\nEnter 6 to delete Contact\nEnter 7 to Establish connection with the server: ");
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
                case 7:
                    m1.runClient();
                    break;
                default:
                    break;
            }
        }
    }
}

