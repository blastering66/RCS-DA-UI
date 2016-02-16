package ui.tech.sahabatmakara.activity;

import ui.tech.sahabatmakara.util.ParameterCollections;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Activity_Splashscreen extends Activity {
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity__splashscreen);

		sp = getSharedPreferences(ParameterCollections.SH_NAME, Context.MODE_PRIVATE);

		ImageView img_Logo = (ImageView)findViewById(R.id.img);
		Animation animate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_in);
		img_Logo.startAnimation(animate);
		new Async_Load().execute();
	}

	
	private class Async_Load extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try{
				Thread.sleep(3000);
			}catch(Exception e){
				
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			if(sp.getBoolean(ParameterCollections.SH_LOGGED, false)){
				startActivity(new Intent(getApplicationContext(), Activity_MenuSlider.class));
				finish();
			}else{
				startActivity(new Intent(getApplicationContext(), Activity_Login.class));
				finish();
			}
			
//			startActivity(new Intent(getApplicationContext(), Activity_MenuSlider.class));


		}
	}
	
	
}
