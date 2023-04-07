package com.orioinc.storageservice;

import com.orioinc.storageservice.exceptions.NotFoundKeyException;
import com.orioinc.storageservice.exceptions.ResponseError;
import com.orioinc.storageservice.model.DataText;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/document")
public class MainController {

    private final CreateUniqueKeyService CreateUniqueKeyService;
    private final Repository repository;
    private final HttpServletResponse response;

    private final MongoTemplate mongoTemplate;

    @GetMapping("/get/{key}")
    public String getText(@PathVariable String key) {
        String response ="";
        if (repository.findByKey(key) != null){
            response = repository.findByKey(key).getInputText();
        } else {
            throw new NotFoundKeyException(HttpStatus.NOT_FOUND, "Invalid key");
        }
        return response;
    }

    @PostMapping("/post")
    public String addText(@RequestBody String text ) {
        String key = CreateUniqueKeyService.getUniqueKey();
        DataText data = new DataText(key, text);
        repository.save(data);
        return "Your key: " + data.getKey();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError emptyRequest(HttpMessageNotReadableException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError("Empty request. Please, enter some text  ", HttpStatus.BAD_REQUEST);
    }
}
