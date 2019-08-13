package org.chtijbug.drools.reverseproxy.mappings;

import com.github.mkopylec.charon.configuration.MappingProperties;
import com.github.mkopylec.charon.core.http.ReceivedResponseInterceptor;
import com.github.mkopylec.charon.core.http.ResponseData;
import org.springframework.stereotype.Component;

@Component
public class CustomReceivedResponseInterceptor implements ReceivedResponseInterceptor {
    @Override
    public void intercept(ResponseData responseData, MappingProperties mappingProperties) {
        System.out.println("response");
    }
}
