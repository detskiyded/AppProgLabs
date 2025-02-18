package Task4;

class FirstClass {
    public static void main(String[] args) {
        SecondClass o = new SecondClass();
        int i, j;
        for (i = 0; i <= 8; i++) {
            for (j = 0; j <= 8; j++) {
                o.setA(i);
                o.setB(j);
                System.out.print(o.add(o.getA(), o.getB()));
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}

class SecondClass{
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
