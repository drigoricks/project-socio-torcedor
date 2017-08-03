package br.com.programa.torcedor;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.programa.torcedor.entity.SocioTorcedor;
import br.com.programa.torcedor.mock.SocioTorcedorRequestMock;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {TorcedorApplication.class})
public class SocioTorcedorRestTest {

    private static final String BASE_URL = "http://localhost:8082/torcedor/";

    @Autowired
    private TestRestTemplate socioTorcedorRestTemplate;
    
    private HttpEntity<String> httpEntity;

    @Before
    public void init() {
        MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<>();
        requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        this.httpEntity = new HttpEntity<>(requestHeaders);
    }

    @Test
    public void deveConsultarTodosSocioTorcedores() {
        ResponseEntity<List<SocioTorcedor>> responseEntity = socioTorcedorRestTemplate.exchange(BASE_URL,
                HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<SocioTorcedor>>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertFalse(responseEntity.getBody().isEmpty());
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
   
    @Test
    public void deveConsultarSocioTorcedorPorId() {
        String url = BASE_URL.concat("1");

        ResponseEntity<SocioTorcedor> responseEntity = socioTorcedorRestTemplate.exchange(
                url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<SocioTorcedor>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
    
    @Test
    public void deveRetornarHttp404AoConsultarSocioTorcedorPorIdQueNaoExiste() {
        String url = BASE_URL.concat("4651");

        ResponseEntity<SocioTorcedor> responseEntity = socioTorcedorRestTemplate.exchange(
                url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<SocioTorcedor>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 404);
    }

    @Test
    public void deveIncluirSocioTorcedorComSucesso() {
    	SocioTorcedor socioTorcedor = SocioTorcedorRequestMock.getSocioTorcedorRequest();

        ResponseEntity<SocioTorcedor> responseEntity =
                socioTorcedorRestTemplate.postForEntity(BASE_URL, socioTorcedor, SocioTorcedor.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
    
    @Test
    public void deveRetornarHttp400AoIncluirSocioTorcedorComNomeNulo() {
    	SocioTorcedor socioTorcedor = SocioTorcedorRequestMock.getSocioTorcedorRequest();
        socioTorcedor.setNome(null);

        ResponseEntity<SocioTorcedor> responseEntity =
                socioTorcedorRestTemplate.postForEntity(BASE_URL, socioTorcedor, SocioTorcedor.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoIncluirSocioTorcedorComTimeCoracaoNulo() {
    	SocioTorcedor socioTorcedor = SocioTorcedorRequestMock.getSocioTorcedorRequest();
        socioTorcedor.setTimeCoracao(null);

        ResponseEntity<SocioTorcedor> responseEntity =
                socioTorcedorRestTemplate.postForEntity(BASE_URL, socioTorcedor, SocioTorcedor.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoIncluirSocioTorcedorComDataNascimentoNulo() {
    	SocioTorcedor socioTorcedor = SocioTorcedorRequestMock.getSocioTorcedorRequest();
        socioTorcedor.setDataNascimento(null);

        ResponseEntity<SocioTorcedor> responseEntity =
                socioTorcedorRestTemplate.postForEntity(BASE_URL, socioTorcedor, SocioTorcedor.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoIncluirSocioTorcedorComEmailNulo() {
    	SocioTorcedor socioTorcedor = SocioTorcedorRequestMock.getSocioTorcedorRequest();
        socioTorcedor.setEmail(null);

        ResponseEntity<SocioTorcedor> responseEntity =
                socioTorcedorRestTemplate.postForEntity(BASE_URL, socioTorcedor, SocioTorcedor.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoAlterarSocioTorcedorComNomeNulo()throws URISyntaxException {
    	String url = BASE_URL.concat("1");
    	SocioTorcedor socioTorcedor = SocioTorcedorRequestMock.getSocioTorcedorRequest();
        socioTorcedor.setNome(null);

        ResponseEntity<SocioTorcedor> responseEntity = socioTorcedorRestTemplate.exchange(
                url,
                HttpMethod.PUT, RequestEntity.put(new URI(url)).body(socioTorcedor),
                new ParameterizedTypeReference<SocioTorcedor>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoAlterarSocioTorcedorComTimeCoracaoNulo() throws URISyntaxException{
    	String url = BASE_URL.concat("1");
    	SocioTorcedor socioTorcedor = SocioTorcedorRequestMock.getSocioTorcedorRequest();
        socioTorcedor.setTimeCoracao(null);

        ResponseEntity<SocioTorcedor> responseEntity = socioTorcedorRestTemplate.exchange(
                url,
                HttpMethod.PUT, RequestEntity.put(new URI(url)).body(socioTorcedor),
                new ParameterizedTypeReference<SocioTorcedor>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoAlterarSocioTorcedorComDataNscimentoCoracaoNulo() throws URISyntaxException{
    	String url = BASE_URL.concat("1");
    	SocioTorcedor socioTorcedor = SocioTorcedorRequestMock.getSocioTorcedorRequest();
        socioTorcedor.setDataNascimento(null);

        ResponseEntity<SocioTorcedor> responseEntity = socioTorcedorRestTemplate.exchange(
                url,
                HttpMethod.PUT, RequestEntity.put(new URI(url)).body(socioTorcedor),
                new ParameterizedTypeReference<SocioTorcedor>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoAlterarSocioTorcedorComEmailNulo() throws URISyntaxException{
    	String url = BASE_URL.concat("1");
    	SocioTorcedor socioTorcedor = SocioTorcedorRequestMock.getSocioTorcedorRequest();
        socioTorcedor.setEmail(null);

        ResponseEntity<SocioTorcedor> responseEntity = socioTorcedorRestTemplate.exchange(
                url,
                HttpMethod.PUT, RequestEntity.put(new URI(url)).body(socioTorcedor),
                new ParameterizedTypeReference<SocioTorcedor>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
   
    
    @Test
    public void deveExcluirPorId() throws URISyntaxException {
        String url = BASE_URL.concat("2");

        ResponseEntity<SocioTorcedor> responseEntity = socioTorcedorRestTemplate.exchange(url, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<SocioTorcedor>() {});

        Assert.assertNull(responseEntity.getBody());
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
}