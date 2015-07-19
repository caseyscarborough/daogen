package ${daoGen.packageName}.${Constants.DAO_PACKAGE_NAME};

import org.apache.log4j.Logger;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Base DAO class for all data access classes.
 */
public abstract class Base${Constants.DAO_CLASS_SUFFIX} {

private static final Logger LOGGER = Logger.getLogger(Base${Constants.DAO_CLASS_SUFFIX}.class);

public Base${Constants.DAO_CLASS_SUFFIX}() {
    }

    private DataSource getDataSource() {
throw new RuntimeException("getDataSource() method has not yet been implemented for Base${Constants.DAO_CLASS_SUFFIX} class");
    }

    protected Connection getConnection() {
        DataSource ds = getDataSource();
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            LOGGER.error("An error occurred retrieving connection from DataSource", e);
            throw new RuntimeException("An error occurred retrieving connection from DataSource", e);
        }
    }

    protected void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource: resources) {
            closeResource(resource);
        }
    }

    private void closeResource(AutoCloseable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred closing resource", e);
        }
    }

}