package com.orioinc.storageservice;

import com.orioinc.storageservice.model.DataText;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController("/document")
public class MainController {

    private final EncodeService encodeService;
    private final StorageSevice storageSevice;
    private final StorageRepository storageRepository;

    //for checking
    @GetMapping
    public Map<String, String> map() {
        return storageRepository.getStorage();
    }

    @GetMapping("/get/{uniqueKey}")
    public String getText(@PathVariable String uniqueKey) {
        return storageSevice.getText(uniqueKey);
    }

    @PostMapping("/post")
    public String sendText(@RequestBody String text ) {
        String key = encodeService.getUniqueKey();
        DataText data = new DataText(key, text);
        storageRepository.addElement(data);
        return "Your key: " + data.getKey();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e) {
        log.error(e.getMessage());
        return "Something wrong";
    }
}
