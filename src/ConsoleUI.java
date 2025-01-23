package src;
import java.util.Scanner;

public class ConsoleUI {
    private final ConversionService conversionService;

    public ConsoleUI() {
        this.conversionService = new ConversionService();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the amount:");
        double amount = scanner.nextDouble();
        System.out.println("Enter the source currency (e.g., USD):");
        String fromCurrency = scanner.next().toUpperCase();
        System.out.println("Enter the target currency (e.g., INR):");
        String toCurrency = scanner.next().toUpperCase();

        double result = conversionService.convert(fromCurrency, toCurrency, amount);
        System.out.printf("Converted Amount: %.2f %s%n", result, toCurrency);
    }
}
