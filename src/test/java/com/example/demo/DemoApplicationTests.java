package com.example.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	ApplicationContext context;

	WebTestClient client;

	@BeforeEach
	public void setup() {
		this.client = WebTestClient
				.bindToApplicationContext(this.context)
				.configureClient()
				.build();
	}

	@Test
	void respondsWithHttp401()
	{
		client
				.get()
				.accept(MediaType.APPLICATION_JSON)
				.header("X-Requested-With","XMLHttpRequest")
				.exchange()
				.expectStatus()
				.isUnauthorized();
	}
}
