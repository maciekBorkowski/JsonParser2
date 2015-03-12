package pl.maciejborkowski.jsonparser2;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public class JsonParser {
	
	public static String toJson(Object o) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		String json = "{ \n";
		Field[] fields = o.getClass().getDeclaredFields();
		String className = o.getClass().getName();
		json += "\"" + className.substring(className.lastIndexOf(".")+1, className.length()) + "\": {";
		for (Field field : fields) {
                    if(field.getType().isArray()) {
                        json += JsonParser.processArray(o, field); 
                    } else {
                        String fieldName = field.getName();
                        Object valueObject = null;
                        Object value = null;
                        if(Modifier.isPrivate(field.getModifiers())) {
                            String getterName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
                            Method method = o.getClass().getMethod(getterName);
                            valueObject = method.invoke(o, (Object[]) null);
                        }
                        if(valueObject != null) {
                            value = valueObject;
                        } else {
                            value = field.get(o);
                        }
                        json += "\"" + field.getName() + "\":";
                        if(field == fields[fields.length-1]) {
                                json += "\"" + value + "\"";
                        } else {
                                json += "\"" + value + "\",";
                        }
                    }	
		}
		json += "} \n";
		json += "}";
		return json;
		
	}
        
        private static String processArray(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
            String json = "\"" + field.getName() + "\":";
            Object array = field.get(o);
            int length = Array.getLength(array);
            json += "[";
            for (int i = 0; i < length; i ++) {
                Object element = Array.get(array, i);
                if(element == Array.get(array, length-1)) {
                    json += "\"" + element + "\"";
                } else {
                    json += "\"" + element + "\",";
                }
            }
            json += "],";
            return json;
        }
}
