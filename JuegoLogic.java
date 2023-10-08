/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 57301
 */
package juegopreescolar2;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class JuegoLogic {

    public static int figurasMostradas = 0;
    private static int intentos = 0;
    private static int fallos = 0;
    private static int aciertos = 0;
    public static String nombreEstudiante;

    public static String[] figurasGeometricas = {
            "Cuadrado",
            "Triángulo",
            "Círculo",
            "Rectángulo"
            // Agrega más figuras geométricas aquí
    };

    public static String obtenerFiguraAleatoria() {
        Random random = new Random();
        int index = random.nextInt(figurasGeometricas.length);
        return figurasGeometricas[index];
    }

    public static void verificarRespuesta(String figuraCorrecta, String respuestaSeleccionada, JFrame frame) {
        intentos++;

        if (figuraCorrecta.equals(respuestaSeleccionada)) {
            JOptionPane.showMessageDialog(frame, "¡Respuesta Correcta!");
            aciertos++;
        } else {
            JOptionPane.showMessageDialog(frame, "Respuesta Incorrecta. Inténtalo de nuevo.");
            fallos++;
        }

        figurasMostradas++;
        JuegoGUI.mostrarSiguienteFigura(frame);
    }

    public static void mostrarEstadisticas(JFrame frame) {
        double porcentajeAciertos = ((double) aciertos / figurasMostradas) * 100;
        double porcentajeFallos = ((double) fallos / figurasMostradas) * 100;

        JOptionPane.showMessageDialog(frame, "Nombre del estudiante: " + nombreEstudiante + "\n" +
                "Cantidad de figuras desplegadas: " + figurasMostradas + "\n" +
                "Cantidad de intentos: " + intentos + "\n" +
                "Cantidad de fallos: " + fallos + "\n" +
                "Cantidad de aciertos: " + aciertos + "\n" +
                "Porcentaje de aciertos: " + porcentajeAciertos + "%\n" +
                "Porcentaje de fallos: " + porcentajeFallos + "%");

        reiniciarJuego();
    }

    public static void reiniciarJuego() {
        figurasMostradas = 0;
        intentos = 0;
        fallos = 0;
        aciertos = 0;
        nombreEstudiante = null;
    }
}




    
    
    
    
    

