package test.java01;

import java.util.HashMap;

// 타입 고정할 때는 generic
public class ParamMap extends HashMap<String, Object>{
	
	private ParamMap() {};
	
	public static ParamMap init() {
		// static : 외부에서 불러오기 위함
		ParamMap map = new ParamMap();
		return map;
	}
	
	public String getString(String key) {
		Object object = this.get(key);
		if(object == null) {
			return null;
		} else {
			//return object.toString();
			return String.valueOf(object);
		}
	}
	
	// Integer는 null이 가능
	// int는 default값 0 
	// 상속 장점 -
	public Integer getInteger(String key) {
		Object object = this.get(key);
		if(object == null) {
			return null;
		} else {
			return Integer.parseInt(String.valueOf(object));
		}
	}
	
	// 동적으로 타입이 변할 때는 가변인자
	public <T>T get(String key, Class<T> clazz){
		Object object = this.get(key);
		return (T) object;	// T 타입으로 캐스팅
	}
}
