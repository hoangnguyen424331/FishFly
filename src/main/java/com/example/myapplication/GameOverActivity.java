package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    private Button StartGame;
    private TextView txtScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        AnhXa();


        StartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent=new Intent(GameOverActivity.this,MainActivity.class);
                startActivity(mainIntent);

            }
        });

        int score =getIntent().getIntExtra("score ",0);
        txtScore.setText("Score "+score);

    }



    private void AnhXa()
    {
        StartGame=(Button) findViewById(R.id.btGameOver);
        txtScore=(TextView) findViewById(R.id.displayScore);
    }
}
