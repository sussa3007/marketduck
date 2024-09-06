package com.devgang.marketduck;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.sql.DataSource;

@SpringBootTest
class MarketduckApplicationTests {


	@MockBean
	private DataSource dataSource;

	@Test
	void contextLoads() {
	}

}
