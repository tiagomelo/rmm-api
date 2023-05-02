package com.ninjaone.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.ninjaone.RmmApiApplication;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = RmmApiApplication.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.yml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class CustomerControllerIntegrationTest {

    private static final String API_BASE_URI = "/api/customer";

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
    public void test_A_CreateCustomer() throws Exception {
        mockMvc.perform(post(API_BASE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Tiago Melo\",\"email\":\"tiagoharris@gmail.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Tiago Melo")))
                .andExpect(jsonPath("$.email", is("tiagoharris@gmail.com")));
    }

    @Test
    @Commit
    public void test_B_AddDeviceToCustomer() throws Exception {
    	mockMvc.perform(post(API_BASE_URI + "/1/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"systemName\":\"Windows 10\",\"typeId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.systemName", is("Windows 10")))
                .andExpect(jsonPath("$.typeId", is(1)))
                .andExpect(jsonPath("$.customerId", is(1)));
    }
    
    @Test
    public void test_C_AddDuplicateDeviceToCustomer() throws Exception {
    	mockMvc.perform(post(API_BASE_URI + "/1/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"systemName\":\"Windows 10\",\"typeId\":1}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.errorCode", is("Conflict: resource does not exist or trying to re-create an existing one")));
    }
    
    @Test
    @Commit
    public void test_D_AddAntivirusServiceToDevice() throws Exception {
    	mockMvc.perform(post(API_BASE_URI + "/1/device/1/service/1"))
    		      .andExpect(status().isOk())
    		      .andReturn();
    }
    
    @Test
    @Commit
    // Customer's invoice should be with value 9:
    // Device ownership = 4
    // Antivirus for Windows = 5
    // Total = 9
    public void test_E_GetCustomerInvoice() throws Exception {
    	mockMvc.perform(get(API_BASE_URI + "/1/invoice")
    	        .contentType(MediaType.APPLICATION_JSON))
    	        .andExpect(status().isOk())
    	        .andExpect(jsonPath("$.customerId", is(1)))
    			.andExpect(jsonPath("$.total", is(9)));
    	
    }
    
    @Test
    @Commit
    public void test_F_RemoveAntivirusServiceFromDevice() throws Exception {
    	mockMvc.perform(delete(API_BASE_URI + "/1/device/1/service/1"))
    		      .andExpect(status().isOk())
    		      .andReturn();
    }
    
    @Test
    @Commit
    // Customer's invoice should be with value 4:
    // Device ownership = 4
    // Total = 4
    public void test_G_GetCustomerInvoiceAfterRemovingAntivirusService() throws Exception {
    	mockMvc.perform(get(API_BASE_URI + "/1/invoice")
    	        .contentType(MediaType.APPLICATION_JSON))
    	        .andExpect(status().isOk())
    	        .andExpect(jsonPath("$.customerId", is(1)))
    			.andExpect(jsonPath("$.total", is(4)));
    	
    }
    
    @Test
    @Commit
    public void test_H_DeleteDeviceFromCustomer() throws Exception {
    	mockMvc.perform(delete(API_BASE_URI + "/1/device/1"))
    		      .andExpect(status().isOk())
    		      .andReturn();
    }
    
    @Test
    @Commit
    // Customer's invoice should be with value 0
    public void test_I_GetCustomerInvoiceAfterDeletingDeviceFromCustomer() throws Exception {
    	mockMvc.perform(get(API_BASE_URI + "/1/invoice")
    	        .contentType(MediaType.APPLICATION_JSON))
    	        .andExpect(status().isOk())
    	        .andExpect(jsonPath("$.customerId", is(1)))
    			.andExpect(jsonPath("$.total", is(0)));
    	
    }

}
