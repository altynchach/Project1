import java.util.Scanner;

public class Main {
    private double totalSum;
    private Scanner scanner;
    private int numberOfPeople;
    private double calculate; // This variable seems unnecessary as it just stores 'totalSum / numberOfPeople'
    private String rouble;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        scanner = new Scanner(System.in);
        totalSum = 0.0;
        checkNumberOfPeople(); // Ensure this is executed before calling 'calculatePerPerson'
        addProduct();
        double totalPerPerson = calculatePerPerson(numberOfPeople);
        roubleFormat(totalPerPerson); // Call 'roubleFormat' to set the correct form of 'rouble'
        printTotalPerPerson(totalPerPerson);
        scanner.close(); // закрытие сканера
    }

    private void addProduct() {
        String answer;
        do {
            scanner.nextLine(); // очистка буфера
            System.out.print("Введите название товара: ");
            String product = scanner.nextLine();
            System.out.print("Введите стоимость товара: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Введите числовое значение для стоимости.");
                scanner.next(); // to clear the invalid input
                System.out.print("Введите стоимость товара: ");
            }

            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline left by nextDouble

            totalSum += price;
            System.out.println("Товар \"" + product + "\" стоимостью " + price + " руб. добавлен. Хотите добавить ещё товар? (да/нет)");

            answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("да") && !answer.equalsIgnoreCase("нет")) {
                System.out.println("Ответ должен быть \"да\" или \"нет\".");
            }
        } while (!answer.equalsIgnoreCase("нет"));
    }


    private boolean checkNumberOfPeople() {
        System.out.print("На сколько человек разделить счёт? ");
        while (!scanner.hasNextInt() || (numberOfPeople = scanner.nextInt()) <= 1) {
            scanner.nextLine(); // очистка буфера
            System.out.println("Количество человек должно быть больше одного.");
            System.out.print("На сколько человек разделить счёт? ");
        }
        return true;
    }

    private double calculatePerPerson(int numberOfPeople) {
        return totalSum / numberOfPeople; // Directly return the calculated value
    }

    private void printTotalPerPerson(double totalPerPerson) {
        System.out.println("Каждый должен заплатить по " + totalPerPerson + " " + rouble + ".");
    }

    private void roubleFormat(double amount) {
        if (amount % 10 == 1 && amount % 100 != 11) {
            rouble = "рубль";
        } else if ((amount % 10 == 2 || amount % 10 == 3 || amount % 10 == 4) && !(amount % 100 >= 11 && amount % 100 <= 14)) {
            rouble = "рубля";
        } else {
            rouble = "рублей";
        }
    }
}
