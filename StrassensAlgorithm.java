/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PR2;

/**
 *
 * @author Ivan
 */
public class StrassensAlgorithm {

    public double[][] strassenMatrixMulti(double[][] A, double[][] B) {

        /**
         * Prosedur untuk membentuk matriks yang elemennya sama denga input tapi
         * berukuran 2^n
         */
        int size;
        size = (int) Math.pow(2, Math.ceil((Math.log(max(A.length, A[0].length))) / (Math.log(2))));
        double[][] C = mapping(A, size);
        double[][] D = mapping(B, size);

        return null;
    }

    /**
     * Method max mengembalikan bilangan yang paling besar dari dua buah input
     *
     * @param a
     * @param b
     * @return
     */
    public int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * Method mapping berfungsi untuk memetakan matriks ke matriks berukuran 2^n
     *
     * @param origin
     * @param size
     * @return
     */
    public double[][] mapping(double[][] origin, int size) {
        double[][] destination = new double[size][size];

        for (int i = 0; i < origin.length; i++) {
            for (int j = 0; j < origin[0].length; j++) {
                destination[i][j] = origin[i][j];
            }
        }

        return destination;
    }

    public double[][] strassen(double[][] A, double[][] B) {
        int size = A.length;
        double[][] C = new double[size][size];
        if (size == 1) {
            C[0][0] = A[0][0] * B[0][0];
        } else {
            double[][] A11 = partition(A, 1, 1);
            double[][] A12 = partition(A, 1, 2);
            double[][] A21 = partition(A, 2, 1);
            double[][] A22 = partition(A, 2, 2);
            double[][] B11 = partition(B, 1, 1);
            double[][] B12 = partition(B, 1, 2);
            double[][] B21 = partition(B, 2, 1);
            double[][] B22 = partition(B, 2, 2);

            double[][] P1 = strassen(sum(A11, A22), sum(B11, B22));
            double[][] P2 = strassen(sum(A21, A22), B11);
            double[][] P3 = strassen(A11, minus(B12, B22));
            double[][] P4 = strassen(A22, minus(B21, B11));
            double[][] P5 = strassen(sum(A11, A12), B22);
            double[][] P6 = strassen(minus(A21, A11), sum(B11, B12));
            double[][] P7 = strassen(minus(A12, A22), sum(B21, B22));
            double[][] C11 = sum(minus(sum(P1, P4), P5), P7);
            double[][] C12 = sum(P3, P5);
            double[][] C21 = sum(P2, P4);
            double[][] C22 = minus(minus(sum(P1, P5), P3), P7);
            C = join(C11, C12, C21, C22);
        }


        return C;
    }

    /**
     * Method join untuk menggabungkan 4 sub-Matriks
     *
     * @param C11
     * @param C12
     * @param C21
     * @param C22
     * @return
     */
    public double[][] join(double[][] C11, double[][] C12, double[][] C21, double[][] C22) {
        int size = C11.length;
        double[][] C = new double[(size) * 2][(size) * 2];
        for (int i = 0; i < C11.length; i++) {
            for (int j = 0; j < C11.length; j++) {
                C[i][j] = C11[i][j];
            }
        }
        for (int i = 0; i < C11.length; i++) {
            for (int j = 0; j < C11.length; j++) {
                C[i][j] = C12[i][(size + j)];
            }
        }
        for (int i = 0; i < C11.length; i++) {
            for (int j = 0; j < C11.length; j++) {
                C[i][j] = C21[size + i][j];
            }
        }
        for (int i = 0; i < C11.length; i++) {
            for (int j = 0; j < C11.length; j++) {
                C[i][j] = C22[size + i][size + j];
            }
        }
        return C;
    }

    /**
     * Method partition untuk menghasilkan matriks hasil partisi
     *
     * @param input
     * @param a
     * @param b
     * @return
     */
    public double[][] partition(double[][] input, int a, int b) {
        int size = input.length / 2;
        double[][] output = new double[size][size];
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output.length; j++) {
                output[i][j] = input[((a - 1) * size + i)][((b - 1) * size + j)];
            }
        }
        return output;
    }

    /**
     * Method ini berguna untuk menjumlahkan dua matriks
     *
     * @param A
     * @param B
     * @return
     */
    public double[][] sum(double[][] A, double[][] B) {
        double[][] C = new double[A.length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    /**
     * Method ini berguna untuk mengurangkan dua matriks
     *
     * @param A
     * @param B
     * @return
     */
    public double[][] minus(double[][] A, double[][] B) {
        double[][] C = new double[A.length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }
}
