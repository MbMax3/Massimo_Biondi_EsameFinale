package com.example.testfinalemb;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    public static final String QUIZ_1 = "Quiz 1";
    public static final String QUIZ_2 = "Quiz 2";
    public static final String QUIZ_3 = "Quiz 3";
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private int answerNr;
    private String quizn;
    public Question() {
    }
    public Question(String question, String option1, String option2,
                    String option3, int answerNr, String quizn) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answerNr = answerNr;
        this.quizn = quizn;
    }
    protected Question(Parcel in) {
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        answerNr = in.readInt();
        quizn = in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeInt(answerNr);
        dest.writeString(quizn);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }
        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getOption1() {
        return option1;
    }
    public void setOption1(String option1) {
        this.option1 = option1;
    }
    public String getOption2() {
        return option2;
    }
    public void setOption2(String option2) {
        this.option2 = option2;
    }
    public String getOption3() {
        return option3;
    }
    public void setOption3(String option3) {
        this.option3 = option3;
    }
    public int getAnswerNr() {
        return answerNr;
    }
    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }
    public String getquizn() {
        return quizn;
    }
    public void setquizn(String difficulty) {
        this.quizn = difficulty;
    }
    public static String[] getAllDifficultyLevels() {
        return new String[]{
                QUIZ_1,
                QUIZ_2,
                QUIZ_3
        };
    }
}