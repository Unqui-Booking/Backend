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

import ar.edu.unq.tip.unquibooking.model.Desk;
import ar.edu.unq.tip.unquibooking.model.Seat;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SeatControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	private Seat seatTest2;
	private Desk deskTest = new Desk("Escritorio 15", "silent", true);
	private Seat seatTest1 = new Seat(deskTest); 
	@Transactional
    @Test
	public void createSeat() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/seat")
              .contentType(MediaType.APPLICATION_JSON)
              .content(JsonUtil.toJson(seatTest1)))
			  .andReturn().getResponse();
		
		assertEquals(200, response.getStatus());
	}
	
	
	@Transactional
    @Test
    public void deleteNonexistentSeat() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/seat/999")
              .contentType(MediaType.APPLICATION_JSON)
              .content(JsonUtil.toJson(seatTest2)))
			  .andReturn().getResponse();
		
		assertEquals(404, response.getStatus());
	}
	
	@Transactional
    @Test
    public void getInexistingSeat() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/seat/600"))
				  .andReturn().getResponse();
		
		assertEquals(404, response.getStatus());
	}
	
}
