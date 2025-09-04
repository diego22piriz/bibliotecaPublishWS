package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaces.IControlador;

public class Gestionar extends JPanel {
    
    private IControlador controlador;
    private JPanel panelCentral;
    
    public Gestionar(IControlador controlador, JPanel panelCentral) {
        this.controlador = controlador;
        this.panelCentral = panelCentral;
        initialize();
    }
    
    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("Gestiones", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(tituloLabel, BorderLayout.NORTH);
        
        // Panel de botones de gestión
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new GridLayout(2, 2, 20, 20));
        botonesPanel.setBackground(Color.WHITE);
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Botón Gestionar Usuario
        JButton btnGestionarUsuario = createGestionButton("Gestionar Usuario", new Color(46, 204, 113));
        btnGestionarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarGestionarUsuario();
            }
        });
        
        // Botón Gestionar Material
        JButton btnGestionarMaterial = createGestionButton("Gestionar Material", new Color(52, 152, 219));
        btnGestionarMaterial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarGestionarMaterial();
            }
        });
        
        // Botón Gestionar Préstamo
        JButton btnGestionarPrestamo = createGestionButton("Gestionar Préstamo", new Color(155, 89, 182));
        btnGestionarPrestamo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarGestionarPrestamo();
            }
        });
        
        // Botón Gestionar Devolución
        JButton btnGestionarDevolucion = createGestionButton("Gestionar Devolución", new Color(231, 76, 60));
        btnGestionarDevolucion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarGestionarDevolucion();
            }
        });
        
        // Agregar botones al panel
        botonesPanel.add(btnGestionarUsuario);
        botonesPanel.add(btnGestionarMaterial);
        botonesPanel.add(btnGestionarPrestamo);
        botonesPanel.add(btnGestionarDevolucion);
        
        add(botonesPanel, BorderLayout.CENTER);
    }
    
    private JButton createGestionButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 50));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
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
    
    private void mostrarGestionarUsuario() {
        panelCentral.removeAll();
        panelCentral.add(new GestionarUsuario(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarGestionarMaterial() {
        panelCentral.removeAll();
        panelCentral.add(new GestionarMaterial(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarGestionarPrestamo() {
        panelCentral.removeAll();
        panelCentral.add(new GestionarPrestamo(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarGestionarDevolucion() {
        panelCentral.removeAll();
        panelCentral.add(new GestionarDevolucion(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
}
