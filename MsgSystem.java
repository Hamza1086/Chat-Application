
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MsgSystem {

    Scanner sc = new Scanner(System.in);

    List<Contacts> contacts = new ArrayList<Contacts>();
    List<Integer> count = new ArrayList<>();
    List<List<Message>> myMsg = new ArrayList<>();
    MethodBody m1;

    MsgSystem(){

        contacts.add(new Contacts("03257612367","Hamza"));
        contacts.add(new Contacts("03171665228","Ahmed"));
        contacts.add(new Contacts("03256542789","Muzamil"));
        contacts.add(new Contacts("03316572287","Shehroz"));

        for(int i=0 ;i<contacts.size();i++){
            count.add(0);
            myMsg.add(new ArrayList<>());
        }
        m1 = new MethodBody(this);

    }

    public void sendMsg(String recNo, String content, boolean status) {
        m1.methodSendMsg(recNo, content, status);
    }

    public void contactList() {
        m1.methodContactList();
    }

    public void receiverMsgs() {
        m1.methodReciverMsgs();
    }

    public void statusHistory() {
        m1.methodStatusHistory();
    }

    public void addContact() {
        m1.methodAddContact();
    }

    public void deleteContact() {
        m1.methodDeleteContact();
    }

    public void runClient(){
        m1.methodRunClient();
    }


}
