package eDiscovery.agents;

public enum AgentType{
    Local("D:\\WORK Projects\\eDiscovery\\repoctsg\\ediscovery.server\\src\\EDiscovery.LocalAgent\\Infrastructure\\API\\EDiscovery.LocalAgent\\bin\\Debug\\net6.0\\EDiscovery.LocalAgent.exe"),
    Cloud("D:\\WORK Projects\\eDiscovery\\repoctsg\\ediscovery.server\\src\\CloudAgent\\Infrastructure\\API\\CloudAgent\\bin\\Debug\\net6.0\\CloudAgent.exe");

    final String urlConnection;

    AgentType(String urlConnection){
        this.urlConnection = urlConnection;
    }

    public String getAgentName(){
        String[] path = this.urlConnection.split("\\\\");
        return path[path.length - 1];
    }
}