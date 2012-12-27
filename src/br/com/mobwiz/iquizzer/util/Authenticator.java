package br.com.mobwiz.iquizzer.util;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import br.com.mobwiz.iquizzer.model.dao.UsuarioDAO;
import br.com.mobwiz.iquizzer.model.entities.Usuario;

public class Authenticator {
	public Context context;
	WebService webService = new WebService(context);

	
	public String login(String username, String password){
		 Usuario usuario = new Usuario();
		 usuario.setApelido(username);
		 usuario.setSenha(password);
		 
		 String parameters = "api/v1/tokens.json";
		 String httpMethod = "POST";
		 String jsonBody = createBody(usuario);
		 String result = webService.RESTCommand(parameters, httpMethod, jsonBody);
		 
		 return isValidLogin(result, usuario);
		
	}

	private String isValidLogin(String jsonBody, Usuario usuario){
		String wReturn = "";
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		try{
			JSONObject jsonObject = new JSONObject(jsonBody);
			String token;
			String message = "";
			try{
				token = jsonObject.getString("token");
			} catch (Exception e){
				token = null;
				message = jsonObject.getString("message");
			}
			if (token != null){
				int id = jsonObject.getInt("id");
				
				editor.putString("username", usuario.getApelido());
				editor.putString("token", token);
				//editor.putString("password",password);
				editor.putInt("usuario_id", id);
				editor.putBoolean("isAuth", true);
				wReturn = "ok";
			} else {
				editor.putBoolean("isAuth", false);
				wReturn = message;
			}
			editor.commit();


		} catch (Exception e){
			e.printStackTrace();
		}
		return wReturn;
	}

	public void logout(){
	    
	}
	private String createBody(Usuario usuario) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("username", usuario.getApelido());
			jsonObject.put("password", usuario.getSenha());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
}
