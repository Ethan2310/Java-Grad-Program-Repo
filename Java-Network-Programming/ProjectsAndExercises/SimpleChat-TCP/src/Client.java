import View.ChatFrame;

import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {

        Socket sock = new Socket("localhost", 9001);
        ChatFrame frame = new ChatFrame("Client:Window", sock);
        frame.setVisible(true);
    }


}
