package eDiscovery.databases;

public enum SQLiteDBType {
    Local("jdbc:sqlite:D:\\WORK Projects\\eDiscovery\\repoctsg\\ediscovery.server\\src\\EDiscovery.LocalAgent\\Infrastructure\\API\\EDiscovery.LocalAgent\\bin\\Debug\\net6.0\\test.db"),
    Cloud("jdbc:sqlite:D:\\WORK Projects\\eDiscovery\\repoctsg\\ediscovery.server\\src\\CloudAgent\\Infrastructure\\API\\CloudAgent\\bin\\Debug\\net6.0\\test.db");

    final String urlConnection;

    SQLiteDBType(String urlConnection){
        this.urlConnection = urlConnection;
    }
}
