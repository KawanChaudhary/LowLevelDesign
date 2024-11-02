package StackOverflow.Entity;

import StackOverflow.AccountManager.Member;
import StackOverflow.Photo.Photo;
import StackOverflow.Status.EntityStatus;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Entity {
    // Entity is an abstract class
    // since we will not be creating an object of this class
    // without specifying if this is a question, answer or comment.

    protected Long id; // there is no need to expose id hence private.
    // id is used to uniquely identify an entity

    protected String text;
    protected LocalDateTime creationDateTime;
    protected LocalDateTime lastUpdated;
    protected Member creator;
    protected List<Photo> photos;
    protected Set<Integer> membersWhoDownvotedThisEntity;
    protected Set<Integer> membersWhoUpvotedThisEntity;
    protected int numberOfUsersReportedThisEntity; // members reported as spam or abuse
    protected EntityStatus status;

    public Entity(Member creator, String text, List<Photo> photos){
        Random rand = new Random();
        this.id = (long) rand.nextInt(1000000);
        status = EntityStatus.DEFAULT;
        this.creator = creator;
        this.text = text;

        this.photos = Objects.requireNonNullElseGet(photos, ArrayList::new);

        membersWhoDownvotedThisEntity = new HashSet<>();
        membersWhoUpvotedThisEntity = new HashSet<>();
        creationDateTime = LocalDateTime.now();
        lastUpdated = LocalDateTime.now();

        numberOfUsersReportedThisEntity = 0;
    }

    public boolean equals(Object that){
        if(that instanceof Entity){
            return Objects.equals(this.id, ((Entity) that).id);
        }
        return false;
    }

    public void upVote(int memberId){
        if(!membersWhoUpvotedThisEntity.contains(memberId)){ // a member cannot upvote a comment that he/she has already upvoted
            if(membersWhoDownvotedThisEntity.contains(memberId)){
                // if the member has down voted this comment in past then upvoting it once just
                // cancels the downvote.
                membersWhoDownvotedThisEntity.remove(memberId);
            } else {
                membersWhoUpvotedThisEntity.add(memberId);
            }
        }
    }

    public void downVote(int memberId){
        if(!membersWhoDownvotedThisEntity.contains(memberId)){ // a member cannot downvote a comment that he/she has already downvoted
            if(membersWhoUpvotedThisEntity.contains(memberId)){
                // if the member has upvoted this comment in past then down voting it once just
                // cancels the upvote.
                membersWhoUpvotedThisEntity.remove(memberId);
            } else {
                membersWhoDownvotedThisEntity.add(memberId);
            }
        }
    }

    // report as abuse or spam
    public void report(){
        numberOfUsersReportedThisEntity++;
    }

    public void updateText(String text){
        this.text = text;
        lastUpdated = LocalDateTime.now();
    }

    public void removePhoto(List<Photo> photosToBeDeleted){
        photos.removeAll(photosToBeDeleted);
        lastUpdated = LocalDateTime.now();
    }

    public void addPhotos(List<Photo> newPhotosToBeAdded){
        photos.addAll(newPhotosToBeAdded);
        lastUpdated = LocalDateTime.now();
    }

    public int getUpvoteCount(){
        return membersWhoUpvotedThisEntity.size();
    }

    public int getDownvoteCount(){
        return membersWhoDownvotedThisEntity.size();
    }

    public Member getCreator(){
        return creator;
    }

    public void delete(){ // Admin can delete an entity
        status = EntityStatus.DELETED;
    }

    public Long getId(){
        return id;
    }

    public String getText(){
        return text;
    }

    public LocalDateTime getCreationDateTime(){
        return creationDateTime;
    }

    public LocalDateTime getLastUpdated(){
        return lastUpdated;
    }

    public List<Photo> getPhotos(){
        return photos;
    }

    public Set<Integer> getMembersWhoDownvotedThisEntity(){
        return membersWhoDownvotedThisEntity;
    }

    public Set<Integer> getMembersWhoUpvotedThisEntity(){
        return membersWhoUpvotedThisEntity;
    }

    public int getVoteCount(){
        return getUpvoteCount() - getDownvoteCount();
    }

    public int getNumberOfUsersReportedThisEntity(){
        return numberOfUsersReportedThisEntity;
    }

    public EntityStatus getStatus(){
        return status;
    }

    public void setEntityStatus(EntityStatus newStatus){
        this.status = newStatus;
    }

}