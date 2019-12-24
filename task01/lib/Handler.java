package part01.lesson10.task01.lib;

import java.net.InetAddress;
import java.util.HashMap;

/**
 * Converts the text of the message
 *
 * @author Oleg_Chapurin
 */
public interface Handler {
    /** Get code message of text */
    CodesMessage getCodeMessage(String text);
    /** Get name client message of text */
    String getNameMessage(String text);
    /** Get recipient message of text */
    String getRecipientMessage(String text);
    /** Get date message of text */
    String getDateMessage(String text);
    /** Creat message to send */
    String getMessageToSend(CodesMessage code,String name,
                            String recipient,String text);
    /** Creat message to send */
    String getMessageToSend(String  recipient,
                            HashMap<String,? extends Object> date,
                            Message message);
    /** Get object message of received text */
    Message processReceivedText(String text,
                                InetAddress address, int port);
}
