public class Main {
    public static void main(String[] args) {
        Vector v1 = new Vector();
        v1.setArgs(1, 2, 3, 4, 5);
        Vector v2 = new Vector(1, 2, 5, 4, 3);
        v1.print();
        v2.sort();
        v2.print();
        System.out.println(Vector.dot_product(v1, v2));
        System.out.println(v1.max());
        System.out.println(v2.min());
        System.out.println(v1.norm());
    }
}
