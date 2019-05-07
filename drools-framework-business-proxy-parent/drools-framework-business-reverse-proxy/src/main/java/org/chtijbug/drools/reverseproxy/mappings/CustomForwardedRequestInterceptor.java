package org.chtijbug.drools.reverseproxy.mappings;

import com.github.mkopylec.charon.configuration.MappingProperties;
import com.github.mkopylec.charon.core.http.ForwardedRequestInterceptor;
import com.github.mkopylec.charon.core.http.RequestData;
import org.springframework.stereotype.Component;

@Component
public class CustomForwardedRequestInterceptor implements ForwardedRequestInterceptor {

    @Override
    public void intercept(RequestData data, MappingProperties mapping) {
        System.out.println("tptp");

    }
}