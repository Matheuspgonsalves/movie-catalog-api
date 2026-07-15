package br.com.moviecatalog.movie_catalog_api.main;

import br.com.moviecatalog.movie_catalog_api.utils.InputReader;

import java.util.Scanner;

public class Menu {
    private Scanner read = new Scanner(System.in);

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

    public void mainMenu() {
        System.out.println("Olá, seja muito bem vindo ao Catálogo de Filmes e séries!\n");
        System.out.println("Para começar, digite o número correspondente a opção que deseja buscar: ");
        optionMenu();

        int option = readOption();

        while (!(option <= 0 || option >= 4)) {
            switch (option) {
                case 1:
                    System.out.println("Consulta filme...");
                    break;
                case 2:
                    System.out.println("Consulta série");
                    break;
                case 3:
                    System.out.println("Programa finalizado!");
                default:
                    System.out.println("Opção inválida. Por favor digite uma opção válida do menu abaixo");
                    optionMenu();
                    option = read.nextInt();
                    read.nextLine();
            }
        }
    }
}
