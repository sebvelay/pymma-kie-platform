package org.chtijbug.drools.indexer.route;

import org.apache.camel.builder.RouteBuilder;


public class IndexerRoute extends RouteBuilder {

    private String login;

    private String password;

    private String host;

    private String port;

    private String id;

    public IndexerRoute(String id,String login, String password, String host, String port) {
        this.login = login;
        this.password = password;
        this.host = host;
        this.port = port;
        this.id=id;
    }



    @Override
    public void configure() throws Exception {
        System.out.println("coucou");

        //from("sftp://foo@myserver?password=secret&ftpClient.dataTimeout=30000").to("bean:foo");
        String url="sftp://"+login+"@"+host+":"+port+"?password="+password+"&move=.done";
        from(url).routeId(id).to("bean:storeService?method=store(${header.CamelFileName},${body})");

    }
}
