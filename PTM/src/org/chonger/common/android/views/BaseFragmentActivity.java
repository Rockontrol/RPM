package org.chonger.common.android.views;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.chonger.rpm.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * ����Fragment���ֹ�����
 * @author Daniel
 * modify 2015-01-16	�޸İ�ť�߼��������ֻԤ����ťλ�ã���ÿ��ʹ�õĽ����Լ�����ť�����ã����ֻ���ð�ť����������ʾ��������ʾ��Ϊnull����ʾ��
 */
public abstract class BaseFragmentActivity extends FragmentActivity {
	
	public BaseFragmentActivity()
	{
		if(fragments==null)
			fragments=new LinkedHashMap<String,BaseFragment>();
		fragments.clear();
		
		if(btns==null)
			btns=new LinkedHashMap<String,String>();
		btns.clear();
	}

	private HashMap<String,BaseFragment> fragments;
	private HashMap<String,String> btns;
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	
	private RadioGroup rdoBtns;
	private TextView titleText;
	private RelativeLayout rtlButtonBox,rtrButtonBox;
	private Button leftButton,rightButton;
	
	
	
	private String key;
	
	//private TempDataUtils dataSession;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
		this.setContentView(R.layout.common_views_title);
		this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.common_views_title);
		titleText = (TextView) findViewById(R.id.titleText);
		
		rtlButtonBox=(RelativeLayout)findViewById(R.id.tlButtonBox);
		rtrButtonBox=(RelativeLayout)findViewById(R.id.trButtonBox);
		
		rtlButtonBox.setOnClickListener(titleButtonClickListener);
		rtrButtonBox.setOnClickListener(titleButtonClickListener);
		
//		leftButton=(Button)findViewById(R.id.tlButton);
//		rightButton=(Button)findViewById(R.id.trButton);
		
		//��ʼ�����ֹ�����
		fragmentManager=this.getSupportFragmentManager();
		
//		backButtonVisibility(false);
		//dataSession=TempDataUtils.getDataSession();
		
//		setLeftButton();
	}
	
	//��ť���¼�����
	private OnClickListener titleButtonClickListener=new OnClickListener(){
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
				case R.id.tlButtonBox:
					if(leftButton!=null)
						leftButton.performClick();
					break;
				case R.id.trButtonBox:
					if(rightButton!=null)
						rightButton.performClick();
					break;
			}
		}
	};
	
//	public void setLeftButton()
//	{
//		leftButton.setText("δ��¼");
//		rightButton.setBackgroundResource(R.drawable.icon_add);
//		
//	}
	
	
	
	
	
	/**
	 * ���÷��ذ�ť�Ƿ�����
	 * @param enable
	 */
