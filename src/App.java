import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class Person {
    private final String name;
    private double money;
    private final List<Product> bag;

    public Person(String name, double money) {
        // Проверка имени
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("Имя не может быть короче 3 символов");
        }
        this.name = name;

        // Проверка и установка денег
        setMoney(money);

        // Инициализация списка покупок
        this.bag = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.money = money;
    }

    public boolean buyProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Продукт не может быть null");
        }

        if (product.getCost() <= money) {
            bag.add(product);
            money -= product.getCost();
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.money, money) == 0 &&
                Objects.equals(name, person.name) &&
                Objects.equals(bag, person.bag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money, bag);
    }

    @Override
    public String toString() {
        if (bag.isEmpty()) {
            return name + " - Ничего не куплено";
        }
        return name + " - " + bag;
    }
}

class Product {
    private final String name;
    private final double cost;

    public Product(String name, double cost) {
        setName(name);
        setCost(cost);
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым");
        }
    }

    public double getCost() {
        return cost;
    }

    private void setCost(double cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Стоимость продукта не может быть отрицательной");
        }
    }

    @Override
    public String toString() {
        return name;
    }
}

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> people = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        // Блок 1: Ввод покупателей
        System.out.println("=== БЛОК 1: Ввод покупателей ===");
        while (true) {
            try {
                System.out.print("Введите имя покупателя (или END для завершения): ");
                String name = scanner.nextLine().trim();

                if (name.equalsIgnoreCase("END")) {
                    break;
                }

                System.out.print("Введите количество денег покупателя: ");
                String moneyInput = scanner.nextLine().trim();
                double money = Double.parseDouble(moneyInput);

                Person person = new Person(name, money);
                people.add(person);
                System.out.println("Покупатель добавлен: " + name + " (" + money + " денег)\n");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Неверный формат денежной суммы\n");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage() + "\n");
            }
        }

        // Блок 2: Ввод продуктов
        System.out.println("\n=== БЛОК 2: Ввод продуктов ===");
        while (true) {
            try {
                System.out.print("Введите название продукта (или END для завершения): ");
                String name = scanner.nextLine().trim();

                if (name.equalsIgnoreCase("END")) {
                    break;
                }

                System.out.print("Введите цену продукта: ");
                String costInput = scanner.nextLine().trim();
                double cost = Double.parseDouble(costInput);

                Product product = new Product(name, cost);
                products.add(product);
                System.out.println("Продукт добавлен: " + name + " (" + cost + " цена)\n");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Неверный формат цены\n");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage() + "\n");
            }
        }

        // Блок 3: Процесс покупки
        System.out.println("\n=== БЛОК 3: Процесс покупки ===");

        // Вывод списка покупателей
        System.out.println("\nДоступные покупатели:");
        people.forEach(person -> System.out.println("- " + person.getName() + " (денег: " + person.getMoney() + ")"));

        // Вывод списка продуктов
        System.out.println("\nДоступные продукты:");
        products.forEach(product -> System.out.println("- " + product.getName() + " (цена: " + product.getCost() + ")"));

        System.out.println();

        while (true) {
            try {
                System.out.print("Введите имя покупателя (или END для завершения): ");
                String personName = scanner.nextLine().trim();

                if (personName.equalsIgnoreCase("END")) {
                    break;
                }

                System.out.print("Введите название продукта для покупки: ");
                String productName = scanner.nextLine().trim();

                Person person = findPerson(people, personName);
                if (person == null) {
                    System.out.println("Ошибка: Покупатель не найден\n");
                    continue;
                }

                Product product = findProduct(products, productName);
                if (product == null) {
                    System.out.println("Ошибка: Продукт не найден\n");
                    continue;
                }

                if (person.buyProduct(product)) {
                    System.out.println(">>> " + personName + " купил(а) " + productName);
                    System.out.println(">>> Остаток денег: " + person.getMoney() + "\n");
                } else {
                    System.out.println(">>> " + personName + " не может позволить себе " + productName);
                    System.out.println(">>> Не хватает: " + (product.getCost() - person.getMoney()) + "\n");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage() + "\n");
            }
        }

        // Вывод результатов
        System.out.println("\n=== ИТОГИ ПОКУПОК ===");
        people.forEach(System.out::println);
    }

    private static Person findPerson(List<Person> people, String name) {
        return people.stream()
                .filter(person -> person.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    private static Product findProduct(List<Product> products, String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}