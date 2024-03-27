import View.ChatFrame;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {

        ServerSocket ser = new ServerSocket(9001);
        while(true) {
            System.out.println("Waiting for client");
            Socket sock = ser.accept();
            ChatFrame frame = new ChatFrame("Server:Window", sock);
            frame.setVisible(true);
        }

    }

}
