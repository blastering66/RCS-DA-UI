package ui.tech.sahabatmakara.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import ui.tech.sahabatmakara.fragment.Fragment_Donate_Step_Info;
import ui.tech.sahabatmakara.fragment.Fragment_Program_Step_Info;
import ui.tech.sahabatmakara.util.Font;

public class Activity_Donate_Program extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_donate_program);
		
		initAllView();

		if(savedInstanceState == null){
			FragmentManager fm = getSupportFragmentManager();
			fm.beginTransaction().replace(R.id.frame_container, new Fragment_Program_Step_Info()).commit();
		}
	}
	
	private void initAllView(){
		TextView tv_Title_ActionBar = (TextView)findViewById(R.id.tv_title_actionbar);
		
		tv_Title_ActionBar.setText("DONASI SAHABAT");
		tv_Title_ActionBar.setTypeface(Font.setFontGaramond(getApplicationContext()));
		
		ImageView img = (ImageView)findViewById(R.id.img_bg);
		ImageView img_Back = (ImageView)findViewById(R.id.btn_actionbar_back);
		img_Back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	public void onBackPressed() {
		finish();
	}
}
