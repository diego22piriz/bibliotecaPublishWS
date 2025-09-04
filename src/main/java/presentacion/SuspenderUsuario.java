package presentacion;

import javax.swing.*;
import java.awt.*;
import interfaces.IControlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuspenderUsuario extends JPanel {
    private IControlador controlador;
    private JPanel panelCentral;
    
    
    public SuspenderUsuario(IControlador controlador, JPanel panelCentral) {
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

        camposBasicos.add(lblCorreo);
        camposBasicos.add(txtCorreo);
        
        // Agregar componentes al panelCentral
        panelCentral.add(camposBasicos);


        // Panel de botones
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new GridLayout(2, 2, 20, 20));
        botonesPanel.setBackground(Color.WHITE);
        add(botonesPanel, BorderLayout.SOUTH);
        // Botones
        JButton btnSuspenderUsuario = new JButton("Suspender Usuario");
        btnSuspenderUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.suspenderUsuario(txtCorreo.getText());
            }
        });
        botonesPanel.add(btnSuspenderUsuario);
    }

}
