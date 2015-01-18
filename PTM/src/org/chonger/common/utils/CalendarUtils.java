package org.chonger.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * �����ĳ��ø�������
 * ��Ҫ����������������ʹ��
 * @author Daniel
 *
 */
public class CalendarUtils {
	
	/**
	 * �����Ƿ�������
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year)
	{
		if(year%100==0&&year%400==0)
			return true;
		else if(year%100!=0&&year%4==0)
			return true;
		return false;
	}
	
	/**
	 * ��ȡָ�����ڵ���������
	 * @param date
	 * @return
	 */
	public static String[] getCalendarData(Date date)
	{
		String[] datas=new String[42];
		if(date!=null)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, 1-calendar.get(Calendar.DAY_OF_MONTH));
			int month=calendar.get(Calendar.MONTH)+1;
			int week=calendar.get(Calendar.DAY_OF_WEEK)-1;
			if(week==1)//��һ				
				calendar.add(Calendar.DAY_OF_MONTH, -8);
			else
				calendar.add(Calendar.DAY_OF_MONTH, 0-week);
			//չʾ
			for(int i=0;i<datas.length;i++)
			{
				datas[i]=calendar.get(Calendar.DAY_OF_MONTH)+" "+((calendar.get(Calendar.MONTH)+1)==month?"0":"1");
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
		}
		return datas;
	}
	
	public static void main(String[] args)
	{
		
	}
	
}
