package part01.lesson10.task01.server;

import part01.lesson10.task01.lib.CodesMessage;
import part01.lesson10.task01.lib.Handler;
import part01.lesson10.task01.lib.HandlerMessage;
import part01.lesson10.task01.lib.Message;
import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author Oleg_Chapurin
 */
public class ChatServer implements Server {

    private int portServer;
    private TreeMap<String, HashMap<String,? extends Object>> clients = new TreeMap<>();
    private Handler handlerMessage = new HandlerMessage();
    private InetAddress inetAddress;
    private DatagramSocket ds;
    private byte[] byteOut;
    private DatagramPacket dpIn;
    private DatagramPacket dpOut;
    private int sizeMessage;

    /**
     *
     * @param portServer port server
     * @param sizeMessage message size in bytes
     */
    public ChatServer(int portServer, int sizeMessage) {
        this.portServer = portServer;
        this.sizeMessage = sizeMessage;
    }

    /** Get local address */
    private boolean getLocalHost() {
        boolean marker = false;
        while (!marker) {
            try {
                this.inetAddress = InetAddress.getLocalHost();
                marker = true;
            } catch (UnknownHostException e) {
                e.printStackTrace();
                break;
            }
        }
        return marker;
    }

    /** Close DatagramSocket */
    private void closeDatagramSocketServer() {
        this.ds.close();
    }

    /** Creat DatagramSocket */
    private boolean creatDatagramSocket() {
        try {
            ds = new DatagramSocket(portServer);
            return true;
        } catch (SocketException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Receiver message from clients */
    private Message receiverFromClient(){
        byte[] byteIn = new byte[sizeMessage];
        dpIn = new DatagramPacket(byteIn, byteIn.length);
        try {
            ds.receive(dpIn);
            return handlerMessage.processReceivedText(new String(byteIn),
                    dpIn.getAddress(),dpIn.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Add record about client in storage */
    private void createRecordAboutClient(Message message){
        HashMap<String,Object> date = new HashMap<>();
        date.put("address",message.getInetAddress());
        date.put("port",message.getPort());
        clients.put(message.getNameClient(),date);
    }

    /** Send message to one client */
    private void sendPersonally(Message message){
        String recipient = message.getRecipient();
        HashMap recipientDate = clients.get(recipient);
        sendPersonally(recipient,recipientDate,message);
    }

    /** Send message to one client */
    private void sendPersonally(String  recipient,HashMap<String,? extends Object> date,
                                Message message){
        String textOut = handlerMessage.getMessageToSend(recipient,date,message);
        byte[] byteOut = textOut.getBytes();
        sendPersonally(byteOut,(InetAddress)date.get("address"),(Integer)date.get("port"));
    }

    /** Send message to one client */
    private void sendPersonally( byte[] byteOut,InetAddress address,int port){
        dpOut = new DatagramPacket(byteOut,byteOut.length,address,port);
        try {
            ds.send(dpOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Send message to all clients */
    private void sendToAll(Message message){
      clients.forEach((K,V)-> sendPersonally(K,V,message));
    }

    /** Message processing based on code **/
    private void processMessage(Message message){
        CodesMessage codesMessage = message.getCodeMessage();
        if(codesMessage != null) {
            switch (codesMessage) {
                case OK:

                    break;
                case HELLO:
                    createRecordAboutClient(message);
                    byte[] byteOut = handlerMessage.getMessageToSend(CodesMessage.OK,
                            "server",message.getRecipient(),"OK").getBytes();
                    sendPersonally(byteOut,message.getInetAddress(),message.getPort());
                    break;
                case TO_ALL:
                    sendToAll(message);
                    break;
                case PERSONALLY:
                    sendPersonally(message);
                    break;
            }
        }
    }

    /** Start server */
    @Override
    public void start(){
        if (getLocalHost() & creatDatagramSocket()) {
           while (true) {
               Message message = receiverFromClient();
               processMessage(message);
           }
        }
       closeDatagramSocketServer();
    }

    public static void main(String[] args) {
        Server server = new ChatServer(5555,1024);
        server.start();
    }
}
