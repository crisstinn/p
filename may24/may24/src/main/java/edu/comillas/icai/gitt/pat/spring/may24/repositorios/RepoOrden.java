package edu.comillas.icai.gitt.pat.spring.may24.repositorios;

import edu.comillas.icai.gitt.pat.spring.may24.entidades.Orden;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoOrden extends CrudRepository<Orden,Long> {
    Long id(long id);
//TODO #4
}
