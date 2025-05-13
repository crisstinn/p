package edu.comillas.icai.gitt.pat.spring.may24.repositorios;

import edu.comillas.icai.gitt.pat.spring.may24.entidades.Operacion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RepoOperacion extends CrudRepository<Operacion,Long> {
//TODO #4
}
