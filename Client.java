import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    static final String IP = "localhost";
    static final int PORT = 8080;
    static PrintWriter out;
    static Scanner in;
    static Scanner sc;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(IP, PORT);
            System.out.println("Connected to server\nStart communication");

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
            sc = new Scanner(System.in);

            new Thread(() -> {
                int i=0;
                while (true) {
                    if (in.hasNextLine()) {
                        String serverMessage = in.nextLine();
                        if(i==0){
                            System.out.println("");
                            i++;
                        }
                        System.out.println("Message from server: " + serverMessage);
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
