package br.com.moviecatalog.movie_catalog_api.utils;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputReader {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputReader() {
    }

    public static String readLine() {
        return SCANNER.nextLine();
    }

    public static int readInt() {
        while (true) {
            String input = SCANNER.nextLine();

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido: ");
            }
        }
    }

    public static double readDouble() {
        while (true) {
            String input = SCANNER.nextLine();

            try {
                return Double.parseDouble(input.trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Digite um número decimal válido: ");
            }
        }
    }

    @PreDestroy
    public static void close() {
        SCANNER.close();
    }
}
