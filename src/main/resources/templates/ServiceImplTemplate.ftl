package ${daoGen.packageName}.service;

import ${daoGen.packageName}.dao.${daoGen.clazz.name}Dao;
import ${daoGen.packageName}.vo.${daoGen.clazz.name};

import java.util.List;

/**
 * Service layer implementation for the ${daoGen.clazz.name} class.
 *
 * @see {@link ${daoGen.clazz.name}Service}
 */
public class ${daoGen.clazz.name}ServiceImpl implements ${daoGen.clazz.name}Service {

    private ${daoGen.clazz.name}Dao ${daoGen.clazz.variableName}Dao;

    public ${daoGen.clazz.name}ServiceImpl(${daoGen.clazz.name}Dao ${daoGen.clazz.variableName}Dao) {
        this.${daoGen.clazz.variableName}Dao = ${daoGen.clazz.variableName}Dao;
    }

    public List<${daoGen.clazz.name}> findAll() {
        return ${daoGen.clazz.variableName}Dao.findAll();
    }

    public ${daoGen.clazz.name} findById(${daoGen.clazz.idColumn.type} id) {
        return ${daoGen.clazz.variableName}Dao.findById(id);
    }

    public ${daoGen.clazz.name} save(${daoGen.clazz.name} ${daoGen.clazz.variableName}) {
        return ${daoGen.clazz.variableName}Dao.save(${daoGen.clazz.variableName});
    }

    public ${daoGen.clazz.name} update(${daoGen.clazz.name} ${daoGen.clazz.variableName}) {
        return ${daoGen.clazz.variableName}Dao.update(${daoGen.clazz.variableName});
    }

    public void delete(${daoGen.clazz.idColumn.type} id) {
        ${daoGen.clazz.variableName}Dao.delete(id);
    }
}