import java.util.Scanner;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String jsonRates = CurrencyAPI.getExchangeRates();

        System.out.println("Bem-vindo ao Conversor de Moedas!");
        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Reais para Dólar");
            System.out.println("2. Reais para Euro");
            System.out.println("3. Reais para Libra Esterlina");
            System.out.println("4. Reais para Shekel Israelense");
            System.out.println("5. Reais para Dinar Jordaniano");
            System.out.println("6. Sair");

            int opcao = scanner.nextInt();
            if (opcao == 6) {
                System.out.println("Encerrando o programa...");
                break;
            }

            System.out.print("Digite o valor em Reais: ");
            BigDecimal valor = scanner.nextBigDecimal();

            double taxa = 0;
            switch (opcao) {
                case 1 -> taxa = ExchangeRateParser.getRate(jsonRates, "USD");
                case 2 -> taxa = ExchangeRateParser.getRate(jsonRates, "EUR");
                case 3 -> taxa = ExchangeRateParser.getRate(jsonRates, "GBP");
                case 4 -> taxa = ExchangeRateParser.getRate(jsonRates, "ILS");
                case 5 -> taxa = ExchangeRateParser.getRate(jsonRates, "JOD");
                default -> System.out.println("Opção inválida!");
            }

            if (taxa > 0) {
                BigDecimal resultado = CurrencyConverter.convert(valor, taxa);
                System.out.printf("O valor convertido é: %.2f%n", resultado);
            }
        }
    }
}
