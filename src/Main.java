import java.util.Locale;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Random rand = new Random();
        ComplexMatrix matrix1 = new ComplexMatrix(3, 3);
        ComplexMatrix matrix2 = new ComplexMatrix(3, 3);
        ComplexNum a = new ComplexNum();
        ComplexNum b = new ComplexNum();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a.setReal(rand.nextDouble() * 100 - 50);
                a.setImaginary(rand.nextDouble() * 100 - 50);
                matrix1.setMatrixElem(a, i, j);
                b.setReal(rand.nextDouble() * 100 - 50);
                b.setImaginary(rand.nextDouble() * 100 - 50);
                matrix2.setMatrixElem(b, i, j);
            }
        }
        System.out.println("Matrix1\n" + matrix1 +"\nMatrix2\n" + matrix2 +
                "\nTransposition of matrix2\n" + matrix2.transpose() +
                "\nMatrix1 + matrix2:\n" + matrix1.add(matrix2) +
                "\nMatrix1 - matrix2:\n" + matrix1.subtract(matrix2) +
                "\nMatrix1 * matrix2:\n" + matrix1.multiply(matrix2) +
                "\nDeterminant of matrix1: " + matrix1.determinant() + "\n" +
                "\nMatrix1 last element / matrix2 last element: " + a.divide(b));
    }
}