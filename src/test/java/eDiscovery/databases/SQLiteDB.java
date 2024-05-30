package eDiscovery.databases;

import java.sql.*;

public class SQLiteDB {
    private String urlConnection;

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public SQLiteDB(SQLiteDBType type){
        this.urlConnection = type.urlConnection;
    }

    private void connectToDataBase(){
        try {
            connection = DriverManager.getConnection(urlConnection);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private void closeConnection(){
        try {
            if (resultSet != null){
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public ResultSet executeSampleQuery(String sqlQuery){

        connectToDataBase();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            return resultSet;

        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public void deleteAllTablesData(){
        executeSampleQuery("DELETE FROM \"BlockResult\"");
        executeSampleQuery("DELETE FROM \"FilePath\"");
        executeSampleQuery("DELETE FROM \"FilePathHistory\"");
        executeSampleQuery("DELETE FROM \"FilePathProceed\"");
        executeSampleQuery("DELETE FROM \"Objective\"");
        executeSampleQuery("DELETE FROM \"Quarantine\"");
        executeSampleQuery("DELETE FROM \"SearchPlace\"");
    }
}
