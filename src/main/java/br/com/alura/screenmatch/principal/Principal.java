package br.com.alura.screenmatch.principal;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import br.com.alura.screenmatch.service.Config;

// @Component
public class Principal {

        private Scanner leitura = new Scanner(System.in);

        @Autowired
        private ConsumoApi consumo = new ConsumoApi();

        @Autowired
        private ConverteDados conversor = new ConverteDados();

        // @Autowired
        // private Config config;

        String apiOmdbKey = new String(System.getenv("API_KEY_OMDB"));

        private final String ENDERECO = "http://www.omdbapi.com/?t=";

        private List<DadosSerie> dadosSeries = new ArrayList<>();

        private SerieRepository repositorio;

        private List<Serie> series = new ArrayList<>();

        public Principal(SerieRepository repositorio) {
                this.repositorio = repositorio;

        }

        public void exibeMenu() {
                var opcao = -1;
                while (opcao != 0) {

                        var menu = """
                                        1 - Buscar séries
                                        2 - Buscar episódios
                                        3 - Listar séries buscadas
                                        4 - Buscar série por título
                                        5 - Buscar série por ator

                                        0 - Sair
                                        """;

                        System.out.println(menu);
                        opcao = leitura.nextInt();
                        leitura.nextLine();

                        switch (opcao) {
                                case 1:
                                        buscarSerieWeb();
                                        break;
                                case 2:
                                        buscarEpisodioPorSerie();
                                        break;
                                case 3:
                                        listarSeriesBuscadas();
                                        break;
                                case 4:
                                        buscarSeriePorTitulo();
                                        break;
                                case 5:
                                        buscarSeriePorAtor();
                                        break;
                                case 0:
                                        System.out.println("Saindo...");
                                        break;
                                default:
                                        System.out.println("Opção inválida");
                        }
                }
        }

        private void buscarSerieWeb() {
                DadosSerie dados = getDadosSerie();
                Serie serie = new Serie(dados);
                // dadosSeries.add(dados);
                repositorio.save(serie);
                System.out.println(dados);
        }

        private DadosSerie getDadosSerie() {
                System.out.println("Digite o nome da série para busca");
                var nomeSerie = leitura.nextLine();

                var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + apiOmdbKey);

                DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
                return dados;
        }

        private void buscarEpisodioPorSerie() {
                // DadosSerie dadosSerie = getDadosSerie();
                listarSeriesBuscadas();
                System.out.println("\nEscolha uma série pelo nome: ");
                var nomeSerie = leitura.nextLine();

                Optional<Serie> serie = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

                if (serie.isPresent()) {

                        var serieEncontrada = serie.get();

                        List<DadosTemporada> temporadas = new ArrayList<>();

                        for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {

                                var json = consumo.obterDados(
                                                ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season="
                                                                + i
                                                                + apiOmdbKey);

                                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                                temporadas.add(dadosTemporada);
                        }
                        temporadas.forEach(System.out::println);

                        List<Episodio> episodios = temporadas.stream()
                                        .flatMap(d -> d.episodios().stream()
                                                        .map(e -> new Episodio(d.numero(), e)))
                                        .collect(Collectors.toList());
                        serieEncontrada.setEpisodios(episodios);
                        repositorio.save(serieEncontrada);

                } else {
                        System.out.println("Série não encontrada");
                }
        }

        private void listarSeriesBuscadas() {
                // List<Serie> series = new ArrayList<>();

                series = repositorio.findAll();

                // series = dadosSeries.stream()
                // .map(d -> new Serie(d))
                // .collect(Collectors.toList());

                series.stream()
                                .sorted(Comparator.comparing(Serie::getGenero))
                                .forEach(System.out::println);

                // dadosSeries.forEach(System.out::println);
        }

        private void buscarSeriePorTitulo() {
                System.out.println("\n Escolha uma série pelo título: ");
                var nomeSerie = leitura.nextLine();

                Optional<Serie> serieBuscada = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

                if (serieBuscada.isPresent()) {
                        System.out.println("\nDados da Série: " + serieBuscada.get());

                } else {
                        System.out.println("Série não encontrada");
                }

        }

        private void buscarSeriePorAtor() {
                System.out.println("Qual o nome do ator? ");
                var nomeAtor = leitura.nextLine();
                
                System.out.println("Avaliações a partir de que valor?");
                var avaliacao = leitura.nextDouble();

                List<Serie> seriesEncontradas = repositorio.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor, avaliacao );
                System.out.println("Séries em que " + nomeAtor + " trabalhou.");
                seriesEncontradas.forEach(s -> System.out.println(s.getTitulo() + " avaliação: " +
                s.getAvaliacao()));

        }

}
