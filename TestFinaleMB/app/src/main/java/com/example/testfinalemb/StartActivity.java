package com.example.testfinalemb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final  String EXTRA_QUIZ = "extraDifficulty";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView testo;
    private Spinner spinnerQuiz;
    private int highscore;

    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testo = findViewById(R.id.text_view_highscore);
        foto = (ImageView) findViewById(R.id.immagine);
        ImageView img=new ImageView(this);
        foto.setImageResource(R.drawable.quiz);

        spinnerQuiz = findViewById(R.id.spinner_quizn);
        spinnerQuiz =findViewById(R.id.spinner_quizn);

        String[] livelloDifficolta = Question.getAllDifficultyLevels();

        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, livelloDifficolta);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuiz.setAdapter(adapterDifficulty);


        loadHighscore();

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }
    private void startQuiz() {
        String difficulty = spinnerQuiz.getSelectedItem().toString();

        Intent intent = new Intent(StartActivity.this, QuestActivity.class);
        intent.putExtra(EXTRA_QUIZ, difficulty);
        startActivityForResult(intent,REQUEST_CODE_QUIZ);
        highscore=-1;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuestActivity.EXTRA_SCORE, 0);
                if (score > highscore) {
                    updateHighscore(score);
                }
            }
        }
    }
    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
    }
    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;
        if (highscore<0){
            testo.setText("Inzia il Quiz! Buondivertimento...");
        }
        else if (highscore>=0 & highscore<2){
            testo.setText("Punti: " + highscore +"\n\nCosì non va bene, Your Lack of knowledge Disturbs Me. \n\n Ora seleziona un altro Quiz!");
            foto.setImageResource(R.drawable.basso);
        }
        else if(highscore>=2  & highscore<=3){
            testo.setText("Punti: " + highscore +"\n\nNella media, sei stato bravo, ma devi applicarti di più. cit \n\n Ora seleziona un altro Quiz!");
            foto.setImageResource(R.drawable.medio);
        }
        else if(highscore>3){
            testo.setText("Punti: " + highscore +"\n\nComplimenti!!! 100 punti ai Grifondoro \n\n Ora seleziona un altro Quiz!");
            foto.setImageResource(R.drawable.alto);
        }

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }
}
