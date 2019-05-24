package com.example.madlibs;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.regex.Pattern;

public class WordsActivity extends AppCompatActivity {


    Story story;
    TextInputLayout wordsField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        if (savedInstanceState == null) {
            InputStream is;

            Intent intent = getIntent();
            int storyNumber = (int) intent.getSerializableExtra("storyNumber");

            switch (storyNumber) {
                case R.id.button0:
                    is = getResources().openRawResource(R.raw.madlib0_simple);
                    break;
                case R.id.button1:
                    is = getResources().openRawResource(R.raw.madlib1_tarzan);
                    break;
                case R.id.button2:
                    is = getResources().openRawResource(R.raw.madlib2_university);
                    break;
                case R.id.button3:
                    is = getResources().openRawResource(R.raw.madlib3_clothes);
                    break;
                case R.id.button4:
                    is = getResources().openRawResource(R.raw.madlib4_dance);
                    break;
                default:
                    is = getResources().openRawResource(R.raw.madlib0_simple);
            }

            story = new Story(is);
        } else {
            story = (Story) savedInstanceState.getSerializable("story");
        }

        // Set the hint.
        String placeholder = story.getNextPlaceholder();
        wordsField = findViewById(R.id.textInputLayout);
        wordsField.setHint(placeholder);

        // Set the counter.
        TextView counter = findViewById(R.id.counter);
        counter.setText(Integer.toString(story.getPlaceholderRemainingCount()));


        final EditText editText = findViewById(R.id.textField);

        // This makes the 'ok' or 'done' button on the keyboard also press the 'ok' button on the screen.
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    submit();
                    return true;
                }
                return false;
            }
        });
    }


    public void submit(){
        // Get the text that the user typed.
        String word = wordsField.getEditText().getText().toString();
        System.out.println(word);

        // Only accept alphabetic characters.
        if(Pattern.matches("[a-zA-Z]+", word))
        {
            story.fillInPlaceholder(word);

        }
        else
        {
            Toast toast = Toast.makeText(this, "Only use alphabetic characters.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        int remaining = story.getPlaceholderRemainingCount();

        if (remaining > 0){

            // If there are placeholders left to be filled in, put the next hint in the textfield.
            String placeholder = story.getNextPlaceholder();
            wordsField.setHint(placeholder);
            wordsField.getEditText().setText("");
            TextView counter = findViewById(R.id.counter);
            counter.setText(Integer.toString(story.getPlaceholderRemainingCount()));

        } else {

            // Otherwise, go to the story.
            Intent intent = new Intent(WordsActivity.this, StoryActivity.class);
            intent.putExtra("story", story);
            startActivity(intent);

        }
    }


    public void onSubmit (View view){
        submit();
    }


    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("story", story);
    }

}
