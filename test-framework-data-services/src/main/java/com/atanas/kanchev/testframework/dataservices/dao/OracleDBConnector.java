/*
 * Copyright 2016 Atanas Stoychev Kanchev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atanas.kanchev.testframework.dataservices.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * <p>OracleDBConnector class.</p>
 *
 * @author Atanas Kanchev
 */
public class OracleDBConnector {

    private static final Logger logger = LoggerFactory.getLogger(OracleDBConnector.class);

    private String jdbcURL;
    private String dbUserName;
    private String dbPassword;

    /**
     * <p>Constructor for OracleDBConnector.</p>
     *
     * @param IP a {@link java.lang.String} object.
     * @param port a {@link java.lang.String} object.
     * @param service a {@link java.lang.String} object.
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
     * @throws java.sql.SQLException if any.
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
