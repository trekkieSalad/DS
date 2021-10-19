package e3;

import java.util.Objects;

import static java.lang.Integer.parseInt;

public class Clock {

    //enum Period
    public enum Period {
        AM("AM"),
        PM("PM");

        private final String abreviatura;

        Period(String abreviatura) {
            this.abreviatura = abreviatura;
        }

        @Override
        public String toString() {
            return abreviatura;
        }
    }

    private final int hour;
    private final int minut;
    private final int second;
    private Period period;

    /**
     * Creates a Clock instance parsing a String .
     * @param s The string representing the hour in 24h format (17:25:15) or in
     * 12h format (05:25:15 PM ).
     * @throws IllegalArgumentException if the string is not a valid hour .
     */
    public Clock(String s) {
        //conversion de string a int manda excepcion IllegaArgumentException si la transformacion no es valida
        this.hour  = parseInt(s.substring(0,2));
        this.minut= parseInt(s.substring(3,5));
        this.second= parseInt(s.substring(6,8));
        try {
            //comprobar si existe el formato de period para seber si el formato es de 24 ó 12 horas
            this.period = Period.valueOf(s.substring(9, 11));
            //comprobar si los tiempos son validos
            if (this.hour > 12 || this.hour < 0 || this.second < 0 || this.minut < 0 ||
                    this.minut > 60 || this.second > 60||(this.hour==12&&(this.second>0||this.minut>0)))
                throw new IllegalArgumentException("hora no valida");
        }
        //si no existe el formato de period manda la excepcion StringIndexOutOfBoundsException y cogumeos esa excepcion
        // con catch sabiendo que el formato es de 24 horas
        catch (StringIndexOutOfBoundsException e){
            if (this.hour > 24 || this.hour < 0 || this.second < 0 || this.minut < 0 ||
                    this.minut > 60 || this.second > 60||(this.hour==24&&(this.second>0||this.minut>0)))
                throw new IllegalArgumentException("hora no valida");
        }


    }
    /**
     * Creates a clock given the values in 24h format .
     * @param hours Hours in 24h format .
     * @param minutes Minutes .
     * @param seconds Seconds .
     * @throws IllegalArgumentException if the values do not represent a valid
     * hour .
     */
    public Clock(int hours , int minutes , int seconds ) {
        this.hour=hours;
        this.minut=minutes;
        this.second=seconds;
        //comprueba si tiene que mandar la excepcion IllegalArgumentException si la hora no es valida en formato 24 horas
        if (this.hour > 24 || this.hour < 0 || this.second < 0 || this.minut < 0 ||
                this.minut > 60 || this.second > 60||(this.hour==24&&(this.second>0||this.minut>0)))
            throw new IllegalArgumentException("hora no valida");
    }
    /**
     * Creates a clock given the values in 12h format . Period is a enumeration
     * located inside the Clock class with two values : AM and PM.
     * @param hours Hours in 12h format .
     * @param minutes Minutes .
     * @param seconds Seconds .
     * @param period Period used by the Clock ( represented by an enum ).
     * @throws IllegalArgumentException if the values do not represent a valid
     * hour .
     */
    public Clock(int hours , int minutes , int seconds , Period period ) {
        this.hour=hours;
        this.minut=minutes;
        this.second=seconds;
        this.period=period;
        // comprueba si tiene que mandar la excepcion IllegalArgumentException si la hora no es valida en formato 12 horas
        if (this.hour > 12 || this.hour < 0 || this.second < 0 || this.minut < 0 ||
                this.minut > 60 || this.second > 60||(this.hour==12&&(this.second>0||this.minut>0)))
            throw new IllegalArgumentException("hora no valida");
    }
    /**
     * Returns the hours of the clock in 24h format
     * @return the hours in 24h format
     */
    public int getHours24 () {
        //comprueba si esta en formato 24 horas ó si tiene formato 12 horas
        if(this.period!=null) {
            //comprueba si es la PM se tiene que sumar 12 a la hora en el formatode 12 horas para representar las 24 horas
            if (this.period.equals(Period.PM) )
                return this.hour + 12;
            else if(this.period.equals(Period.AM)&&this.hour==12)
                return 0;
        }
        return this.hour;

    }
    /**
     * Returns the hours of the clock in 12h format
     * @return the hours in 12h format
     */
    public int getHours12 () {
        if(this.period==null&&this.hour>12)
            return this.hour-12;
        return this.hour;
    }
    /**
     * Returns the minutes of the clock
     * @return the minutes
     */
    public int getMinutes () {
        return this.minut;
    }
    /**
     * Returns the seconds of the clock .
     * @return the seconds .
     */
    public int getSeconds () {
        return this.second;
    }
    /**
     * Returns the period of the day (AM or PM) that the clock is representing
     * @return An instance of the Clock . Period enum depending if the time is
     * before noon (AM) or after noon (PM ).
     */
    public Period getPeriod () {
        //comprobar si tiene formato 12 horas si es cierto se devuelve el periodo que tiene si es falso se comprueba si
        // tiene el formato 24 horas la hora que tiene es mayor que 12 tiene periodo PM en caso contrario AM
        if (this.period!=null)
            return this.period;
        if(this.hour>12)
            return Period.PM;
        else
            return Period.AM;
    }
    /**
     * Prints a String representation of the clock in 24h format .
     * @return An string in 24h format
     * @see String . format function to format integers with leading zeroes
     */
    public String printHour24 () {
        String formato= "%02d:%02d:%02d";
        return String.format(formato,getHours24(),getMinutes(),getSeconds());
    }
    /**
     * Prints a String representation of the clock in 12h format .
     * @return An string in 12h format
     * @see String . format function to format integers with leading zeroes
     */
    public String printHour12 () {
        String formato= "%02d:%02d:%02d %s";
        return String.format(formato,getHours12(),getMinutes(),getSeconds(),getPeriod().abreviatura);
    }
    /**
     * Performs the equality tests of the current clock with another clock
     * passed as a parameter . Two clock are equal if they represent the same
     * instant regardless of being in 12h or 24h format .
     * @param o The clock to be compared with the current clock .
     * @return true if the clocks are equals , false otherwise .
     */
    @Override
    public boolean equals ( Object o) {
        //comprobar si son el mismo objecto si es verdadero devolver true
        if(this== o)
            return true;
        //comprobar si el object es nulo si es verdadero devolver false
        if(o==null)
            return false;
        //comprobacion si son de la misma clase si no devolver false
        if(getClass()!=o.getClass())
            return false;
        //comprobacion si el los reloj representan los mismos segundo minutos y horas
        return this.getHours24() == ((Clock) o).getHours24() && this.getMinutes() == ((Clock) o).getMinutes()
                && this.getSeconds() == ((Clock) o).getSeconds();
    }
    /**
     * Returns a integer that is a hash code representation of the clock using
     * the " hash 31" algorithm .
     * Clocks that are equals must have the same hash code .
     * @return the hash code
     */
    @Override
    public int hashCode () {
        //la fucion objects.hash aplica el algoritmo " hash 31" pasando los elementos que se comparan en equals
        return Objects.hash(this.getHours24(),this.getMinutes(),this.getSeconds());
    }

}