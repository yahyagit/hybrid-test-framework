package com.atanas.kanchev.testframework.dataservices.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Oracle DB Connector boilerplate
 *
 * @author Atanas Ksnchev
 * @version 1.0
 */
public class OracleDBConnector {

    private static final Logger logger = LoggerFactory.getLogger(OracleDBConnector.class);

    private String jdbcURL;
    private String dbUserName;
    private String dbPassword;

    /**
     * Constructor
     * Sets the value of {@link com.atanas.kanchev.testframework.dataservices.dao.OracleDBConnector#jdbcURL}
     *
     * @param IP         Database IP address
     * @param port       Database port
     * @param service    Service name
     * @param dbUserName a {@link java.lang.String} object.
     * @param dbPassword a {@link java.lang.String} object.
     */
    public OracleDBConnector(String IP, String port, String service, String dbUserName,
        String dbPassword) {
        this.jdbcURL =
            "jdbc:oracle:thin:@//".concat(IP).concat(":").concat(port).concat("/").concat(service);
        this.dbUserName = dbUserName;
        this.dbPassword = dbPassword;
    }

    /**
     * <p>queryDB.</p>
     *
     * @param selectQuery a {@link java.lang.String} object.
     * @throws java.lang.ClassNotFoundException if any.
     * @throws java.sql.SQLException            if any.
     */
    public void queryDB(String selectQuery) throws ClassNotFoundException, SQLException {

        Connection connection;

        Class.forName("oracle.jdbc.pool.OracleConnectionPoolDataSource");
        long lStartTime = new java.util.Date().getTime(); // start time
        logger.debug(
            "Getting Database connection using : " + jdbcURL + " " + dbUserName + "/" + dbPassword);

        connection = DriverManager.getConnection(jdbcURL, dbUserName, dbPassword);

        logger.debug(
            "Database connection succeeded for : " + jdbcURL + " " + dbUserName + "/" + dbPassword);

        Statement statement = connection.createStatement();

        logger.debug("Executing query : " + selectQuery);
        ResultSet rs = statement.executeQuery(selectQuery);

        logger.debug("Verifying ResultSet");
        rs.next();

        logger.debug("Closing database connection ");

        statement.close();
        connection.close();

        logger.debug("Closed database connection");
        long lEndTime = new java.util.Date().getTime(); // end time
        long difference = lEndTime - lStartTime; // check different
        logger.debug("Duration of DB query: " + difference / 1000 + " sec. ");

    }
}
