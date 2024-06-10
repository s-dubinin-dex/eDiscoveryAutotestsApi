package eDiscovery.agents;

import lombok.Getter;

public enum AgentType {
    LocalAgent(
            "D:\\WORK Projects\\eDiscovery\\repoctsg\\ediscovery.server\\src\\LocalAgent\\Infrastructure\\API\\LocalAgent\\bin\\Debug\\net6.0\\",
            "LocalAgent.exe"
    ),
    CloudAgent(
            "D:\\WORK Projects\\eDiscovery\\repoctsg\\ediscovery.server\\src\\CloudAgent\\Infrastructure\\API\\CloudAgent\\bin\\Debug\\net6.0\\",
            "CloudAgent.exe"
    );
    @Getter
    final String urlConnection;
    @Getter
    final String agentFileName;

    AgentType(String urlConnection, String agentFileName){
        this.urlConnection = urlConnection;
        this.agentFileName = agentFileName;
    }
}
