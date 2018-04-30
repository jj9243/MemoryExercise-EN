package org.androidtown.memoryexcercise;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.Random;

public class NumRemember extends AppCompatActivity {

    //클래스
    CountDownTimer timer;
    CountDownTimer finishTimer;

    //텍스트 뷰
    TextView scoreText;
    TextView randNumText;
    TextView timerText;
    TextView countDown;


    //변수

    SharedPreferences randNumPref;
    SharedPreferences roundPref;
    SharedPreferences timesPref;
    SharedPreferences scorePref;

    int timerValue;
    int randomNumber;
    static int round = 10;
    int times;
    int score;

    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_remember);

        //클래스
//        roundPref = getSharedPreferences("round",MODE_PRIVATE);
//        round = roundPref.getInt("round",1);
//
//        timesPref = getSharedPreferences("times",MODE_PRIVATE);
//        times = timesPref.getInt("times",1);
//
//        scorePref = getSharedPreferences("score",MODE_PRIVATE);
//        score = scorePref.getInt("score",0);

//        RandomNumber randomNum = new RandomNumber();
//        randomNumber = randomNum.getNumber(round);
        if(round > 1000000) {
            round /= 100;
        }
        random = new Random();
        randomNumber = random.nextInt(round);


        randNumPref = getSharedPreferences("randNum", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = randNumPref.edit();
        editor2.putInt("randNum", randomNumber);
        editor2.commit();

        //텍스트 뷰
//        scoreText = (TextView) findViewById(R.id.scoreText);
        randNumText = (TextView) findViewById(R.id.randNumText);
        timerText = (TextView) findViewById(R.id.timerText);
        countDown = (TextView) findViewById(R.id.countdown);

//        if(round==3 && times==3) {
//            randNumText.setText("준비된 게임이 끝났습니다");
//            SharedPreferences.Editor editor3 = roundPref.edit();
//            editor3.putInt("round", 1);
//            editor3.commit();
//
//            finishTimer = new CountDownTimer(3000,1000) {
//                @Override
//                public void onTick(long l) {
//                }
//
//                @Override
//                public void onFinish() {
//                    finish();
//                }
//            };
//            finishTimer.cancel();
//            finishTimer.start();
//        }
        timerValue = 0;
        timer = new CountDownTimer(4000, 990) {
            @Override
            public void onTick(long millisUntilFinished) {
//                if(round==3&&times==3){
//                    timerText.setText("");
//                }
//                else {
//                    timerText.setText("숫자를 " + (4 - timerValue) + "초간 잘 기억해 주세요");
                    countDown.setText("Get Ready! "+(4 - timerValue));
                    timerValue++;
//                }
//                if(timerValue==3)
//                    randNumText.setText("");
            }

            @Override
            public void onFinish() {
//                if(times==3&&round==3);
//                else {

                    timer.cancel();
                    timerValue = 0;
                    Intent intent = new Intent(getApplicationContext(), NumRemeberQuiz.class);
                    startActivity(intent);
                    finish();

//                }
            }
        };
    }

    protected void onStart() {
        super.onStart();
        timer.cancel();
        timer.start();
        InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
//        scoreText.setText("Score : " +score);

//        if(round==3&& times==3);
//        else{

            randNumText.setText(randomNumber + "");

//        }
    }

    @Override
    public void onBackPressed() {

        dialogShow();
    }

    public void dialogShow() {
        //게임 종료 알림
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Finish workout");
        builder.setCancelable(false);
        builder.setMessage("Are you sure you want to quit the game\n(* Game data will be removed)");
        builder.setPositiveButton("Continue game",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        immhide.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
                    }
                });
        builder.setNegativeButton("home",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        NumRemember.super.onBackPressed();

                        if(timer != null)
                            timer.cancel();
                        if(finishTimer != null)
                            finishTimer.cancel();

                        finish();
                    }
                });
        builder.show();
    }
}