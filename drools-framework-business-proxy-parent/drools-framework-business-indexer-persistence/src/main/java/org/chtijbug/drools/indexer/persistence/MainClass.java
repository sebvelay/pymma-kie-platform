package org.chtijbug.drools.indexer.persistence;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainClass {
    public static void main(String[] args) throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "myClusterName").build();
        TransportClient client = null;
        PreBuiltTransportClient preBuiltTransportClient=null;
        try {
            preBuiltTransportClient = new PreBuiltTransportClient(settings);
            client = preBuiltTransportClient
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        } finally {
            if (client != null) {
                client.close();
            }
            if (preBuiltTransportClient!=null){
                preBuiltTransportClient.close();
            }
        }
// on shutdown

    }
}
