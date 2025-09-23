import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class Teste {

    private final String BASE_URI = "https://serverest.dev";

    public String login() {

        String bearerToken = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body("""
                    {
                      "email": "zblr@emailteste.com",
                      "password": "1234"
                    }
                    """)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract()
                .path("authorization");

        return bearerToken.replace("Bearer ", "");
    }

    @Test
    public void criarUsuarioValido() {
        String token = login();
        long timestamp = System.currentTimeMillis();

        String jsonBody = String.format("""
        {
            "nome": "Dodo Farley",
            "email": "dodo.farley.%d@example.com",
            "password": "teste123",
            "administrador": "true"
        }
        """, timestamp);

        given()
                .baseUri(BASE_URI)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("message", containsString("Cadastro realizado com sucesso"))
                .log().body();
    }

    @Test
    public void listarUsuarios() {
        String token = login();

        given()
                .baseUri(BASE_URI)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/usuarios")
                .then()
                .statusCode(200)
                .body("usuarios", not(empty()))
                .log().body();
    }

    @Test
    public void buscarUsuarioPorId() {
        String token = login();
        String userId = "1bGH1RmZAIcpbUOW";

        given()
                .baseUri(BASE_URI)
                .header("Authorization", "Bearer " + token)
                .pathParam("id", userId)
                .when()
                .get("/usuarios/{id}")
                .then()
                .statusCode(200)
                .log().body();  // imprime o corpo da resposta
    }


    @Test
    public void criarEAtualizarUsuario() {
        String token = login();
        long timestamp = System.currentTimeMillis();

        String userId = given()
                .baseUri(BASE_URI)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(String.format("""
                {
                    "nome": "Usuário Para Atualizar",
                    "email": "update.user.%d@example.com",
                    "password": "teste123",
                    "administrador": "true"
                }
                """, timestamp))
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .extract()
                .path("_id");

        given()
                .baseUri(BASE_URI)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .pathParam("id", userId)
                .body(String.format("""
                {
                    "nome": "Usuário Atualizado",
                    "email": "usuario.atualizado.%d@example.com",
                    "password": "novaSenha123",
                    "administrador": "false"
                }
                """, timestamp))
                .when()
                .put("/usuarios/{id}")
                .then()
                .statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"))
                .log().body();
    }

    @Test
    public void criarEDeletarUsuario() {
        String token = login();
        long timestamp = System.currentTimeMillis();

        String userId = given()
                .baseUri(BASE_URI)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(String.format("""
                {
                    "nome": "Usuário Para Deletar",
                    "email": "delete.user.%d@example.com",
                    "password": "teste123",
                    "administrador": "false"
                }
                """, timestamp))
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .extract()
                .path("_id");

        given()
                .baseUri(BASE_URI)
                .header("Authorization", "Bearer " + token)
                .pathParam("id", userId)
                .when()
                .delete("/usuarios/{id}")
                .then()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"))
                .log().body();
    }

}
