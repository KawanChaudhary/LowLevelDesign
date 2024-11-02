package StackOverflow.AccountManager;

import StackOverflow.Status.AccountStatus;

public class Account {
    private final int id;
    private final String email;
    private final String name;
    private String displayName;
    private final String password;
    private AccountStatus accountStatus;
    private int reputation;

    public Account(int id, String email, String name, String displayName, String password){
        this.id = id;
        this.email = email;
        this.name = name;
        this.displayName = displayName;
        this.password = password;
        reputation = 1;
        accountStatus = AccountStatus.ACTIVE;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDisplayName(){
        return displayName;
    }

    public AccountStatus getAccountStatus(){
        return accountStatus;
    }

    public int getReputation(){
        return reputation;
    }

    protected void setDisplayName(String newDisplayName){
        this.displayName = newDisplayName;
    }

    protected void setAccountStatus(AccountStatus status){
        this.accountStatus = status;
    }

    protected void setReputation(int changes){
        reputation += changes;
    }
}
