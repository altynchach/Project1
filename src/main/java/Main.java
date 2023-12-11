import java.util.Scanner;

public class Main {
    private double totalSum;
    private Scanner scanner;
    private int numberOfPeople;
    private double calculate;
    private String rouble;
    private String productsList = "";

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        scanner = new Scanner(System.in);
        totalSum = 0.0;
        checkNumberOfPeople();
        addProduct();
        double totalPerPerson = calculatePerPerson(numberOfPeople);
        roubleFormat(totalPerPerson);
        printTotalPerPerson(totalPerPerson);
        printProductsList();
        scanner.close();
    }

    private void addProduct() {
        String answer;
        do {
            scanner.nextLine();
            System.out.print("Введите название товара: ");
            String product = scanner.nextLine();

            System.out.print("Введите стоимость товара: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Введите числовое значение для стоимости.");
                scanner.nextLine(); // очищаем буфер сканера
            }
            double price = scanner.nextDouble();
            scanner.nextLine(); // очищаем буфер сканера после чтения числа

            totalSum += price;
            productsList += product + ", "; // Добавление названия товара в список
            System.out.println("Товар \"" + product + "\" стоимостью " + price + " руб. добавлен.");

            System.out.print("Хотите добавить ещё товар? (да/нет) ");
            answer = scanner.nextLine();
            while (!answer.equalsIgnoreCase("да") && !answer.equalsIgnoreCase("нет")) {
                System.out.println("Ответ должен быть \"да\" или \"нет\".");
                System.out.print("Хотите добавить ещё товар? (да/нет) ");
                answer = scanner.nextLine();
            }
        } while (answer.equalsIgnoreCase("да"));
    }




    private void printProductsList() {
        if (!productsList.isEmpty()) {
            productsList = productsList.substring(0, productsList.length() - 2);
            System.out.println("Список товаров: " + productsList);
        }
    }

    private boolean checkNumberOfPeople() {
        System.out.print("На сколько человек нужно разделить счёт? ");
        while (!scanner.hasNextInt() || (numberOfPeople = scanner.nextInt()) <= 1) {
            scanner.nextLine();
            System.out.println("Количество человек должно быть больше одного.");
            System.out.print("На сколько человек разделить счёт? ");
        }
        return true;
    }

    private double calculatePerPerson(int numberOfPeople) {
        return totalSum / numberOfPeople;
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
