import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Comparable<Message>{

    private String receiverNo;
    private String msgContent;
    private String messageId;
    private boolean status;
    private LocalDateTime dateTime;
    static int count = 0;
    
    Message( String receiverNo, String msgContent,boolean status) {

        this.receiverNo = String.format("%-12s", receiverNo.length() > 12 ?
                receiverNo.substring(0, 12) : receiverNo);
        this.msgContent = msgContent;
        this.status = status;
        this.messageId = String.format("%03d",++count);
        this.dateTime = LocalDateTime.now();

    }


    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }
    
    public String getReceiverNo() {
        return receiverNo;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getFormattedDateTime() {
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
        return dateTime.format(myFormat);
    }

    @Override
    public String toString() {

        return "Id: " + messageId +
               ", Receiver: " + getReceiverNo() +
               ", Message: " + msgContent +
               ", Status: " + status + 
               ", Time: " + getFormattedDateTime();
    }

    @Override
    public int compareTo(Message o) {
        return Integer.parseInt(this.messageId) > Integer.parseInt(o.messageId) ? -1 : 1;
    }
}
