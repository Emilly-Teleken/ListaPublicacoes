package blog.info.listapublicacoes.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import blog.info.listapublicacoes.model.Lista;
import blog.info.listapublicacoes.repository.ListaRepository;
import blog.info.listapublicacoes.repository.ClassificacoesRepository;
import blog.info.listapublicacoes.model.Classificacoes;

@RestController
@RequestMapping ("/listas")

public class ListaController{
private static final Logger log = LoggerFactory.getLogger(ListaController.class);


    @Autowired
    private ListaRepository listas;
    @Autowired
    private ClassificacoesRepository classificacoes;

    @PostMapping("/criar") //cria novas listas
    public Lista adicionar(@RequestBody Lista lista){
        return listas.save(lista);
    }
    @GetMapping //mostra as listas criadas
    public List<Lista> listar(){
        return (List<Lista>) listas.findAll();
    }
    @GetMapping("/{id}") //busca uma lista pelo id
    @ResponseBody
    public Lista getListaById(@PathVariable("id") Lista lista){
        return lista;
}
    @PostMapping("/{id}/criar_classe") //cria uma classe em uma lista específica 
    public Classificacoes novaClasse(@PathVariable Long id, @RequestBody Classificacoes classe){
        if(listas.findById(id).isPresent()){
            Lista lista = listas.findById(id).get();
            log.debug(lista.getCategoria());
            classe.setLista(lista);
            classe = classificacoes.save(classe);
            return classe;
        }
        return null;

    }
        @DeleteMapping("/deletar_classe/{id}") //deleta uma classe de uma lista específica
        public Classificacoes deleteClasse(@PathVariable Long id){
            if(classificacoes.findById(id).isPresent()){
                Classificacoes classe = classificacoes.findById(id).get();
                classificacoes.delete(classe);
                return classe;
            }
            return null;
        }
  
        @PutMapping("/alterar_classe/{id}") //altera as classes de uma lista
        public Classificacoes putClassificacoes(@PathVariable Long id, @RequestBody Classificacoes classe){
            if(classificacoes.findById(id).isPresent()){
                Classificacoes classePublicacao = classificacoes.findById(id).get();
                classePublicacao.setDescricao(classe.getDescricao());
                classePublicacao.setPrazo(classe.getPrazo());
                classificacoes.save(classePublicacao);
                return classe;
            }
            return null;
        }    
  }
