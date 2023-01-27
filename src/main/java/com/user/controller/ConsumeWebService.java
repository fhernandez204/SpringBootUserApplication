package com.user.controller;

import com.google.gson.Gson;
import com.user.cifrado.CifrarConDES;
import com.user.model.Root;
import com.user.model.RootResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class ConsumeWebService {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/api")
    public String getApi() {
        CifrarConDES cifrarConDES = new CifrarConDES();
        String valueCifrado = cifrarConDES.cifrar("1-9");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("X-API-Key", "f2f719e0");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "https://my.api.mockaroo.com/test-tecnico/search/"+valueCifrado, HttpMethod.GET, entity, String.class);

        Gson gson = new Gson();
        Root root = gson.fromJson(response.getBody(), Root.class);
        RootResponse rootResponse = new RootResponse();
        rootResponse.setResponseCode(String.valueOf(root.getResponseCode()));
        rootResponse.setDescription(root.getDescription());
        rootResponse.setResult(root.getResult().items.size());
        rootResponse.setElapsedTime(String.valueOf(response.getHeaders().get("x-runtime")));
        String JSON = gson.toJson(rootResponse);
        return JSON;
    }
}
