package eDiscovery.databases;

import java.sql.*;

public class PostgresDeal {
    private final String urlConnection = "jdbc:postgresql://192.168.20.48:5432/TemplateDeal";
    private final String dbUserName = "postgres";
    private final String dbUserPassword = "my-pass~003";

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
        executeSampleQuery("DELETE FROM \"Agent\"");
        executeSampleQuery("DELETE FROM \"Deal\"");
        executeSampleQuery("DELETE FROM \"DealDocumentFinded\"");
        executeSampleQuery("DELETE FROM \"DealDocumentFindedDealTask\"");
        executeSampleQuery("DELETE FROM \"DealDocumentProblemFinded\"");
        executeSampleQuery("DELETE FROM \"DealDocumentProblemFindedDealTask\"");
        executeSampleQuery("DELETE FROM \"DealSearchPlace\"");
        executeSampleQuery("DELETE FROM \"DealSearchQuery\"");
        executeSampleQuery("DELETE FROM \"DealStatusHistory\"");
        executeSampleQuery("DELETE FROM \"DealTask\"");
        executeSampleQuery("DELETE FROM \"SearchPlace\"");
        executeSampleQuery("DELETE FROM \"SearchQuery\"");
    }

}
