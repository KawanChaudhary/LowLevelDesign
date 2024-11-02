package StackOverflow.QuestionManager;

import StackOverflow.AccountManager.Member;
import StackOverflow.Answer.Answer;
import StackOverflow.Comment.Comment;
import StackOverflow.Entity.Entity;
import StackOverflow.Photo.Photo;
import StackOverflow.Status.EntityStatus;

import java.util.*;

public class Question extends Entity {

    private String title;
    private final Set<String> tags;
    private final List<Comment> comments;
    private final List<Answer> answers;

    public Question(Member askingMember, String title, String text, List<Photo> photos, Set<String> tags){

        super(askingMember, text, photos);
        status = EntityStatus.OPEN;
        this.title = title;
        this.tags = tags;
        comments = new ArrayList<>();
        answers = new ArrayList<>();
    }

    // Question Asker or Admin can close a question due to various reasons like a solution has been found or due to inactivity of users or certain other reasons
    public void close(){
        status = EntityStatus.CLOSED;
    }

    public void addComment(Comment newComment){
        comments.add(newComment);
    }

    public void addAnswer(Answer newAnswer){
        answers.add(newAnswer);
    }

    public void addTag(String tag){
        if(tags.size() < 5) tags.add(tag);
        else System.out.println("Maximum 5 tags allowed.");
    }

    public String getTitle(){
        return title;
    }

    public Set<String> getTags(){
        return tags;
    }

    public List<Comment> getComments(){
        return comments;
    }

    public List<Answer> getAnswers(){
        return answers;
    }

    public void setTitle(String newTitle){
        title = newTitle;
    }

    public Answer getAnswerById(Long id) {
        for (Answer answer : answers) {
            if (answer.getId().equals(id)) {
                return answer;
            }
        }
        return null;
    }

}
