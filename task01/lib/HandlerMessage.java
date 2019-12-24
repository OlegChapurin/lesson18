package part01.lesson10.task01.lib;

import java.net.InetAddress;
import java.util.HashMap;

/**
 * Converts the text of the message in accordance with the template
 *
 * @author Oleg_Chapurin
 */
public class HandlerMessage implements Handler{

    private String template1 = "{code:";
    private String template2 = ",name:";
    private String template3 = ",recipient:";
    private String template4= ",date:";
    private String template5 = "}";

    /** Get code message of text */
    @Override
    public CodesMessage getCodeMessage(String text){
        int indexCode  = text.indexOf("code:") + 5;
        int indexEndCode  = text.indexOf(",name");
        return CodesMessage.getEnumByCode(text.substring(indexCode,indexEndCode).trim());
    }

    /** Get name client message of text */
    @Override
    public String getNameMessage(String text){
        int indexName  = text.indexOf("name:") + 5;
        int indexEndName  = text.indexOf(",recipient");
        return text.substring(indexName,indexEndName).trim();
    }

    /** Get recipient message of text */
    @Override
    public String getRecipientMessage(String text){
        int indexCode  = text.indexOf("recipient:") + 10;
        int indexEndCode  = text.indexOf(",date");
        return text.substring(indexCode,indexEndCode).trim();
    }

    /** Get date message of text */
    @Override
    public String getDateMessage(String text){
        int indexDate  = text.indexOf("date:") + 5;
        int indexEndDate  = text.indexOf("}");
        return text.substring(indexDate,indexEndDate).trim();
    }

    /** Creat message to send */
    @Override
    public String getMessageToSend(CodesMessage code,String name,
                                                String recipient,String text){
        return template1.concat(code.getCode()).concat(template2).concat(name).
                concat(template3).concat(recipient).concat(template4).concat(text).
                concat(template5);
    }

    /** Creat message to send */
    @Override
    public String getMessageToSend(String  recipient,
                                                HashMap<String,? extends Object> date,
                                                Message message){
        return getMessageToSend(message.getCodeMessage(),
                message.getNameClient(),recipient,message.getText());
    }

    /** Get object message of received text */
    @Override
    public Message processReceivedText(String text,
                                                          InetAddress address, int port){
        Message message = new ReceiveMessage(getCodeMessage(text),getNameMessage(text),
                getRecipientMessage(text),getDateMessage(text),address,port);

        return message;
    }
}
