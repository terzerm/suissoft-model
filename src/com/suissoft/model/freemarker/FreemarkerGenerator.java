package com.suissoft.model.freemarker;

import java.io.OutputStreamWriter;
import java.util.Map;

import com.suissoft.model.partner.NaturalPerson;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerGenerator {
	public static void main(String[] args)
	{
		// Add the values in the datamodel
		final Map<String, ?> datamodel = ModelParser.parse(NaturalPerson.class);

		// Process the template using FreeMarker
		try {
			freemarkerDo(datamodel, "resources/suissoft.ftl");
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	// Process a template using FreeMarker and print the results
	static void freemarkerDo(Map<?,?> datamodel, String template) throws Exception
	{
		Configuration cfg = new Configuration();
		Template tpl = cfg.getTemplate(template);
		OutputStreamWriter output = new OutputStreamWriter(System.out);

		tpl.process(datamodel, output);
	}

}
