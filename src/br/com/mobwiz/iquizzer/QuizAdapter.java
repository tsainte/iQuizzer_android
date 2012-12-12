package br.com.mobwiz.iquizzer;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import br.com.mobwiz.iquizzer.model.entities.Quiz;

public class QuizAdapter extends ArrayAdapter{
	   Context context; 
	    int layoutResourceId;    
	    ArrayList<Quiz> quizzes = null;
	    
	    
		public QuizAdapter(Context context, int textViewResourceId, ArrayList<Quiz> quizzes) {
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
		    	   
		    	   btnAction.setVisibility(View.INVISIBLE);
		    	   titulo.setText(quiz.getTitulo());
		    	   

		       }
			   return row;

		   }   
		   @Override
		   public int getCount(){
			   if (quizzes == null) return 0;
			   return quizzes.size();
		   }

}
