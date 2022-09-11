package main.java;

public class Screen {
    private int questionOn;
    private int points;

    public void setQuestion(int question) {
        this.questionOn = question;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getQuestion() {
        return questionOn;
    }

    public int getPoints() {
        return points;
    }

}
