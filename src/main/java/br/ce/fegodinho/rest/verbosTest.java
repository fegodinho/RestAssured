package br.ce.fegodinho.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import io.restassured.http.ContentType;

public class verbosTest {
	
	@Test
	public void deveSalvarUsuario() {
		given()
			.log().all()
			.contentType("application/json")
			.body("{\"name\": \"Jose\",\"age\": 50}")
		.when()
			.post("http://restapi.wcaquino.me/users")
		
		.then()
			.log().all()
			.statusCode(201)
			.body("id", is(notNullValue()))
			.body("name", is("Jose"))
			.body("age", is(50))
			;
	}
	
	@Test
	public void naoDeveSalvarUsuarioSemNome() {		
		given()
		.log().all()
		.contentType("application/json")
		.body("{\"age\": 50}")
	.when()
		.post("http://restapi.wcaquino.me/users")
	
	.then()
		.log().all()
		.statusCode(400)
		.body("id", is(nullValue()))
		.body("error", is("Name é um atributo obrigatório"))
		;		
	}
	
	@Test
	public void deveSalvarUsuarioViaXML() {
		given()
			.log().all()
			.contentType(ContentType.XML)
			.body("<user><name>Jose</name><age>50</age></user>")
		.when()
			.post("http://restapi.wcaquino.me/usersXML")
		
		.then()
			.log().all()
			.statusCode(201)
			.body("user.@id", is(notNullValue()))
			.body("user.name", is("Jose"))
			.body("user.age", is("50"))
			;
	}
}