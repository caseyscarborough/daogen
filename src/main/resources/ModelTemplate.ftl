package ${packageName}.vo;

public class ${className} {

${fieldDeclarations}
    public ${className}() {
    }

    public ${className}(${fieldList}) {
${fieldConstructorSetters}    }
${fieldGettersAndSetters}
    @Override
    public String toString() {
        return "${className}{" +
${toString}            "}";
    }
}
