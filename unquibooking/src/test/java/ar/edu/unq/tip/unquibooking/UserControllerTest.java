package ar.edu.unq.tip.unquibooking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.tip.unquibooking.model.User;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	private User user1 = new User("Juan Gonzalez", "juan.gonzalez@alu.edu.unq.com.ar", "superPassword1");
	private User user2 = new User("María Lopez", "juan.gonzalez@gmail.com", "superPassword2");
	
	@Transactional
    @Test
	public void saveUserWithValidatedInformation() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/user")
              .contentType(MediaType.APPLICATION_JSON)
              .content(JsonUtil.toJson(user1)))
			  .andReturn().getResponse();
		
		assertEquals(200, response.getStatus());
	}
	
	@Transactional
    @Test
	public void saveUserWithIncorrectEmail() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/user")
              .contentType(MediaType.APPLICATION_JSON)
              .content(JsonUtil.toJson(user2)))
			  .andReturn().getResponse();
		
		assertEquals(400, response.getStatus());
	}
	
	
}
