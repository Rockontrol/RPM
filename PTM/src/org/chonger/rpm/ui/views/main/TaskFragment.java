package org.chonger.rpm.ui.views.main;

import org.chonger.common.android.views.BaseFragment;
import org.chonger.rpm.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TaskFragment extends BaseFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.view_main_task, container, false);
	}
	
	@Override
	public String setTitle() {
		return "����";
	}
	
	@Override
	public void initButton() {
		this.btnLeftButton=this.createButton();
		this.btnLeftButton.setText("δ��¼");
		
		this.btnRightButton=this.createButton();
		this.btnRightButton.setBackgroundResource(R.drawable.icon_add);
	}
	
}
