package StackOverflow.AccountManager;

public class User {
    private final int guestId;
    private final String searchObj;

    public User(int guestId, String searchObj){
        this.guestId = guestId;
        this.searchObj = searchObj;
    }

    public int getGuestId(){
        return guestId;
    }
    public String getSearchObj(){
        return searchObj;
    }
}
