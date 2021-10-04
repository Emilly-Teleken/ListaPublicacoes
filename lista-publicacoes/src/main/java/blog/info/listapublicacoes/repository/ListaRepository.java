package blog.info.listapublicacoes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import blog.info.listapublicacoes.model.Lista;


@Repository
public interface ListaRepository extends CrudRepository<Lista, Long>{
   
}
