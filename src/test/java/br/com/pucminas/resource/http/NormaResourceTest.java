package br.com.pucminas.resource.http;

import br.com.pucminas.TestContainerLifecycleManager;
import br.com.pucminas.domain.Norma;
import br.com.pucminas.resource.dto.NormaRequest;
import br.com.pucminas.resource.dto.NormaResponse;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.database.rider.core.api.configuration.Orthography.LOWERCASE;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@QuarkusTestResource(TestContainerLifecycleManager.class)
@DBRider
@DBUnit(caseInsensitiveStrategy = LOWERCASE)
class NormaResourceTest {

    @BeforeEach
    @DataSet(cleanBefore = true)
    void setup() {}

    @Test
    @DataSet(value = "test-scenario-normas-salvas-1.yml")
    void listarTodas() {
        var normaSalva1 = new Norma();
        normaSalva1.setId(1L);
        normaSalva1.setCodigo("1.1.1");
        normaSalva1.setNome("Norma disposicoes gerais");
        normaSalva1.setDescricaoCompleta("O objetivo desta Norma é estabelecer as disposições gerais.");

        var normaSalva2 = new Norma();
        normaSalva2.setId(2L);
        normaSalva2.setCodigo("1.1.2");
        normaSalva2.setNome("Norma anexo 1");
        normaSalva2.setDescricaoCompleta("Consideram-se os termos e definições constantes no Anexo I.");


        NormaResponse[] response = given()
                .when()
                .get("/api/v1/normas")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract()
                .as(NormaResponse[].class);

        assertThat(response).hasSize(2);

        assertThat(response[0])
                .usingRecursiveComparison()
                .isEqualTo(normaSalva1);

        assertThat(response[1])
                .usingRecursiveComparison()
                .isEqualTo(normaSalva2);
    }

    @Test
    void cadastrar() {
        var request = new NormaRequest();
        request.setCodigo("1.09");
        request.setNome("nomeNovo");
        request.setDescricaoCompleta("descricaoCompletaNova");

        Response response = given()
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/normas")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .and()
                .extract().response();

        assertThat(response.getHeader("location"))
                .endsWith("/api/v1/normas/" + 1L);
    }

    @Test
    @DataSet(value = "test-scenario-normas-salvas-1.yml")
    void alterarNorma_comSucesso() {
        var requestJson = new NormaRequest();
        requestJson.setCodigo("1.99");
        requestJson.setNome("nomeAlterado");
        requestJson.setDescricaoCompleta("descricaoAlterada");

        given()
                .pathParam("id", 1L)
                .when()
                .body(requestJson)
                .contentType(ContentType.JSON)
                .when()
                .put("/api/v1/normas/{id}")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        NormaResponse response = given()
                .pathParam("id", 1L)
                .when()
                .contentType(ContentType.JSON)
                .get("/api/v1/normas/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract()
                .as(NormaResponse.class);

        assertThat(response)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(requestJson);
}

    @Test
    void alterarNorma_deveRetornarErro_quandoNormaNaoExiste() {
        var requestJson = new NormaRequest();
        requestJson.setCodigo("1.99");
        requestJson.setNome("nomeAlterado");
        requestJson.setDescricaoCompleta("descricaoAlterada");

        given()
                .pathParam("id", 1L)
                .when()
                .body(requestJson)
                .contentType(ContentType.JSON)
                .when()
                .put("/api/v1/normas/{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .and()
                .assertThat()
                .body("erros[0]", equalTo("Norma nao encontrada"));
    }

    @Test
    @DataSet(value = "test-scenario-normas-salvas-1.yml")
    void buscarNormaPorId_deveRetornarOk() {
        var normaSalvaExperada = new Norma();
        normaSalvaExperada.setId(1L);
        normaSalvaExperada.setCodigo("1.1.1");
        normaSalvaExperada.setNome("Norma disposicoes gerais");
        normaSalvaExperada.setDescricaoCompleta("O objetivo desta Norma é estabelecer as disposições gerais.");

        NormaResponse response = given()
                .pathParam("id", 1L)
                .when()
                .contentType(ContentType.JSON)
                .get("/api/v1/normas/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract()
                .as(NormaResponse.class);

        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(normaSalvaExperada);
    }

    @Test
    void buscarNormaPorId_deveRetornar404_quandoNaoEncontrado() {
        given()
                .pathParam("id", 999L)
                .when()
                .contentType(ContentType.JSON)
                .get("/api/v1/normas/{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}