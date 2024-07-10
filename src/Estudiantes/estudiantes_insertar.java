package Estudiantes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class estudiantes_insertar extends Estudiantes{
    public JPanel mainPanel;
    private JTextField nombre;
    private JTextField apellido;
    private JTextField cedula;
    private JTextField nota_1;
    private JTextField nota_2;
    private JButton resgistrarButton;
    private JButton buscarButton;

    String url = "jdbc:mysql://localhost:3306/estudiantes";
    String user = "root";
    String password = "";
    String sql = "INSERT INTO estudiantes (nombre, apellido, cedula, correo, nota_1, nota_2, promedio) VALUES (?, ?, ?, ?, ?, ?, ?)";


    public estudiantes_insertar() {
        resgistrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Estudiantes estudiante = new Estudiantes();

                if (nombre.getText().isEmpty() || apellido.getText().isEmpty() || cedula.getText().isEmpty() || nota_1.getText().isEmpty() || nota_2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos.");
                    return;
                }
                else if (cedula.getText().length() != 10) {
                    JOptionPane.showMessageDialog(null, "Ingrese una cédula válida.");
                    return;
                } else if (Double.parseDouble(nota_1.getText()) < 0 || Double.parseDouble(nota_1.getText()) > 20) {
                    JOptionPane.showMessageDialog(null, "Ingrese una nota válida.");
                    return;
                } else if (Double.parseDouble(nota_2.getText()) < 0 || Double.parseDouble(nota_2.getText()) > 20) {
                    JOptionPane.showMessageDialog(null, "Ingrese una nota válida.");
                    return;
                }

                estudiante.setNombre(nombre.getText());
                estudiante.setApellido(apellido.getText());
                estudiante.setCedula(cedula.getText());
                estudiante.setNota_1(Double.parseDouble(nota_1.getText()));
                estudiante.setNota_2(Double.parseDouble(nota_2.getText()));
                estudiante.generarCorreo();
                estudiante.calcularPromedio();

                try  {
                    Connection conexion = DriverManager.getConnection(url, user, password);
                    PreparedStatement declarar = conexion.prepareStatement(sql);
                    declarar.setString(1, estudiante.getNombre());
                    declarar.setString(2, estudiante.getApellido());
                    declarar.setString(3, estudiante.getCedula());
                    declarar.setString(4, estudiante.getCorreo());
                    declarar.setDouble(5, estudiante.getNota_1());
                    declarar.setDouble(6, estudiante.getNota_2());
                    declarar.setDouble(7, estudiante.getPromedio());
                    declarar.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Estudiante registrado correctamente.");
                    conexion.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar el estudiante." + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame("Busqueda Estudiantes");
                frame.setContentPane(new Buscar_Estudiantes().mainPanel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        });
    }

}
