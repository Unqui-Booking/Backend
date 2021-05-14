package ar.edu.unq.tip.unquibooking.dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.tip.unquibooking.model.Seat;

@Component
public class SeatConverter{
	
	private ModelMapper mapper = new ModelMapper();
	
	public SeatDTO entityToDTO(Seat seat) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		SeatDTO map = mapper.map(seat, SeatDTO.class);
		return map;
	}
	
	public Seat dtoToEntity(SeatDTO seatDTO) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Seat map = mapper.map(seatDTO, Seat.class);
		return map;
	}
	
	public  ArrayList<SeatDTO> entityToDto(List<Seat> seats) {
		return	(ArrayList<SeatDTO>) seats.stream().map(s -> entityToDTO(s)).collect(Collectors.toList());
	}
	
	public List<Seat> dtoToEntity(List<SeatDTO> seatDTO){
		return seatDTO.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}
	
}
