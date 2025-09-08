package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaces.IControlador;

public class GestionarPrestamo extends JPanel {
    
    private IControlador controlador;
    private JPanel panelCentral;
    
    public GestionarPrestamo(IControlador controlador, JPanel panelCentral) {
        this.controlador = controlador;
        this.panelCentral = panelCentral;
        initialize();
    }
    
    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("GestionarPrestamo", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 30, 0));
        add(tituloLabel, BorderLayout.NORTH);
        
        // Panel central con botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Botón Registrar Préstamo
        JButton btnRegistrarPrestamo = createActionButton("Registrar Préstamo", new Color(155, 89, 182));
        btnRegistrarPrestamo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarRegistrarPrestamo();
            }
        });
        
        // Botón Actualizar Préstamo
        JButton btnActualizarPrestamo = createActionButton("Actualizar Préstamo", new Color(46, 204, 113));
        btnActualizarPrestamo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarActualizarPrestamo();
            }
        });
        
        // Botón Listar Préstamos
        JButton btnListarPrestamos = createActionButton("Listar Préstamos", new Color(52, 152, 219));
        btnListarPrestamos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarListarPrestamos();
            }
        });

        // Botón Listar Préstamos Lector
        JButton btnListarPrestamosLector = createActionButton("Listar Préstamos De Un Lector", new Color(52, 152, 219));
        btnListarPrestamosLector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 mostrarListarPrestamosLector();
            }
        });
        
        // Botón Listar Préstamos Bibliotecario
        JButton btnListarPrestamosBibliotecario = createActionButton("Listar Préstamos De Un Bibliotecario", new Color(52, 152, 219));
        btnListarPrestamosBibliotecario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 mostrarListarPrestamosBibliotecario();
            }
        });
        
        // Centrar los botones
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(btnRegistrarPrestamo);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(btnActualizarPrestamo);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(btnListarPrestamosLector);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(btnListarPrestamosBibliotecario);
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
    
    private void mostrarRegistrarPrestamo() {
        panelCentral.removeAll();
        panelCentral.add(new RegistrarPrestamo(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarActualizarPrestamo() {
        panelCentral.removeAll();
        panelCentral.add(new ActualizarPrestamo(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarListarPrestamos() {
        JOptionPane.showMessageDialog(this, "Funcionalidad de listar préstamos en desarrollo", 
            "En desarrollo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarListarPrestamosLector() {
        panelCentral.removeAll();
        panelCentral.add(new ListarPrestamosLector(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void mostrarListarPrestamosBibliotecario() {
        panelCentral.removeAll();
        panelCentral.add(new ListarPrestamosBibliotecario(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
}
