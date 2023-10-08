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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JuegoGUI {

    public static void crearVentana() {
        JFrame frame = new JFrame("Juego De Aprendizaje Preescolar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 150);

        JPanel panel = new JPanel();
        panel.setBackground(Color.RED);
        frame.add(panel);

        JLabel label = new JLabel("Ingrese El Nombre Del Estudiante:");
        panel.add(label);

        JTextField nombreField = new JTextField(15);
        panel.add(nombreField);

        JButton iniciarButton = new JButton("Iniciar Juego");
        panel.add(iniciarButton);

        JButton reglasButton = new JButton("Reglas del juego");
        panel.add(reglasButton);

        JTextArea reglasTexto = new JTextArea("Debes seleccionar la opción que corresponda a la figura geométrica que se muestra en ese momento.");
        reglasTexto.setFont(new Font("Arial", Font.PLAIN, 12));
        reglasTexto.setWrapStyleWord(true);
        reglasTexto.setLineWrap(true);
        reglasTexto.setOpaque(false);
        reglasTexto.setEditable(false);
        reglasTexto.setVisible(false);

        reglasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!reglasTexto.isVisible()) {
                    reglasTexto.setVisible(true);
                } else {
                    reglasTexto.setVisible(false);
                }
            }
        });

        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreJugador = nombreField.getText();
                if (nombreJugador.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese un nombre.");
                } else {
                    JuegoLogic.nombreEstudiante = nombreJugador;
                    mostrarSiguienteFigura(frame);
                }
            }
        });

        frame.add(reglasTexto, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void mostrarSiguienteFigura(JFrame frame) {
        if (JuegoLogic.figurasMostradas >= JuegoLogic.figurasGeometricas.length) {
            JuegoLogic.mostrarEstadisticas(frame);
            return;
        }

        String figuraActual = JuegoLogic.obtenerFiguraAleatoria();

        JFrame figuraFrame = new JFrame("Selecciona la figura correcta");
        figuraFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        figuraFrame.setSize(1720, 1000);

        JPanel figuraPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibuja la figura actual
                if (figuraActual.equals("Cuadrado")) {
                    g.setColor(Color.RED);
                    g.fillRect(100, 100, 200, 200); // Dibuja un cuadrado
                } else if (figuraActual.equals("Triángulo")) {
                    g.setColor(Color.GREEN);
                    int[] xPoints = {200, 100, 300}; // Coordenadas x de los puntos
                    int[] yPoints = {100, 300, 300}; // Coordenadas y de los puntos
                    g.fillPolygon(xPoints, yPoints, 3); // Dibuja un triángulo
                } else if (figuraActual.equals("Círculo")) {
                    g.setColor(Color.BLUE);
                    g.fillOval(100, 100, 200, 200); // Dibuja un círculo
                } else if (figuraActual.equals("Rectángulo")) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(100, 100, 300, 200); // Dibuja un rectángulo
                }
                // Agrega lógica para las otras figuras geométricas
            }
        };

        // Establecer el color de fondo del panel
        figuraPanel.setBackground(Color.PINK);

        JPanel botonesPanel = new JPanel(new GridLayout(JuegoLogic.figurasGeometricas.length, 1));

        for (String figura : JuegoLogic.figurasGeometricas) {
            JButton botonRespuesta = new JButton(figura);
            botonRespuesta.setPreferredSize(new Dimension(200, 50));
            botonRespuesta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JuegoLogic.verificarRespuesta(figuraActual, figura, frame);
                    figuraFrame.dispose();
                }
            });
            botonesPanel.add(botonRespuesta);
        }

        // Configura el layout del panel principal (figuraFrame) como BorderLayout
        figuraFrame.setLayout(new BorderLayout());

        // Agrega el panel de la figura al centro
        figuraFrame.add(figuraPanel, BorderLayout.CENTER);

        // Agrega el panel de botonesPanel en la parte inferior
        figuraFrame.add(botonesPanel, BorderLayout.SOUTH);

        figuraFrame.setVisible(true);
    }
}