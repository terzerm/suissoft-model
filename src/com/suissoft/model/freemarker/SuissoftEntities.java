package com.suissoft.model.freemarker;

import java.util.ArrayList;
import java.util.List;

import freemarker.template.TemplateModelException;
import freemarker.template.TemplateSequenceModel;

public class SuissoftEntities implements TemplateSequenceModel {
	
	private final List<SuissoftEntity> entities = new ArrayList<>();

	@Override
	public SuissoftEntity get(int index) throws TemplateModelException {
		return entities.get(index);
	}
	
	@Override
	public int size() throws TemplateModelException {
		return entities.size();
	}
}
