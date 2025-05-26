package br.com.alura.screenmatch.repository;

import org.hibernate.annotations.processing.Find;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import java.util.List;
import java.util.Optional;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long>{
//EXEMPLO DE DERIVED QUERY QUE É UM RECURSO DA JPA. ELA É FORMADA POR PALAVRAS CHAVES COMO findBy...Containing...verbo introdutório + palavra-chave “By” + critérios de busca
    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();
    
    List<Serie> findByGenero(Categoria categoria);
    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(int totalTemporadas, double avaliacao);

    // @Query(value = "select * from series s where s.total_temporadas <= 1 and s.avaliacao >= 7.5", nativeQuery = true)
    @Query("select s from Serie s WHERE s.totalTemporadas <= :totalTemporadas and s.avaliacao >= :avaliacao")//query JPQL
    List<Serie> seriesPorTemporadaEAvalicao(int totalTemporadas, double avaliacao);

    @Query("select e from Serie s join s.episodios e where e.titulo ilike %:trechoEpisodio% ")
    List<Episodio> episodiosPorTrecho (String trechoEpisodio);

    @Query("select e from Serie s join s.episodios e where s = :serie order by e.avaliacao desc limit 5")
    List<Episodio> topEpisodiosPorSerie(Serie serie);

@Query("select e from Serie s join s.episodios e where s = :serie and YEAR(e.dataLancamento) >= :anoLancamento")
    List<Episodio> episodioPorSerieEAno(Serie serie, int anoLancamento);
    
}


    




