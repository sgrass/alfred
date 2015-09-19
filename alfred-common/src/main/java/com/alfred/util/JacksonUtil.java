package com.alfred.util;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

class Student {
	private int id;
	private String name;
	private Date time;
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	
	public Date getTime() {
		return time;
	}

	
	public void setTime(Date time) {
		this.time = time;
	}
}

public class JacksonUtil {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		String jsonStr = "{\"id\":1,\"name\":\"asdasd\",\"time\":1408432030490}";
		ObjectMapper mapper = new ObjectMapper();
		
//		//配置为true表示mapper接受只有一个元素的数组的反序列化
//		mapper.configure(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//		 
//		//配置为false表示mapper在遇到mapper对象中存在json对象中没有的数据变量时不报错，可以进行反序列化
//		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		 
//		//新版的jackson设置mapper的方法，功能同上
//		mapper.getDeserializationConfig().without(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
//		 
//		//定义针对日期类型的反序列化时的数据格式
//		mapper.getDeserializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		//格式化输出   尽量使用这种方式  之前的mapper.getSerializationConfig().setXxx方法现在很多都已经被标注为@Deprecated了
		mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,Boolean.TRUE);
		Student stu = new Student();
		stu.setId(1);
		stu.setName("asdasd");
		stu.setTime(new Date());
		
		System.out.println(mapper.writeValueAsString(stu));
		
		
		Student student = mapper.readValue(jsonStr, Student.class);
		System.out.println(student.getId()+"-----"+student.getTime());
	}

}
