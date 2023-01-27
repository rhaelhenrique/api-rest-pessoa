package controller;

import br.com.rhr.pessoa.http.controller.PessoaController;
import br.com.rhr.pessoa.model.Pessoa;
import br.com.rhr.pessoa.model.dto.PlainPessoaDto;
import br.com.rhr.pessoa.service.PessoaService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@WebMvcTest
public class PessoaControllerTest {

    private PessoaController pessoaController;

    @MockBean
    private PessoaService pessoaService;

    @BeforeEach
    public void setup(){
        standaloneSetup(this.pessoaController);
    }

    @Test
    public void deveRetornarSucesso_QuandoListarPessoas(){
        Mockito.when(this.pessoaService.listarPessoa())
                .thenReturn(new PlainPessoaDto(1, "Rhael Henrique", "1992-02-07"));

        given()
                .accept(ContentType.JSON)
                .when().get("/pessoas")
                .then().statusCode(HttpStatus.OK.value());
    }
}
