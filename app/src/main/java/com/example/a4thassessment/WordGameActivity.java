package com.example.a4thassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class WordGameActivity extends AppCompatActivity implements View.OnClickListener{

    private String[] words = {"rat", "cat", "dog", "hen", "duck", "sparrow","pigeon","crow","cow","parrot"};
    private EditText etWord;
    SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    TextView textView;
    private int level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar

        setContentView(R.layout.activity_word_game);

        textView =findViewById(R.id.leveltext);
        Button btnOK = findViewById(R.id.btnOK);
        Button btnClean = findViewById(R.id.btnClean);
        etWord = findViewById(R.id.etWord);
        recyclerView = findViewById(R.id.recyclerView);


        SharedPreferences savedata = getSharedPreferences("WordGame", Context.MODE_PRIVATE);
        if (savedata.getInt("Level",0)==0) {
            showWord(level);
        }
        else {
            level=savedata.getInt("Level",0);
            showWord(level);
        }


        btnOK.setOnClickListener(this);
        btnClean.setOnClickListener(this);
    }


    private Character[] shuffleWords(int level){
        char[] word = words[level].toCharArray();


        ArrayList<Character> chars = new ArrayList<>(word.length);
        for(char c: word){
            chars.add(c);
        }

        Collections.shuffle(chars);
        Character[] shuffledWord = new Character[chars.size()];

        for(int i=0; i<shuffledWord.length; i++ ){
            shuffledWord[i] = chars.get(i);
        }
        return shuffledWord;
    }

    private void showWord(int i){
        WordGameCharacterAdapter charactersAdapter = new WordGameCharacterAdapter(WordGameActivity.this, shuffleWords(i),etWord);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setAdapter(charactersAdapter);
        recyclerView.setLayoutManager(layoutManager);
        textView.setText("GAME LEVEL : " + (i+1));
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnOK:
                String usr_word = etWord.getText().toString();
                if(level<words.length) {
                    if (usr_word.equals(words[level])) {
                        level++;
                        showWord(level);
                        sharedPreferences = getSharedPreferences("WordGame",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("Level",level);
                        editor.commit();
                        etWord.setText("");
                        Toast.makeText(WordGameActivity.this, "Next Level", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(WordGameActivity.this, "Wrong Word", Toast.LENGTH_SHORT).show();
                        etWord.setText("");
                        showWord(level);
                    }
                }
                else {
                    level=0;
                    Toast.makeText(WordGameActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnClean:
                etWord.getText().clear();
                showWord(level);
                break;
        }
    }

}
