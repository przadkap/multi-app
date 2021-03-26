package com.pprzadka.multiapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class HangmanActivity extends Activity {
    Button guessLetterButton, guessWordButton, newGameButton;
    TextView wordTextView, missesTextView;
    ImageView hangmanImageView;
    String word = "caveman";
    String guessedWordState = "";
    int[] hangmen = new int[]{
            R.drawable.ic_hangman_0,
            R.drawable.ic_hangman_1,
            R.drawable.ic_hangman_2,
            R.drawable.ic_hangman_3,
            R.drawable.ic_hangman_4,
            R.drawable.ic_hangman_5,
            R.drawable.ic_hangman_6};
    int hits = 0;
    int misses = 0;

    String[] words = new String[]{"caveman", "whoever", "jazz", "continuous", "freezing", "climate",
            "mouse", "moose", "trousers", "cataclysm", "walrus", "totem", "cranberry", "agave",
            "acacia", "dumpling", "chocolate", "crocodile", "chemistry", "herbivore", "gravel",
            "cognitive", "camel", "armadillo", "badger", "mushroom", "holiday", "hemisphere", "dawn",
            "example", "exquisite", "memorial", "justification", "honesty", "castle", "squash",
            "dandelion", "viking", "candle", "atom", "beetroot", "heartbeat", "disaster", "earth",
            "novelisation", "knockout", "doom", "pretentious", "perseverance", "curiosity", "flow",
            "philosopher", "conveyor", "kindergarten", "vermilion", "acoustic", "theatre", "movie",
            "employer", "breach", "security", "bandage", "stew", "satellite", "nausea", "lighthouse"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        linkExistingObjects();
        setListeners();
        setupNewWord();
    }

    private void setupNewWord() {
        guessedWordState = "";
        word = words[ThreadLocalRandom.current().nextInt(0, words.length)];
        for(char c : word.toCharArray()) {
            guessedWordState += "_ ";
        }
        guessedWordState = guessedWordState.trim();
        wordTextView.setText(guessedWordState);
        missesTextView.setText("");
        hangmanImageView.setImageResource(hangmen[0]);
        hits = 0;
        misses = 0;
    }

    private void linkExistingObjects() {
        guessLetterButton = (Button) findViewById(R.id.btn_hangman_guess_letter);
        guessWordButton = (Button) findViewById(R.id.btn_hangman_guess_word);
        wordTextView = (TextView) findViewById(R.id.tv_hangman_word);
        missesTextView = (TextView) findViewById(R.id.tv_hangman_misses);
        hangmanImageView = (ImageView) findViewById(R.id.iv_hangman);
        //TODO newgaamebutton
    }

    private void setListeners() {
        guessLetterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                View promptsView = layoutInflater.inflate(R.layout.dialog_edit_text_letter, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HangmanActivity.this);
                alertDialogBuilder.setView(promptsView);
                final EditText letterDialogEditText = (EditText) promptsView.findViewById(R.id.et_dialog);
                InputFilter[] filters = new InputFilter[1];
                filters[0] = new InputFilter.LengthFilter(1);
                letterDialogEditText.setFilters(filters);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Guess!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                guessLetter(letterDialogEditText.getText().toString());
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog letterDialog = alertDialogBuilder.create();
                letterDialog.show();
            }
        });
        guessWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                View promptsView = layoutInflater.inflate(R.layout.dialog_edit_text_word, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HangmanActivity.this);
                alertDialogBuilder.setView(promptsView);
                final EditText wordDialogEditText = (EditText) promptsView.findViewById(R.id.et_dialog_word);

                InputFilter[] filters = new InputFilter[1];
                filters[0] = new InputFilter.LengthFilter(word.length());
                wordDialogEditText.setFilters(filters);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Guess!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                guessWord(wordDialogEditText.getText().toString());
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog letterDialog = alertDialogBuilder.create();
                letterDialog.show();

            }
        });
    }

    private void guessWord(String wordInput) {
        if(word.equals(wordInput)) {
            showToast("You guessed it!");
            handleHit(true);
        }
        else {
            handleMiss();
        }
    }

    void handleMiss() {
        misses++;
        hangmanImageView.setImageResource(hangmen[misses]);
        if(misses == 6) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            setupNewWord();
                            dialog.cancel();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(HangmanActivity.this);
            builder.setMessage("You lost. The word was: " + word + ". Try again?").setPositiveButton("New word", dialogClickListener)
                    .setNegativeButton("Back to menu", dialogClickListener).show();
        }
    }

    private void handleHit(boolean wordGuess) {
        if (!wordGuess) {
            hits++;
            showToast("That's a hit!");
        }
        if(wordGuess || (hits == word.length())) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            setupNewWord();
                            dialog.cancel();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(HangmanActivity.this);
            builder.setMessage("You won! The word was: " + word + ". Play again?").setPositiveButton("New word", dialogClickListener)
                    .setNegativeButton("Back to menu", dialogClickListener).show();
        }
    }

    private void guessLetter(String letter) {
        guessedWordState = wordTextView.getText().toString();
        if(guessedWordState.toLowerCase().contains(letter.toLowerCase())) {
            showToast("You have already tried this letter.");
            return;
        }
        char[] wordStateCharArray = guessedWordState.toCharArray();
        if(word.contains(letter)) {
            for(int character = 0; character < word.length(); ++character) {
                if(word.toCharArray()[character] == letter.charAt(0)) {
                    wordStateCharArray[(character * 2)] = letter.toUpperCase().charAt(0);
                    handleHit(false);
                }
            }
            wordTextView.setText(new String(wordStateCharArray));
        }
        else {
            String missed = missesTextView.getText().toString();
            if(!missed.toLowerCase().contains(letter.toLowerCase())) {
                if(missed.isEmpty()) {
                    missed = letter.toUpperCase();
                }
                else {
                    missed += ", " + letter.toUpperCase();
                }
                missesTextView.setText(missed);
                handleMiss();
            }
            else {
                showToast("You already tried this letter.");
            }

        }
    }

    private void showToast(CharSequence text) {
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
