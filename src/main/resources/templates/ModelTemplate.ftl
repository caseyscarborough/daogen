package ${daoGen.packageName}.${Constants.MODEL_PACKAGE_NAME};

public class ${daoGen.clazz.name} {

<#list daoGen.clazz.fields as field>
    private ${field.type} ${field.name};
</#list>

    public ${daoGen.clazz.name}() {
    }

    public ${daoGen.clazz.name}(<#list daoGen.clazz.fields as field>${field.type} ${field.name}<#sep>, </#list>) {
    <#list daoGen.clazz.fields as field>
        this.${field.name} = ${field.name};
    </#list>
    }

<#list daoGen.clazz.fields as field>
    public ${field.type} get${field.capitalizedName}() {
        return ${field.name};
    }

    public void set${field.capitalizedName}(${field.type} ${field.name}) {
        this.${field.name} = ${field.name};
    }

</#list>
    @Override
    public String toString() {
        return "${daoGen.clazz.name}{" +
        <#list daoGen.clazz.fields as field>
            "${field.name}='" + ${field.name} + "'<#sep>,</#sep>" +
        </#list>
            "}";
    }
}
