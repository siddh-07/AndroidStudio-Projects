package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Assignment1 extends AppCompatActivity {

    ImageButton[] buttons = new ImageButton[12];
    TextView tw;
    MenuItem newGame;
    Button playAgain;
    ImageButton imageButton;
    TextView tw1;
    Integer[] images = {R.drawable.ca, R.drawable.ck, R.drawable.cq, R.drawable.da, R.drawable.dk, R.drawable.dq};
    ArrayList<Integer> imagesOfCard = new ArrayList<>(Arrays.asList(images));
    int blank = R.drawable.b;
    final int[] index = {0};
    int counter = 0;
    int[] number = new int[2];
    int rightCard = 0;

    @SuppressLint({"MissingInflatedId", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_layout);

        buttons[0] = findViewById(R.id.imageButton1);
        buttons[1] = findViewById(R.id.imageButton2);
        buttons[2] = findViewById(R.id.imageButton3);
        buttons[3] = findViewById(R.id.imageButton4);
        buttons[4] = findViewById(R.id.imageButton5);
        buttons[5] = findViewById(R.id.imageButton6);
        buttons[6] = findViewById(R.id.imageButton7);
        buttons[7] = findViewById(R.id.imageButton8);
        buttons[8] = findViewById(R.id.imageButton9);
        buttons[9] = findViewById(R.id.imageButton10);
        buttons[10] = findViewById(R.id.imageButton11);
        buttons[11] = findViewById(R.id.imageButton12);


        tw = findViewById(R.id.textView4);

        for (ImageButton ib : buttons) {

            ib.setOnClickListener(View -> {
                index[0]++;
                selectCard(ib, index[0]);
            });
        }
    }

    @SuppressLint({"SetTextI18n"})

    public void selectCard(ImageButton ib, int index) {
        int random = RandomNumber();

        int count = ++counter;
        if (index % 2 == 0) {

            new CountDownTimer(1000, 500) {
                public void onTick(long millisUntilFinished) {

                    ib.setImageResource(imagesOfCard.get(random));
                    ib.setSelected(true);
                    ib.setClickable(false);
                    number[1] = random;
                }

                public void onFinish() {

                    if (number[0] != number[1]) {
                        ib.setImageResource(blank);
                        imageButton.setImageResource(blank);

                        ib.setClickable(true);
                        imageButton.setClickable(true);
                    }
                    else {
                        rightCard++;
                        imagesOfCard.remove(number[0]);
                        if (rightCard == 6) {
                            wonTheGame();
                        }
                    }
                    tw.setText("You've made " + count + " guesses.");
                }
            }.start();
        } else {


            ib.setImageResource(imagesOfCard.get(random));

            number[0] = random;
            imageButton = ib;

            ib.setClickable(false);
            tw.setText("You've made " + count + " guesses.");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        newGame = findViewById(R.id.newGame);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.newGame) {
            Intent intent = new Intent();
            intent.setClass(this, Assignment1.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    public void wonTheGame() {
            setContentView(R.layout.won);
            tw1 = findViewById(R.id.Guesses);
            tw1.setText("You Won After " + counter + " Guesses");

            playAgain = findViewById(R.id.playAgain);

            playAgain.setOnClickListener(event -> {
                Intent intent = new Intent();
                intent.setClass(this, Assignment1.class);
                startActivity(intent);
            });
    }

    public int RandomNumber() {
        Random random = new Random();
        return random.nextInt(imagesOfCard.size());
    }
}

