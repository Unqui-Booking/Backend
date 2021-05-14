package ar.edu.unq.tip.unquibooking.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.tip.unquibooking.model.Library;
import ar.edu.unq.tip.unquibooking.services.LibraryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
    LibraryService libraryService;
	
	//feriados

    @GetMapping()
    public ArrayList<Library> getAllLibraries(){
        return libraryService.getAllLibraries();
    }

    @PostMapping()
    public Library saveLibrary(@RequestBody Library library){
        return libraryService.saveLibrary(library);
    }

    @GetMapping(path="/{id}")
    public Optional<Library> getLibrary(@PathVariable("id") Long id){
        return libraryService.getLibrary(id);
    }

    @DeleteMapping(path="/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean deleted = libraryService.deleteById(id);
        String message = deleted ? "Library deleted: " + id : "Library could not be deleted: " + id;
        return message;
    }
	
}
