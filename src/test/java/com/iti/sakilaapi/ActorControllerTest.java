package com.iti.sakilaapi;

import com.iti.sakilaapi.model.dto.response.ActorDTOResp;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

import java.time.Instant;

public class ActorControllerTest {
    private static final int HTTP_OK = 200;
    private static final int DELETE_ID = 225;
    private static final int GET_BY_ID = 2;
    private static final int UPDATE_ID = 243;
    private static Client client;
    private final String url = "http://localhost:8080/sakila-restful/webapi/actors";

    @BeforeAll
    public static void setUp() {
        client = ClientBuilder.newClient();
    }

    @DisplayName("GET /actors/{id}")
    @Test
    public void test_Get_Actor_By_Id() {
        // Act
        Response response = client.target(url)
                .path("{id}")
                .resolveTemplate("id", GET_BY_ID)
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
    }

    @DisplayName("GET /actors")
    @Test
    public void test_Get_All_Actors() {
        // Act
        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
    }

    @DisplayName("DELETE /actors/{id}")
    @Disabled
    @Test
    public void test_Delete_Actor_By_Id() {
        // Act
        Response response = client.target(url)
                .path("{id}")
                .resolveTemplate("id", DELETE_ID)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
    }

    @DisplayName("POST /actors")
    @Test
    public void test_Create_Actor() {
        // Arrange
        ActorDTOResp actorDto = new ActorDTOResp("karim", "taha");
        // Act
        Response response = client.target(url)
                .request()
                .post(Entity.entity(actorDto, MediaType.APPLICATION_JSON));
        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
        System.out.println(response.getEntity() + " - id -> " + actorDto.getId());
        response.close();
    }

    @DisplayName("PUT /actors")
    @Test
    public void test_Update_Actor() {
        // Arrange
        ActorDTOResp actorDto = new ActorDTOResp("karim", "taha");
        // Act
        Response response = client.target(url)
                .path("{id}")
                .resolveTemplate("id", UPDATE_ID)
                .request()
                .put(Entity.entity(actorDto, MediaType.APPLICATION_JSON));
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
