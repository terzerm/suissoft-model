package com.suissoft.model.freemarker;

import java.util.LinkedHashMap;
import java.util.Map;

import freemarker.template.SimpleCollection;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class SuissoftEntity implements TemplateHashModelEx {
	
	private final Map<String, SuissoftField> fields = new LinkedHashMap<>();

	@Override
	public TemplateModel get(String key) throws TemplateModelException {
		return fields.get(key);
	}

	@Override
	public boolean isEmpty() throws TemplateModelException {
		return fields.isEmpty();
	}

	@Override
	public int size() throws TemplateModelException {
		return fields.size();
	}

	@Override
	public TemplateCollectionModel keys() throws TemplateModelException {
		return new SimpleCollection(fields.keySet());
	}

	@Override
	public TemplateCollectionModel values() throws TemplateModelException {
		return new SimpleCollection(fields.values());
	}
	
}
