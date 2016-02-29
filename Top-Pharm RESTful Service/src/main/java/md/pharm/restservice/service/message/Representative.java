package md.pharm.restservice.service.message;

/**
 * Created by Andrei on 1/6/2016.
 */
public class Representative {

    private Integer id;
    private String representativeName;
    private Integer messagesNumber;
    private Integer messagesUnread;
    private boolean hasUnreadMessages;
    private Integer ongoingActivityID;
    private String representativePhone1;
    private String representativeAddress;

    public Representative(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    public Integer getMessagesNumber() {
        return messagesNumber;
    }

    public void setMessagesNumber(Integer messagesNumber) {
        this.messagesNumber = messagesNumber;
    }

    public boolean isHasUnreadMessages() {
        return hasUnreadMessages;
    }

    public void setHasUnreadMessages(boolean hasUnreadMessages) {
        this.hasUnreadMessages = hasUnreadMessages;
    }

    public Integer getMessagesUnread() {
        return messagesUnread;
    }

    public void setMessagesUnread(Integer messagesUnread) {
        this.messagesUnread = messagesUnread;
    }

    public Integer getOngoingActivityID() {
        return ongoingActivityID;
    }

    public void setOngoingActivityID(Integer ongoingActivityID) {
        this.ongoingActivityID = ongoingActivityID;
    }

    public String getRepresentativePhone1() {
        return representativePhone1;
    }

    public void setRepresentativePhone1(String representativePhone1) {
        this.representativePhone1 = representativePhone1;
    }

    public String getRepresentativeAddress() {
        return representativeAddress;
    }

    public void setRepresentativeAddress(String representativeAddress) {
        this.representativeAddress = representativeAddress;
    }
}
