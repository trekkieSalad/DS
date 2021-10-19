package e1;



public class StringUtilities {
    /**
     * Checks if a given string is a valid c of two others . That is , it contains
     * all characters of the other two and order of all characters in the individual
     * strings is preserved .
     *
     * @param a First String to be mixed
     * @param b Second String to be mixed
     * @param c Mix of the two other Strings
     * @return true if the c is valid , false otherwise
     */
    public static boolean isValidMix(String a, String b, String c) {

        int contLetrasStringA = 0, contLetrasStringB = 0;
        if (c.length() != (a.length() + b.length()))
            return false;
        else {
            //recorrer caracter a caracter el string c mirando si el caracter coincide
            // con el caracter de alguno de los string a ó b
            for (int i = 0; i < c.length(); i++) {
                //comprobar que se preserva el orden de los caracteres del string a y b en el string c
                // comprobando con la funcion indexof que da el indice  del caracter indicada
                // si la posicion que se comprueba es
                if (a.length() > contLetrasStringA && c.indexOf(a.charAt(contLetrasStringA)) == (contLetrasStringA +
                        contLetrasStringB))
                    contLetrasStringA++;
                else if (b.length() > contLetrasStringB && c.indexOf(b.charAt(contLetrasStringB)) == (contLetrasStringB +
                        contLetrasStringA))
                    contLetrasStringB++;
                else
                    return false;
            }
            return true;
        }
    }

    /**
     * Generates a random valid mix for two Strings
     *
     * @param a First String to be mixed
     * @param b Second String to be mixed
     * @return A String that is a random valid mix of the other two .
     */

    public static String generateRandomValidMix(String a, String b) {
        int contLetrasC = 0, contLetrasA = 0, contLetrasB = 0;
        StringBuilder c = new StringBuilder(a.length() + b.length());
        while (contLetrasC < c.capacity()) {
            int numero = (int) (Math.random() * +2);//funcion que devuelde alectoria mente 1 ó 0
            if ((numero == 0 && a.length() > contLetrasA) || b.length() < contLetrasB) {
                c.insert(contLetrasC, a.charAt(contLetrasA));//inserta en el nuevo string el caracter correspondiente
                contLetrasC++;
                contLetrasA++;
            } else if ((numero == 1 && b.length() > contLetrasB) || a.length() < contLetrasA) {
                //elegurir inserta del string  cual insettar en esa posicion
                c.insert(contLetrasC, b.charAt(contLetrasB));
                contLetrasC++;
                contLetrasB++;
            }
        }
        return c.toString();
    }


}