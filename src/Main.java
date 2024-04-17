import java.util.Scanner;
public class Main {
    public static Scanner input = new Scanner(System.in);
    public static CustomArray<Integer> listInt = null;
    public static CustomArray<String> listStr = null;

    public static void main(String[] args) {
        while (true) {
            try {
            System.out.println("1. Создать новый массив");
            System.out.println("2. Завершение работы");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Выберите тип массива:");
                    System.out.println("1. Integer");
                    System.out.println("2. String");
                    int choiceType = input.nextInt();
                    createList(choiceType);
                    while (true) {
                        printMenu();
                        int choiceMove = input.nextInt();
                        switch (choiceMove) {
                            case 1:

                        }
                    }
                case 2:
                    System.out.println("Программа завершена");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ошибка ввода");
                    break;
            }
            } catch (CustomException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка ввода");
                input.next();
        }
        }

    }
    public static void printMenu() {
        System.out.println("1. Чтение элемента с заданным индексом");
        System.out.println("2. Изменение элемента с заданными индексом");
        System.out.println("3. Изменение начального индекса");
        System.out.println("4. Изменение конечного индекса");
        System.out.println("5. Инвертирование массива");
        System.out.println("6. Конкатенация двух массивов");
        System.out.println("7. Получение индекса первого вхождения передаваемого элемента");
        System.out.println("8. Вставка последовательности после индекса");
        System.out.println("9. Вывод массива или его части");
        System.out.println("10. Завершение работы с текущим массивом");
    }
    public static void createList(int choiceType) throws CustomException{
        try {
            System.out.println("Введите начальный индекс:");
            int startIndex = input.nextInt();
            System.out.println("Введите конечный индекс");
            int endIndex = input.nextInt();
            if (choiceType == 1) {
                listInt = new CustomArray<Integer>(startIndex, endIndex);
                for (int i = startIndex; i < endIndex + 1; i++) {
                    System.out.println("Введите Элемент с индексом " + i);
                    int value = input.nextInt();
                    listInt.setValue(i, value);
                }
                System.out.println("Массив введен");
            } else if (choiceType == 2) {
                listStr = new CustomArray<String>(startIndex, endIndex);
                input.nextLine();
                for (int i = startIndex; i < endIndex + 1; i++) {
                    System.out.println("Введите Элемент с индексом " + i);
                    String value = input.nextLine();
                    listStr.setValue(i, value);
                }
                System.out.println("Массив введен");
            } else {
                throw new CustomException("Неверный тип данных");
            }

        } catch (Exception e) {
            System.out.println("Ошибка ввода");
            input.next();
        }
    }

}