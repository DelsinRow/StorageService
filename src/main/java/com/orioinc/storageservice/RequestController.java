package com.orioinc.storageservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RequestController {

    @GetMapping("/test-request/{key}")
    public TestRequest testRequest(@PathVariable String key) {
        TestRequest testRequest = new TestRequest();
        testRequest.setInputText("This text i get following key-link");
        return testRequest;
    }

    @PostMapping("/test-request")
    public void createPostRequest(@RequestBody TestRequest postRequest) {
        //create mapping my request
    }

    public void sendRequest() {
        RestTemplate restTemplate = new RestTemplate();

        TestRequest testRequest = restTemplate.getForObject("http://localhost:8080/test-request/first-key", TestRequest.class);

//        TestRequest newTestRequest = new TestRequest();
//        newTestRequest.setInputText("Some text for checking post request");
//        restTemplate.postForObject("http://localhost:8080/test-request", newTestRequest, Void.class);
    }
}
