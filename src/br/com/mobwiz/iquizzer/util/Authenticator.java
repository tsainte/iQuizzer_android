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
	WebService webService = new WebService();
	public Context context;
	public boolean authenticate(String username, String password){
		String jsonBody = verifyLoginOnCloud(username, password);
		boolean success = false;
		try{
			JSONObject jsonObject = new JSONObject(jsonBody);
			int id = jsonObject.getInt("id");
			success = jsonObject.getBoolean("success");
			if (success){
				SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
				SharedPreferences.Editor editor = preferences.edit();

				editor.putString("username", username);
				editor.putString("password",password);
				editor.putInt("usuario_id", id);
				editor.putBoolean("isAuth", success);
				editor.commit();
			}

		} catch (Exception e){
			e.printStackTrace();
		}
		return success;
	}


	public String verifyLoginOnCloud(String username, String password){
		 Usuario usuario = new Usuario();
		 usuario.setApelido(username);
		 usuario.setSenha(password);
		 
		 String parameters = "usuarios/validate";
		 String httpMethod = "PUT";
		 String jsonBody = createBody(usuario);
		 String result = webService.RESTCommand(parameters, httpMethod, jsonBody);
		 Log.i("iQuizzer",result);
		 return result;
	 }

	private String createBody(Usuario usuario) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("apelido", usuario.getApelido());
			jsonObject.put("senha", usuario.getSenha());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	/*-(void)authenticate:(NSString*)pUsername password:(NSString*)pPassword callbackView:(UIViewController*)view callbackMethod:(SEL)method{
	    username = pUsername;
	    password = pPassword;
	    callbackMethod = method;
	    callbackView = view;
	    [self verifyLoginOnCloud:username password:password];
	}

	//webservice verify if login is ok //TODO
	-(BOOL)verifyLoginOnCloud:(NSString*)username password:(NSString*)password{
	    UsuarioDAO* usuarioDAO = [[UsuarioDAO alloc] init];
	    [usuarioDAO login:username password:password callbackClass:self callbackMethod:@selector(resultAuth:)];

	    return YES;
	}
	-(void)resultAuth:(NSNumber*)success{

	    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
	    if([success intValue] > 0){
	        [defaults setObject:username forKey:@"username"];
	        [defaults setObject:password forKey:@"password"];
	        [defaults setObject:success forKey:@"usuario_id"];

	        [defaults setBool:YES forKey:@"isAuth"];
	    } else {
	        [defaults setBool:NO forKey:@"isAuth"];
	    }
	    [callbackView performSelector:callbackMethod withObject:success];
	}*/
}
