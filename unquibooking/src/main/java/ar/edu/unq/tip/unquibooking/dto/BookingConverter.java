package ar.edu.unq.tip.unquibooking.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import ar.edu.unq.tip.unquibooking.model.Booking;

@Component
public class BookingConverter {

	private ModelMapper mapper = new ModelMapper();
	
	public BookingDTO entityToDTO(Booking booking) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		BookingDTO map = mapper.map(booking, BookingDTO.class);
		return map;
	}
	
	public Booking dtoToEntity(BookingDTO bookingDTO) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Booking map = mapper.map(bookingDTO, Booking.class);
		return map;
	}
	
	public  ArrayList<BookingDTO> entityToDto(List<Booking> bookings) {
		return	(ArrayList<BookingDTO>) bookings.stream().map(s -> entityToDTO(s)).collect(Collectors.toList());
	}
	
	public List<Booking> dtoToEntity(List<BookingDTO> bookingDTO){
		return bookingDTO.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}
	
}
