package eDiscovery.tests;

import eDiscovery.agents.Agent;
import eDiscovery.agents.AgentType;
import eDiscovery.databases.PostgresDeal;
import eDiscovery.databases.SQLiteDB;
import eDiscovery.databases.SQLiteDBType;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class LocalAgentTest {
    @Test
    public void firstTest() throws InterruptedException {

        Agent localAgent = new Agent(AgentType.Cloud);
        localAgent.startAgent();
        //Создать поисковый запрос
        //Создать дело
        //Запустить дело
        //Проверить результат
        Thread.sleep(Duration.ofSeconds(30));
        localAgent.stopAgent();
//
//        SQLiteDB sqLiteDB = new SQLiteDB(SQLiteDBType.Local);
//        sqLiteDB.deleteAllTablesData();
//
//        PostgresDeal postgresDeal = new PostgresDeal();
//        postgresDeal.deleteAllTablesData();
    }
}
