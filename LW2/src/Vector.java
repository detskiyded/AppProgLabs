public class Vector {
    private double[] args;

    // Геттеры и сеттеры
    public double[] getArgs() {
        return args;
    }
    public void setArgs(double... args) {
        this.args = args;
    }

    // Получение длины вектора
    public int len(){
        return args.length;
    }

    // Вывод вектора
    public void print() {
        for (double arg : args) {
            System.out.print(arg + " ");
        }
        System.out.println();
    }

    // Сумма двух векторов
    public static Vector add(Vector v1, Vector v2) {
        if (v1.args.length == v2.args.length) {
            double[] result = new double[v1.args.length];
            for (int i = 0; i < v1.args.length; i++) {
                result[i] = v1.args[i] + v2.args[i];
            }
            return new Vector(result);
        }
        else{
            throw new IllegalArgumentException("Vector length does not match");
        }
    }

    // Скалярное произведение двух векторов
    public static double dot_product(Vector v1, Vector v2) {
        if (v1.args.length == v2.args.length) {
            double result = 0;
            for (int i = 0; i < v1.args.length; i++) {
                result += v1.args[i] * v2.args[i];
            }
            return result;
        }
        else{
            throw new IllegalArgumentException("Vector length does not match");
        }
    }

    // Умножение вектора на число
    public void multiply(double scalar) {
        for (int i = 0; i < args.length; i++) {
            args[i] *= scalar;
        }
    }

    // Конструкторы
    public Vector(double ... args) {
        this.args = args;
    }
    public Vector(){
        this.args = new double[0];
    }

    // Минимальное и максимальное значения вектора
    public double min(){
        double min = Double.POSITIVE_INFINITY;
        for (double arg : args) {
            if (arg < min) {
                min = arg;
            }
        }
        return min;
    }
    public double max(){
        double max = Double.NEGATIVE_INFINITY;
        for (double arg : args) {
            if (arg > max) {
                max = arg;
            }
        }
        return max;
    }

    // Сортировка вектора пузырькой
    public void sort() {
        for (int i = 1; i < args.length; i++) {
            boolean isSorted = true;
            for (int j = 0; j < args.length - i; j++) {
                if (args[j] > args[j + 1]) {
                    double temp = args[j];
                    args[j] = args[j + 1];
                    args[j + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    // Нормализация вектора (скалярное произведение само на себя)
    public double norm() { 
        return dot_product(this, this);
    }
}
