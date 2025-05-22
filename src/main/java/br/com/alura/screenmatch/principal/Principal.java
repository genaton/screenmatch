package br.com.alura.screenmatch.principal;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import br.com.alura.screenmatch.service.Config;

@Component
public class Principal {

        private Scanner leitura = new Scanner(System.in);

        @Autowired
        private ConsumoApi consumo = new ConsumoApi();

        @Autowired
        private ConverteDados conversor = new ConverteDados();

        @Autowired
        private Config config;

        private final String ENDERECO = "http://www.omdbapi.com/?t=";

        private Optional<Episodio> first;

        public void exibeMenu() {
                System.out.println("Digite o nome da série ");
                var nomeSerie = leitura.nextLine();

                var json = consumo.obterDados(
                                ENDERECO + nomeSerie.replace(" ", "+") + config.getApiKey());

                DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
                
                System.out.println("\n Dados da Série: ");
                
                System.out.println(dados);
                List<DadosTemporada> temporadas = new ArrayList<>();

                for (int i = 1; i <= dados.totalTemporadas(); i++) {
                        json = consumo.obterDados(
                                        ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + config.getApiKey());
                        DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);

                        temporadas.add(dadosTemporada);

                }

                System.out.println("\n Dados das temporadas: ");
                temporadas.forEach(System.out::println);

                //###CÓDIGO PARA BUSCAR NOME DO TÍTULO SEM USAR STREAM:

                // for(int i = 0; i < dados.totalTemporadas(); i++){
                // List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
                // for(int j = 0; j < episodiosTemporada.size(); j++){
                // System.out.println(episodiosTemporada.get(j).titulo());
                // }
                // }

                System.out.println("\n Título dos Episódios: ");
                temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

                List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                                .flatMap(t -> t.episodios().stream())
                                .collect(Collectors.toList());

                // System.out.println("\ntop 10 episódios:");

                // dadosEpisodios.stream()
                // .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                // .peek( e -> System.out.println("Primeiro filtro(N/A)" + e)) // função para
                // facilitar o debug do código
                // .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                // .peek( e -> System.out.println("Ordenação " + e))
                // .limit(10)
                // .peek( e -> System.out.println("Limite " + e))
                // .map(e -> e.titulo().toUpperCase())
                // .peek( e -> System.out.println("Mapeamento " + e))
                // .forEach(System.out::println);

                List<Episodio> episodios = temporadas.stream()
                                .flatMap(t -> t.episodios().stream()
                                                .map(d -> new Episodio(t.numero(), d))
                                                .filter(e -> e.getAvaliacao() != 0.0))
                                .collect(Collectors.toList());

                System.out.println("\n lista de episódios: ");
                episodios.forEach(System.out::println);

                System.out.println("\n Digite o nome do episódio ");
                var trechoTitulo = leitura.nextLine();

                Optional<Episodio> episodioBuscado = episodios.stream()
                                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
                                .findFirst();

                if (episodioBuscado.isPresent()) {
                        System.out.println("Episódio encontrado!");
                        System.out.println("Temporada:" + episodioBuscado.get().getTemporada());


                }else{
                        System.out.println("Episódio não encontrado.");
                }

                // System.out.println(" A partir de que ano vc deseja ver os episódios?");
                // var ano = leitura.nextInt();
                // leitura.nextLine();

                // LocalDate dataBusca = LocalDate.of(ano, 1, 1);

                // DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                // episodios.stream()
                // .filter(e -> e.getDataLancamento() != null &&
                // e.getDataLancamento().isAfter(dataBusca))
                // .forEach(e -> System.out.println("Temporada: " + e.getTemporada() +
                // "Episódio: " + e.getTitulo() + "Data lançamento: " +
                // e.getDataLancamento().format(formatador)));

        }

}
