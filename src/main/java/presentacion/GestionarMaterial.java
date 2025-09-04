package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaces.IControlador;

public class GestionarMaterial extends JPanel {
    
    private IControlador controlador;
    private JPanel panelCentral;
    
    public GestionarMaterial(IControlador controlador, JPanel panelCentral) {
        this.controlador = controlador;
        this.panelCentral = panelCentral;
        initialize();
    }
    
    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("GestionarMaterial", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 30, 0));
        add(tituloLabel, BorderLayout.NORTH);
        
        // Panel central con botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Botón Registrar Material
        JButton btnRegistrarMaterial = createActionButton("Registrar Material", new Color(46, 204, 113));
        btnRegistrarMaterial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarRegistrarMaterial();
            }
        });
        
        // Botón Listar Materiales
        JButton btnListarMateriales = createActionButton("Listar Materiales", new Color(52, 152, 219));
        btnListarMateriales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarListarMateriales();
            }
        });
        
        // Centrar los botones
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(btnRegistrarMaterial);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(btnListarMateriales);
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
    
    private void mostrarRegistrarMaterial() {
        panelCentral.removeAll();
        panelCentral.add(new RegistrarMaterial(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarListarMateriales() {
        JOptionPane.showMessageDialog(this, "Funcionalidad de listar materiales en desarrollo", 
            "En desarrollo", JOptionPane.INFORMATION_MESSAGE);
    }
}
