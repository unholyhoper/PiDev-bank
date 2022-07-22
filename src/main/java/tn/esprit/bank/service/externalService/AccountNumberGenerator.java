package tn.esprit.bank.service.externalService;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.math.BigInteger;


@Service
public  class AccountNumberGenerator {


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    @Value("${application.external.api.rapidApi.key}")
    String rapidApiKey;

    @Value("${application.external.api.rapidApi.host}")
    String rapidApHost;

    @Value("${application.external.api.rapidApi.url}")
    String rapidApUrl;

    public BigInteger generateAccountNumber()  {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", rapidApiKey);
        headers.set("X-RapidAPI-Host", rapidApHost);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                rapidApUrl, HttpMethod.GET,
                requestEntity, String.class, "");

        try {
            JSONObject jsonResponse = new JSONObject(response.getBody());
            return  new BigInteger(jsonResponse.getJSONArray("cards").getString(0));
        }catch (JSONException jsonException){
            throw new RuntimeException(jsonException);
        }

    }


}
