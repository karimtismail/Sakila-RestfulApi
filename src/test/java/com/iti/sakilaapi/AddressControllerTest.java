package com.iti.sakilaapi;

import com.iti.sakilaapi.model.dto.response.AddressDTOResp;
import com.iti.sakilaapi.model.dto.response.CityDTOResp;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

public class AddressControllerTest {
    private static final int HTTP_OK = 200;
    private static final int DELETE_ID = 232;
    private static final int UPDATE_ID = 243;
    private static final int GET_BY_ID = 2;
    private static Client client;
    private final String url = "http://localhost:8080/sakila-restful/webapi/addresses";

    @BeforeAll
    public static void setUp() {
        client = ClientBuilder.newClient();
    }

    @DisplayName("GET /addresses/{id}")
    @Test
    public void test_Get_Address_By_Id() {
        // Act
        Response response = client.target(url)
                .path("{id}")
                .resolveTemplate("id", GET_BY_ID)
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
    }

    @DisplayName("GET /addresses")
    @Test
    public void test_Get_All_Addresss() {
        // Act
        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
    }

    @DisplayName("DELETE /addresses/{id}")
    @Test
    public void test_Delete_Address_By_Id() {
        // Act
        Response response = client.target(url)
                .path("{id}")
                .resolveTemplate("id", DELETE_ID)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
    }

    @DisplayName("POST /addresses")
    @Test
    public void test_Create_Address() {
        // Arrange
        AddressDTOResp addressDto = new AddressDTOResp();
        addressDto.setAddress("123 Main St");
        addressDto.setAddress2("Apt 4");
        addressDto.setDistrict("Downtown");
        addressDto.setCity(new CityDTOResp());
        addressDto.setPostalCode("12345");
        addressDto.setPhone("555-5555");
        // Act
        Response response = client.target(url)
                .request()
                .post(Entity.entity(addressDto, MediaType.APPLICATION_JSON));
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
        System.out.println(response.getEntity() + " - id -> " + addressDto.getId());
        response.close();
    }

    @DisplayName("PUT /addresses")
    @Test
    public void test_Update_Address() {
        // Arrange
        AddressDTOResp addressDto = new AddressDTOResp();
        addressDto.setAddress("123 Main St");
        addressDto.setAddress2("Apt 4");
        addressDto.setDistrict("Downtown");
        addressDto.setCity(new CityDTOResp());
        addressDto.setPostalCode("12345");
        addressDto.setPhone("555-5555");
        // Act
        Response response = client.target(url)
                .path("{id}")
                .resolveTemplate("id", UPDATE_ID)
                .request()
                .put(Entity.entity(addressDto, MediaType.APPLICATION_JSON));
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
