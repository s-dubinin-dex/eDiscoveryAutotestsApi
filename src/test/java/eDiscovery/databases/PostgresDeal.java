package eDiscovery.databases;

import java.sql.*;
import java.util.List;

public class PostgresDeal {
    private final String urlConnection = System.getProperty("PG_DEAL_CONNECTION");
    private final String dbUserName = System.getProperty("PG_USERNAME");
    private final String dbUserPassword = System.getProperty("PG_PASSWORD");

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private void connectToDataBase(){
        try {
            connection = DriverManager.getConnection(urlConnection, dbUserName, dbUserPassword);
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
        for (String tableName: getDealTablesAllowedToDelete()){
            executeSampleQuery("DELETE FROM " + tableName);
        }
    }

    public void truncateAllTablesData(){
        executeSampleQuery("TRUNCATE " + String.join(", ", getDealTablesAllowedToDelete()) + ";");
    }

    private List<String> getDealTablesAllowedToDelete(){
        return List.of(
                "\"Agent\"",
                "\"Deal\"",
                "\"DealDocumentFinded\"",
                "\"DealDocumentFindedDealTask\"",
                "\"DealDocumentProblemFinded\"",
                "\"DealDocumentProblemFindedDealTask\"",
                "\"DealSearchPlace\"",
                "\"DealSearchQuery\"",
                "\"DealStatusHistory\"",
                "\"DealTask\"",
                "\"SearchPlace\"",
                "\"SearchQuery\""
        );
    }

}
