package com.example.demo;


import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.time.Duration;
import java.util.*;
import java.util.stream.*;

public class TestQuery {
    // FOR ELASTIC CACHE CLUSTER
    private static volatile RedisClient elasticRedisWriterClient;

    private static volatile StatefulRedisConnection elasticRedisWriteConnection;

    private static volatile  RedisClient elasticRedisReaderClient;

    private static volatile  StatefulRedisConnection elasticRedisReadConnection;

    private String elasticCachePassword = "dev-consumeprod-redis_passwordtest";

    private String elasticCacheReaderUrl = "redis://replica.pg-rtdd-nonprod-valkey.5eyg3r.aps1.cache.amazonaws.com:7000";
    private String elasticCacheWriterUrl = "redis://master.pg-rtdd-nonprod-valkey.5eyg3r.aps1.cache.amazonaws.com:7000";;

    private RedisClient getElastiCacheReaderClient() {
        if (elasticRedisReaderClient == null) {
            synchronized (TestQuery.class) {
                if (elasticRedisReaderClient == null) {
                    RedisURI redisURI = RedisURI.create(elasticCacheReaderUrl);
                    redisURI.setPassword(elasticCachePassword.toCharArray());
                    redisURI.setSsl(true);
                    elasticRedisReaderClient = RedisClient.create(redisURI);
                    elasticRedisReaderClient.setDefaultTimeout(Duration.ofMillis(3000));
                }
            }
        }
        return elasticRedisReaderClient;
    }

    private RedisClient getElastiCacheWriterClient() {
        if (elasticRedisWriterClient == null) {
            synchronized (TestQuery.class) {
                if (elasticRedisWriterClient == null) {
                    RedisURI redisURI = RedisURI.create(elasticCacheWriterUrl);
                    redisURI.setPassword(elasticCachePassword.toCharArray());
                    redisURI.setSsl(true);
                    elasticRedisWriterClient = RedisClient.create(redisURI);
                    elasticRedisWriterClient.setDefaultTimeout(Duration.ofMillis(3000));
                }
            }
        }
        return elasticRedisWriterClient;
    }

    public StatefulRedisConnection getElastiCacheReadConnection(){
        RedisClient redisClient=getElastiCacheReaderClient();

        if(elasticRedisReadConnection==null || !elasticRedisReadConnection.isOpen()){
            synchronized(TestQuery.class) {
                if(elasticRedisReadConnection==null || !elasticRedisReadConnection.isOpen()) {
                    elasticRedisReadConnection = redisClient.connect();
                }
            }
        }
        return elasticRedisReadConnection;
    }

    public StatefulRedisConnection getElasticCacheWriteConnection(){
        RedisClient redisClient=getElastiCacheWriterClient();
        if(elasticRedisWriteConnection==null || !elasticRedisWriteConnection.isOpen()){
            synchronized(TestQuery.class) {
                if(elasticRedisWriteConnection==null || !elasticRedisWriteConnection.isOpen()) {
                    elasticRedisWriteConnection = redisClient.connect();
                }
            }
        }
        return elasticRedisWriteConnection;
    }

