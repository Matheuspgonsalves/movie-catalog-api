package br.com.moviecatalog.movie_catalog_api;

import br.com.moviecatalog.movie_catalog_api.main.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieCatalogApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu menu = new Menu();
	}
}
