package com.orioinc.storageservice;

import com.orioinc.storageservice.model.DataText;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/document")
public class MainController {

    private final CreateUniqueKeyService CreateUniqueKeyService;
    private final Repository repository;

    private final MongoTemplate mongoTemplate;

    @GetMapping("/get/{key}")
    public String getText(@PathVariable String key) {
        //add exception handling
        return repository.findByKey(key).getInputText();
    }

    @PostMapping("/post")
    public String addText(@RequestBody String text ) {
        String key = CreateUniqueKeyService.getUniqueKey();
        DataText data = new DataText(key, text);
        repository.save(data);
        return "Your key: " + data.getKey();
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    public String handle(IllegalArgumentException e) {
//        log.error(e.getMessage());
//        return "Something wrong";
//    }
}
