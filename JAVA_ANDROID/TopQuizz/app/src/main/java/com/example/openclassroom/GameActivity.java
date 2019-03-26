package com.example.openclassroom;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.openclassroom.model.Question;
import com.example.openclassroom.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextQuestion;

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestionAnswer;
    private int mNumberOfQuestions;
    private int mScore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mNumberOfQuestions = 3;
        mScore = 0;

        // Ajoute les questions dans QuestionBank
        mQuestionBank = insertQuestion();

        mTextQuestion = findViewById(R.id.question);
        mButton1 = findViewById(R.id.activity_game_answer1_btn);
        mButton2 = findViewById(R.id.activity_game_answer2_btn);
        mButton3 = findViewById(R.id.activity_game_answer3_btn);
        mButton4 = findViewById(R.id.activity_game_answer4_btn);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);

        // ?
        mCurrentQuestionAnswer = mQuestionBank.getQuestion();
        displayQuestion(mCurrentQuestionAnswer);

    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();

        if (responseIndex == mCurrentQuestionAnswer.getAnswerIndex()) {
            // Good answer
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            // Wrong answer
            Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
            //}

        }
        if(--mNumberOfQuestions == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Well done!")
                    .setMessage("Your score is " + mScore + " " + mNumberOfQuestions)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).create().show();
        }else{
            mCurrentQuestionAnswer = mQuestionBank.getQuestion();
            displayQuestion(mCurrentQuestionAnswer);
        }

    }

    /**
     * Affiche la question
     * Affiche dans les boutons les réponses
     * Autant de bouton que de réponse
     * @param question
     */
   public void displayQuestion(final Question question) {
        // Set the text for the question text view and the four buttons
        mTextQuestion.setText(question.getQuestion());
        mButton1.setText(question.getChoiceList().get(0));
        mButton2.setText(question.getChoiceList().get(1));
        mButton3.setText(question.getChoiceList().get(2));
        mButton4.setText(question.getChoiceList().get(3));
    }

    /**
     *
     * @return la liste des questions au constructeur QuestionBank
     */
    public QuestionBank insertQuestion(){
        Question question1 = new Question("Who is the creator of Android?",
                Arrays.asList("Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith",
                        "Gerard"),
                0);
        Question question2 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958",
                        "1962",
                        "1967",
                        "1875",
                        "1969"),
                1);
        Question question3 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42",
                        "101",
                        "666",
                        "154",
                        "742"),
                2);
        return new QuestionBank(Arrays.asList(question1, question2, question3));
    }


}
