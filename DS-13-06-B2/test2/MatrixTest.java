import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MatrixTest {

    @Test
    void constructores1(){
        //construir la matrix apartir del numero de filas y columnas siendo la matriz 5X4 y se recorre por columnas
        var matrix1=new Matrix(5,4, Matrix.tipoIterador.rowColumn);

        //comprobacion que se le correctamente las columnas
        assertEquals(4,matrix1.columnas());
        //comprobacion que se le correctamente las filas
        assertEquals(5,matrix1.filas());

        //comprobacion que se lee el valor que ocupa en esa posicion
        assertEquals(0,matrix1.leer(2,3));
        //comprobacion que se produce una excepcion si se intenta leer una posicion de la matrix que no es valida
        assertThrows(IllegalArgumentException.class, () -> matrix1.leer(3,6));
        assertThrows(IllegalArgumentException.class, () -> matrix1.leer(-2,3));
        assertThrows(IllegalArgumentException.class, () -> matrix1.leer(3,-6));
        assertThrows(IllegalArgumentException.class, () -> matrix1.leer(9,3));

        //comprobacion que se modifica el valor de una celda de una fila o columa
        matrix1.modificar(2,3,7);
        assertEquals(7,matrix1.leer(2,3));
        //comprobacion que se mandan errores si la fila o columana no son valida
        assertThrows(IllegalArgumentException.class, () -> matrix1.modificar(-1,3,4));
        assertThrows(IllegalArgumentException.class, () -> matrix1.modificar(3,6,4));
        assertThrows(IllegalArgumentException.class, () -> matrix1.modificar(9,3,4));
        assertThrows(IllegalArgumentException.class, () -> matrix1.modificar(2,-6,4));

        //comprobar que es una copia de la matrix en formato bidemensional
        Integer[][] copia;
        copia=matrix1.copia();
        for (int i = 0; i <matrix1.filas() ; i++) {
            for (int j = 0; j <matrix1.columnas() ; j++) {
                assertEquals(copia[i][j],matrix1.leer(i,j));
            }
        }
        //comprobar que es envia una copia de una columna o fila de la matrix
        Integer[] fila;
        Integer[] columna;
        fila=matrix1.copiaFila(1);
        //compobacio que los elementos de la fila de la matriz  son los mismos que del array fila
        for (int i = 0; i <matrix1.columnas(); i++) {
            assertEquals(fila[i],matrix1.leer(1,i));
        }
        columna=matrix1.copiaColumna(3);
        //comprobacion que los elementos de la columna de la matriz que los elemento del array columna
        for (int i = 0; i <matrix1.filas() ; i++) {
            assertEquals(columna[i],matrix1.leer(i,3));
        }
        assertThrows(IllegalArgumentException.class, () -> matrix1.copiaFila(-1));
        assertThrows(IllegalArgumentException.class, () -> matrix1.copiaColumna(6));
        assertEquals("[0, 0, 0, 0]\n" +
                        "[0, 0, 0, 0]\n"+
                        "[0, 0, 0, 7]\n"+
                        "[0, 0, 0, 0]\n"+
                        "[0, 0, 0, 0]\n",
                matrix1.toString());
    }
    @Test
    void constructores2(){
        //construir la matrix apartir un array bidemensional
        Integer[][] matriz ={{3,4,5},
                {1,2,5},
                {5,7,8}
        };

        //comprobar que da error si la matriz no es rectangular
        Integer[][] error ={{3,4},
                {1,2,5},
                {7,8}};

        assertThrows(IllegalArgumentException.class, () -> new Matrix(error,Matrix.tipoIterador.rowColumn));

        var matrix1=new Matrix(matriz,Matrix.tipoIterador.rowColumn);

        //comprobacion que se le correctamente las columnas
        assertEquals(3,matrix1.columnas());
        //comprobacion que se le correctamente las filas
        assertEquals(3,matrix1.filas());

        //comprobacion que se lee el valor que ocupa en esa posicion
        assertEquals(5,matrix1.leer(1,2));

        //comprobar que es una copia de la matrix en formato bidemensional
        Integer[][] copia;
        copia=matrix1.copia();
        for (int i = 0; i <matrix1.filas() ; i++) {
            for (int j = 0; j <matrix1.columnas() ; j++) {
                assertEquals(copia[i][j],matrix1.leer(i,j));
            }
        }
        //comprobar que es envia una copia de una columna o fila de la matrix
        Integer[] fila;
        Integer[] columna;
        fila=matrix1.copiaFila(2);
        //compobacio que los elementos de la fila de la matriz  son los mismos que del array fila
        for (int i = 0; i <matrix1.columnas(); i++) {
            assertEquals(fila[i],matrix1.leer(2,i));
        }
        columna=matrix1.copiaColumna(2);
        //comprobacion que los elementos de la columna de la matriz que los elemento del array columna
        for (int i = 0; i <matrix1.filas() ; i++) {
            assertEquals(columna[i],matrix1.leer(i,2));
        }
        assertEquals("[3, 4, 5]\n" +
                "[1, 2, 5]\n"+
                "[5, 7, 8]\n", matrix1.toString());
    }
    @Test
    void matrixAddition(){

        //comprobacio que lanza una advertencia si las matrix tienen diferentes tamaños
        var errort1=new Matrix(4,2, Matrix.tipoIterador.rowColumn);
        var errort2=new Matrix(5,3, Matrix.tipoIterador.rowColumn);
        var errort3=new Matrix(5,2, Matrix.tipoIterador.rowColumn);
        var errort4=new Matrix(5,3, Matrix.tipoIterador.rowColumn);
        var errort5 =new Matrix(5,3, Matrix.tipoIterador.columnRow);
        var matrixAddition =new MatrixAddition();
        assertThrows(ArithmeticException.class, () ->matrixAddition.sumaMatrices(errort1,errort2, Matrix.tipoIterador.rowColumn));
        assertThrows(ArithmeticException.class, () ->matrixAddition.sumaMatrices(errort3,errort4, Matrix.tipoIterador.rowColumn));
        assertThrows(IllegalArgumentException.class, () ->matrixAddition.sumaMatrices(errort5,errort4, Matrix.tipoIterador.rowColumn));
        Integer[][] matriz1 = {
                {7,-4,9},
                {3,1,0},
                {6,7,7}
        };
        Integer[][] matriz2 = {
                {1,8,2},
                {3,1,0},
                {0,0,0}
        };
        var matrix1=new Matrix(matriz1, Matrix.tipoIterador.rowColumn);
        var matrix2=new Matrix(matriz2, Matrix.tipoIterador.rowColumn);
        assertThrows(UnsupportedOperationException.class, () ->matrix1.rowColumnIterator().remove());
        assertThrows(UnsupportedOperationException.class, () ->matrix1.columnRowIterator().remove());
        Matrix resultado;
        resultado=matrixAddition.sumaMatrices(matrix1,matrix2, Matrix.tipoIterador.rowColumn);
        assertEquals("[8, 4, 11]\n"+
                        "[6, 2, 0]\n"+
                        "[6, 7, 7]\n",
                resultado.toString());
        var matrix3=new Matrix(matriz1, Matrix.tipoIterador.columnRow);
        var matrix4=new Matrix(matriz2, Matrix.tipoIterador.columnRow);
        resultado=matrixAddition.sumaMatrices(matrix3,matrix4, Matrix.tipoIterador.columnRow);
        assertEquals("[8, 4, 11]\n"+
                        "[6, 2, 0]\n"+
                        "[6, 7, 7]\n",
                resultado.toString());

    }


}