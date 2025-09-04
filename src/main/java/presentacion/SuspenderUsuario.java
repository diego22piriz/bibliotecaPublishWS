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
        JLabel tituloLabel = new JLabel("Suspender Usuario", SwingConstants.CENTER);
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
        JLabel lblCorreo = new JLabel("Correo del Usuario a suspender:");
        lblCorreo.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField txtCorreo = new JTextField(15);
        txtCorreo.setPreferredSize(new Dimension(200, 25));

        camposBasicos.add(lblCorreo);
        camposBasicos.add(txtCorreo);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        // Botón para suspender usuario
        JButton btnSuspender = createActionButton("Suspender Usuario", new Color(231, 76, 60));
        btnSuspender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                suspenderUsuario(txtCorreo.getText());
            }
        });
        
        // Botón para volver
        JButton btnVolver = createActionButton("Volver", new Color(128, 128, 128));
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });
        
        buttonPanel.add(btnSuspender);
        buttonPanel.add(btnVolver);
        
        formPanel.add(camposBasicos);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(buttonPanel);
        add(formPanel, BorderLayout.CENTER);
    }
    
    private JButton createActionButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });
        
        return button;
    }
    
    // Método para manejar la suspensión de usuario
    private void suspenderUsuario(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El correo es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            controlador.suspenderUsuario(correo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al suspender usuario: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void volver() {
        panelCentral.removeAll();
        panelCentral.add(new GestionarUsuario(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }

}
