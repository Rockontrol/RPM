package org.chonger.common.utils;

import java.util.List;
/**
 * �ַ������ø�������
 * 
 * @author Daniel
 * 
 */
public class StringUtils {
	/**
	 * ָ�����ַ����Ƿ�Ϊ��
	 * 
	 * @param string
	 * @return
	 * 
	 * @author Daniel
	 */
	public static boolean IsEmpty(String string) {
		return (string == null || "".equals(string) || "".equals(string.trim()));
	}

	public static boolean IsArrayEmpty(String[] string) {
		for (int i = 0; i < string.length; i++) {
			if (StringUtils.IsEmpty(string[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ָ���ļ����Ƿ�Ϊ��
	 * 
	 * @param list
	 * @return
	 */
	public static boolean IsEmpty(List list) {
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ��ָ������ʼcount���ַ�ת��Ϊ��д
	 * 
	 * @param value
	 * @param count
	 * @return
	 * 
	 * @author Daniel
	 */
	public static String toUpperCase(String value, int count) {
		if (IsEmpty(value))
			return value;
		else if (count > value.length())
			return value;
		else {
			return value.substring(0, count).toUpperCase()
					+ value.substring(count);
		}
	}

	/**
	 * ���ַ���ת��ΪURL����
	 * 
	 * @param value
	 * @return
	 * 
	 * @author Daniel
	 * 
	 */
	public static String string2Url(String value) {
		if (IsEmpty(value))
			return value;
		return java.net.URLEncoder.encode(java.net.URLEncoder.encode(value));
	}
}
