package br.com.alura.screenmatch.repository;

import org.hibernate.annotations.processing.Find;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.screenmatch.model.Serie;
import java.util.List;
import java.util.Optional;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long>{
//EXEMPLO DE DERIVED QUERY QUE É UM RECURSO DA JPA. ELA É FORMADA POR PALAVRAS CHAVES COMO findBy...Containing...verbo introdutório + palavra-chave “By” + critérios de busca
    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

    



}
