package br.com.mobwiz.iquizzer.model.dao;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.mobwiz.iquizzer.model.entities.Pergunta;
import br.com.mobwiz.iquizzer.model.entities.Quiz;
import br.com.mobwiz.iquizzer.model.entities.Resposta;
import br.com.mobwiz.iquizzer.util.Functions;
import br.com.mobwiz.iquizzer.util.WebService;

public class PerguntaDAO extends SQLiteOpenHelper implements Serializable{
	private SQLiteDatabase db;
	Context context;
	WebService webService;
	RespostaDAO respostaDAO;
	
	public PerguntaDAO(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	public PerguntaDAO(Context context){
		super(context, "iQuizzer", null, 1);
		this.context = context;
		webService =  new WebService(context);
		db = getWritableDatabase();
	}
	public PerguntaDAO(Context context, SQLiteDatabase db){
		super(context, "iQuizzer", null, 1);
		this.context = context;
		this.db = db;
		onCreate(db);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table if not exists pergunta ( " +
				  "id integer primary key, conteudo text not null, quiz_id integer )";
			
		db.execSQL(sql);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public void downloadJSONPerguntas(JSONArray jsonArray, Quiz quiz) throws JSONException{

		for (int i = 0; i < jsonArray.length(); i++){
			JSONObject jsonPergunta  = jsonArray.getJSONObject(i);
			
			Pergunta pergunta = new Pergunta(jsonPergunta.getInt("id"),jsonPergunta.getString("conteudo"));

			pergunta.setQuiz(quiz);
			
			RespostaDAO respostaDAO = new RespostaDAO(context, db);
			respostaDAO.downloadJSONRespostas(jsonPergunta.getJSONArray("respostas"), pergunta);
			
			insert(pergunta);
		}
	}
	public boolean insert(Pergunta pergunta){
		
		String sql = "insert into pergunta values(";
		sql += pergunta.getId() + ", ";
		sql += "'" + pergunta.getConteudo()+ "', ";
		sql += pergunta.getQuiz().getId() + " )";

		
		try { 
			db.execSQL(sql);
		} catch(Exception e) {
			
			return false;
		}

		return true;

	}
	public ArrayList<Pergunta> getRandomPerguntas(Quiz quiz, int maxRounds) {
		ArrayList<Pergunta> perguntas = null;
		String sql = "select * from pergunta where quiz_id = " + quiz.getId() + " ORDER BY RANDOM() limit "+ maxRounds;
		
		Cursor cursor;
		try {
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToFirst()){
				 perguntas = new ArrayList<Pergunta>();
				do{
					Pergunta pergunta = new Pergunta(cursor.getInt(0), cursor.getString(1));
					
					pergunta.setRespostas(getRespostas(pergunta));
					perguntas.add(pergunta);
				} while(cursor.moveToNext());
			}
		} catch(Exception e) {
			return null;
		}
		return perguntas;
	}

	private ArrayList<Resposta> getRespostas(Pergunta pergunta) {
		respostaDAO = new RespostaDAO(context,db);
		
		return respostaDAO.getRespostas(pergunta);
	}
	public void open() {
	    db = getWritableDatabase();
	}
	public void close(){
		db.close();

	}
}
