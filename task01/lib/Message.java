package part01.lesson10.task01.lib;

import java.net.InetAddress;

/**
 * @author Oleg_Chapurin
 */
public interface Message {
    /** Get cod message */
    CodesMessage getCodeMessage();
    /** Get name sender message */
    String getNameClient();
    /** Get recipient */
    String getRecipient();
    /** Get date */
    String getText();
    /** Get IP or DNS */
    InetAddress getInetAddress();
    /** Get port sender */
    int getPort();
}
