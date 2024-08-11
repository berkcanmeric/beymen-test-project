package Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class ApiAutomationTest {

    private static final String BASE_URI = "https://api.trello.com";
    private static final String API_KEY = "e2bed3149b650b4e55909ba4b8be0743";
    private static final String API_TOKEN = "674f62b9a95e0924d931f93c6cb1748d1ae0f05de985e4987597f69212d5f923";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    public void testTrelloApi() {
        String boardId = createBoard("Testinium");
        String listId = createList("test1", boardId);
        String card1Id = createCard(listId);
        String card2Id = createCard(listId);

        String[] cardIds = {card1Id, card2Id};
        Random random = new Random();
        int randomIndex = random.nextInt(cardIds.length);

        updateCard(cardIds[randomIndex]);
        deleteCard(card1Id);
        deleteCard(card2Id);
        deleteBoard(boardId);
    }

    private String createBoard(String name) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .queryParam("name", name)
                .when()
                .post("/1/boards/");

        System.out.println("Create Board Response: " + response.getBody().asString());

        response.then().statusCode(200);
        return response.path("id");
    }

    private String createList(String name, String boardId) {
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .queryParam("name", name)
                .queryParam("idBoard", boardId)
                .when().post("/1/lists/");

        System.out.println("Create List Response: " + response.getBody().asString());

        response.then().statusCode(200);
        return response.path("id");
    }

    private String createCard(String listId) {
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .queryParam("idList", listId)
                .when().post("/1/cards/");

        System.out.println("Create Card Response: " + response.getBody().asString());

        response.then().statusCode(200);
        return response.path("id");
    }

    private void updateCard(String cardId) {
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .queryParam("name", "cardtest")
                .when().put("/1/cards/{id}/", cardId);

        System.out.println("Update Card Response: " + response.getBody().asString());

        response.then().statusCode(200);
    }

    private void deleteCard(String cardId) {
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .when().delete("/1/cards/{id}/", cardId);

        System.out.println("Delete Card Response: " + response.getBody().asString());

        response.then().statusCode(200);
    }

    private void deleteBoard(String boardId) {
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .when().delete("/1/boards/{id}/", boardId);

        System.out.println("Delete Board Response: " + response.getBody().asString());

        response.then().statusCode(200);
    }
}
