package part01.lesson10.task01.lib;

import java.net.InetAddress;

/**
 * @author Oleg_Chapurin
 */
public class ReceiveMessage implements Message {

    private CodesMessage code;
    private String nameClient;
    private String recipient;
    private String text;
    private InetAddress inetAddress;
    private int port;

    /**
     *
     * @param code code message
     * @param nameClient name client
     * @param recipient recipient
     * @param text date
     * @param inetAddress IP or DNS
     * @param port port client
     */
    public ReceiveMessage(CodesMessage code, String nameClient,
                          String recipient, String text, InetAddress inetAddress, int port) {
        this.code = code;
        this.nameClient = nameClient;
        this.recipient = recipient;
        this.text = text;
        this.inetAddress = inetAddress;
        this.port = port;
    }

    /** Get cod message */
    @Override
    public CodesMessage getCodeMessage() {
        return this.code;
    }

    /** Get name sender message */
    @Override
    public String getNameClient() {
        return this.nameClient;
    }

    /** Get recipient */
    @Override
    public String getRecipient() {
        return this.recipient;
    }

    /** Get date */
    @Override
    public String getText() {
        return this.text;
    }

    /** Get IP or DNS */
    @Override
    public InetAddress getInetAddress() {
        return this.inetAddress;
    }

    /** Get port sender */
    @Override
    public int getPort() {
        return this.port;
    }
}
