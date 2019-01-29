package org.chtijbug.drools.indexer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.chtijbug.drools.indexer.persistence.model.BusinessTransactionPersistence;
import org.chtijbug.drools.indexer.persistence.repository.BusinessTransactionPersistenceRepository;
import org.chtijbug.drools.logging.SessionExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service("storeService")
public class StoreLoggingService {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private BusinessTransactionPersistenceRepository repository;


    public void store(String fileName, String fileContent) {
        if (fileName!= null) {

            String[] data = fileName.split("-");
            Integer year = Integer.valueOf(data[0]);
            Integer month = Integer.valueOf(data[1]);
            Integer day =  Integer.valueOf(data[2]);
            Integer hour =  Integer.valueOf(data[3]);
            Integer minute =  Integer.valueOf(data[4]);
            Integer second = Integer.valueOf(data[5]);
            Integer millis =  Integer.valueOf(data[6]);
            String id  = data[7].substring(0,data[7].indexOf("."));
            BusinessTransactionPersistence item = new BusinessTransactionPersistence();
            item.setYear(year);
            item.setMonth(month);
            item.setDay(day);
            item.setHour(hour);
            item.setMinute(minute);
            item.setSecond(second);
            item.setMillis(millis);
            item.setTransactionId(id);
            try {
                SessionExecution object = mapper.readValue(fileContent,SessionExecution.class);

                item.setContent(object);
            } catch (IOException e) {
                e.printStackTrace();
            }
            item.setContent(fileContent);
            item.setId(UUID.randomUUID().toString());
            repository.save(item);
         }

        System.out.println("coucou");
    }
}
