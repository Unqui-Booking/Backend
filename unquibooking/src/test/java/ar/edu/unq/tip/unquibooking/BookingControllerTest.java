package ar.edu.unq.tip.unquibooking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

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

import ar.edu.unq.tip.unquibooking.model.Booking;
import ar.edu.unq.tip.unquibooking.model.Desk;
import ar.edu.unq.tip.unquibooking.model.Seat;
import ar.edu.unq.tip.unquibooking.model.User;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookingControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	private Desk desk = new Desk("Escritorio 16", "silent", true);
	private Seat seat = new Seat(desk); 
	private User user = new User("José Paz", "jose.paz@alu.edu.unq.com.ar", "superPassword3");
	private Booking booking = new Booking( seat,  9, 10, LocalDate.now(), user);
	
	@Transactional
    @Test
	public void createBooking() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/booking")
              .contentType(MediaType.APPLICATION_JSON)
              .content(JsonUtil.toJson(booking)))
			  .andReturn().getResponse();
		
		assertEquals(200, response.getStatus());
	}
	

	
}
