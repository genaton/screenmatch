package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import br.com.alura.screenmatch.model.DadosSerie;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override	
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		// var json = consumoApi.obterDados("https://api.themoviedb.org/3/tv/popular?api_key=9fbc83be52fd2c56205d3433825fdde6");
		var json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&apikey=9785c32c");
		System.out.println("os filmes podulares são:  " + json);

		// json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
		// System.out.println("imagem no seguinte link:  " + json);
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);

		System.out.println(dados);
	}

}
