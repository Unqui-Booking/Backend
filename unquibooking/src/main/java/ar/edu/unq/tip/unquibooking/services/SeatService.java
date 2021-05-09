package ar.edu.unq.tip.unquibooking.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.tip.unquibooking.model.Seat;
import ar.edu.unq.tip.unquibooking.model.SeatDTO;
import ar.edu.unq.tip.unquibooking.repositories.SeatRepository;

@Service
public class SeatService {
	@Autowired
    SeatRepository seatRepository;

    public ArrayList<Seat> getAllSeats(){
        return (ArrayList<Seat>) seatRepository.findAll();
    }

    public Seat saveSeat(Seat seat){
        return seatRepository.save(seat);
    }

    public Optional<Seat> getSeat(Long idSeat){
        return seatRepository.findById(idSeat);
    }

    public boolean deleteById(Long id){
        try{
            seatRepository.deleteById(id);
            return true;
        }
        catch(Exception error){
            return false;
        }
    }

    public ArrayList<Seat> getByDesk(Long desk){
        return seatRepository.findByDeskId(desk);
    }

}
