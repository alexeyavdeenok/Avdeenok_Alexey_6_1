public class CustomArray<T> {
    private T[] array;
    private int startIndex;
    private int endIndex;
    private int length;

    public CustomArray() {
        
    }

    public CustomArray(int startIndex, int endIndex) {
        setStartIndex(startIndex);
        setEndIndex(endIndex);
        this.length = endIndex - startIndex +  1;
        this.array = (T[]) new Object[this.length];
    }
    public void setStartIndex(int index) {
        this.startIndex = index;

    }
    public void changeStartIndex(int index){
        setStartIndex(index);
        setEndIndex(length - 1 + index);
    }
    public int getStartIndex() {
        return startIndex;
    }
    public void setEndIndex(int index) {
        this.endIndex = index;
    }
    public void changeEndIndex(int index) {
        setEndIndex(index);
        setStartIndex(index - length + 1);
    }
    public int getEndIndex() {
        return endIndex;
    }
    public void setValue(int index, T value) throws CustomException{
        try {
            array[index - startIndex] = value;
        } catch(Exception e) {
            throw new CustomException("Неверный индекс");
        }
    }
    public T getValue(int index) throws CustomException{
        try {
            return array[index - startIndex];
        } catch(Exception e) {
            throw new CustomException("Неверный индекс");
        }
    }
    public void reverse() {
        T temp;
        for (int i = 0; i < length / 2; i++) {
            temp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = temp;
        }
    }
    public void sumMas(T[] array2) {
        Object[] mergedArray = new Object[array.length + array2.length];
        System.arraycopy(array, 0, mergedArray, 0, array.length);
        System.arraycopy(array2, 0, mergedArray, array.length, array2.length);
        this.array = (T[]) mergedArray;
        setEndIndex(endIndex + array2.length);
        this.length = this.array.length;
    }
    public int indexOfElem(T elem) throws CustomException{
        for (int i = 0; i < length; i++) {
            if (elem.equals(array[i])) {
                return i;
            }
        }
        throw new CustomException("Объект не найден");
    }
    public void addSequence(int index, T[] sequence) {
        Object[] mergedArray = new Object[array.length + sequence.length];
        System.arraycopy(array, 0, mergedArray, 0, index - startIndex + 1);
        System.arraycopy(sequence, 0, mergedArray, index - startIndex + 1, sequence.length);
        System.arraycopy(array, index - startIndex, mergedArray,index - startIndex + 1
        + sequence.length, endIndex - index);
        this.array = (T[]) mergedArray;
        changeStartIndex(startIndex);
        this.length = this.array.length;
    }
    public void printArray(int start, int end) throws CustomException{
        if (start < startIndex || end > endIndex) {
            throw new CustomException("Индекс за пределами массива");
        }
        System.out.println("Начальный индекс: " + start + "Конечный индекс: " + end);
        for (int i = start; i <= end; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}