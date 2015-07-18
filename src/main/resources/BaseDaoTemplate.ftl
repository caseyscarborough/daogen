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

    protected Connection getConnection() {
      throw new RuntimeException("getConnection() method has not yet been implemented for Base DAO class");
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