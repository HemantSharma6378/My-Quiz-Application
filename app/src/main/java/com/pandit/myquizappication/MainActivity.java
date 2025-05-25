package com.pandit.myquizappication;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
TextView TotalQuestionText,QuestionText;
Button ansA,ansB,ansC,ansD,submit;
int score=0;
int correntQuestionIndex=0;
String SelectAnswer="";
String Answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TotalQuestionText=findViewById(R.id.totalquestion);
        QuestionText=findViewById(R.id.Question);
        ansA=findViewById(R.id.ans_A);
        ansB=findViewById(R.id.ans_B);
        ansC=findViewById(R.id.ans_C);
        ansD=findViewById(R.id.ans_D);
        submit=findViewById(R.id.submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submit.setOnClickListener(this);

        loadQuestion();
    }

    @Override
    public void onClick(View v) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);
        Button clickedbtr=(Button) v;
        if(clickedbtr.getId() == R.id.submit){
            if(SelectAnswer.equals(Answer)){
                score++;
            }
            correntQuestionIndex++;
            loadQuestion();
        }else{
            SelectAnswer=clickedbtr.getText().toString();
            clickedbtr.setBackgroundColor(Color.MAGENTA);
        }
    }
    public void loadQuestion(){
        if(correntQuestionIndex == QuestionAnswer.Question.length){
            finishQuiz();
            return;
        }
        TotalQuestionText.setText("Total Question = "+QuestionAnswer.Question.length);
        QuestionText.setText(QuestionAnswer.Question[correntQuestionIndex]);
        ansA.setText(QuestionAnswer.Answeroption[correntQuestionIndex][0]);
        ansB.setText(QuestionAnswer.Answeroption[correntQuestionIndex][1]);
        ansC.setText(QuestionAnswer.Answeroption[correntQuestionIndex][2]);
        ansD.setText(QuestionAnswer.Answeroption[correntQuestionIndex][3]);
        Answer=QuestionAnswer.CurrentAnswer[correntQuestionIndex];
    }


    public void finishQuiz(){
        String passStatus;
        if(score > QuestionAnswer.Question.length*0.60){
            passStatus="Passed";
        }else{
            passStatus="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("score is "+score+" out of "+QuestionAnswer.Question.length)
                .setPositiveButton("Restart",(dialogInterface,i) -> restart())
                .setCancelable(false)
                .show();
    }
    void restart(){
        score=0;
        correntQuestionIndex=0;
        loadQuestion();
    }
}