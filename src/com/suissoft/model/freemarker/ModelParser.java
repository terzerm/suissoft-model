package com.suissoft.model.freemarker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ModelParser {
	public static Map<String,?> parse(Class<?> modelClass) {
		final Map<String, Object> entity = new LinkedHashMap<>();
		entity.put("name", modelClass.getName());
		entity.put("simpleName", modelClass.getSimpleName());
		entity.put("package", modelClass.getPackage().getName());
		entity.put("fields", parseFields(modelClass));
		return entity;
	}

	private static List<?> parseFields(Class<?> modelClass) {
		final List<Object> fields = new ArrayList<>();
		for (Field field : modelClass.getDeclaredFields()) {
			fields.add(parseFields(field));
		}
		return fields;
	}

	private static Map<String,?> parseFields(Field field) {
		final Map<String, Object> fld = new LinkedHashMap<>();
		fld.put("name", field.getName());
		fld.put("type", field.getType());
		return fld;
	}
}
