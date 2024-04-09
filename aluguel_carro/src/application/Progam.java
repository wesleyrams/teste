package application;

import model.entites.CarRental;
import model.entites.Vehicle;
import model.services.BrazilianTaxService;
import model.services.RentalServices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Progam {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner keyboard = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Entre com os dados do aluguel");
        System.out.print("Modelo do carro: ");
        String carModel = keyboard.nextLine();
        System.out.println("Retirada (dd/MM/yyyy): ");
        LocalDateTime start = LocalDateTime.parse(keyboard.nextLine(), fmt);

        System.out.println("Retorno (dd/MM/yyyy): ");
        LocalDateTime finish = LocalDateTime.parse(keyboard.nextLine(), fmt);

        CarRental newCarRental = new CarRental(start, finish, new Vehicle(carModel));

        System.out.println("Entre com o preço por hora: ");
        double pricePerHour = keyboard.nextDouble();
        System.out.println("Entre com o preço por hora: ");
        double pricePerDay = keyboard.nextDouble();

        RentalServices rentalServices = new RentalServices(pricePerHour, pricePerDay, new BrazilianTaxService());
        rentalServices.processInvoice(newCarRental);
        System.out.println("FATURA: ");
        System.out.println("Pagamento básico: "+ String.format("%.2f", newCarRental.getInvoice().getBasicPayment()));
        System.out.println("Imposto: " + newCarRental.getInvoice().getTax());
        System.out.println("Pagamento total: " + String.format("%.2f", newCarRental.getInvoice().getTotalPayment()));


        keyboard.close();
    }
}
