package org.chtijbug.drools.proxy.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AutodeployRouter extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        from("quartz2://myGroup/myTimerName?cron=0+0/1+*+?+*+MON-FRI").to("bean:kieService?method=updateConfig()");

    }
}
