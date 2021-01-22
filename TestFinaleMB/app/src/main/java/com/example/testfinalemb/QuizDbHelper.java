package com.example.testfinalemb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;




public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuizDb.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE ="CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER," +
                QuizContract.QuestionsTable.COLUMN_QUIZ + " TEXT" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        //QUIZ 1
        Question q1 = new Question("Quale è l'ordine giusto dei film di star wars?", "4-5-6-1-2-3", "1-2-3-4-5-6", "Io guardo Star Trek", 1, Question.QUIZ_1);
        addQuestion(q1);
        Question q2 = new Question("Le serie tv più \"piratate\" nel 2020", "Game of Thrones", "Mandalorian", "The Boys", 2, Question.QUIZ_1);
        addQuestion(q2);
        Question q3 = new Question("Di che colere è il cavallo di Gandalf?", "Nero", "Marrone", "Bianco", 3, Question.QUIZ_1);
        addQuestion(q3);
        Question q4 = new Question("Quando uscirà hogwarts legacy", "2021", "2022", "Speriamo non tanto come Ciberpunk 2077", 2, Question.QUIZ_1);
        addQuestion(q4);
        Question q5 = new Question("Qual'è il gioco più discusso del 2020 su twitwer?", "The last of us", "Animal Crossing: New Horizon", "Ciberpunk 2077", 2, Question.QUIZ_1);
        addQuestion(q5);
        //QUIZ 2
        Question q6 = new Question("Chi è chiamato il Custode di Narya", "Gandalf", "Artu", "Spock", 1, Question.QUIZ_2);
        addQuestion(q6);
        Question q7 = new Question("Che cosa vuol dire \"aslan\" in Turco?", "Re", "Leone", "Protettore", 2, Question.QUIZ_2);
        addQuestion(q7);
        Question q8 = new Question("Chi ha scritto Eragon?", "Marion Zimmer Bradley", "Philip Pullman", "Christopher Paolini", 3, Question.QUIZ_2);
        addQuestion(q8);
        Question q9 = new Question("In Star Wars, che colore è la spada Laser di Ray", "Verde", "Gialla", "Blu", 2, Question.QUIZ_2);
        addQuestion(q9);
        Question q10 = new Question("In Harry potter perchè non si vede più Tiger dal 6 film in poi?", "L'attore ha iniziato un altro Film", "L'attore è stato condannato a svolgere lavori socialmente utili", "L'attore ha deciso di cambiare carriera", 2, Question.QUIZ_2);
        addQuestion(q10);
        //QUIZ 3
        Question q11 = new Question("Secondo Yoda quanti Sith ci sono ancora vivi?", "2", "3", "4", 1, Question.QUIZ_3);
        addQuestion(q11);
        Question q12 = new Question("Quante uova hanno avuto Merlin e Cora in \"alla ricerca di Nemo\"?", "700", "1000", "400", 2, Question.QUIZ_3);
        addQuestion(q12);
        Question q13 = new Question("Di Quale malattia mentale soffre Bel in \"La Bella e la Bestia\"?", "Disturbo ossessivo compulsivo", "Disturbo Schizoaffettivo", "Sindrome di Stoccolma", 3, Question.QUIZ_3);
        addQuestion(q13);
        Question q14 = new Question("Cosa sono i Nargilli in Harry Potter", "Un animale simile a una rana", "Solo Luna Lovegood lo sa...", "Un parassita delle piante", 2, Question.QUIZ_3);
        addQuestion(q14);
        Question q15 = new Question("Cosa succede ad Anakin Skywalker durante la battaglai contro il Count Dooku??", "Perde la gamba destra", "Perde il braccio sinistro", "Perde e basta", 2, Question.QUIZ_3);
        addQuestion(q15);
    }
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuizContract.QuestionsTable.COLUMN_QUIZ, question.getquizn());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                question.setquizn(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUIZ)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME +
                " WHERE " + QuizContract.QuestionsTable.COLUMN_QUIZ + " = ?", selectionArgs);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                question.setquizn(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUIZ)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}