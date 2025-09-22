

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class Teste {

    @Test
    public void teste (){
        baseURI = "https://serverest.dev";
//        basePath = "/usuarios";

        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"nome\": \"Fulano da Silva\",\n" +
                        "  \"email\": \"ffff@qa.com.br\",\n" +
                        "  \"password\": \"teste\",\n" +
                        "  \"administrador\": \"true\"\n" +
                        "}")
                .when()
                    .post("/usuarios")
                .then()
                .log()
                .all();

    }

}
