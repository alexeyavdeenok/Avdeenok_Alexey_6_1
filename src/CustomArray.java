/**
 * Класс CustomArray представляет собой обобщенную реализацию массива с задаваемыми/изменяемыми
 * начальным и конечным индексами.
 *
 * @param <T> тип элементов в массиве
 */
public class CustomArray<T> {
  private T[] array;
  private int startIndex;
  private int endIndex;
  private int length;

  public CustomArray() { // Конструктор по умолчанию
    setStartIndex(0);
    setEndIndex(0);
    this.length = 0;
    this.array = (T[]) new Object[this.length];
  }

  /**
   * Конструктор с параметрами
   *
   * @param startIndex начальный индекс массива
   * @param endIndex конечный индекс массива
   * @throws CustomException исключение при попытке создать массив недопустимой длины
   */
  public CustomArray(int startIndex, int endIndex) throws CustomException {
    setStartIndex(startIndex);
    setEndIndex(endIndex);
    this.length = endIndex - startIndex + 1;
    if (length < 1) {
      throw new CustomException("Нельзя создать массив такой длины");
    } else {
      this.array = (T[]) new Object[this.length];
    }
  }

  /**
   * Метод для изменения начального индекса, метод также изменяет конечный индекс, сохраняя
   * изначальную длину массива
   *
   * @param index новый начальный индекс
   */
  public void changeStartIndex(int index) {
    setStartIndex(index);
    setEndIndex(length - 1 + index);
  }

  /**
   * Метод для получения начального индекса
   *
   * @return начальный индекс массива
   */
  public int getStartIndex() {
    return startIndex;
  }

  /**
   * Метод устанавливает начальный индекс
   *
   * @param index
   */
  public void setStartIndex(int index) {
    this.startIndex = index;
  }

  /**
   * Метод для изменения конечного индекса, также изменяет начальный индекс, сохраняя длину массива
   *
   * @param index новый конечный индекс
   */
  public void changeEndIndex(int index) {
    setEndIndex(index);
    setStartIndex(index - length + 1);
  }

  /**
   * Метод возвращает конечный индекс
   *
   * @return конечный индекс
   */
  public int getEndIndex() {
    return endIndex;
  }

  /**
   * Метод устанавливает конечный индекс
   *
   * @param index конечный индекс
   */
  public void setEndIndex(int index) {
    this.endIndex = index;
  }

  /**
   * Метод для изменения элемента по его индексу
   *
   * @param index индекс элемента
   * @param value новое значение элемента
   * @throws CustomException исключение, возникающее при неверно указанном индексе
   */
  public void setValue(int index, T value) throws CustomException {
    try {
      array[index - startIndex] = value;
    } catch (Exception e) {
      throw new CustomException("Неверный индекс");
    }
  }

  /**
   * Метод возвращает элемент по индексу
   *
   * @param index индекс элемента
   * @return элемент
   * @throws CustomException исключение при неверном индексе
   */
  public T getValue(int index) throws CustomException {
    try {
      return array[index - startIndex];
    } catch (Exception e) {
      throw new CustomException("Неверный индекс");
    }
  }

  /** Метод инвертирует массив */
  public void reverse() {
    T temp;
    for (int i = 0; i < length / 2; i++) {
      temp = array[i];
      array[i] = array[length - 1 - i];
      array[length - 1 - i] = temp;
    }
  }

  /**
   * Метод для конкатенации массивов
   *
   * @param array2 массив который, будет сложен с первым
   */
  public void sumMas(T[] array2) {
    Object[] mergedArray = new Object[array.length + array2.length];
    System.arraycopy(array, 0, mergedArray, 0, array.length);
    System.arraycopy(array2, 0, mergedArray, array.length, array2.length);
    this.array = (T[]) mergedArray;
    setEndIndex(endIndex + array2.length);
    this.length = this.array.length;
  }

  /**
   * Метод возвращает индекс элемента по его значению
   *
   * @param elem элемент
   * @return индекс элемента
   * @throws CustomException Исключение возникает если в массиве нет переданного элемента
   */
  public int indexOfElem(T elem) throws CustomException {
    if (elem.getClass() == String.class) {
      for (int i = 0; i < length; i++) {
        if (elem.equals(array[i])) {
          return i + startIndex;
        }
      }
    } else {
      for (int i = 0; i < length; i++) {
        if (elem == array[i]) {
          return i + startIndex;
        }
      }
    }
    throw new CustomException("Элемент не найден");
  }

  /**
   * Метод для добавления последовательности после индекса
   *
   * @param index индекс
   * @param sequence последовательность
   * @throws CustomException исключение при неверном индексе
   */
  public void addSequence(int index, T[] sequence) throws CustomException {
    if (index < startIndex || index > endIndex) {
      throw new CustomException("Индекс вне допустимого диапазона");
    }

    int sequenceLength = sequence.length;
    int newArrayLength = length + sequenceLength;

    // Создаем новый массив с увеличенной длиной
    T[] newArray = (T[]) new Object[newArrayLength];

    // Копируем элементы из исходного массива до указанного индекса (включительно)
    System.arraycopy(array, 0, newArray, 0, index - startIndex + 1);

    // Копируем вставляемый массив после указанного индекса
    System.arraycopy(sequence, 0, newArray, index - startIndex + 1, sequenceLength);

    // Копируем оставшиеся элементы из исходного массива
    System.arraycopy(
        array,
        index - startIndex + 1,
        newArray,
        index - startIndex + 1 + sequenceLength,
        length - (index - startIndex + 1));

    // Устанавливаем новый массив, обновляем длину и конечный индекс
    this.array = newArray;
    this.length = newArrayLength;
    setEndIndex(endIndex + sequenceLength);
  }

  /**
   * Метод для вывода содержимого массива
   *
   * @param start начальный индекс
   * @param end конечный индекс
   * @throws CustomException искюлчение при неверном индексе
   */
  public void printArray(int start, int end) throws CustomException {
    if (start < startIndex || end > endIndex) {
      throw new CustomException("Индекс за пределами массива");
    }
    System.out.println("Начальный индекс: " + start + " Конечный индекс: " + end);
    for (int i = start; i <= end; i++) {
      System.out.print(array[i - startIndex] + " ");
    }
    System.out.println();
  }
}
