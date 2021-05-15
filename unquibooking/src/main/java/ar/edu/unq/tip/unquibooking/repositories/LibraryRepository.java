package ar.edu.unq.tip.unquibooking.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.tip.unquibooking.model.Library;

@Repository
public interface LibraryRepository extends CrudRepository<Library, Long>{

}
