import java.util.Scanner;

public class Main {
  public static Scanner input = new Scanner(System.in);
  public static CustomArray<Integer> listInt = null;
  public static CustomArray<String> listStr = null;
  public static CustomArray currentList;

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
            int choiceType = input.nextInt(); // Переменная хранит текущий тип массива
            createList(choiceType);
            int flag = 0; // Переменная для завершения цикла
            while (flag == 0) {
              try {
                printMenu();
                int choiceMove = input.nextInt();
                switch (choiceMove) {
                  case 1:
                    System.out.println("Введите индекс:");
                    int index1 = input.nextInt();
                    System.out.println(
                        "Элемент с индексом " + index1 + ": " + currentList.getValue(index1));
                    break;
                  case 2:
                    System.out.println("Введите индекс: ");
                    int index2 = input.nextInt();
                    System.out.println("Введите новый элемент:");
                    if (choiceType == 1) {
                      currentList.setValue(index2, input.nextInt());
                      System.out.println("Элемент изменен");
                    } else {
                      currentList.setValue(index2, input.nextLine());
                      System.out.println("Элемент изменен");
                    }
                    break;
                  case 3:
                    System.out.println("Введите новый начальный индекс:");
                    int index3 = input.nextInt();
                    currentList.changeStartIndex(index3);
                    System.out.println("Новый начальный индекс: " + currentList.getStartIndex());
                    System.out.println("Новый конечный индекс: " + currentList.getEndIndex());
                    break;
                  case 4:
                    System.out.println("Введите новый конечный индекс:");
                    int index4 = input.nextInt();
                    currentList.changeEndIndex(index4);
                    System.out.println("Новый конечный индекс: " + currentList.getEndIndex());
                    System.out.println("Новый начальный индекс: " + currentList.getStartIndex());
                    break;
                  case 5:
                    currentList.reverse();
                    System.out.println("Массив инвертирован:");
                    currentList.printArray(currentList.getStartIndex(), currentList.getEndIndex());
                    break;
                  case 6:
                    sumList(choiceType);
                    break;
                  case 7:
                    System.out.println("Введите элемент: ");
                    if (choiceType == 1) {
                      int elem = input.nextInt();
                      System.out.println(
                          "Индекс первого вхождения: " + currentList.indexOfElem(elem));
                    } else {
                      input.nextLine();
                      String elem = input.nextLine();
                      System.out.println(
                          "Индекс первого вхождения: " + currentList.indexOfElem(elem));
                    }
                    break;
                  case 8:
                    insertMas(choiceType);
                    break;
                  case 9:
                    printList();
                    break;
                  case 10:
                    flag = 1;
                    break;
                  default:
                    System.out.println("Ошибка ввода");
                }
              } catch (CustomException e) {
                System.out.println(e.getMessage());
              } catch (Exception e) {
                System.out.println("Ошибка ввода");
                input.next();
              }
            }
            break;
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

  /** Метод для вывода пунктов меню */
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

  /**
   * Метод для создания массива
   *
   * @param choiceType тип массива
   * @throws CustomException Исключение при неверных индексах
   */
  public static void createList(int choiceType) throws CustomException {
    try {
      System.out.println("Введите начальный индекс:");
      int startIndex = input.nextInt();
      System.out.println("Введите конечный индекс");
      int endIndex = input.nextInt();
      if (endIndex - startIndex + 1 == 0) {
        throw new CustomException("Нельзя создать массив с такими индексами");
      }
      if (choiceType == 1) {
        listInt = new CustomArray<Integer>(startIndex, endIndex);
        for (int i = startIndex; i < endIndex + 1; i++) {
          System.out.println("Введите Элемент с индексом " + i);
          int value = input.nextInt();
          listInt.setValue(i, value);
        }
        System.out.println("Массив введен");
        currentList = listInt;
      } else if (choiceType == 2) {
        listStr = new CustomArray<String>(startIndex, endIndex);
        input.nextLine();
        for (int i = startIndex; i < endIndex + 1; i++) {
          System.out.println("Введите Элемент с индексом " + i);
          String value = input.nextLine();
          listStr.setValue(i, value);
        }
        System.out.println("Массив введен");
        currentList = listStr;
      } else {
        throw new CustomException("Неверный тип данных");
      }

    } catch (CustomException e) {
      throw new CustomException(e.getMessage());
    } catch (Exception e) {
      input.next();
      throw new CustomException("Ошибка ввода, Массив не создан");
    }
  }

  /**
   * Метод для конкатенации массивов
   *
   * @param type тип массива
   */
  public static void sumList(int type) {
    System.out.println("Введите длину второго массива:");
    int listLength = input.nextInt();
    if (type == 1) {
      Integer[] mas = new Integer[listLength];
      for (int i = 0; i < listLength; i++) {
        System.out.println("Введите элемент:");
        mas[i] = input.nextInt();
      }
      System.out.println("Массив введен");
      currentList.sumMas(mas);
      System.out.println("Операция успешна");
    } else {
      input.nextLine();
      String[] mas = new String[listLength];
      for (int i = 0; i < listLength; i++) {
        System.out.println("Введите элемент:");
        mas[i] = input.nextLine();
      }
      System.out.println("Массив введен");
      currentList.sumMas(mas);
      System.out.println("Операция успешна");
    }
  }

  /**
   * Метод для вставки последовательности после индекса
   *
   * @param type тип массива
   * @throws CustomException исключение в случае неверного индекса
   */
  public static void insertMas(int type) throws CustomException {
    System.out.println("Введите длину последовательности:");
    int listLength = input.nextInt();
    if (type == 1) {
      Integer[] mas = new Integer[listLength];
      for (int i = 0; i < listLength; i++) {
        System.out.println("Введите элемент:");
        mas[i] = input.nextInt();
      }
      System.out.println("Последовательность введена");
      System.out.println(
          "Введите индекс, после которого необходимо вставить" + " последовательность");
      int index = input.nextInt();
      currentList.addSequence(index, mas);
      System.out.println("Операция успешна");
    } else {
      input.nextLine();
      String[] mas = new String[listLength];
      for (int i = 0; i < listLength; i++) {
        System.out.println("Введите элемент:");
        mas[i] = input.nextLine();
      }
      System.out.println("Последовательность введена");
      System.out.println(
          "Введите индекс, после которого необходимо вставить" + " последовательность");
      int index = input.nextInt();
      currentList.addSequence(index, mas);
      System.out.println("Операция успешна");
    }
  }

  /**
   * Метод для вывода массива
   *
   * @throws Exception Исключение при неверном вводе данных
   */
  public static void printList() throws Exception {
    System.out.println("1. Вывести массив целиком");
    System.out.println("2. Вывести часть массива");
    int choice = input.nextInt();
    try {
      if (choice == 1) {
        currentList.printArray(currentList.getStartIndex(), currentList.getEndIndex());
      } else {
        System.out.println("Введите начальный индекс: ");
        int indexStart = input.nextInt();
        System.out.println("Введите конечный индекс: ");
        int indexEnd = input.nextInt();
        currentList.printArray(indexStart, indexEnd);
      }
    } catch (CustomException e) {
      throw new CustomException(e.getMessage());
    } catch (Exception e) {
      throw new Exception();
    }
  }
}
