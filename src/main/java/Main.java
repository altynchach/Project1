import java.util.Scanner;

public class Main {
    private double totalSum;
    private Scanner scanner;
    private int numberOfPeople;
    private double calculate;
    private String rouble;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        scanner = new Scanner(System.in);
        totalSum = 0.0;
        int calculate;
        String rouble;
        checkNumberOfPeople();
        addProduct();
        double totalPerPerson = calculatePerPerson(numberOfPeople);
        printTotalPerPerson(totalPerPerson);
        scanner.close(); // закрытие сканера
    }

    private void addProduct() {
        String answer;
        do {
            System.out.print("Введите название товара: ");
            String product = scanner.nextLine();
            System.out.print("Введите стоимость товара: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Введите числовое значение для стоимости.");
                scanner.next();
                System.out.print("Введите стоимость товара: ");
            }
            double price = scanner.nextDouble();
            totalSum += price;
            System.out.println("Товар \"" + product + "\" стоимостью " + price + " руб. добавлен. Хотите добавить ещё товар? (да/нет)");
            scanner.nextLine();
            answer = scanner.nextLine();
            if (!answer.equals("да") && !answer.equals("нет")) {
                System.out.println("Ответ должен быть \"да\" или \"нет\".");
            }
        } while (!answer.equals("нет"));
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
        calculate = totalSum / numberOfPeople;
        return calculate;
    }

    private void printTotalPerPerson(double totalPerPerson) {
        System.out.println("Каждый должен заплатить по " + calculate + " " + rouble + ".");
    }
    private void roubleFormat() {
        if (calculate % 10 == 1) {
            rouble = "рубль";
        } else if (calculate % 10 == 2 || calculate % 10 == 3 || calculate % 10 == 4) {
            rouble = "рубля";
        } else {
            rouble = "рублей";
        }
    }
}
