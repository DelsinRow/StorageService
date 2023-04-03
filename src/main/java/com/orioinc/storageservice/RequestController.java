package com.orioinc.storageservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("documents")
public class RequestController {

    private Map<String, String> map = new HashMap<>() {{
        put("qwerty", "This is first row");
        put("asdfgh", "This is second row");
        put("zxcvbn", "This is third row");

    }};

    @GetMapping
    public Map<String, String> map() {
        return map;
    }

    @GetMapping("{key}")
    public String getText(@PathVariable String key) {
        return map.get(key);
    }

    @PostMapping
    public String create(@RequestBody String text) {
        String newKey = new EncodeClass().encodeKey(text);

        map.put(newKey, text);

        return text;
    }

    //send object in DB
    //create hash-key
    //set key-field by dataText object
    //create .../hash - link
//    public void sendRequest() {
//        RestTemplate restTemplate = new RestTemplate();
//        DataText dataText = restTemplate.getForObject("http://localhost:8080/document/key2 ", DataText.class);
//        DataText dataText = new DataText();
//        dataText.setInputText("Some text for checking post request");
//        restTemplate.postForObject("http://localhost:8080/document", newTestRequest, Void.class);
//    }
}
