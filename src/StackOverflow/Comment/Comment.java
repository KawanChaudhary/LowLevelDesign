package StackOverflow.Comment;

import StackOverflow.AccountManager.Member;
import StackOverflow.Entity.Entity;
import StackOverflow.Photo.Photo;

import java.util.List;

public class Comment extends Entity {

    public Comment(Member commenter, String text, List<Photo> photos){
        super(commenter, text, photos);
    }

}