    /**
     * Ping Redis reader connection to test if it's working
     * @return true if ping successful, false otherwise
     */
    public boolean pingRedisReader() {
        try {
            StatefulRedisConnection connection = getElastiCacheReadConnection();
            if (connection != null && connection.isOpen()) {
                String response = connection.sync().ping();
                return "PONG".equalsIgnoreCase(response);
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error pinging Redis reader: " + e.getMessage());
            return false;
        }
    }

    /**
     * Ping Redis writer connection to test if it's working
     * @return true if ping successful, false otherwise
     */
    public boolean pingRedisWriter() {
        try {
            StatefulRedisConnection connection = getElasticCacheWriteConnection();
            if (connection != null && connection.isOpen()) {
                String response = connection.sync().ping();
                return "PONG".equalsIgnoreCase(response);
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error pinging Redis writer: " + e.getMessage());
            return false;
        }
    }

    /**
     * Ping Valkey/ElastiCache reader connection to test if it's working
     * @return true if ping successful, false otherwise
     */
    public boolean pingValkeyReader() {
        try {
            StatefulRedisConnection connection = getElastiCacheReadConnection();
            if (connection != null && connection.isOpen()) {
                String response = connection.sync().ping();
                return "PONG".equalsIgnoreCase(response);
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error pinging Valkey reader: " + e.getMessage());
            return false;
        }
    }

    /**
     * Ping Valkey/ElastiCache writer connection to test if it's working
     * @return true if ping successful, false otherwise
     */
    public boolean pingValkeyWriter() {
        try {
            StatefulRedisConnection connection = getElasticCacheWriteConnection();
            if (connection != null && connection.isOpen()) {
                String response = connection.sync().ping();
                return "PONG".equalsIgnoreCase(response);
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error pinging Valkey writer: " + e.getMessage());
            return false;
        }
    }

    /**
     * Test all Redis connections and return status
     * @return String containing status of all connections
     */
    public String testAllConnections() {
        StringBuilder status = new StringBuilder();
        status.append("Redis Connection Status:\n");
        status.append("======================\n");

        // Test regular Redis
        status.append("Redis Reader: ").append(pingRedisReader() ? "✅ CONNECTED" : "❌ FAILED").append("\n");
        status.append("Redis Writer: ").append(pingRedisWriter() ? "✅ CONNECTED" : "❌ FAILED").append("\n");

        // Test Valkey/ElastiCache
        status.append("Valkey Reader: ").append(pingValkeyReader() ? " CONNECTED" : "❌ FAILED").append("\n");
        status.append("Valkey Writer: ").append(pingValkeyWriter() ? "✅ CONNECTED" : "❌ FAILED").append("\n");

        return status.toString();
    }

    /**
     * Add a single member with score to a sorted set
     * @param key The sorted set key
     * @param score The score for the member
     * @param member The member to add
     * @return true if successful, false otherwise
     */
    public boolean zaddSingle(String key, double score, String member) {
        try {
            StatefulRedisConnection connection = getElasticCacheWriteConnection();
            if (connection != null && connection.isOpen()) {
                Long result = connection.sync().zadd(key, score, member);
                return result != null && result >= 0;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error adding to sorted set: " + e.getMessage());
            return false;
        }
    }

    /**
     * Add multiple members with scores to a sorted set
     * @param key The sorted set key
     * @param scoreMembers Map of member to score
     * @return true if successful, false otherwise
     */
    public boolean zaddMultiple(String key, Map<String, Double> scoreMembers) {
        try {
            StatefulRedisConnection connection = getElasticCacheWriteConnection();
            if (connection != null && connection.isOpen()) {
                // Convert Map to varargs format for Lettuce
                Object[] scoreMemberArray = new Object[scoreMembers.size() * 2];
                int i = 0;
                for (Map.Entry<String, Double> entry : scoreMembers.entrySet()) {
                    scoreMemberArray[i++] = entry.getValue(); // score
                    scoreMemberArray[i++] = entry.getKey();   // member
                }
                Long result = connection.sync().zadd(key, scoreMemberArray);
                return result != null && result >= 0;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error adding multiple to sorted set: " + e.getMessage());
            return false;
        }
    }

    /**
     * Add multiple members with scores to a sorted set using varargs
     * @param key The sorted set key
     * @param scoreMembers Alternating score and member pairs
     * @return true if successful, false otherwise
     */
    public boolean zaddVarargs(String key, Object... scoreMembers) {
        try {
            StatefulRedisConnection connection = getElasticCacheWriteConnection();
            if (connection != null && connection.isOpen()) {
                Long result = connection.sync().zadd(key, scoreMembers);
                return result != null && result >= 0;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error adding varargs to sorted set: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get the size of a sorted set
     * @param key The sorted set key
     * @return The size of the sorted set, or -1 if error
     */
    public long zcard(String key) {
        try {
            StatefulRedisConnection connection = getElastiCacheReadConnection();
            if (connection != null && connection.isOpen()) {
                Long result = connection.sync().zcard(key);
                return result != null ? result : -1;
            }
            return -1;
        } catch (Exception e) {
            System.err.println("Error getting sorted set size: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Get members of a sorted set with scores in range
     * @param key The sorted set key
     * @param start Start index (0-based)
     * @param stop Stop index (-1 for all)
     * @return List of members with scores, or empty list if error
     */
    public List<Map.Entry<String, Double>> zrangeWithScores(String key, long start, long stop) {
        try {
            StatefulRedisConnection connection = getElastiCacheReadConnection();
            if (connection != null && connection.isOpen()) {
                List<io.lettuce.core.ScoredValue<String>> scoredValues = connection.sync().zrangeWithScores(key, start, stop);
                if (scoredValues != null) {
                    List<Map.Entry<String, Double>> result = new ArrayList<>();
                    for (io.lettuce.core.ScoredValue<String> sv : scoredValues) {
                        result.add(new AbstractMap.SimpleEntry<>(sv.getValue(), sv.getScore()));
                    }
                    return result;
                }
                return new ArrayList<>();
            }
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error getting sorted set range: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException {
        TestQuery testQuery = new TestQuery();
        String status = testQuery.testAllConnections();
        System.out.println(status);
        
        // Test ZADD functionality
        System.out.println("\n=== Testing ZADD Operations ===");
        
        String testKey = "test:scores";
        
        // Test single ZADD
        boolean singleResult = testQuery.zaddSingle(testKey, 95.5, "Alice");
        System.out.println("Single ZADD result: " + (singleResult ? "✅ SUCCESS" : "❌ FAILED"));
        
        // Test multiple ZADD with Map
        Map<String, Double> multipleScores = new HashMap<>();
        multipleScores.put("Bob", 87.2);
        multipleScores.put("Charlie", 92.8);
        multipleScores.put("Diana", 89.1);
        
        boolean multipleResult = testQuery.zaddMultiple(testKey, multipleScores);
        System.out.println("Multiple ZADD result: " + (multipleResult ? "✅ SUCCESS" : "❌ FAILED"));
        
        // Test varargs ZADD
        boolean varargsResult = testQuery.zaddVarargs(testKey, 78.5, "Eve", 91.3, "Frank");
        System.out.println("Varargs ZADD result: " + (varargsResult ? "✅ SUCCESS" : "❌ FAILED"));
        
        // Get the size of the sorted set
        long setSize = testQuery.zcard(testKey);
        System.out.println("Sorted set size: " + setSize);
        
        // Get all members with scores
        List<Map.Entry<String, Double>> allMembers = testQuery.zrangeWithScores(testKey, 0, -1);
        System.out.println("All members with scores:");
        for (Map.Entry<String, Double> entry : allMembers) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        
        // Clean up connections
        if (elasticRedisReadConnection != null && elasticRedisReadConnection.isOpen()) {
            elasticRedisReadConnection.close();
        }
        if (elasticRedisWriteConnection != null && elasticRedisWriteConnection.isOpen()) {
            elasticRedisWriteConnection.close();
        }
        if (elasticRedisReaderClient != null) {
            elasticRedisReaderClient.shutdown();
        }
        if (elasticRedisWriterClient != null) {
            elasticRedisWriterClient.shutdown();
        }
    }


}