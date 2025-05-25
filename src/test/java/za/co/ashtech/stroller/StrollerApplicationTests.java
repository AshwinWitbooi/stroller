package za.co.ashtech.stroller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import za.co.ashtech.stroller.controller.entities.Stroll;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class StrollerApplicationTests {
	
    @Autowired
    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    

    @Test
    void testRandomStroll() throws Exception {
        MvcResult resultMvc = mockMvc.perform(get("/api/v1/stroll"))
               .andExpect(status().isOk())
               .andReturn();
        
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(resultMvc.getResponse().getContentAsString());
        
        //verify response is returned
        assertNotNull(jsonNode.get("longitude"));

    }
    
    @Test
    void testAddStroll() throws Exception {       

        MvcResult resultMvc = mockMvc.perform(post("/admin/api/v1/stroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Stroll("inTestName", "inTestLocation", "-12.3364", "6.6364"))))
        		.andExpect(status().isOk())
        		.andReturn();
        
        JsonNode jsonNode = objectMapper.readTree(resultMvc.getResponse().getContentAsString());
        
        //verify response is returned
        assertNotNull(jsonNode.get("longitude"));

    }
    
    @Test
    void testUpdateStroll() throws Exception {       

        MvcResult resultMvc = mockMvc.perform(put("/admin/api/v1/stroll/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Stroll("inTestName", "inTestLocation", "-12.3364", "6.6364"))))
        		.andExpect(status().isOk())
        		.andReturn();
        
        JsonNode jsonNode = objectMapper.readTree(resultMvc.getResponse().getContentAsString());
        
        //verify response is returned
        assertNotNull(jsonNode.get("longitude"));

    }
    
    @Test
    void testDeleteStroll() throws Exception {       

        mockMvc.perform(delete("/admin/api/v1/stroll/1")).andExpect(status().is(204));      

    }

}
