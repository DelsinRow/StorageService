package com.orioinc.storageservice.model;

import com.orioinc.storageservice.EncodeService;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
//@Document(collection = "text")
public class DataText {
    private String key;
    private String inputText;

    public DataText() {
        this.key = new EncodeService().getUniqueKey();

    }

}
