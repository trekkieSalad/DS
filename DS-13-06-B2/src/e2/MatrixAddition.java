import java.util.Iterator;

public class MatrixAddition {
    public Matrix sumaMatrices(Matrix m1, Matrix m2, Matrix.tipoIterador iterador) {
        int fila = 0, columna = 0;
        if (m1.filas() != m2.filas() || m1.columnas() != m2.columnas())
            throw new ArithmeticException("La dimension de las matrices no es la misma");
        if (m1.getIter() != m2.getIter())
            throw new IllegalArgumentException("No se pueden sumar matrices con distintos iteradores");

        Matrix m3 = new Matrix(m1.filas(), m1.columnas(), iterador);
        Iterator<Integer> iter1 = m1.iterator();
        Iterator<Integer> iter2 = m2.iterator();

        while (iter1.hasNext() || iter2.hasNext()) {
            m3.modificar(fila, columna, iter1.next() + iter2.next());
            if (iterador == Matrix.tipoIterador.rowColumn) {
                if (columna < m3.columnas() - 1) {
                    columna++;
                } else {
                    fila++;
                    columna = 0;
                }
            } else {
                if (fila < m3.filas() - 1) {
                    fila++;
                } else {
                    fila = 0;
                    columna++;
                }
            }
        }
        return m3;
    }
}

