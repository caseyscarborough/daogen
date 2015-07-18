package ${packageName}.dao;

import ${packageName}.vo.${className};

import java.util.List;

/**
 * Data access layer for the ${className} class.
 */
public interface ${className}Dao {

    /**
     * Returns a list of ${className} instances from the data source.
     */
    public List<${className}> findAll();

    /**
     * Finds a single ${className} instance from the data source by id.
     *
     * @param id The id of the ${className} to find.
     */
    public ${className} findById(${idClass} id);

    /**
     * Saves a new ${className} instance to the data source.
     *
     * @param ${variableName} The ${className} instance to persist.
     */
    public ${className} save(${className} ${variableName});

    /**
     * Updates an existing ${className} instance in the data source.
     *
     * @param ${variableName} The ${className} instance to update.
     */
    public ${className} update(${className} ${variableName});

    /**
     * Deletes an existing ${className} instance from the data source.
     *
     * @param id The id of the instance to delete.
     */
    public void delete(${idClass} id);
}