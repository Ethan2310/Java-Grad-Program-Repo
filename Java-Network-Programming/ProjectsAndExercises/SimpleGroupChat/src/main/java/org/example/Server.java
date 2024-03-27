package org.example;

import com.google.gson.Gson;
import org.example.commons.model.Message;
import org.example.commons.utils.ChatUtils;
import org.example.server.core.MessageBroadCastService;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static final int MAX_DATA_SIZE = 2048;
    public static final int CONCURRENCY = 2;

    public static void main(String[] args) throws Exception {

        DatagramSocket serverSocket = new DatagramSocket(9000);
        ExecutorService executorService = Executors.newFixedThreadPool(CONCURRENCY);

        final Gson gson = new Gson();
        final MessageBroadCastService broadcastService =
                new MessageBroadCastService(serverSocket);


        byte [] buffer = new byte[MAX_DATA_SIZE];

        while(true) {
            final DatagramPacket packet =
                    new DatagramPacket(buffer, MAX_DATA_SIZE);

            System.out.println("Waiting from datagram");

            serverSocket.receive(packet);

            String msgData = new String(packet.getData());

            System.out.println("Obtained message \n " + msgData);

            final Message message = gson.fromJson(msgData.trim(), Message.class);

            executorService.execute(new Runnable() {
                public void run() {
                    broadcastService.handleMessage(
                            message, packet.getAddress(), packet.getPort());
                }
            });

            ChatUtils.clearBuffer(buffer);
        }
    }
}
