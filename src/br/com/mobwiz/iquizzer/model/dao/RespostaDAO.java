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

public class RespostaDAO extends SQLiteOpenHelper implements Serializable{
	private SQLiteDatabase db;
	Context context;
	WebService webService = new WebService();
	
	public RespostaDAO(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	public RespostaDAO(Context context){
		super(context, "iQuizzer", null, 1);
		this.context = context;
		db = getWritableDatabase();
		
	}
	public RespostaDAO(Context context, SQLiteDatabase db){
		super(context, "iQuizzer", null, 1);
		this.context = context;
		this.db = db;
		onCreate(db);
	}

	public void downloadJSONRespostas(JSONArray jsonArray, Pergunta pergunta) throws JSONException {
		for (int i = 0; i < jsonArray.length(); i++){
			JSONObject jsonResposta = jsonArray.getJSONObject(i);
			
			Resposta resposta = new Resposta(jsonResposta.getInt("id"),jsonResposta.getString("conteudo"), jsonResposta.getBoolean("correta"));

			resposta.setPergunta(pergunta);
			
			insert(resposta);
		}
		
	}
	private boolean insert(Resposta resposta) {
		String sql = "insert into resposta values(";
		sql += resposta.getId() + ", ";
		sql += "'" + resposta.getConteudo()+ "', ";
		sql += Functions.boolToInt(resposta.isCorreta()) + ", ";
		sql += resposta.getPergunta().getId() + " )";

		
		try { 
			db.execSQL(sql);
		} catch(Exception e) {
			
			return false;
		}

		return true;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table if not exists resposta ( " +
				  "id integer primary key, conteudo text not null, correta boolean not null, pergunta_id integer)";
			
		db.execSQL(sql);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public ArrayList<Resposta> getRespostas(Pergunta pergunta) {
		ArrayList<Resposta> respostas = null;
		String sql = "select * from resposta where pergunta_id = " + pergunta.getId();
		Cursor cursor;
		
		try {
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToFirst()){
				 respostas = new ArrayList<Resposta>();
				do{
					Resposta resposta = new Resposta(cursor.getInt(0), cursor.getString(1), Functions.intToBool(cursor.getInt(2)));
					resposta.setPergunta(pergunta);
					respostas.add(resposta);
				} while(cursor.moveToNext());
			}
			
;
		} catch(Exception e) {
			return null;
		}
		return respostas;
	}
	public void open() {
	    db = getWritableDatabase();
	}
	public void close(){
		db.close();
	}


}
