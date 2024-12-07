
import java.util.Random;

public class DefaultMsgs {

    public void defMsgs(MsgSystem def) {
        Random rand = new Random();
        for (int i = 0; i < def.contacts.size(); i++) {
            for (int j = 0; j < 20; j++) {
                    def.myMsg.get(i).add(new Message(def.contacts.get(i).getName(),
                            "Hello", rand.nextBoolean()));
                    def.count.set(i, def.count.get(i) + 1);
            }
        }
    }
}
