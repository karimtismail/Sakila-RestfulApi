package com.iti.sakilaapi;

import com.iti.sakilaapi.model.dto.response.CategoryDTOResp;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

public class CategoryControllerTest {
    private static final int HTTP_OK = 200;
    private static final int DELETE_ID = 235;
    private static final int UPDATE_ID = 19;
    private static final int GET_BY_ID = 2;
    private static Client client;
    private final String url = "http://localhost:8080/sakila-restful/webapi/categories";

    @BeforeAll
    public static void setUp() {
        client = ClientBuilder.newClient();
    }

    @DisplayName("GET /categories/{id}")
    @Test
    public void test_Get_Category_By_Id() {
        // Act
        Response response = client.target(url)
                .path("{id}")
                .resolveTemplate("id", GET_BY_ID)
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
    }

    @DisplayName("GET /categories")
    @Test
    public void test_Get_All_Categorys() {
        // Act
        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
    }

    @DisplayName("DELETE /categories/{id}")
    @Test
    public void test_Delete_Category_By_Id() {
        // Act
        Response response = client.target(url)
                .path("{id}")
                .resolveTemplate("id", DELETE_ID)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
    }

    @DisplayName("POST /categories")
    @Test
    public void test_Create_Category() {
        // Arrange
        CategoryDTOResp categoryDto = new CategoryDTOResp();
        categoryDto.setName("category 1");
        // Act
        Response response = client.target(url)
                .request()
                .post(Entity.entity(categoryDto, MediaType.APPLICATION_JSON));
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
        System.out.println(response.getEntity() + " - id -> " + categoryDto.getId());
    }

    @DisplayName("PUT /categories")
    @Test
    public void test_Update_Category() {
        // Arrange
        CategoryDTOResp categoryDto = new CategoryDTOResp();
        categoryDto.setName("Travell");
        // Act
        Response response = client.target(url)
                .path("{id}")
                .resolveTemplate("id", UPDATE_ID)
                .request()
                .put(Entity.entity(categoryDto, MediaType.APPLICATION_JSON));
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