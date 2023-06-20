package entities;

import java.io.Serializable;
import java.util.List;

public class Quiz implements Serializable {
    private int id;
    private List<Question> questions;
    private int groupId;
    private int score;

    public Quiz(){}

    public Quiz(int groupId, int score) {
        this.groupId = groupId;
        this.score = score;
    }

    public Quiz(int id, List<Question> questions, int groupId, int score) {
        this.id = id;
        this.questions = questions;
        this.groupId = groupId;
        this.score = score;
    }
    public Quiz(int id, List<Question> questions, int score) {
        this.id = id;
        this.questions = questions;
        this.score = score;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }


}
