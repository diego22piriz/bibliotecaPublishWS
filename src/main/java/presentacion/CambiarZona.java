package presentacion;

import javax.swing.*;
import java.awt.*;
import interfaces.IControlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CambiarZona extends JPanel{
    private IControlador controlador;
    private JPanel panelCentral;
    
    
    public CambiarZona(IControlador controlador, JPanel panelCentral) {
        this.controlador = controlador;
        this.panelCentral = panelCentral;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        // Título
        JLabel tituloLabel = new JLabel("Gestión de Usuarios", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(tituloLabel, BorderLayout.NORTH);
        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBackground(Color.WHITE);
        add(panelCentral, BorderLayout.CENTER);
       
        // Panel para campos básicos
        JPanel camposBasicos = new JPanel();
        camposBasicos.setLayout(new GridLayout(0, 2, 10, 10));
        camposBasicos.setBackground(Color.WHITE);


        // Campo Correo
        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField txtCorreo = new JTextField(10);

        // Campo Correo
        JLabel lblZona = new JLabel("nNeva zona:");
        lblZona.setFont(new Font("Arial", Font.BOLD, 12));
        String[] zonas = {"BIBLIOTECA_CENTRAL", "SUCURSAL_ESTE", "SUCURSAL_OESTE", "BIBLIOTECA_INFANTIL", "ARCHIVO_GENERAL"};
        JComboBox<String> cmbZona = new JComboBox<>(zonas);

        camposBasicos.add(lblCorreo);
        camposBasicos.add(txtCorreo);
        camposBasicos.add(cmbZona);



        // Agregar componentes al panelCentral
        panelCentral.add(camposBasicos);


        // Panel de botones
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new GridLayout(2, 2, 20, 20));
        botonesPanel.setBackground(Color.WHITE);
        add(botonesPanel, BorderLayout.SOUTH);
        // Botones
        JButton btnCambiarZona = new JButton("Cambiar zona");
        btnCambiarZona.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.cambiarZona(txtCorreo.getText(), (String)cmbZona.getSelectedItem());
            }
        });
        botonesPanel.add(btnCambiarZona);
    }
}
