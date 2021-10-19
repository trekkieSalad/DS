package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TermostatoTest {
    @Test
    void termostos() {
        var termostato = new Termostato();
        termostato.newTemperature((float) 20.1);
        assertEquals("20.1 Modo Off - Calefaccion apagada .\n", termostato.screenInfo());
        termostato.cambiarManual();
        assertEquals("Se activa el modo Manual .\n", termostato.eventos());
        termostato.newTemperature((float) 20.1);
        assertEquals("20.1 Modo Manual - Calefaccion encendida .\n", termostato.screenInfo());
        termostato.newTemperature((float) 21.5);
        assertEquals("21.5 Modo Manual - Calefaccion encendida .\n", termostato.screenInfo());
        termostato.newTemperature((float) 21.1);
        assertEquals("21.1 Modo Manual - Calefaccion encendida .\n", termostato.screenInfo());
        termostato.cambiarTimer(19);
        assertEquals("Se activa el modo Manual .\n" + "Se activa el modo Timer 19 minutos .\n"
                , termostato.eventos());

        termostato.newTemperature((float) 21.0);
        assertEquals("21.0 Modo Timer ( faltan 14 minutos ) - Calefaccion encendida .\n"
                , termostato.screenInfo());
        termostato.newTemperature((float) 21.9);
        assertEquals("21.9 Modo Timer ( faltan 9 minutos ) - Calefaccion encendida .\n"
                , termostato.screenInfo());
        termostato.newTemperature((float) 22.8);
        assertEquals("22.8 Modo Timer ( faltan 4 minutos ) - Calefaccion encendida .\n"
                , termostato.screenInfo());

        termostato.newTemperature((float) 22.5);
        assertEquals("Se activa el modo Manual .\n" + "Se activa el modo Timer 19 minutos .\n"
                + "Se desactiva el modo Timer .\n", termostato.eventos());
        assertEquals("22.5 Modo Off - Calefaccion apagada .\n"
                , termostato.screenInfo());
        termostato.newTemperature((float) 21.4);
        assertEquals("21.4 Modo Off - Calefaccion apagada .\n"
                , termostato.screenInfo());
        termostato.cambiarProgram((float) 20.0);
        assertEquals("Se activa el modo Manual .\n" + "Se activa el modo Timer 19 minutos .\n"
                        + "Se desactiva el modo Timer .\n"+
                        "Se activa el modo Program a 20.0 grados .\n"
                , termostato.eventos());

        termostato.newTemperature((float) 21.2);
        assertEquals("21.2 Modo Program (a 20.0 grados ) - Calefaccion apagada .\n"
                , termostato.screenInfo());
        termostato.newTemperature((float) 20.8);
        assertEquals("20.8 Modo Program (a 20.0 grados ) - Calefaccion apagada .\n"
                , termostato.screenInfo());
        termostato.newTemperature((float) 19.2);
        assertEquals("19.2 Modo Program (a 20.0 grados ) - Calefaccion encendida .\n"
                , termostato.screenInfo());
        termostato.newTemperature((float) 19.9);
        assertEquals("19.9 Modo Program (a 20.0 grados ) - Calefaccion encendida .\n"
                , termostato.screenInfo());
        termostato.newTemperature((float) 20.7);
        assertEquals("20.7 Modo Program (a 20.0 grados ) - Calefaccion apagada .\n"
                , termostato.screenInfo());
        termostato.newTemperature((float) 22.8);
        assertEquals("22.8 Modo Program (a 20.0 grados ) - Calefaccion apagada .\n"
                , termostato.screenInfo());
        termostato.cambiaOff();
        assertEquals("Se activa el modo Manual .\n" + "Se activa el modo Timer 19 minutos .\n"
                        + "Se desactiva el modo Timer .\n"+
                        "Se activa el modo Program a 20.0 grados .\n"+
                        "Se activa el modo Off .\n"
                , termostato.eventos());
        termostato.newTemperature((float) 20.2);
        assertEquals("20.2 Modo Off - Calefaccion apagada .\n", termostato.screenInfo());


        termostato.cambiarProgram(20);
        assertThrows(IllegalArgumentException.class, () -> termostato.cambiarTimer(20));
    }
}