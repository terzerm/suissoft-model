package com.suissoft.model.freemarker;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import freemarker.template.SimpleCollection;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class SuissoftField implements TemplateHashModelEx {
	
	private final Map<String, SimpleScalar> map = new LinkedHashMap<>();

	public SuissoftField(Field field) {
		this(field.getName(), field.getType());
	}
	public SuissoftField(String name, Class<?> type) {
		map.put("name", new SimpleScalar(name));
		map.put("type", new SimpleScalar(type.getName()));
	}

	@Override
	public TemplateModel get(String key) throws TemplateModelException {
		return map.get(key);
	}

	@Override
	public boolean isEmpty() throws TemplateModelException {
		return false;
	}

	@Override
	public int size() throws TemplateModelException {
		return 2;
	}

	@Override
	public TemplateCollectionModel keys() throws TemplateModelException {
		return new SimpleCollection(map.keySet());
	}

	@Override
	public TemplateCollectionModel values() throws TemplateModelException {
		return new SimpleCollection(map.values());
	}

	
}
