package com.ninjaone.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.yml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class ITServiceControllerIntegrationTest {

	private static final String API_BASE_URI = "/api/service";
	
	@Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeClass
    public static void setUpClass() throws LiquibaseException {
        // Set up the test database
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog("classpath:db/liquibase-changelog.yml");
        liquibase.setResourceLoader(new DefaultResourceLoader());
        liquibase.setContexts("test");
        liquibase.setDefaultSchema("PUBLIC");
        liquibase.setShouldRun(true);
        liquibase.afterPropertiesSet();
    }

    @AfterClass
    public static void tearDownClass() {
    	((EmbeddedDatabase) dataSource()).shutdown();
    }

    private static DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    @Commit
    public void test_A_CreateService() throws Exception {
        mockMvc.perform(post(API_BASE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"type\":\"Some service\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.type", is("Some service")));
    }
    
    @Test
    @Commit
    public void test_B_CreateDuplicateService() throws Exception {
        mockMvc.perform(post(API_BASE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"type\":\"Some service\"}"))
                .andExpect(status().isConflict());
    }
    
    @Test
    @Commit
    public void test_C_DeleteService() throws Exception {
    	mockMvc.perform(delete(API_BASE_URI + "/5"))
	      .andExpect(status().isOk())
	      .andReturn();
    }
}
