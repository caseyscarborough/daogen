package ${daoGen.packageName}.dao;

import ${daoGen.packageName}.vo.${daoGen.clazz.name};

import java.util.List;

/**
 * Data access layer for the ${daoGen.clazz.name} class.
 */
public interface ${daoGen.clazz.name}Dao {

    /**
     * Returns a list of ${daoGen.clazz.name} instances from the data source.
     */
    List<${daoGen.clazz.name}> findAll();

    /**
     * Finds a single ${daoGen.clazz.name} instance from the data source by id.
     *
     * @param id The id of the ${daoGen.clazz.name} to find.
     */
    ${daoGen.clazz.name} findById(${daoGen.clazz.idField.type} id);

    /**
     * Saves a new ${daoGen.clazz.name} instance to the data source.
     *
     * @param ${daoGen.clazz.variableName} The ${daoGen.clazz.name} instance to persist.
     */
    ${daoGen.clazz.name} save(${daoGen.clazz.name} ${daoGen.clazz.variableName});

    /**
     * Updates an existing ${daoGen.clazz.name} instance in the data source.
     *
     * @param ${daoGen.clazz.variableName} The ${daoGen.clazz.name} instance to update.
     */
    ${daoGen.clazz.name} update(${daoGen.clazz.name} ${daoGen.clazz.variableName});

    /**
     * Deletes an existing ${daoGen.clazz.name} instance from the data source.
     *
     * @param id The id of the instance to delete.
     */
    void delete(${daoGen.clazz.idField.type} id);
}