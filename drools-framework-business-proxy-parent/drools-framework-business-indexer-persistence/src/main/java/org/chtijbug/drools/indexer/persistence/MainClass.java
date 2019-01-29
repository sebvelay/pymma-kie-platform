package org.chtijbug.drools.indexer.persistence;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainClass {
    public static void main(String [] args) throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "myClusterName").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

// on shutdown

        client.close();    }
}
