package ${packageName}.service;

import ${packageName}.dao.${className}Dao;
import ${packageName}.vo.${className};

import java.util.List;

/**
 * Service layer implementation for the ${className} class.
 *
 * @see {@link ${className}Service}
 */
public class ${className}ServiceImpl implements ${className}Service {

    private ${className}Dao ${variableName}Dao;

    public ${className}ServiceImpl(${className}Dao ${variableName}Dao) {
        this.${variableName}Dao = ${variableName}Dao;
    }

    public List<${className}> findAll() {
        return ${variableName}Dao.findAll();
    }

    public ${className} findById(${idClass} id) {
        return ${variableName}Dao.findById(id);
    }

    public ${className} save(${className} ${variableName}) {
        return ${variableName}Dao.save(${variableName});
    }

    public ${className} update(${className} ${variableName}) {
        return ${variableName}Dao.update(${variableName});
    }

    public void delete(${idClass} id) {
        ${variableName}Dao.delete(id);
    }
}