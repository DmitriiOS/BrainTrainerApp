package ru.startandroid.braintrainerapp;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button easyLevelButton;
    Button hardLevelButton;

    TextView titleTextView;
    int locationCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    Boolean levelEasy;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timerTextView;
    TextView scoreTextView;
    TextView taskTextView;
    TextView resultTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    ConstraintLayout startLayout;

    ArrayList<Integer> answers = new ArrayList<>();

    public void playAgainClicked(View view) {

        startLayout.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }

    public void playAgain () {

        startLayout.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);

        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        newQuestion();
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000-1)+ "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("GAME OVER\nRight answers: " + Integer.toString(score));
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();


    }

    public void answerChosen(View view) {
        if (Integer.toString(locationCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("CORRECT");
            score++;
        } else {
            resultTextView.setText("WRONG");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }


    public void easyLevelClicked(View view) {

        levelEasy = true;
        playAgain();

    }
    public void hardLevelClicked(View view) {

        levelEasy = false;
        playAgain();

    }

    public void newQuestion() {
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        locationCorrectAnswer = random.nextInt(4);

        if (levelEasy) {
        taskTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        answers.clear();

        for (int i=0; i<4; i++) {
            if (i == locationCorrectAnswer) {
                answers.add(a+b);
            } else {
                int wrongAnswer = random.nextInt(41);
                while (wrongAnswer == a + b) {
                    wrongAnswer = random.nextInt(41);
                }

                answers.add(wrongAnswer);
            }
        }
        } else {
            taskTextView.setText(Integer.toString(a) + " * " + Integer.toString(b));

            answers.clear();

            for (int i=0; i<4; i++) {
                if (i == locationCorrectAnswer) {
                    answers.add(a*b);
                } else {
                    int wrongAnswer = random.nextInt(161);
                    while (wrongAnswer == a * b) {
                        wrongAnswer = random.nextInt(161);
                    }

                    answers.add(wrongAnswer);
                }
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        easyLevelButton = findViewById(R.id.easyLevelButton);
        hardLevelButton = findViewById(R.id.hardLevelButton);

        titleTextView = findViewById(R.id.titleTextView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        taskTextView = findViewById(R.id.taskTextView);
        resultTextView = findViewById(R.id.resultTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        startLayout = findViewById(R.id.startLayout);
        gameLayout = findViewById(R.id.gameLayout);

        startLayout.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}
