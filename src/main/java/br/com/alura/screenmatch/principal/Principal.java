package br.com.alura.screenmatch.principal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.text.Normalizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.alura.screenmatch.model.Categoria;
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

        private Scanner leitura = new Scanner(System.in, "UTF-8");

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

        private Optional<Serie> serieBusca;

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
                                        6 - Top 5 séries
                                        7 - Buscar série por categoria
                                        8 - Filtrar séries
                                        9 - Buscar episódio por trecho do título
                                        10 - Top 5 episódios por série
                                        11 - Buscar episódios a partir de uma data

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
                                case 6:
                                        buscarTop5Series();
                                        break;
                                case 7:
                                        buscarSeriesPorCategoria();
                                        break;
                                case 8:
                                        filtrarSeriesPorTemporadaEAvaliacao();
                                        break;
                                case 9:
                                        buscarEpisodioPorTrecho();
                                        break;
                                case 10:
                                        topEpisodiosPorSerie();
                                        break;
                                case 11:
                                        buscarEpisodiosDepoisDeUmaData();
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

                serieBusca = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

                if (serieBusca.isPresent()) {
                        System.out.println("\nDados da Série: " + serieBusca.get());

                } else {
                        System.out.println("Série não encontrada");
                }

        }

        private void buscarSeriePorAtor() {
                System.out.println("Qual o nome do ator? ");
                var nomeAtor = leitura.nextLine();

                System.out.println("Avaliações a partir de que valor?");
                var avaliacao = leitura.nextDouble();

                List<Serie> seriesEncontradas = repositorio
                                .findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor, avaliacao);
                System.out.println("Séries em que " + nomeAtor + " trabalhou.");
                seriesEncontradas.forEach(s -> System.out.println(s.getTitulo() + " avaliação: " +
                                s.getAvaliacao()));

        }

        private void buscarTop5Series() {
                List<Serie> serieTop = repositorio.findTop5ByOrderByAvaliacaoDesc();

                serieTop.forEach(s -> System.out.println(s.getTitulo() + " avaliacao " + s.getAvaliacao()));
        }

        private void buscarSeriesPorCategoria() {
                System.out.println("\nDeseja buscar séries de que categoria/gênero? ");

                var nomeGenero = leitura.nextLine();
                System.out.println("Entrada capturada: [" + nomeGenero + "]");

                String nomeGeneroCorrigido = nomeGenero.replaceAll("[^\\p{ASCII}]", "");

                System.out.println("Entrada capturada corrigida: [" + nomeGeneroCorrigido +
                                "]");
                Categoria categoria = Categoria.fromPortugues(nomeGeneroCorrigido);

                List<Serie> seriesPorCategoria = repositorio.findByGenero(categoria);

                System.out.println("Séreis da categoria " + nomeGeneroCorrigido);

                seriesPorCategoria.forEach(System.out::println);
        }

        private void filtrarSeriesPorTemporadaEAvaliacao() {
                System.out.println("Filtrar séries até quantas temporadas? ");
                var totalTemporadas = leitura.nextInt();
                leitura.nextLine();
                System.out.println("Com avaliação a partir de que valor? ");
                var avaliacao = leitura.nextDouble();
                leitura.nextLine();
                List<Serie> filtroSeries = repositorio.seriesPorTemporadaEAvalicao(totalTemporadas, avaliacao);
                // List<Serie> filtroSeries =
                // repositorio.findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(
                // totalTemporadas, avaliacao);
                System.out.println("*** Séries filtradas ***");
                filtroSeries.forEach(s -> System.out.println(s.getTitulo() + "  - avaliação: " + s.getAvaliacao()));

        }

        public void buscarEpisodioPorTrecho() {
                System.out.println("Digite o trecho do nome do episódio: ");
                var trechoEpisodio = leitura.nextLine();

                List<Episodio> episodiosEncontrados = repositorio.episodiosPorTrecho(trechoEpisodio);

                episodiosEncontrados.forEach(e -> System.out.printf("Séries %s Temporada %s - Episódio %s - %s\n",
                                e.getSerie().getTitulo(), e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()));

        }

        public void topEpisodiosPorSerie() {

                buscarSeriePorTitulo();
                if (serieBusca.isPresent()) {
                        Serie serie = serieBusca.get();
                        List<Episodio> topEpisodios = repositorio.topEpisodiosPorSerie(serie);
                        topEpisodios.forEach(e -> System.out.printf(
                                        "Séries %s Temporada %s - Episódio %s - %s - Avaliaão %s\n",
                                        e.getSerie().getTitulo(), e.getTemporada(), e.getNumeroEpisodio(),
                                        e.getTitulo(), e.getAvaliacao())

                        );
                }

        };

        public void buscarEpisodiosDepoisDeUmaData() {
                buscarSeriePorTitulo();
                if (serieBusca.isPresent()) {
                        System.out.println("\n Digite o ano limite de lançamento");
                        var anoLancamento = leitura.nextInt();
                        leitura.nextLine();
                        Serie serie = serieBusca.get();

                        List<Episodio> episodiosAno = repositorio.episodioPorSerieEAno(serie, anoLancamento);
                        episodiosAno.forEach(e -> System.out.printf(
                                        "Séries %s Temporada %s - Episódio %s - %s - Avaliaão %s - Data Lançamento %s\n",
                                        e.getSerie().getTitulo(), e.getTemporada(), e.getNumeroEpisodio(),
                                        e.getTitulo(), e.getAvaliacao(), e.getDataLancamento())

                        );
                }
        };

}
