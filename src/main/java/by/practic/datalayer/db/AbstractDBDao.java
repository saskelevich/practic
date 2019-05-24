package by.practic.datalayer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractDBDao {

    // classroom
//    private static final String PASSWORD = "1";
//    private static final String USER = "postgres";
//    private static final String LOCALHOST = "jdbc:postgresql://localhost:5432/saskel-cars";

    // home
    private static final String PASSWORD = "1";
    private static final String USER = "new_user";
    private static final String LOCALHOST = "jdbc:postgresql://localhost:5432/practic7";

    protected static final String UPDATED = "updated";
    protected static final String CREATED = "created";
    protected static final String ID = "id";

    protected Connection createConnection() throws SQLException {
        return DriverManager.getConnection(LOCALHOST, USER, PASSWORD);
    }
}