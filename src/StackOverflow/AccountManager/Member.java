package StackOverflow.AccountManager;

import StackOverflow.Entity.Entity;
import StackOverflow.QuestionManager.Question;
import StackOverflow.Status.AccountStatus;

public class Member {
    private final Account account;
    private boolean isModerator;
    private boolean isAdmin;

    public Member(int id, String email, String name, String displayName, String password){
        account = new Account(id, email, name, displayName, password);
        this.isModerator = false;
        this.isAdmin = false;
    }

    public void promoteToAdmin(){
        isAdmin = true;
    }

    public void promoteToModerator(){
        isModerator = true;
    }

    public void block(){
        account.setAccountStatus(AccountStatus.BLOCKED);
    }

    public void closed(){
        account.setAccountStatus(AccountStatus.CLOSED);
    }

    public boolean blockMember(Member member){
        if(isAdmin){
            member.block();
        }
        return false;
    }

    public boolean unblockMember(Member member){
        if(isAdmin){
            member.account.setAccountStatus(AccountStatus.ACTIVE);
        }
        return false;
    }

    public Account getAccount(){
        return account;
    }

    public boolean closeQuestion(Question question){
        // only moderator, admin or creator of the question can close a question
        if(isAdmin || isModerator || account.getId() == question.getCreator().account.getId()){
            question.close();
        }
        return false;
    }

    public boolean deleteEntity(Entity entity){
        // admin of the question can close a question
        if(isAdmin){
            entity.delete();
        }
        return false;
    }

    public void handleReputation(int reputation){
        account.setReputation(reputation);
    }

}
