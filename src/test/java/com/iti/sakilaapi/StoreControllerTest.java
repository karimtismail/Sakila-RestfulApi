package com.iti.sakilaapi;

import com.iti.sakilaapi.model.dto.response.AddressDTOResp;
import com.iti.sakilaapi.model.dto.response.StaffDTOResp;
import com.iti.sakilaapi.model.dto.response.StoreDTOResp;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

import java.time.Instant;

public class StoreControllerTest {
    private static final int HTTP_OK = 200;
    private static final int DELETE_ID = 235;
    private static final int ACTOR_ID = 2;
    private static Client client;
    private final String url = "http://localhost:8080/sakila-restful/webapi/stores";

    @BeforeAll
    public static void setUp() {
        client = ClientBuilder.newClient();
    }

    @DisplayName("GET /stores/{id}")
    @Test
    public void test_Get_Store_By_Id() {
        // Act
        Response response = client.target(url)
                .path("{id}")
                .resolveTemplate("id", ACTOR_ID)
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
    }

    @DisplayName("GET /stores")
    @Test
    public void test_Get_All_Stores() {
        // Act
        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
    }

    @DisplayName("DELETE /stores/{id}")
    @Test
    public void test_Delete_Store_By_Id() {
        // Act
        Response response = client.target(url)
                .path("{id}")
                .resolveTemplate("id", DELETE_ID)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
    }

    @DisplayName("POST /stores")
    @Test
    public void test_Create_Store() {
        // Arrange
        StoreDTOResp storeDto = new StoreDTOResp();
        storeDto.setLastUpdate(Instant.now());
        storeDto.setManagerStaff(new StaffDTOResp());
        storeDto.setAddress(new AddressDTOResp());
        storeDto.setLastUpdate(Instant.now());
        // Act
        Response response = client.target(url)
                .request()
                .post(Entity.entity(storeDto, MediaType.APPLICATION_JSON));
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
        System.out.println(response.getEntity() + " - id -> " + storeDto.getId());
    }

    @DisplayName("PUT /stores")
    @Test
    public void test_Update_Store() {
        // Arrange
        StoreDTOResp storeDto = new StoreDTOResp();
        storeDto.setId(2);
        storeDto.setLastUpdate(Instant.now());
        storeDto.setManagerStaff(new StaffDTOResp());
        storeDto.setAddress(new AddressDTOResp());
        storeDto.setLastUpdate(Instant.now());
        // Act
        Response response = client.target(url)
                .request()
                .put(Entity.entity(storeDto, MediaType.APPLICATION_JSON));
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
        System.out.println(response.getEntity());
    }

    @AfterAll
    public static void destroy() {
        client.close();
    }
}