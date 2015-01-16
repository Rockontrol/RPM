package org.chonger.common.android.views;

import org.chonger.rpm.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;


/**
 * ����Fragment����
 * @author Daniel
 *
 */
public abstract class BaseFragment extends Fragment  {
	
	//private TempDataUtils dataSession;
	/**
	 * ��������ఴť
	 */
	public Button btnLeftButton=null;
	/**
	 * �������Ҳఴť
	 */
	public Button btnRightButton=null;
	
	/**
	 * ��������ఴť
	 */
//	public Button getLeftButton() {
//		if(btnLeftButton==null)
//			btnLeftButton=createButton();
//		return btnLeftButton;
//	}
	
	/**
	 * �������Ҳఴť
	 */
//	public Button getRightButton() {
//		if(btnRightButton==null)
//			btnRightButton=createButton();
//		return btnRightButton;
//	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//dataSession=TempDataUtils.getDataSession();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.initButton();
	}

	public View findViewById(int id)
	{
		return this.getView().findViewById(id);
	}
	
	public void finish()
	{
		this.getActivity().finish();
	}
	
	public Object getSystemService(String name)
	{
		return this.getActivity().getSystemService(name);
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
	 * ������ʾ����
	 * @return
	 */
	public abstract String setTitle();
	
	/**
	 * ��ť��ʼ�������������ʹ�ð�ť���������
	 */
	public abstract void initButton();
	
	/**
	 * �������õ�Button
	 * @return
	 */
	public Button createButton()
	{
		return (Button)LayoutInflater.from(this.getView().getContext()).inflate(R.layout.common_views_button,null);
	}
	
	/**
	 * �����������¼�
	 */
	public void hide(){};
	
	/**
	 * ��������ʾ�¼�
	 */
	public void show(){};
}