package org.chtijbug.drools.reverseproxy.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AutodeployRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("quartz2://myGroup/myTimerName?cron=0/5+*+*+?+*+*").to("bean:updateService?method=updateConfig()");
    }
}
