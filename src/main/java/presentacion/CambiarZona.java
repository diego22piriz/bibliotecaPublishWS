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
        JLabel tituloLabel = new JLabel("Cambiar Zona", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(tituloLabel, BorderLayout.NORTH);
        // Panel central con formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Panel para campos básicos
        JPanel camposBasicos = new JPanel();
        camposBasicos.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        camposBasicos.setBackground(Color.WHITE);

        // Campo Correo
        JLabel lblCorreo = new JLabel("Correo del Usuario:");
        lblCorreo.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField txtCorreo = new JTextField(15);
        txtCorreo.setPreferredSize(new Dimension(200, 25));

        // Campo Zona
        JLabel lblZona = new JLabel("Nueva Zona:");
        lblZona.setFont(new Font("Arial", Font.BOLD, 12));
        String[] zonas = {"BIBLIOTECA_CENTRAL", "SUCURSAL_ESTE", "SUCURSAL_OESTE", "BIBLIOTECA_INFANTIL", "ARCHIVO_GENERAL"};
        JComboBox<String> cmbZona = new JComboBox<>(zonas);
        cmbZona.setPreferredSize(new Dimension(200, 25));

        camposBasicos.add(lblCorreo);
        camposBasicos.add(txtCorreo);
        camposBasicos.add(Box.createVerticalStrut(10));
        camposBasicos.add(lblZona);
        camposBasicos.add(cmbZona);

        formPanel.add(camposBasicos);
        add(formPanel, BorderLayout.CENTER);


        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Botones
        JButton btnCambiarZona = new JButton("Cambiar Zona");
        btnCambiarZona.setBackground(new Color(52, 152, 219));
        btnCambiarZona.setForeground(Color.WHITE);
        btnCambiarZona.setFont(new Font("Arial", Font.BOLD, 12));
        btnCambiarZona.setPreferredSize(new Dimension(120, 35));
        btnCambiarZona.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.cambiarZona(txtCorreo.getText(), (String)cmbZona.getSelectedItem());
            }
        });
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(149, 165, 166));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 12));
        btnVolver.setPreferredSize(new Dimension(100, 35));
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelCentral.removeAll();
                panelCentral.add(new GestionarUsuario(controlador, panelCentral));
                panelCentral.revalidate();
                panelCentral.repaint();
            }
        });
        
        buttonPanel.add(btnCambiarZona);
        buttonPanel.add(btnVolver);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
