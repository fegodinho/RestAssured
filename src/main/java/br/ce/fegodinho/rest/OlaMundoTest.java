package br.ce.fegodinho.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTest {
	
	@Test
	public void testOlaMundo() {
		
		Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");
		Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		Assert.assertTrue(response.statusCode() == 200);
		Assert.assertTrue("Status code deve ser 200", response.statusCode() == 200);
		Assert.assertEquals(200, response.statusCode());		
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);		
	}
	
	@Test
	public void devoConhecerOutrasFormasRestAssured() {
		
		Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
		
		get("http://restapi.wcaquino.me/ola").then().statusCode(200);
		
		given()
			//Pré condições
		.when()
			//Ação
			.get("http://restapi.wcaquino.me/ola")
		.then()
			//Assertivas
			.statusCode(200);
	}
	
	@Test
	public void devoConhecerOsMatchersComHamcrest() {
		Assert.assertThat("Maria", Matchers.is("Maria"));
		Assert.assertThat(128, Matchers.is(128));
		Assert.assertThat(128, Matchers.isA(Integer.class));
		Assert.assertThat(128d, Matchers.isA(Double.class));
		Assert.assertThat(128d, Matchers.greaterThan(127d));
		Assert.assertThat(128d, Matchers.lessThan(129d));
		
		List<Integer> impares = Arrays.asList(1,3,5,7,9);
		assertThat(impares, hasSize(5));
		assertThat(impares, containsInAnyOrder(3,5,1,7,9));
		assertThat(impares, hasItem(1));
		assertThat(impares, hasItems(1,5));
		
		assertThat("Maria", is(not("João")));
		assertThat("Maria", not("João"));
		assertThat("Joaquina", anyOf(is("Maria"), is("Joaquina")));
		assertThat("Joaquina", allOf(startsWith("Joa"), endsWith("ina"), containsString("qui")));
	}
	
	@Test
	public void devoValidarBody() {
		given()
		//Pré condições
	.when()
		//Ação
		.get("http://restapi.wcaquino.me/ola")
	.then()
		//Assertivas
		.statusCode(200)
		.body(Matchers.is("Ola Mundo!"))
		.body(containsString("Mundo"))
		.body(is(not(nullValue())))
		;
	}

}
