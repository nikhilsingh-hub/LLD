package DesignPatterns.Singleton.DbConnection;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolImpl implements ConnectionPool {
    private static ConnectionPoolImpl instance;
    private AtomicInteger maxConnection;
    private ConcurrentHashMap<DatabaseConnection, Boolean> activeConnections;
    private ConcurrentLinkedQueue<DatabaseConnection> availableConnections;

    private ConnectionPoolImpl(){}

    private ConnectionPoolImpl(int x){
        activeConnections = new ConcurrentHashMap<>();
        availableConnections = new ConcurrentLinkedQueue<>();
        this.maxConnection = new AtomicInteger(x);
    }
    @Override
    public void initializePool() {
        for(int i=0; i<maxConnection.get(); i++){
            availableConnections.add(new DatabaseConnection());
        }
    }

    @Override
    public DatabaseConnection getConnection() {
        if(availableConnections.isEmpty()) return null;
        DatabaseConnection temp = availableConnections.remove();
        activeConnections.put(temp, true);
        return temp;
    }

    @Override
    public void releaseConnection(DatabaseConnection connection) {
        if(connection==null) return;
        activeConnections.remove(connection);
        availableConnections.add(connection);
    }

    @Override
    public int getAvailableConnectionsCount() {
        return availableConnections.size();
    }

    @Override
    public int getTotalConnectionsCount() {
        return maxConnection.get();
    }

    public static ConnectionPoolImpl getInstance(int maxConnection){
        if(instance == null){
            synchronized (ConnectionPoolImpl.class){
                if (instance == null){
                    instance = new ConnectionPoolImpl(maxConnection);
                    instance.initializePool(); // Initialize the pool with connections
                }
            }
        }
        return instance;
    }
     public static void resetInstance(){
        instance = null;
     }
}
