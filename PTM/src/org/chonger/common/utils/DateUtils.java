package org.chonger.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���ڳ��ø�������
 * @author Daniel
 *
 */
public class DateUtils {
	/**���ø�ʽ���ַ�����yyyy-MM-dd*/
	public static final String FORMAT_STRING_TYPE1="yyyy-MM-dd";
	/**���ø�ʽ���ַ�����yyyy��MM��dd��*/
	public static final String FORMAT_STRING_TYPE2="yyyy��MM��dd��";
	/**���ø�ʽ���ַ�����yyyy��MM��*/
	public static final String FORMAT_STRING_TYPE3="yyyy��MM��";
	
	
	/**
	 * ���Զ����ģ���ʽ����ǰ����
	 * @param format
	 * @return
	 */
	public static String formatDateTime(String format)
	{
		return new SimpleDateFormat(format).format(new Date());
	}
	
}