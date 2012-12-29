package br.com.mobwiz.iquizzer;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import br.com.mobwiz.iquizzer.model.dao.QuizDAO;
import br.com.mobwiz.iquizzer.model.entities.Quiz;

public class BaixarQuizAdapter extends ArrayAdapter{
	   Context context; 
	    int layoutResourceId;    
	    ArrayList<Quiz> quizzes = null;
	    
		public BaixarQuizAdapter(Context context, int textViewResourceId, ArrayList<Quiz> quizzes) {
			super(context, textViewResourceId);
			this.context= context; 
			this.layoutResourceId = textViewResourceId;
			this.quizzes = quizzes;
			
		}
		
	   @Override
	   public View getView(int position, View convertView, ViewGroup parent) {
		   LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		   View row = inflater.inflate(R.layout.buy_cell, parent, false);
		   
		   Quiz quiz = quizzes.get(position);
		   
	       if (quiz != null) {
	    	   TextView titulo = (TextView) row.findViewById(R.id.titulo);
	    	   Button btnAction = (Button) row.findViewById(R.id.btnAction);

	    	   
	    	   titulo.setText(quiz.getTitulo());
	    	  // btnAction.setText("Baixar");
	    	   configureButton(btnAction,quiz.getId());
	    	   btnAction.setTag(quiz.getId()); //to get index!!

	       }
		   return row;
		   
	   }   
	   @Override
	   public int getCount(){
		   return quizzes.size();
	   }
	   public void configureButton(Button button, int id){
		    /*if ([dao find:index]){ //se encontrar algum quiz
		        [button setTitle:@"Instalado" forState:UIControlStateNormal];
		        [button setEnabled:NO];
		        [button setTitleColor:[UIColor grayColor] forState:UIControlStateNormal];
		    } else {
		        [button setTitle:@"Baixar" forState:UIControlStateNormal];
		        [button setTitleColor:[UIColor blueColor] forState:UIControlStateNormal];
		        [button setEnabled:YES];
		    }*/
		   QuizDAO dao = new QuizDAO(context);
		   if (dao.find(id) != null){
			   button.setText("Instalado");
			   button.setEnabled(false);
		   } else {
			   button.setText("Baixar");
			   button.setEnabled(true);
		   }
	   }
	  /* -(void)configureButton:(UIButton*)button forID:(NSNumber*)index{
		    if ([dao find:index]){ //se encontrar algum quiz
		        [button setTitle:@"Instalado" forState:UIControlStateNormal];
		        [button setEnabled:NO];
		        [button setTitleColor:[UIColor grayColor] forState:UIControlStateNormal];
		    } else {
		        [button setTitle:@"Baixar" forState:UIControlStateNormal];
		        [button setTitleColor:[UIColor blueColor] forState:UIControlStateNormal];
		        [button setEnabled:YES];
		    }
		}*/
}
