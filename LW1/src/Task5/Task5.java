package Task5;

import Task5.FirstPackage.*;
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
