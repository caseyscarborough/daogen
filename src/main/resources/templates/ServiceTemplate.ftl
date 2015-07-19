package ${daoGen.packageName}.service;

import ${daoGen.packageName}.vo.${daoGen.clazz.name};

import java.util.List;

/**
 * Service layer interface for the ${daoGen.clazz.name} class.
 */
public interface ${daoGen.clazz.name}Service {

    /**
     * Retrieves a list of all ${daoGen.clazz.name} instances.
     */
    List<${daoGen.clazz.name}> findAll();

    /**
     * Finds a single ${daoGen.clazz.name} instance by its ID.
     *
     * @param id The ID of the ${daoGen.clazz.name} instance.
     */
    ${daoGen.clazz.name} findById(${daoGen.clazz.idColumn.type} id);

    /**
     * Saves a new ${daoGen.clazz.name} instance.
     *
     * @param ${daoGen.clazz.variableName} The ${daoGen.clazz.name} instance to save.
     */
    ${daoGen.clazz.name} save(${daoGen.clazz.name} ${daoGen.clazz.variableName});

    /**
     * Updates an existing ${daoGen.clazz.name} instance.
     *
     * @param ${daoGen.clazz.variableName} The ${daoGen.clazz.name} instance to update.
     */
    ${daoGen.clazz.name} update(${daoGen.clazz.name} ${daoGen.clazz.variableName});

    /**
     * Deletes an existing ${daoGen.clazz.name} instance.
     *
     * @param id The ID of the ${daoGen.clazz.name} instance to delete.
     */
    void delete(${daoGen.clazz.idColumn.type} id);
}