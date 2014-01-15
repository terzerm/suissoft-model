package ${package};

public interface ${simpleName} {

<#list fields as field>
	${field.type} get${field.name}();
	void set${field.name}(${field.type} value);
</#list>

}