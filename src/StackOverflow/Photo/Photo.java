package StackOverflow.Photo;

import StackOverflow.AccountManager.Member;

import java.time.LocalDateTime;
import java.util.Random;

public class Photo {
    private final long id;
    private String path;
    private final LocalDateTime creationOn;
    private LocalDateTime modifiedOn;
    private final Member author;

    public Photo(String path, Member author){
        Random rand = new Random();
        this.id = rand.nextInt(1000000);
        this.path = path;
        this.creationOn = LocalDateTime.now();
        this.modifiedOn = LocalDateTime.now();
        this.author = author;
    }

    public long getId(){
        return id;
    }

    public String getPath(){
        return path;
    }

    public LocalDateTime getCreationOn(){
        return creationOn;
    }

    public LocalDateTime getModifiedOn(){
        return modifiedOn;
    }

    public Member getAuthor(){
        return author;
    }

    public void setPhotoPath(String newPath){
        path = newPath;
        setModifiedOn();
    }

    public void setModifiedOn(){
        modifiedOn = LocalDateTime.now();
    }
}
