package com.cx.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.alfred.model.User;

/**
 * 
 * @author grass
 *
 */
public class Reflect {

	public static Method getGetMethod(Class objectClass, String fieldName) {
		StringBuffer sb = new StringBuffer();
		sb.append("get");
		sb.append(fieldName.substring(0, 1).toUpperCase());
		sb.append(fieldName.substring(1));
		try {
			return objectClass.getMethod(sb.toString());
		} catch (Exception e) {
		}
		return null;
	}

	public static Method getSetMethod(Class objectClass, String fieldName) {
		try {
			Class[] parameterTypes = new Class[1];
			Field field = objectClass.getDeclaredField(fieldName);
			parameterTypes[0] = field.getType();
			StringBuffer sb = new StringBuffer();
			sb.append("set");
			sb.append(fieldName.substring(0, 1).toUpperCase());
			sb.append(fieldName.substring(1));
			Method method = objectClass.getMethod(sb.toString(), parameterTypes);
			return method;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void invokeSet(Object o, String fieldName, Object value) {
		Method method = getSetMethod(o.getClass(), fieldName);
		try {
			method.invoke(o, new Object[] { value });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object invokeGet(Object o, String fieldName) {
		Method method = getGetMethod(o.getClass(), fieldName);
		try {
			return method.invoke(o, new Object[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		User user = new User();
		Field fields[] = user.getClass().getDeclaredFields();
		String[] name = new String[fields.length];
		Object[] value = new Object[fields.length];
		
		Object obj = new User();

		Field.setAccessible(fields, true);

		for (int i = 0; i < name.length; i++) {
			name[i] = fields[i].getName();
			
			invokeSet(obj, "accountName","aaa");
			
			System.out.println(name[i]);
			
			value[i] = fields[i].get(user);
			
			System.out.println(value[i]);
		}

	}
}
