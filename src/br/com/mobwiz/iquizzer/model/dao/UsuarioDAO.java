package br.com.mobwiz.iquizzer.model.dao;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.mobwiz.iquizzer.model.entities.Usuario;
import br.com.mobwiz.iquizzer.util.WebService;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UsuarioDAO  extends SQLiteOpenHelper implements Serializable{
	private SQLiteDatabase db;
	Context context;
	WebService webService;
	
	public UsuarioDAO(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}


	

		 
	    /*currentUsuario = [NSEntityDescription insertNewObjectForEntityForName:entity inManagedObjectContext:managedContext];
	    currentUsuario.apelido = username;
	    currentUsuario.senha = password;
	    
	    callbackClass = classCallback;
	    callbackMethod = methodCallback;
	    
	    NSString* parameters = @"usuarios/validate";
	    NSString* method = @"PUT";
	    NSData* body = [self createBody:currentUsuario];
	    
	    [webService RESTCommand:parameters HTTPMethod:method jsonBody:body onFinishObj:self onFinishSel:@selector(isValidLogin:)];
	}
	-(void)isValidLogin:(NSData*)jsonData{
	    //{"success":true,"id":1}

	    
	    NSError* error;
	    NSDictionary* jsonObj = [NSJSONSerialization JSONObjectWithData:jsonData options:kNilOptions error:&error];
	    NSNumber *success = [jsonObj objectForKey:@"success"];
	    if ([success intValue] > 0){
	        currentUsuario.id = [jsonObj objectForKey:@"id"];
	        //pegar outros campos?
	        [self saveContext];
	    }
	    NSLog(@"isValidLogin");
	    //AppDelegate - login
	    [callbackClass performSelector:callbackMethod withObject:currentUsuario.id];


	}*/

}
