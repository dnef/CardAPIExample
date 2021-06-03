package org.example.connect;

public class ConnectionBuilderFactory
{
    public static ConnectionBuilder getConnectionBuilder() {
        return new PoolConnectionBuilder();
    }
}
