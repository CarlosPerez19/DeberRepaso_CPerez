package Estudiantes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Buscar_Estudiantes {
    private JButton buscar;
    private JTextField cedula;
    public JPanel mainPanel;

    String url = "jdbc:mysql://localhost:3306/estudiantes";
    String user = "root";
    String password = "";
    String sql = "SELECT * FROM estudiantes WHERE cedula = ?";


    public Buscar_Estudiantes() {
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cedula.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos.");
                    return;
                } else if (cedula.getText().length() !=10 ) {
                    JOptionPane.showMessageDialog(null, "Ingrese una cédula válida.");
                    return;
                }

                try {
                    Connection conexion = DriverManager.getConnection(url, user,password);
                    PreparedStatement sentencia = conexion.prepareStatement(sql);
                    sentencia.setString(1, cedula.getText());
                    ResultSet resultado = sentencia.executeQuery();

                    if (resultado.next()){
                        String nombre = resultado.getString("nombre");
                        String apellido = resultado.getString("apellido");
                        String cedula = resultado.getString("cedula");
                        String  correo = resultado.getString("correo");
                        Double nota_1 = resultado.getDouble("nota_1");
                        Double nota_2 = resultado.getDouble("nota_2");
                        Double promedio = resultado.getDouble("promedio");

                        JOptionPane.showMessageDialog(null, "Estudiante encontrado" +
                                "\n" + "Nombre: " + nombre + "\n" + "Apellido: " + apellido + "\n" + "Cédula: " +
                                "" + cedula + "\n" + "Correo: " + correo + "\n" + "Nota 1: " + nota_1 + "\n" +
                                "Nota 2: " + nota_2 + "\n" + "Promedio: " + promedio);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontraron registros.");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
