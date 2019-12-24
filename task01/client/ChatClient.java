package part01.lesson10.task01.client;

import part01.lesson10.task01.lib.CodesMessage;
import part01.lesson10.task01.lib.Handler;
import part01.lesson10.task01.lib.HandlerMessage;
import part01.lesson10.task01.utils.ReadWriteConsole;
import java.io.IOException;
import java.net.*;

/**
 * Client
 *
 * @author Oleg_Chapurin
 */
public class ChatClient implements Client {

    private String nameClient;
    private int portServer;
    private DatagramSocket ds;
    private String inetAddressServer;
    private InetAddress inetAddress;
    private byte[] byteOut;
    private DatagramPacket dpIn;
    private DatagramPacket dpOut;
    private Handler handlerMessage = new HandlerMessage();
    private static String instruction = "******* Message Examples *******\n " +
            "** send all enter text \n   ttttttttttt ttttttttttt tttt. \n " +
            "** send to another chat user  enter name user:text \n    " +
            "user: tttttttttttt tttttttttttttt.\n";

    /**
     *
     * @param nameClient name client
     * @param ipNameDNSServer IP or DNS server
     * @param portServer port server
     * @param sizeMessage message size in bytes
     */
    public ChatClient(String nameClient, String ipNameDNSServer, int portServer, int sizeMessage) {
        this.nameClient = nameClient;
        this.inetAddressServer = ipNameDNSServer;
        this.portServer = portServer;
        this.byteOut = new byte[sizeMessage];
    }

    /** Creat DatagramSocket */
    private boolean creatDatagramSocket() {
        try {
            this.ds = new DatagramSocket();
            return true;
        } catch (SocketException e) {
            ReadWriteConsole.writeConsole(e.getMessage() + "\n");
            ReadWriteConsole.writeConsole("Client no start \n");
            return false;
        }
    }

    /** Close DatagramSocket */
    private void closeDatagramSocket() {
        this.ds.close();
    }

    /** Open socket */
    private boolean openSocket() {
        boolean marker = false;
        while (!marker) {
            ReadWriteConsole.writeConsole("Start client yes / no \n");
            if ("yes".equals(ReadWriteConsole.readConsoleString().trim())) {
                marker = creatDatagramSocket();
            } else {
                break;
            }
        }
        return marker;
    }

    /** Get local address */
    private boolean getLocalHost() {
        boolean marker = false;
        while (!marker) {
            try {
                this.inetAddress = InetAddress.getLocalHost();
                marker = true;
            } catch (UnknownHostException e) {
                ReadWriteConsole.writeConsole(e.getMessage());
                ReadWriteConsole.writeConsole("Error get localHost");
                ReadWriteConsole.writeConsole("Start get localHost yes / no");
                if (!"yes".equals(ReadWriteConsole.readConsoleString().trim())) {
                    break;
                }
            }
        }
        return marker;
    }

    /** Send message on server */
    private void sendOnServer(byte[] bIn) {
        try {
            dpIn = new DatagramPacket(bIn, bIn.length, InetAddress.getByName(inetAddressServer), portServer);
            ds.send(dpIn);
        } catch (IOException e) {
            ReadWriteConsole.writeConsole("Error:-> ".concat(e.getMessage()));
        }
    }

    /** Receiver message from server */
    private String receiveFromServer() {
        dpOut = new DatagramPacket(byteOut, byteOut.length);
        try {
            ds.receive(dpOut);
            return new String(byteOut);
        } catch (IOException e) {
            ReadWriteConsole.writeConsole("Error:-> ".concat(e.getMessage()));
        }
        return null;
    }

    /** Form message to send */
    private void formMessageToSend(String text) {
        String recipient = "";
        CodesMessage codesMessage = CodesMessage.TO_ALL;
        int index = text.indexOf(":");
        if (index > 0) {
            recipient = text.substring(0, index);
            text = text.substring(index + 1, text.length());
            codesMessage = CodesMessage.PERSONALLY;
        }
        byte[] bIn = handlerMessage.getMessageToSend(codesMessage, nameClient, recipient, text).getBytes();
        sendOnServer(bIn);
    }

    /** Form message to send */
    private void formMessageToSend(CodesMessage codesMessage, String text) {
        String recipient = "";
        byte[] bIn = handlerMessage.getMessageToSend(codesMessage, nameClient, recipient, text).getBytes();
        sendOnServer(bIn);
    }

    /** Print message from server */
    private void printMessage() {
        String receiverText = receiveFromServer();
        if (receiverText != null) {
            ReadWriteConsole.writeConsole(handlerMessage.getNameMessage(receiverText) +
                    ":-> ".concat(handlerMessage.getDateMessage(receiverText)));
        }
    }

    /** Register on server **/
    private boolean registerOnServer() {
        for (int i = 0; i < 5; i++) {
            formMessageToSend(CodesMessage.HELLO, nameClient);
            String receiverText = receiveFromServer();
            int indexCode = receiverText.indexOf("code:") + 5;
            int indexEndCode = receiverText.indexOf(",name");
            if (CodesMessage.OK.getCode().
                    equals(receiverText.substring(indexCode, indexEndCode))) {
                return true;
            }
        }
        return false;
    }

    /** Start client */
    @Override
    public void start() {
        ReadWriteConsole.newFlowRead();
        ReadWriteConsole.newFlowWrite();
        if (getLocalHost() & openSocket()) {
            if (registerOnServer()) {
                ReadWriteConsole.writeConsole("Client yes start");
                ReadWriteConsole.writeConsole("Close client enter quit");
                ReadWriteConsole.writeConsole(instruction);
                String text;
                while (true) {
                    text = ReadWriteConsole.readConsoleString().trim();
                    if ("quit".equals(text)) {
                        break;
                    }
                    if (text.length() > 0) {
                        formMessageToSend(text);
                    }
                    printMessage();
                }
            }
        }
        ReadWriteConsole.closeAllFlow();
        closeDatagramSocket();
    }

    public static void main(String[] args) {
        Client client = new ChatClient("client1","127.0.0.1", 5555, 1024);
        client.start();
    }
}
