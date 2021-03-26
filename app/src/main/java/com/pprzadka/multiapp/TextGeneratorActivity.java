package com.pprzadka.multiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class TextGeneratorActivity extends AppCompatActivity {
    Button generateSentenceButton, generateParagraphsButton;
    EditText wordsEditText, paragraphsEditText;
    TextView mainTextView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_generator);
        linkExistingObjects();
        setListeners();
    }

    private void setListeners() {
        generateSentenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wordCountText = wordsEditText.getText().toString();
                if(!wordCountText.isEmpty()) {
                    int wordCount = Integer.parseInt(wordCountText);
                    if(wordCount > 0 && wordCount < 16) {
                        RandomSentenceGenerator randomSentenceGenerator = new RandomSentenceGenerator();
                        mainTextView.setText(randomSentenceGenerator.generateSentence(wordCount, false));
                    }
                    else {
                        showToast("Incorrect number of words (1-15).");
                    }
                }
                else {
                    showToast("Incorrect number of words (1-15).");
                }

            }
        });
        generateParagraphsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String paragraphCountText = paragraphsEditText.getText().toString();
                if(!paragraphCountText.isEmpty()) {
                    int paragraphCount = Integer.parseInt(paragraphCountText);
                    RandomSentenceGenerator randomSentenceGenerator = new RandomSentenceGenerator();
                    if (paragraphCount > 0 && paragraphCount <= 7) {
                        StringBuilder text = new StringBuilder();
                        for (int paragraph = 0; paragraph < paragraphCount; ++paragraph) {
                            int sentences = ThreadLocalRandom.current().nextInt(3, 8);
                            for (int sentence = 0; sentence < sentences; ++sentence) {
                                int words = ThreadLocalRandom.current().nextInt(5, 14);
                                text.append(randomSentenceGenerator.generateSentence(words, sentence != 0));
                            }
                            text.append("\n\n");
                        }
                        mainTextView.setText(text.toString());
                    }
                    else {
                        showToast("Incorrect number of paragraphs (1-7)");
                    }
                }
                else {
                    showToast("Incorrect number of paragraphs (1-7)");
                }
            }
        });
    }

    private void showToast(CharSequence text) {
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void linkExistingObjects() {
        generateSentenceButton = (Button) findViewById(R.id.btn_tg_gen_sentence);
        generateParagraphsButton = (Button) findViewById(R.id.btn_tg_gen_paragraph);
        wordsEditText = (EditText) findViewById(R.id.et_tg_word_no);
        paragraphsEditText = (EditText) findViewById(R.id.et_tg_paragraph_no);
        mainTextView = (TextView) findViewById(R.id.tv_tg_text);
    }
}
