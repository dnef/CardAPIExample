package org.example.controller;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.MyHTTPServer;
import org.example.exception.ServiceException;
import org.example.service.ServiceCard;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HandlerTest {
    ServiceCard serviceCard = new ServiceCard();

    @Before
    public void setUp() throws Exception {
        MyHTTPServer.run("src/main/resources/configTest.properties");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void handleAllCard() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8001/apiCard/allCard");
        CloseableHttpResponse response = null;
        String content;
        response = httpclient.execute(httpGet);
        content = EntityUtils.toString(response.getEntity(), "UTF-8");
        Assert.assertEquals("[{\"id\":1,\"cardNumber\":\"1111111111111111\",\"accountNumber\":\"11111111111111111111\",\"active\":true,\"date_open\":1614970800000},{\"id\":2,\"cardNumber\":\"1111111111111112\",\"accountNumber\":\"11111111111111111112\",\"active\":true,\"date_open\":1617562800000},{\"id\":3,\"cardNumber\":\"1111111111111113\",\"accountNumber\":\"11111111111111111113\",\"active\":true,\"date_open\":1617562800000},{\"id\":4,\"cardNumber\":\"1111111111111114\",\"accountNumber\":\"11111111111111111113\",\"active\":true,\"date_open\":1617562800000}]",
                content);

    }

    @Test
    public void handleAddBalanse() throws IOException, ServiceException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestBuilder reqbuilder = RequestBuilder.post();
        RequestBuilder reqbuilder1 = reqbuilder.setUri("http://localhost:8001/apiCard/addBalance");
        RequestBuilder reqbuilder2 = reqbuilder1.addParameter("card", "1111111111111111")
                .addParameter("balance", "99999");
        HttpUriRequest httppost = reqbuilder2.build();
        httpclient.execute(httppost);
        HttpResponse httpresponse = httpclient.execute(httppost);
        Assert.assertEquals(httpresponse.getStatusLine().getStatusCode(), 200);
//        long balance = serviceCard.getBalanceAccountForCard("1111111111111111");
//        Assert.assertEquals(balance, 199999L);
    }

    @Test
    public void handleBalanse() throws URISyntaxException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URI uri = new URIBuilder("http://localhost:8001/apiCard/getBalance").setParameter("card", "1111111111111112")
                .build();
        HttpGet httpGet = new HttpGet(uri);

        CloseableHttpResponse response = null;
        response = httpclient.execute(httpGet);
        String content = EntityUtils.toString(response.getEntity(), "UTF-8");
        //System.out.println("--------------"+content);
        Assert.assertEquals(content, "Карта №: 1111111111111112 баланс: 200000");

    }

    @Test
    public void handleNewCard() throws IOException, ServiceException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("http://localhost:8001/apiCard/newCard");
        StringEntity params = new StringEntity("[{\"id\":1,\"cardNumber\":\"1111111111111119\",\"accountNumber\":\"11111111111111111111\",\"active\":true,\"date_open\":1614970800000}]");
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        //Assert.assertEquals(serviceCard.getCardByNumber("1111111111111119").getAccountNumber(), "11111111111111111111");
    }
}