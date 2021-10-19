package e2;


public class Code {


    /**
     * Determines whether a keypad is valid . To do this , it must be a rectangle and
     * the numbers must follow the alphanumeric sequence indicated . If the array
     * (or any of its subarrays ) is null it will be returned false .
     *
     * @param keypad The keypad to be analyzed .
     * @return true if it is valid , false otherwise .
     */
    public static boolean isKeypadValid(char[][] keypad) {
        char conparaCaracteres = '1';
        try {
            //recorrido de la matriz por filas
            for (char[] chars : keypad) {
                for (char aChar : chars) {
                                //comparar si el elemento de la matriz sigue el orden prestablecido por filas
                                if (aChar == conparaCaracteres) {
                                    //asincaciones que se tienen que hacer en conparaCaracteres para siguir la
                                    // secuencia prestablecida usando el orden en la tabla ascii
                                    if (conparaCaracteres == '0')
                                        conparaCaracteres = 'A';
                                    else if (conparaCaracteres == '9')
                                        conparaCaracteres = '0';
                                    else
                                        //se aumenta en 1 conparaCaracteres para coguer el siguiente caracter que esta a
                                        // continucacion en la tabla ASCII
                                        conparaCaracteres++;
                                }
                                //sigue cumple esta condicion e comprueba si sigue un orden por columnas
                                else if (conparaCaracteres=='2') {
                                    //recorrido de la matriz por columnas
                                    for (int i = 0; i < keypad[0].length; i++) {
                                        for (int j = 0; j < keypad.length; j++) {
                                            if (conparaCaracteres == '2')
                                                j++;
                                            //comparar si el elemento de la matriz sigue el orden prestablecido por columnas
                                            if (keypad[j][i] == conparaCaracteres) {
                                                if (conparaCaracteres == '0')
                                                    conparaCaracteres = 'A';
                                                else if (conparaCaracteres == '9')
                                                    conparaCaracteres = '0';
                                                else
                                                    conparaCaracteres++;
                                            } else
                                                return false;
                                        }
                                    }
                                    //situacion que se sigue el orden prestablecido en columnas
                                    return true;
                                }
                                //situacion en el que el caracter no sigue el orden prestablecido devolver false
                                else
                                    return false;
                            }
                        }
            //si se produce la excepcion NullPointerException significa que la matriz tiene un elemento nulo y se
            // devuelve false
        } catch (NullPointerException e) {
            return false;
        }
        //situacion que se sigue el orden prestablecido en filas
        return true;
    }

    /**
     * Checks if an array of movements is valid when obtaining a key on a keypad .
     * An array of movements is valid if it is formed by Strings that only have the
     * four capital letters U, D, L and R. If the array of Strings or any of the
     * Strings is null it will be returned false .
     *
     * @param movements Array of Strings representing movements .
     * @return true if it is valid , false otherwise .
     */
    public static boolean areMovementsValid(String[] movements) {
        try {
            //recorrer el string de movimientos
            for (String movement : movements) {
                for (int j = 0; j < movement.length(); j++) {//comprobar si cada movimiento es valido
                    if (movement.charAt(j) != 'U' && movement.charAt(j) != 'D' && movement.charAt(j) != 'L' &&
                            movement.charAt(j) != 'R') {
                        return false;
                    }
                }
            }
            //si se produce la excepcion NullPointerException significa que la matriz tiene un elemento nulo y se
            // devuelve false
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * Given a keypad and an array of movements , it returns a String with the code
     * resulting from applying the movements on the keypad .
     *
     * @param keypad    alphanumeric keypad .
     * @param movements Array with the different movements to be made for each
     *                  number of the key .
     * @return Resulting code .
     * @throws IllegalArgumentException if the keypad of the movements are invalid .
     */
    public static String obtainCode(char[][] keypad, String[] movements) {
        StringBuilder codigo = new StringBuilder(movements.length);
        int columnas = 0;
        int filas = 0;
        for (int i = 0; i < movements.length; i++) {//leer string
            for (int k = 0; k < movements[i].length(); k++) {//leer caracteres string
            //comprobacion de los movimientos y si hay que ignorar alguno
                if (movements[i].charAt(k) == 'U' && filas != 0)
                    filas--;
                else if (movements[i].charAt(k) == 'D' && filas != keypad.length - 1 )
                    //comprobar que el array no se sale de los limites
                    filas++;
                else if (movements[i].charAt(k) == 'L' && columnas != 0)
                    columnas--;
                else if (movements[i].charAt(k) == 'R' && columnas != keypad[0].length - 1 )
                    columnas++;
                if (movements[i].charAt(k) != 'U' && movements[i].charAt(k) != 'D' && movements[i].charAt(k) != 'L' &&
                        movements[i].charAt(k) != 'R') {
                    throw new IllegalArgumentException("erro teclado no valido");
                }
            }
            codigo.insert(i, keypad[filas][columnas]);//inserta el elemento correspondiente del tablero en el codigo
        }
        return codigo.toString();
    }




}