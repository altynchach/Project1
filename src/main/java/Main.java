import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BillCalculator calculator = new BillCalculator();
        calculator.run();
    }
}

class BillCalculator {
    private double totalSum;
    private Scanner scanner;
    private int numberOfPeople;
    private List<Product> products;

    public BillCalculator() {
        scanner = new Scanner(System.in);
        products = new ArrayList<>();
    }

    public void run() {
        totalSum = 0.0;
        checkNumberOfPeople();
        addProducts();
        double totalPerPerson = calculatePerPerson();
        String rouble = formatRoubles(totalPerPerson);
        printTotalPerPerson(totalPerPerson, rouble);
        printProductsList();
        scanner.close();
    }

    private void addProducts() {
        String answer;
        do {
            System.out.print("Введите название товара: ");
            String productName = scanner.nextLine();

            System.out.print("Введите стоимость товара: ");
            double price = 0;
            boolean validPrice = false;
            while (!validPrice) {
                if (scanner.hasNextDouble()) {
                    price = scanner.nextDouble();
                    validPrice = true;
                } else {
                    System.out.println("Введите числовое значение для стоимости.");
                    scanner.next();
                }
            }
            scanner.nextLine();

            Product product = new Product(productName, price);
            products.add(product);
            totalSum += price;

            System.out.println("Товар \"" + productName + "\" стоимостью " + formatPrice(price) + " " + formatRoubles(price) + " добавлен.");
            do {
                System.out.print("Хотите добавить ещё товар? (да/нет) ");
                answer = scanner.nextLine();
                if (!answer.equalsIgnoreCase("да") && !answer.equalsIgnoreCase("нет")) {
                    System.out.println("Ответ должен быть \"да\" или \"нет\".");
                }
            } while (!answer.equalsIgnoreCase("да") && !answer.equalsIgnoreCase("нет"));
        } while (answer.equalsIgnoreCase("да"));
    }

    private void printProductsList() {
        if (!products.isEmpty()) {
            System.out.print("Список товаров: ");
            for (int i = 0; i < products.size(); i++) {
                System.out.print(products.get(i).getName());
                if (i < products.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    private boolean checkNumberOfPeople() {
        System.out.print("На сколько человек нужно разделить счёт? ");
        while (!scanner.hasNextInt() || (numberOfPeople = scanner.nextInt()) <= 1) {
            System.out.println("Количество человек должно быть больше одного.");
            System.out.print("На сколько человек разделить счёт? ");
            scanner.nextLine();
        }
        scanner.nextLine();
        return true;
    }

    private double calculatePerPerson() {
        return totalSum / numberOfPeople;
    }

    private void printTotalPerPerson(double totalPerPerson, String rouble) {
        System.out.println("Каждый должен заплатить по " + formatPrice(totalPerPerson) + " " + rouble + ".");
    }

    private String formatRoubles(double amount) {
        if (amount % 10 == 1 && amount % 100 != 11) {
            return "рубль";
        } else if ((amount % 10 == 2 || amount % 10 == 3 || amount % 10 == 4) && !(amount % 100 >= 11 && amount % 100 <= 14)) {
            return "рубля";
        } else {
            return "рублей";
        }
    }

    private String formatPrice(double price) {
        return String.format("%.2f", price);
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
