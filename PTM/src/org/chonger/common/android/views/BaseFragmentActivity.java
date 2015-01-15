package org.chonger.common.android.views;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.chonger.rpm.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * ����Fragment���ֹ�����
 * @author Daniel
 *
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
	private ImageView imgView;
	private RelativeLayout rLayout;
	
	private String key;
	
	//private TempDataUtils dataSession;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
		this.setContentView(R.layout.common_views_title);
		this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.common_views_title);
		titleText = (TextView) findViewById(R.id.titleText);
		rLayout=(RelativeLayout)findViewById(R.id.rlBack);
		
		//��ʼ�����ֹ�����
		fragmentManager=this.getSupportFragmentManager();
		
		backButtonVisibility(false);
		
		//dataSession=TempDataUtils.getDataSession();
	}
	
	/**
	 * ���÷��ذ�ť�Ƿ�����
	 * @param enable
	 */
	public void backButtonVisibility(boolean enable)
	{
		if(rLayout==null)
			rLayout=(RelativeLayout)findViewById(R.id.rlBack);
		rLayout.setVisibility(enable?View.VISIBLE:View.INVISIBLE);
	}
	
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
	
	private void hiden()
	{
		fragmentTransaction=fragmentManager.beginTransaction();
		for(BaseFragment fragmentElement : fragments.values())
		{
			fragmentElement.hide();
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
}