package com.orioinc.storageservice;

import com.orioinc.storageservice.exceptions.NotFoundKeyException;
import com.orioinc.storageservice.model.DataText;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class DataControlService {
    private final Repository repository;

    public DataControlService(Repository repository) {
        this.repository = repository;
    }

    public String getText(String key) {
        String response;
        if (repository.findByKey(key) != null){
            response = repository.findByKey(key).getInputText();
        } else {
            throw new NotFoundKeyException(HttpStatus.NOT_FOUND, "Document not found");
        }
        return response;
    }

    public DataText saveData(String title, String text) {
        String key = getUniqueKey();
        LocalDate date = LocalDate.now();
        DataText data = new DataText(key, text, title, date);
        repository.save(data);
        return data;
    }

    public String getUniqueKey() {
        UUID uuid = UUID.randomUUID();
        ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());
        String base64 = Base64.getUrlEncoder().withoutPadding().encodeToString(buffer.array());
        return base64;
    }

    public List<DataText> getAllDocument() {
        return repository.findAll();
    }
}
