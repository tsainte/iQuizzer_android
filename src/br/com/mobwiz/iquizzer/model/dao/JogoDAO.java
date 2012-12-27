package br.com.mobwiz.iquizzer.model.dao;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import br.com.mobwiz.iquizzer.model.entities.Jogo;
import br.com.mobwiz.iquizzer.model.entities.Resultado;
import br.com.mobwiz.iquizzer.util.WebService;

public class JogoDAO extends SQLiteOpenHelper implements Serializable{
	private SQLiteDatabase db;
	Context context;
	WebService webService;

	public JogoDAO(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);

	}
	public JogoDAO(Context context){
		super(context, "iQuizzer", null, 1);
		this.context = context;
		webService =  new WebService(context);
		db = getWritableDatabase();
	}
	@Override
	public void onCreate(SQLiteDatabase db) {

		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public void saveOnCloud(Jogo jogo){
		String parameters = "jogos";
		String httpMethod = "POST";
		String jsonBody = createBody(jogo);
		webService.RESTCommand(parameters, httpMethod, jsonBody);
	}
	private String createBody(Jogo jogo) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		int usuario_id = preferences.getInt("usuario_id", 0);
		
		JSONArray jsonResultados = createResultadoBody(jogo.getResultados());
		JSONObject jsonObject = new JSONObject();
		try{
			jsonObject.put("dia", jogo.getDia());
			jsonObject.put("hora", jogo.getHora());
			jsonObject.put("pontos", jogo.getPontos());
			jsonObject.put("resultados_attributes", jsonResultados);
			jsonObject.put("user_id", usuario_id);
		} catch (Exception e ){
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	private JSONArray createResultadoBody(ArrayList<Resultado> resultados) {
		JSONArray jsonResultados = new JSONArray();
		try{

			for (Resultado r : resultados){
				JSONObject jsonResultado = new JSONObject();
				jsonResultado.put("resposta_id", r.getResposta().getId());
				jsonResultado.put("resposta_conteudo", r.getResposta().getConteudo());
				jsonResultado.put("pergunta_conteudo", r.getResposta().getPergunta().getConteudo());
				jsonResultados.put(jsonResultado);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return jsonResultados;
	}
}
