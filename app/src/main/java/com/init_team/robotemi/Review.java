package com.init_team.robotemi;

public class Review {
    private String comment;
    private int rate_score;
    public Review()
    {
    }
    public Review(int rate_score , String comment)
    {
        this.comment = comment;
        this.rate_score = rate_score;
    }
    public String getComment()
    {
        return comment;
    }

    public int getRate_score() {
        return rate_score;
    }

    public void setRate_score(int rate_score)
    {
        this.rate_score = rate_score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
