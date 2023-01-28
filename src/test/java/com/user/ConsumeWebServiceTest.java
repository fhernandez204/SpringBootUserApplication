package com.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.cifrado.CifrarConDES;
import com.user.controller.ConsumeWebService;
import org.apache.catalina.security.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.user.mock.UserMock.getRootResponse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ConsumeWebService.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@ContextConfiguration(classes={SecurityConfig.class})
public class ConsumeWebServiceTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ObjectMapper mapper;

    private String URL  =   "https://my.api.mockaroo.com/test-tecnico/search/";
    final   Logger LOG  =    Logger.getLogger("ConsumeWebServiceTest");

    @Test
    public void givenConsumeWebService_whenGet_thenReturnJsonArray() {
        try {
            var response = getRootResponse();
            CifrarConDES cifrarConDES = new CifrarConDES();
            String valueCifrado = cifrarConDES.cifrar("1-9");

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("X-API-Key", "f2f719e0");
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            LOG.log(Level.INFO, "valueCifrado: " + valueCifrado);

            ResultActions result = mvc.perform(
                    get("https://my.api.mockaroo.com/test-tecnico/search/" + valueCifrado)
                            .contentType(MediaType.APPLICATION_JSON)
                            .headers(headers)
                            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(mapper.writeValueAsString(response)));
            String content = result.toString();
            LOG.info("content: " + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
