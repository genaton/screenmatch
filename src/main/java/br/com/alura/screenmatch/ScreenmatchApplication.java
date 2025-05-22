package br.com.alura.screenmatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.principal.Principal;

@SpringBootApplication(scanBasePackages = "br.com.alura.screenmatch")

public class ScreenmatchApplication implements CommandLineRunner {
	@Autowired
	private Principal principal;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override	
	public void run(String... args) throws Exception {
		
		
		// Principal principal = new Principal();
		principal.exibeMenu();

	
	}

}
