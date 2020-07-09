package br.ce.fegodinho.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class AuthTest {
	
	@Test
	public void deveAcessarSWAPI() {
		given()
			.log().all()
		.when()
			.get("https://swapi.dev/api/people/1")
		.then()
			.log().all()
			.statusCode(200)
			.body("name", is("Luke Skywalker"))
		;
	}
	
	@Test
	public void deveObterClima() {
		given()
			.log().all()
			.queryParam("q","Sorocaba,br")
			.queryParam("appid", "0f9f1519bb1a665fdcdab9f4eb223eb1")
			.queryParam("units", "metric")
		.when()
			.get("http://api.openweathermap.org/data/2.5/weather")
		.then()
			.log().all()
			.statusCode(200)
			.body("name", is("Sorocaba"))
			.body("coord.lon", is(-47.46f))
			.body("main.temp", greaterThan(20))
		;
	}
	
	@Test
	public void naoDeveAcessarSemSenha() {
		given()
		.log().all()
	.when()
		.get("http://restapi.wcaquino.me/basicauth")
	.then()
		.log().all()
		.statusCode(401)
		;
	}
	
	@Test
	public void deveFazerAutenticacaoBasica() {
		given()
		.log().all()
	.when()
		.get("http://admin:senha@restapi.wcaquino.me/basicauth")
	.then()
		.log().all()
		.statusCode(200)
		.body("status", is("logado"))
		;
	}
	
	@Test
	public void deveFazerAutenticacaoBasica2() {
		given()
		.log().all()
		.auth().basic("admin", "senha")
	.when()
		.get("http://restapi.wcaquino.me/basicauth")
	.then()
		.log().all()
		.statusCode(200)
		.body("status", is("logado"))
		;
	}
	
	@Test
	public void deveFazerAutenticacaoBasicaChallenge() {
		given()
		.log().all()
		.auth().preemptive().basic("admin", "senha")
	.when()
		.get("http://restapi.wcaquino.me/basicauth2")
	.then()
		.log().all()
		.statusCode(200)
		.body("status", is("logado"))
		;
	}
	
}
