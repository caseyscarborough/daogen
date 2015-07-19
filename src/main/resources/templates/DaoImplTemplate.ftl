package ${packageName}.dao;

import ${packageName}.vo.${className};
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access layer for the ${className} class.
 */
public class Jdbc${className}Dao extends BaseDao implements ${className}Dao {

    private static final Logger LOGGER = Logger.getLogger(${className}.class);

    private static final String FIND_ALL_STATEMENT = "SELECT ${columnsList} FROM ${tableName}";
    private static final String FIND_BY_ID_STATEMENT = "SELECT ${columnsList} FROM ${tableName} WHERE ${idColumn} = ?";
    private static final String SAVE_STATEMENT = "INSERT INTO ${tableName} (${columnsList}) VALUES (${bindValuesList})";
    private static final String UPDATE_STATEMENT = "UPDATE ${tableName} SET ${updateSetters} WHERE ${idColumn} = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM ${tableName} WHERE ${idColumn} = ?";

    /**
     * Returns a list of ${className} instances from the database.
     */
    public List<${className}> findAll() {
        LOGGER.debug("Retrieving a list of all ${className} instances from the database...");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(FIND_ALL_STATEMENT);

            LOGGER.debug("Executing query: '" + FIND_ALL_STATEMENT + "'...");
            resultSet = statement.executeQuery();

            List<${className}> list = new ArrayList<${className}>();
            while (resultSet.next()) {
                list.add(resultSetTo${className}(resultSet));
            }
            return list;
        } catch (Exception e) {
            LOGGER.error("An error occurred while retrieving ${className} instances from the database", e);
            throw new RuntimeException("An error occurred while retrieving ${className} instances from the database", e);
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }

    /**
     * Finds a single ${className} instance from the database by id.
     *
     * @param id The id of the ${className} to find.
     */
    public ${className} findById(${idClass} id) {
        LOGGER.debug("Looking up ${className} by ID '" + id + "'...");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
    
        try {
            connection = getConnection();
            statement = connection.prepareStatement(FIND_BY_ID_STATEMENT);
            statement.set${idResultSetClass}(1, id);

            LOGGER.debug("Executing query: '" + FIND_BY_ID_STATEMENT + "' with ${idColumn} '" + id + "'...");
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSetTo${className}(resultSet);
            }
            return null;
        } catch (Exception e) {
            LOGGER.error("An error occurred while retrieving ${className} instance with ID '" + id + "' from the database", e);
            throw new RuntimeException("An error occurred while retrieving ${className} instance with ID '" + id + "' from the database", e);
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }

    /**
     * Saves a new ${className} instance to the database.
     *
     * @param ${variableName} The ${className} instance to persist.
     */
    public ${className} save(${className} ${variableName}) {
        LOGGER.debug("Saving new ${className} instance to the database: " + ${variableName});
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SAVE_STATEMENT);
${bindSetters}
            LOGGER.debug("Executing save: '" + SAVE_STATEMENT + "' for " + ${variableName});
            statement.executeUpdate();
            return ${variableName};
        } catch (Exception e) {
            LOGGER.error("An error occurred while saving ${className} instance to the database", e);
            throw new RuntimeException("An error occurred while saving ${className} instance to the database", e);
        } finally {
            closeResources(connection, statement);
        }
    }

    /**
     * Updates an existing ${className} instance in the database.
     *
     * @param ${variableName} The ${className} instance to update.
     */
    public ${className} update(${className} ${variableName}) {
        LOGGER.debug("Updating ${className} to the database...");
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(UPDATE_STATEMENT);
${bindUpdateSetters}
            LOGGER.debug("Executing update: '" + UPDATE_STATEMENT + "' for " + ${variableName});
            statement.executeUpdate();
            return ${variableName};
        } catch (Exception e) {
            LOGGER.error("An error occurred while updating ${className} instance in the database", e);
            throw new RuntimeException("An error occurred while updating ${className} instance in the database", e);
        } finally {
            closeResources(connection, statement);
        }
    }

    /**
     * Deletes an existing ${className} instance from the database.
     *
     * @param id The id of the instance to delete.
     */
    public void delete(${idClass} id) {
        LOGGER.debug("Deleting ${className} with ID '" + id + "' from the database...");
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(DELETE_STATEMENT);
            statement.set${idResultSetClass}(1, id);

            LOGGER.debug("Executing query: '" + DELETE_STATEMENT + "' for ID '" + id + "'...");
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("An error occurred while deleting ${className} instance with ID '" + id + "' from the database", e);
            throw new RuntimeException("An error occurred while deleting ${className} instance with ID '" + id + "' from the database", e);
        } finally {
            closeResources(connection, statement);
        }
    }

    private ${className} resultSetTo${className}(ResultSet resultSet) throws SQLException {
        ${className} ${variableName} = new ${className}();
        ${setters}
        return ${variableName};
    }
}