import java.util.Arrays;
import java.util.Scanner;

public class MethodBody {

    MsgSystem ms;
    Scanner sc = new Scanner(System.in);

    MethodBody(MsgSystem ms){
        this.ms = ms;
    }

    public int getReceiverIndex(String recNo) {

        for (int i = 0; i < ms.contact.length; i++) {
            if (ms.contact[i].getName().equalsIgnoreCase(recNo)) {
                return i;
            }
        }
        return -1;


    }

    public void displayMsgs(Message[] tempMessages, int index, boolean status) {

        Arrays.sort(tempMessages);

        for (Message message : tempMessages) {
            System.out.println(message);

            if (status) {
                message.setStatus(true);
            }

        }

        if (status) {
            System.out.println(ms.contact[index].getName() + "'s" + " Messages" + " seen");
        }

    }

    public void methodSendMsg(String recNo, String content, boolean status) {

        int index = getReceiverIndex(recNo);

        if (index != -1) {
            ms.myMsg[index][ms.count[index]] = new Message(recNo, content, status);
            ms.count[index]++;
            System.out.println("Message sent .. ");
        } else {
            System.out.println("Incorrect Receiver Name ");
        }

    }

    public void methodContactList(){

        System.out.println("-----------------------------\n");
        System.out.printf("%-12s %-15s\n", "Name:", "Number:");
        for (int i = 0; i < ms.contact.length; i++) {
            System.out.printf("%-12s %-15s%n", ms.contact[i].getName(),  ms.contact[i].getPhoneNo());
        }
        System.out.println("\n-----------------------------");

    }

    public void methodReciverMsgs(){
        boolean update = false;

        while (true) {

            if (update)
                break;

            System.out.print("\n\nView Options:-\nEnter 1 to display all the Receiver Messages\n" +
                    "Enter 2 to view the specific receiver Messages\nEnter 3 to exit from it: ");
            int choice = sc.nextInt();
            switch (choice) {

                case 1:

                    for (int i = 0; i < ms.contact.length; i++) {
                        System.out.println("\nReceiver: " + ms.contact[i].getName() + " Messages");

                        Message[] tempMessages = new Message[ms.count[i]]; //20

                        for (int j = 0; j < ms.count[i]; j++) {
                            tempMessages[j] = ms.myMsg[i][j];
                        }

                        displayMsgs(tempMessages, i, true);
                    }

                    break;

                case 2:
                    methodContactList();
                    System.out.print("\nEnter a name from the list above: ");
                    sc.nextLine();
                    String name = sc.nextLine();

                    for (int i = 0; i < ms.contact.length; i++) {
                        if (name.equalsIgnoreCase(ms.contact[i].getName())) {

                            Message[] temp = new Message[ms.count[i]];
                            for (int j = 0; j < ms.count[i]; j++) {
                                temp[j] = ms.myMsg[i][j];
                            }
                            displayMsgs(temp, i, true);
                        }
                    }
                    break;

                case 3:
                    update = true;
                    break;

                default:
                    break;
            }
        }
    }

