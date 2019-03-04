package org.chtijbug.drools.reverseproxy.mappings;

import com.github.mkopylec.charon.configuration.CharonProperties;
import com.github.mkopylec.charon.configuration.MappingProperties;
import com.github.mkopylec.charon.core.http.HttpClientProvider;
import com.github.mkopylec.charon.core.mappings.MappingsCorrector;
import com.github.mkopylec.charon.core.mappings.MappingsProvider;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomMappingsProvider extends MappingsProvider {

    public CustomMappingsProvider(ServerProperties server, CharonProperties charon, MappingsCorrector mappingsCorrector, HttpClientProvider httpClientProvider) {
        super(server, charon, mappingsCorrector,httpClientProvider);
    }



    @Override
    protected boolean shouldUpdateMappings(HttpServletRequest httpServletRequest) {
        return false;
    }

    @Override
    protected List<MappingProperties> retrieveMappings() {
        List<MappingProperties> total = new ArrayList<>();
        MappingProperties mappingProperties = new MappingProperties();
        total.add(mappingProperties);
        mappingProperties.setName("fraud-doc");
        mappingProperties.setPath("/fraud-doc");
        mappingProperties.getCustomConfiguration().put("connect",200);
        mappingProperties.getCustomConfiguration().put("read",200);
        mappingProperties.setStripPath(true);
        mappingProperties.getDestinations().add("http://macbook-pro-de-nicolas-2.local:8091/api/");
        MappingProperties mappingProperties2 = new MappingProperties();
        total.add(mappingProperties2);
        mappingProperties2.setName("fraud-run");
        mappingProperties2.setPath("/fraud-Oney-fraud");
        mappingProperties2.getCustomConfiguration().put("connect",200);
        mappingProperties2.getCustomConfiguration().put("read",200);
        mappingProperties2.setStripPath(true);
        mappingProperties2.getDestinations().add("http://macbook-pro-de-nicolas-2.local:8091/api/fraud-Oney-fraud/");
        return total;
    }
}
