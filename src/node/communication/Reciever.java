package node.communication;

import java.io.IOException;
import java.net.*;

public class Reciever implements Runnable {
    private volatile static DatagramSocket server;

    public void run(){
        while (true){
            byte[] recvBuf = new byte[8192];
            DatagramPacket recvPacket = new DatagramPacket(recvBuf , recvBuf.length);
            try {
                server.receive(recvPacket);
            }catch (IOException e){
                e.printStackTrace();
            }
            String recvStr = new String(recvPacket.getData() , 0 , recvPacket.getLength());
            Resolver.resolve(recvStr);
        }
    }

    public static void init(){
        if (server == null){
            synchronized (Reciever.class){
                if (server == null){
                    try {
                        server = new DatagramSocket(7777);
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void clean(){
        server.close();
    }
}