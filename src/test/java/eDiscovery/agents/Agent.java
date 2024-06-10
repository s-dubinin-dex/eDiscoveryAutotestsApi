package eDiscovery.agents;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Agent {

    private final String urlConnection;
    private final String agentName;

    public Agent(AgentType agentType){
        this.urlConnection = agentType.urlConnection;
        this.agentName = agentType.agentFileName;
    }

    public void startAgent(){
        Desktop desktop = Desktop.getDesktop();
        File file = new File(urlConnection);
        try {
            desktop.open(file);
            Thread.sleep(5000);
        } catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    public void stopAgent(){
        try {
            ProcessBuilder killer = new ProcessBuilder("taskkill", "/f", "/IM", agentName);
            killer.start();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
