package br.com.moviecatalog.movie_catalog_api.main;

import br.com.moviecatalog.movie_catalog_api.dto.MovieDTO;
import br.com.moviecatalog.movie_catalog_api.dto.SeasonDTO;
import br.com.moviecatalog.movie_catalog_api.dto.SeriesDTO;
import br.com.moviecatalog.movie_catalog_api.service.ApiReader;
import br.com.moviecatalog.movie_catalog_api.service.ConvertData;
import br.com.moviecatalog.movie_catalog_api.utils.InputReader;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    final private String URL = "https://www.omdbapi.com/?t=";
    final private String API_KEY = "&apikey=5839cfbd";

    public void optionMenu() {
        System.out.println("*****************************************************");
        System.out.println("1 - Digite '1' para consultar Filmes");
        System.out.println("2 - Digite '2' para consultar Séries");
        System.out.println("3 - Digite '3' para encerrar o programa");
        System.out.println("*****************************************************\n");
    }

    public int readOption() {
        System.out.println("Digite a opção: ");
        return InputReader.readInt();
    }

    public int newSearch() {
        System.out.println("Para uma nova consulta, digite uma das opções válidas abaixo: \n");

        System.out.println("*****************************************************");
        System.out.println("1 - Digite '1' para consultar Filmes");
        System.out.println("2 - Digite '2' para consultar Séries");
        System.out.println("3 - Digite '3' para encerrar o programa");
        System.out.println("*****************************************************\n");
        System.out.println("Digite a opção: ");
        return InputReader.readInt();
    }

    public void mainMenu() {
        ApiReader apiReader = new ApiReader();
        ConvertData convertData = new ConvertData();

        System.out.println("Olá, seja muito bem vindo ao Catálogo de Filmes e séries!\n");
        System.out.println("Para começar, digite o número correspondente a opção que deseja buscar: ");
        optionMenu();
        int option = readOption();
        boolean flag;

        do {
            String jsonResponse = "";
            switch (option) {
                case 1:
                    System.out.println("Digite o nome do filme: ");
                    String movieName = InputReader.readLine();

                    jsonResponse = apiReader.getDataApi(URL + movieName.replace(" ", "+") + API_KEY);
                    MovieDTO movieData = convertData.getData(jsonResponse, MovieDTO.class);
                    System.out.println(movieData.toString() + "\n");

                    option = newSearch();
                    flag = option != 3;
                    break;
                case 2:
                    System.out.println("Digite o nome da série: ");
                    String seriesName = InputReader.readLine();

                    jsonResponse = apiReader.getDataApi(URL + seriesName.replace(" ", "+") + API_KEY);
                    SeriesDTO seriesData = convertData.getData(jsonResponse, SeriesDTO.class);
                    System.out.println(seriesData.toString());
                    System.out.println("Totel de temporadas: " + seriesData.totalSeasons());

                    List<SeasonDTO> seasons = new ArrayList<>();

                    for (int i = 1; i <= seriesData.totalSeasons(); i++) {
                        jsonResponse = apiReader.getDataApi(URL + seriesName.replace(" ", "+") + "&season=" + i + API_KEY);
                        SeasonDTO seasonDTO = convertData.getData(jsonResponse, SeasonDTO.class);
                        seasons.add(seasonDTO);
                    }

                    boolean seasonFlag;
                    do {
                        System.out.println("Digite a temporada que deseja buscar: ");
                        int season = InputReader.readInt();

                        seasonFlag = season < 1 || season > seriesData.totalSeasons();
                        if (!seasonFlag) System.out.println("Digite uma temporada válida para a série.\n\n");

                        seasons.stream()
                                .filter(e -> e.season() == 1)
                                .forEach(e -> System.out.println(e.episodes()));
                    } while (seasonFlag);


                    option = newSearch();
                    flag = option != 3;
                    break;
                case 3:
                    System.out.println("Programa finalizado!");
                    flag = false;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor digite uma opção válida do menu abaixo: \n");
                    optionMenu();
                    option = readOption();
                    flag = option != 3;
            }
        } while (flag);
    }
}
