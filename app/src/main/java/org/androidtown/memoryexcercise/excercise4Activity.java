package org.androidtown.memoryexcercise;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 박재성 on 2017-12-02.
 */

public class excercise4Activity extends AppCompatActivity {

    //UI 변순
    TextView ex4Question1;
    TextView ex4Question2;

    LinearLayout fruitLinear1;
    RelativeLayout fruitRelative2;

    TextView countResult;
    TextView countdown;

    TextView playChance;

    //타이머
    CountDownTimer timer = null;

    //타이머 카운트
    int count = 6;

    //과일 이미지뷰
    ImageView fruit1;
    ImageView fruit2;
    ImageView fruit3;
    ImageView fruit4;
    ImageView fruit5;
    ImageView fruit6;
    ImageView fruit7;
    ImageView fruit8;
    ImageView fruit9;

    ImageView fruit1Answer;
    ImageView fruit2Answer;
    ImageView fruit3Answer;
    ImageView fruit4Answer;
    ImageView fruit5Answer;
    ImageView fruit6Answer;
    ImageView fruit7Answer;
    ImageView fruit8Answer;
    ImageView fruit9Answer;

    //문제 과일 이미지 뷰
    ImageView fruitImage1;
    ImageView fruitImage2;

    //정답 과일
    int[] answer;

    //정답 수
    int answerCount = 0;
    int answerNumber = 2;

    //기회 부여
    int chance = 2;

