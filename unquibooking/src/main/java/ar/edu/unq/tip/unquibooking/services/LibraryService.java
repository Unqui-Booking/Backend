package ar.edu.unq.tip.unquibooking.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.tip.unquibooking.model.Library;
import ar.edu.unq.tip.unquibooking.repositories.LibraryRepository;

@Service
public class LibraryService {

	@Autowired
	LibraryRepository  libraryRepository;
	
	public ArrayList<Library> getAllLibraries(){
        return (ArrayList<Library>) libraryRepository.findAll();
    }
	
	public Library saveLibrary(Library library){
        return libraryRepository.save(library);
    }
	
    public Optional<Library> getLibrary(Long idLibrary){
        return libraryRepository.findById(idLibrary);
    }

    public boolean deleteById(Long id){
        try{
            libraryRepository.deleteById(id);
            return true;
        }
        catch(Exception error){
            return false;
        }
    }
}
