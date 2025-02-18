package FirstPackage;

public class SecondClass{
    private int a, b; // Два приватных поля

    // Методы для получения и модификации значений
    public int getA() {
        return a;
    }
    public void setA(int a) {
        this.a = a;
    }
    public int getB() {
        return b;
    }
    public void setB(int b) {
        this.b = b;
    }

    // Конструкторы
    public SecondClass(int a, int b){
        this.a = a;
        this.b = b;
    }
    public SecondClass(){
        this.a = 0;
        this.b = 0;
    }

    // Метод, реализующий сложение
    public int add(int a, int b){
        return a + b;
    }

}