    //스테이지 부여
    static int stageNumber = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_excercise4);


        //UI 가져오기
        ex4Question1 = (TextView) findViewById(R.id.ex4Question1);
        ex4Question2 = (TextView) findViewById(R.id.ex4Question2);

        fruitLinear1 = (LinearLayout) findViewById(R.id.fruitLinear1);
        fruitRelative2 = (RelativeLayout) findViewById(R.id.fruitRelative2);

        countResult = (TextView) findViewById(R.id.countResult);
        countdown = (TextView) findViewById(R.id.countdown);

        playChance = (TextView)findViewById(R.id.playChance);

        fruit1 = (ImageView) findViewById(R.id.fruit1);
        fruit2 = (ImageView) findViewById(R.id.fruit2);
        fruit3 = (ImageView) findViewById(R.id.fruit3);
        fruit4 = (ImageView) findViewById(R.id.fruit4);
        fruit5 = (ImageView) findViewById(R.id.fruit5);
        fruit6 = (ImageView) findViewById(R.id.fruit6);
        fruit7 = (ImageView) findViewById(R.id.fruit7);
        fruit8 = (ImageView) findViewById(R.id.fruit8);
        fruit9 = (ImageView) findViewById(R.id.fruit9);

        fruit1Answer = (ImageView) findViewById(R.id.fruit1Answer);
        fruit2Answer = (ImageView) findViewById(R.id.fruit2Answer);
        fruit3Answer = (ImageView) findViewById(R.id.fruit3Answer);
        fruit4Answer = (ImageView) findViewById(R.id.fruit4Answer);
        fruit5Answer = (ImageView) findViewById(R.id.fruit5Answer);
        fruit6Answer = (ImageView) findViewById(R.id.fruit6Answer);
        fruit7Answer = (ImageView) findViewById(R.id.fruit7Answer);
        fruit8Answer = (ImageView) findViewById(R.id.fruit8Answer);
        fruit9Answer = (ImageView) findViewById(R.id.fruit9Answer);

        fruitImage1 = (ImageView) findViewById(R.id.fruitImage1);
        fruitImage2 = (ImageView) findViewById(R.id.fruitImage2);

        //과일 이미지 UI들 ARRAY
        ImageView[] fruits = {fruit1, fruit2, fruit3, fruit4, fruit5, fruit6, fruit7, fruit8, fruit9};
        //문제 과일 이미지 array
        ImageView[] fruitsAnswerImages = {fruitImage1, fruitImage2};
        // 틀렸는지 맞았는지 (0,X) 나타내줄 이미지
        //ImageView[] fruitsAnswer = {fruit1Answer, fruit2Answer, fruit3Answer, fruit4Answer, fruit5Answer, fruit6Answer, fruit7Answer, fruit8Answer, fruit9Answer};

        //스테이지
        stageNumber++;

        //카운트 다운
        timer = new CountDownTimer(5000, 900) {
            @Override
            public void onTick(long millisUntilFinished) {
                count--;
                countdown.setText("Questions will be asked\nin " + count + " seconds");
            }

            @Override
            public void onFinish() {
                count = 5;

                ex4Question1.setVisibility(View.INVISIBLE);
                fruitLinear1.setVisibility(View.INVISIBLE);
                countdown.setVisibility(View.INVISIBLE);

                ex4Question2.setVisibility(View.VISIBLE);
                fruitRelative2.setVisibility(View.VISIBLE);
                countResult.setVisibility(View.VISIBLE);
                playChance.setVisibility(View.VISIBLE);
            }
        }.start();

        randomFruits(fruits, fruitsAnswerImages);


        fruit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int found = 0;
                for (int i = 0; i < 2; i++) {
                    if (answer[i] == 0) {
                       // Toast.makeText(getApplicationContext(), "정답입니다", Toast.LENGTH_LONG).show();
                        found = 1;
                        fruit1Answer.setImageResource(R.drawable.answer);

                        answerCount++;
                        countResult.setText("Fruits found(" + answerCount + "/" + answerNumber + ")");
                        break;
                    }
                }
                if (found == 0) {
                  //  Toast.makeText(getApplicationContext(), "틀렸습니다", Toast.LENGTH_LONG).show();
                    fruit1Answer.setImageResource(R.drawable.fail);
                    chanceFail();
                }
                fruit1Answer.setVisibility(View.VISIBLE);
                fruit1.setEnabled(false);

                if (answerCount == answerNumber) {
                    nextStage(1);
                }
            }
        });

        fruit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int found = 0;
                for (int i = 0; i < 2; i++) {
                    if (answer[i] == 1) {
                      //  Toast.makeText(getApplicationContext(), "정답입니다", Toast.LENGTH_LONG).show();
                        found = 1;
                        fruit2Answer.setImageResource(R.drawable.answer);

                        answerCount++;
                        countResult.setText("Fruits found(" + answerCount + "/" + answerNumber + ")");
                        break;
                    }
                }
                if (found == 0) {
                 //   Toast.makeText(getApplicationContext(), "틀렸습니다", Toast.LENGTH_LONG).show();
                    fruit2Answer.setImageResource(R.drawable.fail);
                    chanceFail();
                }
                fruit2Answer.setVisibility(View.VISIBLE);
                fruit2.setEnabled(false);

                if (answerCount == answerNumber) {
                    nextStage(1);
                }
            }
        });

        fruit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int found = 0;
                for (int i = 0; i < 2; i++) {
                    if (answer[i] == 2) {
                     //   Toast.makeText(getApplicationContext(), "정답입니다", Toast.LENGTH_LONG).show();
                        found = 1;
                        fruit3Answer.setImageResource(R.drawable.answer);

                        answerCount++;
                        countResult.setText("Fruits found(" + answerCount + "/" + answerNumber + ")");
                        break;
                    }
                }
                if (found == 0) {
                  //  Toast.makeText(getApplicationContext(), "틀렸습니다", Toast.LENGTH_LONG).show();
                    fruit3Answer.setImageResource(R.drawable.fail);
                    chanceFail();
                }
                fruit3Answer.setVisibility(View.VISIBLE);
                fruit3.setEnabled(false);

                if (answerCount == answerNumber) {
                    nextStage(1);
                }
            }
        });

        fruit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int found = 0;
                for (int i = 0; i < 2; i++) {
                    if (answer[i] == 3) {
                    //    Toast.makeText(getApplicationContext(), "정답입니다", Toast.LENGTH_LONG).show();
                        found = 1;
                        fruit4Answer.setImageResource(R.drawable.answer);

                        answerCount++;
                        countResult.setText("Fruits found(" + answerCount + "/" + answerNumber + ")");
                        break;
                    }
                }
                if (found == 0) {
                  //  Toast.makeText(getApplicationContext(), "틀렸습니다", Toast.LENGTH_LONG).show();
                    fruit4Answer.setImageResource(R.drawable.fail);
                    chanceFail();
                }

                fruit4Answer.setVisibility(View.VISIBLE);
                fruit4.setEnabled(false);

                if (answerCount == answerNumber) {
                    nextStage(1);
                }
            }
        });

        fruit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int found = 0;
                for (int i = 0; i < 2; i++) {
                    if (answer[i] == 4) {
                      //  Toast.makeText(getApplicationContext(), "정답입니다", Toast.LENGTH_LONG).show();
                        found = 1;
                        fruit5Answer.setImageResource(R.drawable.answer);

                        answerCount++;
                        countResult.setText("Fruits found(" + answerCount + "/" + answerNumber + ")");
                        break;
                    }
                }
                if (found == 0) {
                  //  Toast.makeText(getApplicationContext(), "틀렸습니다", Toast.LENGTH_LONG).show();
                    fruit5Answer.setImageResource(R.drawable.fail);
                    chanceFail();
                }
                ;

                fruit5Answer.setVisibility(View.VISIBLE);
                fruit5.setEnabled(false);

                if (answerCount == answerNumber) {
                    nextStage(1);
                }
            }
        });

        fruit6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int found = 0;
                for (int i = 0; i < 2; i++) {
                    if (answer[i] == 5) {
                     //   Toast.makeText(getApplicationContext(), "정답입니다", Toast.LENGTH_LONG).show();
                        found = 1;
                        fruit6Answer.setImageResource(R.drawable.answer);

                        answerCount++;
                        countResult.setText("Fruits found(" + answerCount + "/" + answerNumber + ")");
                        break;
                    }
                }
                if (found == 0) {
                  //  Toast.makeText(getApplicationContext(), "틀렸습니다", Toast.LENGTH_LONG).show();
                    fruit6Answer.setImageResource(R.drawable.fail);
                    chanceFail();
                }
                fruit6Answer.setVisibility(View.VISIBLE);
                fruit6.setEnabled(false);

                if (answerCount == answerNumber) {
                    nextStage(1);
                }
            }
        });

        fruit7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int found = 0;
                for (int i = 0; i < 2; i++) {
                    if (answer[i] == 6) {
                      //  Toast.makeText(getApplicationContext(), "정답입니다", Toast.LENGTH_LONG).show();
                        found = 1;
                        fruit7Answer.setImageResource(R.drawable.answer);

                        answerCount++;
                        countResult.setText("Fruits found(" + answerCount + "/" + answerNumber + ")");
                        break;
                    }
                }
                if (found == 0) {
                  //  Toast.makeText(getApplicationContext(), "틀렸습니다", Toast.LENGTH_LONG).show();
                    fruit7Answer.setImageResource(R.drawable.fail);
                    chanceFail();
                }
                fruit7.setEnabled(false);
                fruit7Answer.setVisibility(View.VISIBLE);

                if (answerCount == answerNumber) {
                    nextStage(1);
                }
            }
        });

        fruit8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int found = 0;
                for (int i = 0; i < 2; i++) {
                    if (answer[i] == 7) {
                      //  Toast.makeText(getApplicationContext(), "정답입니다", Toast.LENGTH_LONG).show();
                        found = 1;
                        fruit8Answer.setImageResource(R.drawable.answer);

                        answerCount++;
                        countResult.setText("Fruits found(" + answerCount + "/" + answerNumber + ")");
                        break;
                    }
                }
                if (found == 0) {
                  //  Toast.makeText(getApplicationContext(), "틀렸습니다", Toast.LENGTH_LONG).show();
                    fruit8Answer.setImageResource(R.drawable.fail);
                    chanceFail();
                }

                fruit8Answer.setVisibility(View.VISIBLE);
                fruit8.setEnabled(false);

                if (answerCount == answerNumber) {
                    nextStage(1);
                }
            }
        });

        fruit9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int found = 0;
                for (int i = 0; i < 2; i++) {
                    if (answer[i] == 8) {
                        //Toast.makeText(getApplicationContext(), "정답입니다", Toast.LENGTH_LONG).show();
                        found = 1;
                        fruit9Answer.setImageResource(R.drawable.answer);

                        answerCount++;
                        countResult.setText("Fruits found(" + answerCount + "/" + answerNumber + ")");
                        break;
                    }
                }
                if (found == 0) {
                    //Toast.makeText(getApplicationContext(), "틀렸습니다", Toast.LENGTH_LONG).show();
                    fruit9Answer.setImageResource(R.drawable.fail);
                    chanceFail();
                }
                fruit9Answer.setVisibility(View.VISIBLE);
                fruit9.setEnabled(false);

                if (answerCount == answerNumber) {
                    nextStage(1);
                }
            }
        });
    }

    public void randomFruits(ImageView[] fruits, ImageView[] fruitsAnswerImages) {
        int randNumber1, randNumber2;
        //랜덤숫자 만들기(처음에 이미지 질문 할 2가지 과일종류)
        randNumber1 = (int) (Math.random() * 9);
        randNumber2 = (int) (Math.random() * 9);

        //랜덤 숫자가 둘이 같다면 다시 만들어준다.
        while (true) {
            if (randNumber1 == randNumber2) {
                randNumber1 = (int) (Math.random() * 9);
                randNumber2 = (int) (Math.random() * 9);
            } else
                break;
        }


        // 9개의 과일 이미지를 섞기 위해서
        ArrayList<Integer> fruitImages = new ArrayList<Integer>(9);

        fruitImages.add(R.drawable.fruit1);
        fruitImages.add(R.drawable.fruit2);
        fruitImages.add(R.drawable.fruit3);
        fruitImages.add(R.drawable.fruit4);
        fruitImages.add(R.drawable.fruit5);
        fruitImages.add(R.drawable.fruit6);
        fruitImages.add(R.drawable.fruit7);
        fruitImages.add(R.drawable.fruit8);
        fruitImages.add(R.drawable.fruit9);

        //섞는다.
        Collections.shuffle(fruitImages);

        //9개의 과일이미지를 섞은 값을 차례대로 넣어준다.
        for (int i = 0; i < fruitImages.size(); i++) {
            fruits[i].setImageResource(fruitImages.get(i));
        }

        //잘섞였나 확인
        System.out.println("fruits " + randNumber1 + "frutis2" + randNumber2);
        System.out.println("fruits " + fruits[1].getDrawable());

        //2개의 과일 이미지 문제용으로 사용한다.
        fruitsAnswerImages[0].setImageResource(fruitImages.get(randNumber1));
        fruitsAnswerImages[1].setImageResource(fruitImages.get(randNumber2));

        //정답 넣기
        answer = new int[2];
        answer[0] = randNumber1;
        answer[1] = randNumber2;
    }

    public void chanceFail()
    {
        //틀린경우

        chance--;

        if(chance < 0)
        {
            nextStage(0);
        }
        else if(chance == 0)
        {
            playChance.setText("(Last chance)");
        }
        else if(chance > 0)
            playChance.setText(chance + " remaining chances)" );

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);
    }

    public void nextStage(int i)
    {
        //정답 시
        if(i == 1) {
            //게임 종료 알림
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Correct!");
           // builder.setMessage("맞았습니다.\n(다음문제로 넘어가시겠습니까?)");
            builder.setPositiveButton("next game",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), excercise4Activity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    });
            builder.setNegativeButton("Go Home",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            stageNumber = 0;
                            finish();
                        }
                    });
            builder.show();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Incorrect!");
            //builder.setMessage("틀렸습니다.\n(다음문제로 넘어가시겠습니까?)");
            builder.setPositiveButton("next game",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), excercise4Activity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    });
            builder.setNegativeButton("Go Home",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            stageNumber = 0;
                            finish();
                        }
                    });
            builder.show();
        }
    }

    @Override
    public void onBackPressed() {
        dialogShow();
    }

    public void dialogShow() {
        //게임 종료 알림
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("End game");
        builder.setCancelable(false);
        builder.setMessage("Would you like to end the game?\n(* Game data will be removed)");
        builder.setPositiveButton("next game",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.setNegativeButton("Go Home",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        excercise4Activity.super.onBackPressed();
                    }
                });
        builder.show();
    }

}




