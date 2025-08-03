import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/*
Аттестационное задание 1
Постановка:
По условиям реализован механизм покупки, включающий три блока, выполняемых поочерёдно, переход на следующий этап по вводу команды end
1) Ввод имен пользователей и количества у них денег
2) Ввод товаров и их цен
3) Покупка товаров с выводом итогов согласно условиям
Задача реализована по условиям, плюс небольшие улучшения (итоговый список товаров, остаток средств после покупки, список доступных к вводу пользователей/товаров перед блоком 3)
учтены требования по созданию классов, переопределению методов, типу полей, задача проверена на тестовых данных, условия и данные прикреплены
*/

/*
Условия задания:
Промежуточная аттестация Модуль 1 «Введение в разработку/Введение в Java» Формулировка задания: Необходимо реализовать приложение, принимающее список пользователей, продуктов и обрабатывающее покупку пользователя.
Подробное описание функционала приложения
1. Создать классы Покупатель (Person) и Продукт (Product). Характеристики Покупателя: имя, сумма денег и пакет с продуктами (массив объектов типа Продукт). Имя не может быть пустой строкой и не может быть короче 3 символов.
Деньги не могут быть отрицательным числом. Если Покупатель может позволить себе Продукт, то Продукт добавляется в пакет. Если у Покупателя недостаточно денег, то добавление не происходит.
Характеристики Продукта: название и стоимость. Название продукта не может быть пустой строкой, оно должно быть. Стоимость продукта не может быть отрицательным числом.
2. Поля в классах должны быть private, доступ к полям осуществляется через геттеры и сеттеры или конструктор класса.
3. В классах переопределены методы toString(), equals(), hashcode().
4. Создать в классе App метод main и проверить работу приложения. Данные Покупателей и Продукты вводятся с клавиатуры,для считывания данных потребуется использовать класс Scanner и его метод nextLine().
Продукты в цикле выбираются покупателями по очереди и, пока не введено слово END, наполняется пакет.
5. Обработать следующие ситуации: а. Если покупатель не может позволить себе продукт, то напечатайте соответствующее сообщение ("[Имя человека] не может позволить себе [Название продукта]").
б. Если ничего не куплено, выведите имя человека, за которым следует "Ничего не куплено". в. В случае неверного ввода - сообщение: "Деньги не могут быть отрицательными", пустого имени - сообщение: "Имя не может быть пустым" или длина имени менее 3 символов – сообщение: "Имя не может быть короче 3 символов". Программа реализуется в отдельной ветке git attestation/attestation01. При сохранении состояния программы (коммиты) пишется сообщение с описанием хода работы по задаче. В корне папки с программой должен быть файл .gitignore. Программа локально коммитится и публикуется в репозиторий GitHub на проверку. Тестовые данные: Ожидаемый результат : Павел Андреевич = 10000; Анна Петровна = 2000; Борис = 10 Хлеб = 40; Молоко = 60; Торт = 1000; Кофе растворимый = 879; Масло = 150 Павел Андреевич - Хлеб Павел Андреевич - Масло Анна Петровна - Кофе растворимый Анна Петровна - Молоко Анна Петровна - Молоко Анна Петровна - Молоко Анна Петровна - Торт Борис - Торт Павел Андреевич - Торт END Павел Андреевич купил Хлеб Павел Андреевич купил Масло Анна Петровна купил Кофе растворимый Анна Петровна купил Молоко Анна Петровна купил Молоко Анна Петровна купил Молоко Анна Петровна не может позволить себе Торт Борис не может позволить себе Торт Павел Андреевич купил Торт Павел Андреевич - Хлеб, Масло, Торт Анна Петровна - Кофе растворимый, Молоко, Молоко, Молоко Борис - Ничего не куплено Женя = 0 Мороженое = 200 Женя - Мороженое END Женя не может позволить себе Мороженое Женя - Ничего не куплено Света = -3 Деньги не могут быть отрицательными Фа = 100 Имя не может быть короче 3 символов Тестовые данные вводятся по очереди, сначала проверяется успешный кейс, потом не успешные, для неуспешных достаточно получить сообщение о невозможности покупки или ошибке валидации. После данного сообщения, сделать скриншот и завершить программу.

Тест данные из задачи для проверки
Павел Андреевич = 10000; Анна
Петровна = 2000; Борис = 10
Хлеб = 40; Молоко = 60; Торт = 1000;
Кофе растворимый = 879; Масло = 150

---Ввод в блоке 3-Покупки  ->  Вывод на экран---
Павел Андреевич - Хлеб -> Павел Андреевич купил Хлеб
Павел Андреевич - Масло -> Павел Андреевич купил Масло
Анна Петровна - Кофе растворимый -> Анна Петровна купил Кофе
растворимый
Анна Петровна - Молоко -> Анна Петровна купил Молоко
Анна Петровна - Молоко -> Анна Петровна купил Молоко
Анна Петровна - Молоко -> Анна Петровна купил Молоко
Анна Петровна - Торт -> Анна Петровна купил Торт
Борис - Торт -> Борис купил Торт
Павел Андреевич - Торт -> Павел Андреевич купил Торт
END


*/


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