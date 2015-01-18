package org.chonger.rpm.ui.views.main;

import org.chonger.common.android.views.BaseFragment;
import org.chonger.rpm.R;
import org.chonger.rpm.ui.views.LayoutMainActivity;
import org.chonger.rpm.ui.views.vp.LoginFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class TaskFragment extends BaseFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		return inflater.inflate(R.layout.view_main_task, container, false);
	}
	
	@Override
	public String setTitle() {
		return this.getString(R.string.menu_task);
	}
	
	@Override
	public void initButton() {
		this.btnLeftButton=this.createButton();
		this.btnLeftButton.setText("δ��¼");
		this.btnLeftButton.setOnClickListener(buttonOnClickListener);
		
		this.btnRightButton=this.createButton();
		this.btnRightButton.setBackgroundResource(R.drawable.icon_add);
		this.btnRightButton.setOnClickListener(buttonOnClickListener);
	}
	
//	public void leftButtonOnClickListener(View v)
//	{
//		
//	}
//	
//	public void rightButtonOnClickListener(View v)
//	{
//		
//	}
	
	private OnClickListener buttonOnClickListener=new OnClickListener(){
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
				case R.id.title_button_left_id:
					LayoutMainActivity.getThis().ShowOther(R.id.frament_vp_login);
					break;
				case R.id.title_button_right_id:
					LayoutMainActivity.getThis().ShowOther(R.id.frament_task_add);
					break;
			}
		}
	};
}
