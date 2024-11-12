
import java.util.Random;

public class DefaultMsgs {

    public void defMsgs(MsgSystem def) {

        Random rand = new Random();

        for (int i = 0; i < def.contact.length; i++) {

            for (int j = 0; j < 20; j++) {

                if (def.count[i] < def.myMsg[i].length) {

                    def.myMsg[i][j] = new Message(def.contact[i].getName(),
                            "Hello", rand.nextBoolean());
                    def.count[i]++;

                }

            }

        }

    }

}
