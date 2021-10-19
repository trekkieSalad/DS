import java.util.Arrays;
import  java.util.Iterator;

public class Matrix implements Iterable<Integer>{
    enum tipoIterador{
        rowColumn,
        columnRow
    }

    private final Integer [][] matriz;
    private final tipoIterador iter;
    //constructor que crea una matrix a partir del numero de columans y fila
    public Matrix(int filas, int columnas, tipoIterador iter){
        this.iter = iter;
        matriz =new Integer[filas][columnas];
        for (int fila = 0; fila < filas; fila++)
            for(int columna = 0; columna < columnas; columna++)
                matriz[fila][columna] = 0;
    }
    //constructor que crea una matrix a partir de una array bidemensional
    public Matrix (Integer [][] matriz, tipoIterador iter){
        this.iter = iter;
        for (int i = 1; i < matriz.length; i++){
            if (matriz[i].length != matriz[i-1].length)
                throw new IllegalArgumentException("matriz no rectangular");
        }
        this.matriz = matriz;
    }
    //devuelve el numero de filas de la matrix
    public int filas(){
        return matriz.length;
    }
    //devuelve el numero de columnas de la matrix
    public int columnas(){
        return matriz[0].length;
    }
    //devuelve el interador
    public tipoIterador getIter() {
        return iter;
    }

     //funcion que lee una fila de la una celda de una fila o columna
    public int leer(int fila, int columna){
        if (fila > matriz.length||fila<0)
            throw new IllegalArgumentException("Numero de fila incorrecto");
        if (columna > matriz[0].length||columna<0)
            throw new IllegalArgumentException("Numero de columna incorrecto");
        return matriz[fila][columna];
    }
    //funcion que modifica el valor de una celda de la matrix
    public void modificar(int fila, int columna, int valor){
        if (fila > matriz.length||fila<0)
            throw new IllegalArgumentException("Numero de fila incorrecto");
        if (columna > matriz[0].length||columna<0)
            throw new IllegalArgumentException("Numero de columna incorrecto");
        matriz[fila][columna] = valor;
    }

    //funcion que devuel una copia de la matrix en formato array bimensional
    public Integer [][] copia(){
        return Arrays.copyOf(this.matriz,matriz.length);
    }
    //funcion que devuelve una copia de una fila de la matrix
    public Integer [] copiaFila(int fila){
        if (fila > matriz.length||fila<0)
            throw new IllegalArgumentException("Numero de fila incorrecto");
        return Arrays.copyOf(matriz[fila],matriz.length);
    }
    //funcion que devuelve una copia de una columna
    public Integer [] copiaColumna(int columna){
        if (columna > matriz[0].length||columna<0)
            throw new IllegalArgumentException("Numero de columna incorrecto");
        Integer [] copia = new Integer[matriz.length];
        for (int i = 0; i < matriz.length; i++)
            copia[i] = matriz[i][columna];
        return copia;
    }
    //funcion que devuel el interador que recorre primero por columnas y despues por filas
    public Iterator<Integer> rowColumnIterator(){
        return new IterarFilas();
    }
    //funcion que devuel el interador que recorre primero por filas y despues por columnas
    public Iterator<Integer> columnRowIterator(){
        return new IterarColumnas();
    }
    //funcion que imprime la matrix
    public String toString(){
        StringBuilder matriz = new StringBuilder();
        for (Integer[] ints : this.matriz)
            matriz.append(Arrays.toString(ints)).append("\n");
        return matriz.toString();
    }
    //funcion que devuelve el iterator correspondiente al pedido en el constructor
    public Iterator<Integer> iterator() {
        if (iter == tipoIterador.rowColumn) return rowColumnIterator();
        else return  columnRowIterator();
    }



    //clase que implementa el Iterator para que empiez por filas
    protected class IterarFilas implements Iterator<Integer> {
        protected int columna;
        protected int fila;

        public IterarFilas() {
            fila = 0;
            columna = 0;
        }

        @Override
        public boolean hasNext() {
            return ((fila < matriz.length) && (columna < matriz[fila].length));
        }

        @Override
        public Integer next() {
            Integer dato;
            dato = matriz[fila][columna];
            if (columna < matriz[fila].length-1) {
               this.columna++;
            }
            else {
                this.fila++;
                this.columna = 0;
            }
            return dato;
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException("No soportado.");
        }

    }
    //clase que implementa el Iterator para que empiez por columans
    protected class IterarColumnas implements Iterator<Integer> {
        protected int columna;
        protected int fila;

        public IterarColumnas() {
            fila = 0;
            columna = 0;
        }

        @Override
        public boolean hasNext() {
            return ((fila < matriz.length) && (columna < matriz[fila].length));
        }

        @Override
        public Integer next() {
            Integer dato;
            dato = matriz[fila][columna];
            if (fila < matriz.length-1) {
                fila++;
            }
            else {
                fila = 0;
                columna++;
            }
            return dato;
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException("No soportado.");
        }
    }
}