//	public void backButtonVisibility(boolean enable)
//	{
//		if(rLayout==null)
//			rLayout=(RelativeLayout)findViewById(R.id.rlBack);
//		rLayout.setVisibility(enable?View.VISIBLE:View.INVISIBLE);
//	}
	
	/**
	 * ��ָ����{@link BaseFragment}Ԫ��id�󶨵�ָ����btnid��ť��
	 * @param id
	 */
	public void addTabs(int id,int btnid)
	{
		BaseFragment fragmentElement = (BaseFragment)fragmentManager.findFragmentById(id);
		if(fragmentElement!=null)
		{
			fragments.put(id+"", fragmentElement);
			btns.put(btnid+"",id+"");
		}
	}
	
	//������֡��ʾ�����滻Key
	public void ShowOther(int id)
	{
		hiden();
		BaseFragment fragmentElement=(BaseFragment)fragmentManager.findFragmentById(id);
		String _text=fragmentElement.setTitle();
		titleText.setText(_text);
		fragmentElement.show();
		if((leftButton=fragmentElement.btnLeftButton)!=null)
		{
			leftButton.setId(R.id.title_button_left_id);
			this.rtlButtonBox.addView(fragmentElement.btnLeftButton);
		}
		if((rightButton=fragmentElement.btnRightButton)!=null)
		{
			rightButton.setId(R.id.title_button_right_id);
			this.rtrButtonBox.addView(fragmentElement.btnRightButton);
		}
		fragmentTransaction.show(fragmentElement).commit();
	}
	
	//ȡ��
	public void CancelOther()
	{
		show(Integer.parseInt(key));
	}
	
	/**
	 * 
	 */
	private void hiden()
	{
		//Daniel 2015-01-18	1:��ӽ����л��������뷨
		InputMethodManager imm = ( InputMethodManager )this.getSystemService( Context.INPUT_METHOD_SERVICE ); 
		if(imm.isActive())
			imm.hideSoftInputFromWindow(this.titleText.getWindowToken(), 0);
		//���ؽ���
		fragmentTransaction=fragmentManager.beginTransaction();
		for(BaseFragment fragmentElement : fragments.values())
		{
			fragmentElement.hide();
			//Daniel �������л���ʱ����а�ť���л������Ƴ�ָ�����ܰ�ť
			rtlButtonBox.removeAllViews();
			rtrButtonBox.removeAllViews();
			fragmentTransaction.hide(fragmentElement);
		}
		

	}
	
	/**
	 * չʾĬ�����ݣ�Ĭ��Ϊ��һ����ӵ�{@link BaseFragment}Ԫ��
	 */
	public void show()
	{
		if(fragments.size()>0)
			show(Integer.parseInt(fragments.keySet().toArray()[0].toString()));
	}
	
	/**
	 * ͨ��id��ʾ{@link BaseFragment}Ԫ��
	 * @param id
	 */
	public void show(int id)
	{
		if(fragments.containsKey(id+""))
		{
			
			hiden();
			key=id+"";
			BaseFragment fragmentElement = fragments.get(key);
			String _text=fragmentElement.setTitle();
			titleText.setText(_text);
			fragmentElement.show();
			//Daniel ��ȡָ���Ĺ��ܰ�ť������ʾ
			if((leftButton=fragmentElement.btnLeftButton)!=null)
			{
				leftButton.setId(R.id.title_button_left_id);
				this.rtlButtonBox.addView(fragmentElement.btnLeftButton);
			}
			if((rightButton=fragmentElement.btnRightButton)!=null)
			{
				rightButton.setId(R.id.title_button_right_id);
				this.rtrButtonBox.addView(fragmentElement.btnRightButton);
			}
			
			fragmentTransaction.show(fragmentElement).commit();
		}
	}
	
//	/**
//	 * put the data in session
//	 * @param key
//	 * @param value
//	 */
//	public void setSessionAttribute(String key,Object value)
//	{
//		dataSession.setAttribute(key, value);
//	}
//	
//	/**
//	 * get data value from session
//	 * @param key
//	 * @return
//	 */
//	public Object getSessionAttribute(String key)
//	{
//		return dataSession.getAttribute(key);
//	}
//	
//	/**
//	 * clear some data value
//	 * @param keys
//	 */
//	public void clearSessionAttribute(String... keys)
//	{
//		dataSession.clearAttribute(keys);
//	}
	
	/**
	 * ��ָ��id��{@link RadioGroup}Ԫ��
	 * @param id
	 */
	public void bindBtns(int id)
	{
		rdoBtns=(RadioGroup)findViewById(id);
		if(rdoBtns!=null)
		{
			rdoBtns.setOnCheckedChangeListener(new OnCheckedChangeListener(){
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					if(btns.containsKey(checkedId+""))
					{
						BaseFragmentActivity.this.show(Integer.parseInt(btns.get(checkedId+"")));
					}
				}
			});
		}
	}
	
	//��������ť����¼�
//	private OnClickListener buttonOnClickListener=new OnClickListener(){
//		@Override
//		public void onClick(View v) {
//			switch(v.getId())
//			{
//				case R.id.title_button_left_id:
//					
//					break;
//				case R.id.title_button_right_id:
//					LayoutMainActivity.getThis().ShowOther(R.id.frament_task_add);
//					break;
//			}
//		}
//	};
	
}