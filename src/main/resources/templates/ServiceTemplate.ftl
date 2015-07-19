package ${packageName}.service;

import ${packageName}.vo.${className};

import java.util.List;

/**
 * Service layer interface for the ${className} class.
 */
public interface ${className}Service {

    /**
     * Retrieves a list of all ${className} instances.
     */
    List<${className}> findAll();

    /**
     * Finds a single ${className} instance by its ID.
     *
     * @param id The ID of the ${className} instance.
     */
    ${className} findById(${idClass} id);

    /**
     * Saves a new ${className} instance.
     *
     * @param ${variableName} The ${className} instance to save.
     */
    ${className} save(${className} ${variableName});

    /**
     * Updates an existing ${className} instance.
     *
     * @param ${variableName} The ${className} instance to update.
     */
    ${className} update(${className} ${variableName});

    /**
     * Deletes an existing ${className} instance.
     *
     * @param id The ID of the ${className} instance to delete.
     */
    void delete(${idClass} id);
}