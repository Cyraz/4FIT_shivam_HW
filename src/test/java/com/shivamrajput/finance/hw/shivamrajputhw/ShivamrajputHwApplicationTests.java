package com.shivamrajput.finance.hw.shivamrajputhw;

import com.shivamrajput.finance.hw.shivamrajputhw.Application.ShivamrajputHwApplication;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto.LoanDTO;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShivamrajputHwApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShivamrajputHwApplicationTests {
    @LocalServerPort
    int port;

    String baseUrl;
    HttpHeaders headers;

    @Before
    public void inti() {
        baseUrl = "http://localhost:" + port;
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateLoan() throws JSONException {
        String url = baseUrl + "/loan/create";
        TestRestTemplate restTemplate = new TestRestTemplate();

        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setIP("127.0.0.1");
        loanDTO.setFirstname("Shivam");
        loanDTO.setLastname("Rajput");
        loanDTO.setPersonalNumber(123456789l);
        loanDTO.setTerm(5);
        loanDTO.setAmount(BigDecimal.valueOf(150));

        HttpEntity entity = new HttpEntity<LoanDTO>(loanDTO, headers);
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        assertEquals(200,response.getStatusCodeValue());

        JSONAssert.assertEquals("{type:SUCCESS}",response.getBody().toString(),false);
    }

}
