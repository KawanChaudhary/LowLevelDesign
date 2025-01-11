package StackOverflow.QuestionManager;

import StackOverflow.AccountManager.Member;
import StackOverflow.Answer.Answer;
import StackOverflow.Comment.Comment;
import StackOverflow.Input.Input;
import StackOverflow.Photo.Photo;
import StackOverflow.Status.EntityStatus;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class QuestionManager {
    private final Map<Long, Question> questions;
    private final Input getInput;

    public QuestionManager(){
        this.questions = new ConcurrentHashMap<>();
        this.getInput = new Input();
    }

    public Question getQuestion(Long id){
        return questions.get(id);
    }

    public void createQuestion(Member askingMember){
        String title = getInput.getValidatedInput(
                "Enter question title: ",
                input -> !input.isEmpty(),
                "Title cannot be empty."
        );

        String text = getInput.getValidatedInput(
                "Enter question text: ",
                input -> !input.isEmpty(),
                "Text cannot be empty."
        );

        Set<String> tags = getTagsInput(new HashSet<>());
        Question newQuestion = new Question(askingMember, title, text, getPhotos(askingMember), tags);
        questions.put(newQuestion.getId(), newQuestion);
        System.out.println("Question created successfully!");
    }

    private Set<String> getTagsInput(Set<String> tags){
        while (tags.size() < 5) {
            String tag = getInput.getValidatedInput(
                    "Enter a tag (or type 'done' to finish): ",
                    input -> !input.isEmpty(),
                    "Tag cannot be empty."
            ).toLowerCase();
            tags.add(tag);
        }
        return tags;
    }

    // Main function that combines both title and tag matching
    public List<Question> getMatchingQuestions() {
        String searchTitle = getInput.getValidatedInput(
                "Enter question title: ",
                input -> !input.isEmpty(),
                "Title cannot be empty."
        );
        List<Question> titleMatches = getTitleMatchQuestions(searchTitle);
        List<Question> tagMatches = getTagMatchQuestions(getTagsInput(new HashSet<>()));

        // Return the intersection of both lists
        Set<Question> intersection = new HashSet<>(titleMatches);
        intersection.retainAll(tagMatches);

        // Printing questions after matching
        for(Question q: intersection){
            System.out.println("--- " + q.getTitle());
        }

        return new ArrayList<>(intersection);
    }

    private List<Question> getTitleMatchQuestions(String searchTitle) {
        Set<String> searchWords = Set.of(searchTitle.toLowerCase().split("\\s+"));

        return questions.values().parallelStream()
                .filter(question -> calculateTitleMatchPercentage(question.getTitle(), searchWords) >= 30.0)
                .collect(Collectors.toList());
    }

    // Function to get questions with at least one matching tag
    private List<Question> getTagMatchQuestions(Set<String> requiredTags) {
        return questions.values().parallelStream()
                .filter(question -> getTagMatchCount(question.getTags(), requiredTags) > 0)
                .sorted((q1, q2) -> {
                    // Sort by the number of matching tags in descending order
                    long q1TagMatchCount = getTagMatchCount(q1.getTags(), requiredTags);
                    long q2TagMatchCount = getTagMatchCount(q2.getTags(), requiredTags);
                    return Long.compare(q2TagMatchCount, q1TagMatchCount);
                })
                .collect(Collectors.toList());
    }

    private double calculateTitleMatchPercentage(String title, Set<String> searchWords) {
        Set<String> titleWords = Set.of(title.toLowerCase().split("\\s+"));

        long matchCount = titleWords.stream().filter(searchWords::contains).count();

        // Calculate percentage of search words found in title
        return (matchCount * 100.0) / searchWords.size();
    }

    private long getTagMatchCount(Set<String> questionTags, Set<String> requiredTags) {
        return questionTags.stream().filter(requiredTags::contains).count();
    }

    public void addAnswer(Long questionId, Member askingMember){
        Question q = getQuestion(questionId);
        String answerText = getInput.getValidatedInput(
                "Enter the text: ",
                input -> !input.isEmpty(),
                "Answer cannot be empty."
        );
        Answer answer = new Answer(askingMember, answerText, getPhotos(askingMember));

        q.addAnswer(answer);
    }

    public void markAnswerAsSolution(Long questionId, Long answerId){
        Question q = getQuestion(questionId);
        Answer answer = q.getAnswerById(answerId);
        answer.markAsASolution();
        q.setEntityStatus(EntityStatus.CLOSED);
    }

    public void addCommentOnQuestion(Long questionId, Member askingMember){
        Question q = getQuestion(questionId);
        Comment newComment = new Comment(askingMember, "new comment", getPhotos(askingMember));
        q.addComment(newComment);
    }

    // this can be simplified by making an answer manager:
    public void addCommentOnAnswer(Long questionId, Long answerId, Member askingMember){
        Question q = getQuestion(questionId);
        Answer ans = q.getAnswerById(answerId);
        Comment newComment = new Comment(askingMember, "new comment", getPhotos(askingMember));
        ans.addComment(newComment);
    }

    private List<Photo> getPhotos(Member askingMember){
        List<Photo> photos = new ArrayList<>();

        // Added photos
        photos.add(new Photo("photo/1", askingMember));
        photos.add(new Photo("photo/3", askingMember));

        return photos;
    }

    public void upVoteQuestion(Long questionId, Member askingMember){
        Question q = getQuestion(questionId);
        q.upVote(askingMember.getAccount().getId());
    }
    // this can be simplified by making an answer manager:
    public void upVoteAnswer(Long questionId, Long answerId, Member askingMember){
        Question q = getQuestion(questionId);
        Answer ans = q.getAnswerById(answerId);
        ans.upVote(askingMember.getAccount().getId());
    }

    // this can be simplified by making a comment manager:
    public void upVoteComment(Long questionId, Long answerId, Long commentId, Member askingMember){
        Question q = getQuestion(questionId);
        Answer ans = q.getAnswerById(answerId);
        Comment comment = ans.getCommentById(commentId);
        comment.upVote(askingMember.getAccount().getId());
    }

    public void downVoteQuestion(Long questionId, Member askingMember) {
        Question q = getQuestion(questionId);
        q.downVote(askingMember.getAccount().getId());
    }

    // This can be simplified by making an answer manager:
    public void downVoteAnswer(Long questionId, Long answerId, Member askingMember) {
        Question q = getQuestion(questionId);
        Answer ans = q.getAnswerById(answerId);
        ans.downVote(askingMember.getAccount().getId());
    }

    // This can be simplified by making a comment manager:
    public void downVoteComment(Long questionId, Long answerId, Long commentId, Member askingMember) {
        Question q = getQuestion(questionId);
        Answer ans = q.getAnswerById(answerId);
        Comment comment = ans.getCommentById(commentId);
        comment.downVote(askingMember.getAccount().getId());
    }

}
