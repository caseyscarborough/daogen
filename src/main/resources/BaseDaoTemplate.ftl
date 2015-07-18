package ${packageName}.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Base DAO class for all data access classes.
 */
public abstract class BaseDao {

    private static final Logger LOGGER = Logger.getLogger(BaseDao.class);

    public BaseDao() {
    }

    private DataSource getDataSource() {
        throw new RuntimeException("getDataSource() method has not yet been implemented for Base DAO class");
    }

    protected Connection getConnection() {
        DataSource ds = getDataSource();
        return ds.getConnection();
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