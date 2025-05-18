package za.co.ashtech.stroller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class StrollerApplicationTests {
	
    @Autowired
    private MockMvc mockMvc;

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

}
