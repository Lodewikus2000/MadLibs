package com.example.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StoryActivity extends AppCompatActivity {

    Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("story");

        TextView storyTextView = findViewById(R.id.storyTextView);
        storyTextView.setText(story.toString());
    }


    @Override
    public void onBackPressed() {
        // Don't go back to the word submitting, but go back to the main screen.
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
