package Redis;

import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import io.lettuce.core.cluster.models.partitions.Partitions;
import io.lettuce.core.cluster.models.partitions.RedisClusterNode;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RedisConnectionFactory {
    private String elasticacheClusterUrl = "redis://dev-bombay-elasticache-cluster.5eyg3r.clustercfg.aps1.cache.amazonaws.com:6379";
    String elasticachePassword = "";

    // Valkey variables for Redis Listing

    private static volatile  RedisClusterClient elasticacheWriterClusterClient;

    private static volatile  StatefulRedisClusterConnection<String, String> elasticacheClusterWriterConnection;

    // New method for ElastiCache cluster connection
    private RedisClusterClient getelasticacheWriterClusterClient() {
        if (elasticacheWriterClusterClient == null) {
            synchronized (RedisConnectionFactory.class) {
                if (elasticacheWriterClusterClient == null) {
                    RedisURI redisURI = RedisURI.create(elasticacheClusterUrl);
                    redisURI.setPassword(elasticachePassword.toCharArray());
//                    redisURI.setSsl(true);
                    elasticacheWriterClusterClient = RedisClusterClient.create(redisURI);
                    elasticacheWriterClusterClient.setDefaultTimeout(Duration.ofMillis(5000));
                }
            }
        }
        return elasticacheWriterClusterClient;
    }

    // New method to get ElastiCache cluster connection
    public StatefulRedisClusterConnection<String, String> getelasticacheClusterWriterConnection(){
        RedisClusterClient redisClient = getelasticacheWriterClusterClient();
        if(elasticacheClusterWriterConnection==null || !elasticacheClusterWriterConnection.isOpen()){
            synchronized(RedisConnectionFactory.class) {
                if(elasticacheClusterWriterConnection==null || !elasticacheClusterWriterConnection.isOpen()) {
                    elasticacheClusterWriterConnection = redisClient.connect();
                }
            }
        }
        return elasticacheClusterWriterConnection;
    }

    // Method to show cluster topology
    public void printClusterTopology() {
        try {
            StatefulRedisClusterConnection<String, String> connection = getelasticacheClusterWriterConnection();
            Partitions partitions = connection.getPartitions();
            
            System.out.println("=== Cluster Topology ===");
            for (RedisClusterNode partition : partitions) {
                System.out.println("Node: " + partition.getUri());
                System.out.println("  - Role: " + (partition.getFlags().contains("master") ? "PRIMARY" : "REPLICA"));
                System.out.println("  - Slot Range: " + partition.getSlots());
                System.out.println("  - Connected: " + partition.isConnected());
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error getting cluster topology: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to show cluster info
    public void showClusterInfo() {
        try {
            StatefulRedisClusterConnection<String, String> connection = getelasticacheClusterWriterConnection();
            RedisAdvancedClusterAsyncCommands<String, String> commands = connection.async();
            
            // Get cluster info
            RedisFuture<String> infoFuture = commands.clusterInfo();
            String info = infoFuture.get(5, TimeUnit.SECONDS);
            System.out.println("=== Cluster Info ===");
            System.out.println(info);
            System.out.println();
            
            // Get cluster nodes
            RedisFuture<String> nodesFuture = commands.clusterNodes();
            String nodes = nodesFuture.get(5, TimeUnit.SECONDS);
            System.out.println("=== Cluster Nodes ===");
            System.out.println(nodes);
            System.out.println();
            
        } catch (Exception e) {
            System.out.println("Error getting cluster info: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void addDummyDataToZSet(RedisAdvancedClusterAsyncCommands<String, String> asyncCommands, String key) {
        asyncCommands.zadd(key, 1, "apple");
        asyncCommands.zadd(key, 2, "banana");
        asyncCommands.zadd(key, 3, "cherry");
        asyncCommands.zadd(key, 4, "mango");
        asyncCommands.zadd(key, 5, "orange");
    }

    public static void main(String[] args) {
        RedisConnectionFactory redisConnectionFactory = new RedisConnectionFactory();
        
        // Show cluster topology and info first
        redisConnectionFactory.printClusterTopology();
        redisConnectionFactory.showClusterInfo();
        
        StatefulRedisClusterConnection<String, String> readConnection = redisConnectionFactory.getelasticacheClusterWriterConnection();
        RedisAdvancedClusterAsyncCommands<String, String> asyncCommands = readConnection.async();

        // Insert dummy data
        System.out.println("=== Executing Commands ===");
        addDummyDataToZSet(asyncCommands, "fruits");

        RedisFuture<List<String>> redisFuture = asyncCommands.zrevrange("fruits", 0, 3);
        try {
            List<String> returnList = redisFuture.get();
            System.out.println("Result: " + returnList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
