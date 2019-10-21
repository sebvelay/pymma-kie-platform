package org.chtijbug.drools.proxy.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AliveTimeStampRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("quartz2://myGroup/myTimeStampTimer?cron=0/5+*+*+?+*+*").to("bean:kieService?method=setTimeStamp()");
    }
}
