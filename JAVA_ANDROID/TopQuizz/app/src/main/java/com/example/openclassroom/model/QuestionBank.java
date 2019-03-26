package com.example.openclassroom.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QuestionBank {

    // Liste de questions
    private List<Question> mQuestionList;
    // Identifiant des questions
    private int mIdQuestion;


    public QuestionBank(List<Question> questionList) {
        mQuestionList = questionList;
        // Mélange l'ordre des questions
        Collections.shuffle(mQuestionList);
        // On commence part la première
        mIdQuestion = 0;
    }


    public Question getQuestion() {
       // Retourne une question par question
        return mQuestionList.get(mIdQuestion++);
    }
}
