package org.chtijbug.drools.indexer.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IndexerRoute extends RouteBuilder {
    @Value("${kieserver.login}")
    private String login;
    @Value("${kieserver.password}")
    private String password;


    @Override
    public void configure() throws Exception {
        System.out.println("coucou");

        //from("sftp://foo@myserver?password=secret&ftpClient.dataTimeout=30000").to("bean:foo");
        String url="sftp://"+login+"@localhost:9080?password="+password+"&move=.done";
        from(url).to("bean:storeService?method=store(${header.CamelFileName},${body})");

    }
}
