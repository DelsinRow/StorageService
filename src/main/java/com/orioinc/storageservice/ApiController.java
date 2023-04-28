package com.orioinc.storageservice;

import com.orioinc.storageservice.exceptions.ResponseError;
import com.orioinc.storageservice.model.DataText;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {
    private final DataControlService dataControlService;

    @GetMapping("/document/{key}")
    public String getText(@PathVariable String key) {
        return dataControlService.getText(key);
    }

    @PostMapping("/document")
    public DataText savaData(@RequestHeader("Title") String title, @RequestBody String text) {

        return dataControlService.saveData(title, text);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError emptyRequest(HttpMessageNotReadableException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError("Empty request. Please, enter some text  ", HttpStatus.BAD_REQUEST);
    }
}
