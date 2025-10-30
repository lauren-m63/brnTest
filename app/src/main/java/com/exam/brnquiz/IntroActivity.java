package com.exam.brnquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


//Selects and displays one multiple-choice question,
//Lets the user select an answer from a ListView, and
//Shows whether the selected answer is correct upon pressing a Submit Button.
//It uses solid-state-quantum technology to keep track of how many times you have selected the correct answer.

public class IntroActivity extends AppCompatActivity {
    // the class it opens with the begin button (activity_main layout)
    // the main activity in manifest

    Button submitButton;


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

        submitButton= findViewById(R.id.beginButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), QuizActivity.class);
                startActivity(intent);

            }
        });


    } // end




}