    public void methodStatusHistory(){


        boolean update = false;

        while (true) {

            if (update) {
                break;
            }

            String name;
            int index;
            System.out.print("\nEnter 1 to view all the seen messages\nEnter 2 to view the all unseen messages\nEnter 3 to view the the specific person's seen messages\nEnter 4 to view the specific person's unseen messages\nEnter 5 to exit: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    for (int i = 0; i < ms.contact.length; i++) {

                        int statusCount = 0;
                        int p = 0;

                        System.out.println("\nReceiver: " + ms.contact[i].getName() + " Messages");

                        for (int y = 0; y < ms.count[i]; y++) {

                            if (ms.myMsg[i][y].getStatus()) {
                                statusCount++;
                            }

                        }

                        Message[] tempMessages = new Message[statusCount];

                        for (int j = 0; j < ms.count[i]; j++) {

                            if (ms.myMsg[i][j].getStatus()) {
                                tempMessages[p] = ms.myMsg[i][j];
                                p++;
                            }

                        }

                        displayMsgs(tempMessages, i, false);

                    }

                    break;

                case 2:

                    for (int i = 0; i < ms.contact.length; i++) {

                        int statusCount = 0;
                        int p = 0;

                        System.out.println("\nReceiver: " + ms.contact[i].getName() + " Messages");

                        for (int y = 0; y < ms.count[i]; y++) {

                            if (!ms.myMsg[i][y].getStatus()) {
                                statusCount++;
                            }

                        }

                        Message[] tempMessages = new Message[statusCount];

                        for (int j = 0; j < ms.count[i]; j++) {

                            if (!ms.myMsg[i][j].getStatus()) {
                                tempMessages[p] = ms.myMsg[i][j];
                                p++;
                            }

                        }

                        displayMsgs(tempMessages, i, false);

                    }

                    break;

                case 3:

                    methodContactList();
                    System.out.print("\nEnter the name : ");
                    sc.nextLine();
                    name = sc.nextLine();
                    index = getReceiverIndex(name);

                    if (index != -1) {

                        int statusCount = 0;
                        int p = 0;

                        System.out.println("\nReceiver: " + ms.contact[index].getName() + " Messages");

                        for (int y = 0; y < ms.count[index]; y++) {

                            if (ms.myMsg[index][y].getStatus()) {
                                statusCount++;
                            }

                        }

                        Message[] tempMessages = new Message[statusCount];

                        for (int j = 0; j < ms.count[index]; j++) {

                            if (ms.myMsg[index][j].getStatus()) {
                                tempMessages[p] = ms.myMsg[index][j];
                                p++;
                            }

                        }

                        displayMsgs(tempMessages, index, false);

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

                        int statusCount = 0;
                        int p = 0;

                        System.out.println("\nReceiver: " + ms.contact[index].getName() + " Messages");

                        for (int y = 0; y < ms.count[index]; y++) {

                            if (!ms.myMsg[index][y].getStatus()) {
                                statusCount++;
                            }

                        }

                        Message[] tempMessages = new Message[statusCount];

                        for (int j = 0; j < ms.count[index]; j++) {
                            if (!ms.myMsg[index][j].getStatus()) {
                                tempMessages[p] = ms.myMsg[index][j];
                                p++;
                            }
                        }
                        displayMsgs(tempMessages, index, false);
                    }
                    else {
                        System.out.println("Incorrect Name ");
                    }
                    break;

                case 5:
                    update = true;
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

        Contacts [] newContact = new Contacts[ms.contact.length + 1];
        int[] newCount = new int[ms.count.length + 1];
        Message[][] newMyMsg = new Message[ms.contact.length + 1][500];

        for (int i = 0; i < ms.contact.length; i++) {

            newContact[i] = ms.contact[i];
            newCount[i] = ms.count[i];
            newMyMsg[i] = ms.myMsg[i];

        }

        newContact[newContact.length - 1] = new Contacts(number,name);
        newCount[newCount.length - 1] = 0;

        ms.contact = newContact;
        ms.count = newCount;
        ms.myMsg = newMyMsg;

        System.out.println("Contact Added Successfully .. ");

    }

    public void methodDeleteContact(){
        methodContactList();
        System.out.print("Enter the contact name: ");
        String name = sc.nextLine();

        int index = getReceiverIndex(name);
        int j = 0;

        if (index != -1) {

            Contacts [] newContact = new Contacts[ms.contact.length - 1];
            int[] newCount = new int[ms.count.length - 1];
            Message[][] newMyMsg = new Message[ms.contact.length - 1][500];

            for (int i = 0; i < ms.contact.length; i++) {
                if (i != index) {
                    newContact[j] = ms.contact[i];
                    newCount[j] = ms.count[i];
                    newMyMsg[j] = ms.myMsg[i];
                    j++;
                }
            }
            ms.contact = newContact;
            ms.count = newCount;
            ms.myMsg = newMyMsg;

            System.out.println("Contact deleted Successfully .. ");
        } else {
            System.out.println("Incorrect Name ");
        }
    }

}
