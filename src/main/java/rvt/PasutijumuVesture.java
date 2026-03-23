package rvt;

import java.util.Scanner;
import java.nio.file.Paths;

public class PasutijumuVesture {
    public static void main(String[] args) {
        boolean skipFirstLine = true;
        Double sum = 0.0;

        try (Scanner scanner = new Scanner(Paths.get("data/orders.csv"))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.isEmpty()) {
                    break;
                }

                if (skipFirstLine) {
                    skipFirstLine = false;
                    continue;
                }

                String[] info = line.split(",");

                int quantity = Integer.valueOf(info[3]);
                Double price = Double.valueOf(info[4]);

                System.out.println("Pasutijums #" + info[0] + ": " + info[1] + " pasutija " + info[3] + " x " +
                        info[2] + " (" + info[4] + " EUR) -> Kopa: " + (quantity * price) + " EUR");
                sum = sum + quantity * price;
            }

            System.out.println("Kopeja pasutijumu summa: " + sum + " EUR");

        } catch (Exception e) {
            System.out.println("Erorr: " + e.getMessage());
        }
    }
}