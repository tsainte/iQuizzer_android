package br.com.mobwiz.iquizzer.model.dao;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.mobwiz.iquizzer.model.entities.Quiz;
import br.com.mobwiz.iquizzer.util.WebService;

public class QuizDAO extends SQLiteOpenHelper implements Serializable{
	private SQLiteDatabase db;
	Context context;
	WebService webService;
	
	public QuizDAO(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	public QuizDAO(Context context){
		super(context, "iQuizzer", null, 1);
		this.context = context;
		webService = new WebService(context);
		db = getWritableDatabase();
		
	}
	
	
	public ArrayList<Quiz> findAllFromServer() throws Exception{
		String jsonData = webService.get("quizzes.json");
		
		JSONArray jsonArray = new JSONArray(jsonData);
		
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		for (int i = 0; i < jsonArray.length(); i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			
			Quiz quiz = new Quiz(jsonObj.getInt("id"), jsonObj.getString("titulo"));
			
			quizzes.add(quiz);
		}
		return quizzes;		
	}
	public boolean downloadQuiz(int id) throws Exception{
		String param = "quizzes/" + id + ".json";
		String jsonData = webService.get(param);
		
		JSONObject jsonObj = new JSONObject(jsonData);
		JSONObject jsonQuiz = jsonObj.getJSONObject("quiz");
		
		Quiz quiz =  new Quiz(jsonQuiz.getInt("id"), jsonQuiz.getString("titulo"));
		PerguntaDAO perguntaDAO = new PerguntaDAO(context, db);
		perguntaDAO.downloadJSONPerguntas(jsonQuiz.getJSONArray("perguntas"),quiz);

		return insert(quiz);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table if not exists quiz ( " +
				  "id integer primary key, titulo text not null)";
			
		db.execSQL(sql);	
	}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	public boolean insert(Quiz quiz){
		String sql = "insert into quiz values(";
		sql += quiz.getId() + ", ";
		sql += "'" + quiz.getTitulo()+ "') ";

		
		try { 
			db.execSQL(sql);
		} catch(Exception e) {
			
			return false;
		}

		return true;

	}
	public ArrayList<Quiz> findAll() {
		ArrayList<Quiz> quizzes = null;
		String sql = "select * from quiz";
		Cursor cursor;
		
		try {
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToFirst()){
				 quizzes = new ArrayList<Quiz>();
				do{
					Quiz quiz = new Quiz(cursor.getInt(0), cursor.getString(1));
					quizzes.add(quiz);
				} while(cursor.moveToNext());
			}
			
;
		} catch(Exception e) {
			return null;
		}
		return quizzes;
	}
	public void open() {
	    db = getWritableDatabase();
	}
	public void close(){
		db.close();
	}
}

