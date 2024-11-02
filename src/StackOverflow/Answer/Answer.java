package StackOverflow.Answer;

import StackOverflow.AccountManager.Member;
import StackOverflow.Comment.Comment;
import StackOverflow.Entity.Entity;
import StackOverflow.Photo.Photo;

import java.util.ArrayList;
import java.util.List;

public class Answer extends Entity {

    private boolean solvedProblem; // is this answer a proper solution to the question ?
    private final List<Comment> comments;

    public Answer(Member creatingMember, String text, List<Photo> photos){
        super(creatingMember, text, photos);
        comments = new ArrayList<>();
    }

    public void markAsASolution(){
        solvedProblem = true;
    }

    public void addComment(Comment newComment){
        comments.add(newComment);
    }

    public boolean isSolvedProblem(){
        return solvedProblem;
    }

    public List<Comment> getComments(){
        return comments;
    }

}
