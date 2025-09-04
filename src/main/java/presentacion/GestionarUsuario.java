package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaces.IControlador;

public class GestionarUsuario extends JPanel {
    
    private IControlador controlador;
    private JPanel panelCentral;
    
    public GestionarUsuario(IControlador controlador, JPanel panelCentral) {
        this.controlador = controlador;
        this.panelCentral = panelCentral;
        initialize();
    }
    
    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("Gestionar Usuario", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 30, 0));
        add(tituloLabel, BorderLayout.NORTH);
        
        // Panel central con botones principales
        JPanel mainButtonPanel = new JPanel();
        mainButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        mainButtonPanel.setBackground(Color.WHITE);
        
        // Botón Suspender Usuario
        JButton btnSuspenderUsuario = createActionButton("Suspender Usuario", new Color(46, 204, 113));
        btnSuspenderUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarSuspenderUsuario();
            }
        });
        
        // Botón Cambiar zona
        JButton btnCambiarZona = createActionButton("Cambiar zona", new Color(52, 152, 219));
        btnCambiarZona.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarCambiarZona();
            }
        });
        
        mainButtonPanel.add(btnSuspenderUsuario);
        mainButtonPanel.add(btnCambiarZona);
        
        // Panel para el botón Volver
        JPanel volverPanel = new JPanel();
        volverPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        volverPanel.setBackground(Color.WHITE);
        
        // Botón Volver
        JButton btnVolver = createVolverButton("Volver", new Color(128, 128, 128));
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });
        
        volverPanel.add(btnVolver);
        
        // Panel contenedor principal
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Centrar los botones
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(mainButtonPanel);
        buttonPanel.add(volverPanel);
        buttonPanel.add(Box.createVerticalGlue());
        
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    private JButton createActionButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(300, 80));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        
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
    
    private JButton createVolverButton(String text, Color backgroundColor) {
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
    
    private void mostrarSuspenderUsuario() {
        panelCentral.removeAll();
        panelCentral.add(new SuspenderUsuario(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarCambiarZona() {
        panelCentral.removeAll();
        panelCentral.add(new CambiarZona(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void volver() {
        panelCentral.removeAll();
        panelCentral.add(new Gestionar(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
}