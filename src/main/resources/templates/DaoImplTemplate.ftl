package ${daoGen.packageName}.dao;

import ${daoGen.packageName}.vo.${daoGen.clazz.name};
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access layer implementation for the ${daoGen.clazz.name} class.
 *
 * @see {@link ${daoGen.clazz.name}Dao}
 */
public class Jdbc${daoGen.clazz.name}Dao extends BaseDao implements ${daoGen.clazz.name}Dao {

    private static final Logger LOGGER = Logger.getLogger(${daoGen.clazz.name}.class);

    private static final String FIND_ALL_STATEMENT = "SELECT <#list daoGen.clazz.fields as field>${field.columnName}<#sep>, </#list> FROM ${daoGen.databaseName}.${daoGen.clazz.tableName}";
    private static final String FIND_BY_ID_STATEMENT = "SELECT <#list daoGen.clazz.fields as field>${field.columnName}<#sep>, </#list> FROM ${daoGen.databaseName}.${daoGen.clazz.tableName} WHERE ${daoGen.clazz.idField.columnName} = ?";
    private static final String SAVE_STATEMENT = "INSERT INTO ${daoGen.databaseName}.${daoGen.clazz.tableName} (<#list daoGen.clazz.fields as field>${field.columnName}<#sep>, </#list>) VALUES (<#list daoGen.clazz.fields as field>?<#sep>, </#list>)";
    private static final String UPDATE_STATEMENT = "UPDATE ${daoGen.databaseName}.${daoGen.clazz.tableName} SET <#list daoGen.clazz.fields as field>${field.columnName} = ?<#sep>, </#list> WHERE ${daoGen.clazz.idField.columnName} = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM ${daoGen.databaseName}.${daoGen.clazz.tableName} WHERE ${daoGen.clazz.idField.columnName} = ?";

    /**
     * Returns a list of ${daoGen.clazz.name} instances from the database.
     */
    public List<${daoGen.clazz.name}> findAll() {
        LOGGER.debug("Retrieving a list of all ${daoGen.clazz.name} instances from the database...");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(FIND_ALL_STATEMENT);

            LOGGER.debug("Executing query: '" + FIND_ALL_STATEMENT + "'...");
            resultSet = statement.executeQuery();

            List<${daoGen.clazz.name}> list = new ArrayList<${daoGen.clazz.name}>();
            while (resultSet.next()) {
                list.add(resultSetTo${daoGen.clazz.name}(resultSet));
            }
            return list;
        } catch (Exception e) {
            LOGGER.error("An error occurred while retrieving ${daoGen.clazz.name} instances from the database", e);
            throw new RuntimeException("An error occurred while retrieving ${daoGen.clazz.name} instances from the database", e);
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }

    /**
     * Finds a single ${daoGen.clazz.name} instance from the database by id.
     *
     * @param id The id of the ${daoGen.clazz.name} to find.
     */
    public ${daoGen.clazz.name} findById(${daoGen.clazz.idField.type} id) {
        LOGGER.debug("Looking up ${daoGen.clazz.name} by ID '" + id + "'...");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
    
        try {
            connection = getConnection();
            statement = connection.prepareStatement(FIND_BY_ID_STATEMENT);
            statement.set${daoGen.clazz.idField.resultSetType}(1, id);

            LOGGER.debug("Executing query: '" + FIND_BY_ID_STATEMENT + "' with ${daoGen.clazz.idField.columnName} '" + id + "'...");
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSetTo${daoGen.clazz.name}(resultSet);
            }
            return null;
        } catch (Exception e) {
            LOGGER.error("An error occurred while retrieving ${daoGen.clazz.name} instance with ID '" + id + "' from the database", e);
            throw new RuntimeException("An error occurred while retrieving ${daoGen.clazz.name} instance with ID '" + id + "' from the database", e);
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }

    /**
     * Saves a new ${daoGen.clazz.name} instance to the database.
     *
     * @param ${daoGen.clazz.variableName} The ${daoGen.clazz.name} instance to persist.
     */
    public ${daoGen.clazz.name} save(${daoGen.clazz.name} ${daoGen.clazz.variableName}) {
        LOGGER.debug("Saving new ${daoGen.clazz.name} instance to the database: " + ${daoGen.clazz.variableName});
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SAVE_STATEMENT);
        <#list daoGen.clazz.fields as field>
            statement.set${field.resultSetType}(${field?counter}, ${daoGen.clazz.variableName}.get${field.capitalizedName}());
        </#list>

            LOGGER.debug("Executing save: '" + SAVE_STATEMENT + "' for " + ${daoGen.clazz.variableName});
            statement.executeUpdate();
            return ${daoGen.clazz.variableName};
        } catch (Exception e) {
            LOGGER.error("An error occurred while saving ${daoGen.clazz.name} instance to the database", e);
            throw new RuntimeException("An error occurred while saving ${daoGen.clazz.name} instance to the database", e);
        } finally {
            closeResources(connection, statement);
        }
    }

    /**
     * Updates an existing ${daoGen.clazz.name} instance in the database.
     *
     * @param ${daoGen.clazz.variableName} The ${daoGen.clazz.name} instance to update.
     */
    public ${daoGen.clazz.name} update(${daoGen.clazz.name} ${daoGen.clazz.variableName}) {
        LOGGER.debug("Updating ${daoGen.clazz.name} to the database...");
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(UPDATE_STATEMENT);
        <#list daoGen.clazz.fields as field>
            statement.set${field.resultSetType}(${field?counter}, ${daoGen.clazz.variableName}.get${field.capitalizedName}());
        </#list>
            statement.set${daoGen.clazz.idField.resultSetType}(${daoGen.clazz.numberOfFields + 1}, ${daoGen.clazz.variableName}.get${daoGen.clazz.idField.capitalizedName}());

            LOGGER.debug("Executing update: '" + UPDATE_STATEMENT + "' for " + ${daoGen.clazz.variableName});
            statement.executeUpdate();
            return ${daoGen.clazz.variableName};
        } catch (Exception e) {
            LOGGER.error("An error occurred while updating ${daoGen.clazz.name} instance in the database", e);
            throw new RuntimeException("An error occurred while updating ${daoGen.clazz.name} instance in the database", e);
        } finally {
            closeResources(connection, statement);
        }
    }

    /**
     * Deletes an existing ${daoGen.clazz.name} instance from the database.
     *
     * @param id The id of the instance to delete.
     */
    public void delete(${daoGen.clazz.idField.type} id) {
        LOGGER.debug("Deleting ${daoGen.clazz.name} with ID '" + id + "' from the database...");
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(DELETE_STATEMENT);
            statement.set${daoGen.clazz.idField.resultSetType}(1, id);

            LOGGER.debug("Executing query: '" + DELETE_STATEMENT + "' for ID '" + id + "'...");
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("An error occurred while deleting ${daoGen.clazz.name} instance with ID '" + id + "' from the database", e);
            throw new RuntimeException("An error occurred while deleting ${daoGen.clazz.name} instance with ID '" + id + "' from the database", e);
        } finally {
            closeResources(connection, statement);
        }
    }

    private ${daoGen.clazz.name} resultSetTo${daoGen.clazz.name}(ResultSet resultSet) throws SQLException {
        ${daoGen.clazz.name} ${daoGen.clazz.variableName} = new ${daoGen.clazz.name}();
    <#list daoGen.clazz.fields as field>
        ${daoGen.clazz.variableName}.set${field.capitalizedName}(resultSet.get${field.resultSetType}("${field.columnName}"));
    </#list>
        return ${daoGen.clazz.variableName};
    }
}