package com.orioinc.storageservice;

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
        String uniqueKey = encodeService.getUniqueKey();
        storageRepository.putInStorage(uniqueKey, text);
        return "Your key: " + uniqueKey;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e) {
        log.error(e.getMessage());
        return "Something wrong";
    }
}
