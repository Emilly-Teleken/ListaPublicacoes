package blog.info.listapublicacoes.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import blog.info.listapublicacoes.model.Classificacoes;

public interface ClassificacoesRepository extends CrudRepository<Classificacoes, Long>{
    List<Classificacoes> findByPrazo(Date prazo);
}