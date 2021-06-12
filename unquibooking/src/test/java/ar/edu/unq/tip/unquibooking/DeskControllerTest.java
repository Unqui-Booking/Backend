package ar.edu.unq.tip.unquibooking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.tip.unquibooking.model.Desk;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DeskControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	private Desk deskTest1;
	private Desk deskTest2;
	
	@BeforeEach
	public void setUp() {
		
	}
	
	@Transactional
    @Test
	public void createDesk() throws Exception {
		deskTest1 = new Desk("deskTest1", "silent", true);
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/desk")
              .contentType(MediaType.APPLICATION_JSON)
              .content(JsonUtil.toJson(deskTest1)))
			  .andReturn().getResponse();
		
		assertEquals(200, response.getStatus());
		
		
	}
	
	
}
