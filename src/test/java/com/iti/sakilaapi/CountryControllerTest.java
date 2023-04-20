package com.iti.sakilaapi;

import com.iti.sakilaapi.model.dto.requests.CountryDTOReq;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

public class CountryControllerTest {
    private static final int HTTP_OK = 200;
    private static final int DELETE_ID = 235;
    private static final int UPDATE_ID = 51;
    private static final int GET_BY_ID = 2;
    private static Client client;
    private final String url = "http://localhost:8080/sakila-restful/webapi/countries";

    @BeforeAll
    public static void setUp() {
        client = ClientBuilder.newClient();
    }

    @DisplayName("GET /countries/{id}")
    @Test
    public void test_Get_Country_By_Id() {
        // Act
        Response response = client.target(url)
                .path("{id}")
                .resolveTemplate("id", GET_BY_ID)
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
        response.close();
    }

    @DisplayName("GET /countries")
    @Test
    public void test_Get_All_Countries() {
        // Act
        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
        response.close();
    }

    @DisplayName("DELETE /countries/{id}")
    @Test
    public void test_Delete_Country_By_Id() {
        // Act
        Response response = client.target(url)
                .path("{id}")
                .resolveTemplate("id", DELETE_ID)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
        response.close();
    }

    @DisplayName("POST /countries")
    @Test
    public void test_Create_Country() {
        // Arrange
        CountryDTOReq countryDto = new CountryDTOReq();
        countryDto.setCountry("Cairo");
        // Act
        Response response = client.target(url)
                .request()
                .post(Entity.entity(countryDto, MediaType.APPLICATION_JSON));
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
        response.close();
    }

    @DisplayName("PUT /countries")
    @Test
    public void test_Update_Country() {
        // Arrange
        CountryDTOReq countryDto = new CountryDTOReq();
        countryDto.setCountry("Cairo");
        // Act
        Response response = client.target(url)
                .path("{id}")
                .resolveTemplate("id", UPDATE_ID)
                .request()
                .put(Entity.entity(countryDto, MediaType.APPLICATION_JSON));
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
        System.out.println(response.getEntity());
        response.close();
    }

    @AfterAll
    public static void destroy() {
        client.close();
    }
}