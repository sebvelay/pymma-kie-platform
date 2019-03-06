package org.chtijbug.drools.reverseproxy.mappings;

import com.github.mkopylec.charon.configuration.CharonProperties;
import com.github.mkopylec.charon.configuration.MappingProperties;
import com.github.mkopylec.charon.core.http.HttpClientProvider;
import com.github.mkopylec.charon.core.mappings.MappingsCorrector;
import com.github.mkopylec.charon.core.mappings.MappingsProvider;
import org.chtijbug.drools.reverseproxy.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class CustomMappingsProvider extends MappingsProvider {


    @Autowired
    private UpdateService updateService;


    public CustomMappingsProvider(ServerProperties server, CharonProperties charon, MappingsCorrector mappingsCorrector, HttpClientProvider httpClientProvider) {
        super(server, charon, mappingsCorrector,httpClientProvider);
    }



    @Override
    protected boolean shouldUpdateMappings(HttpServletRequest httpServletRequest) {
        return updateService.getToUpdate();
    }

    @Override
    protected List<MappingProperties> retrieveMappings() {
          return updateService.retrievePath();
    }
}
