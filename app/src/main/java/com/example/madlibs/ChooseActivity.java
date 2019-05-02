package com.example.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }

    public void onChosen (View view) {

        Button clickedButton = (Button) view;

        int storyNumber = clickedButton.getId();
        Intent intent = new Intent(ChooseActivity.this, WordsActivity.class);
        intent.putExtra("storyNumber",storyNumber);
        startActivity(intent);
    }


}
