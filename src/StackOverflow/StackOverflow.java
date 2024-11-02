package StackOverflow;

import StackOverflow.AccountManager.AccountManager;
import StackOverflow.AccountManager.Member;
import StackOverflow.QuestionManager.QuestionManager;

public class StackOverflow {
    private final AccountManager accountManager;
    private final QuestionManager questionManager;

    public StackOverflow(){
        this.accountManager = new AccountManager();
        this.questionManager = new QuestionManager();
    }

    public void addNewMember(){
        accountManager.addUser();
    }

    public void addNewQuestion(Member askingMember){
        questionManager.createQuestion(askingMember);
    }

    public void searchQuestion(){
        questionManager.getMatchingQuestions();
    }

}
