package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaces.IControlador;

public class PanelRegistros extends JPanel {
    
    private IControlador controlador;
    private JPanel panelCentral;
    
    public PanelRegistros(IControlador controlador, JPanel panelCentral) {
        this.controlador = controlador;
        this.panelCentral = panelCentral;
        initialize();
    }
    
    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("Registros", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(tituloLabel, BorderLayout.NORTH);
        
        // Panel de botones de registro
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new GridLayout(2, 2, 20, 20));
        botonesPanel.setBackground(Color.WHITE);
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Botones de registro
        JButton btnRegistrarUsuario = createActionButton("Registrar Usuario", new Color(46, 204, 113));
        btnRegistrarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarRegistrarUsuario();
            }
        });
        
        JButton btnRegistrarLibro = createActionButton("Registrar Material", new Color(52, 152, 219));
        btnRegistrarLibro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarRegistrarMaterial();
            }
        });
        
        JButton btnRegistrarPrestamo = createActionButton("Registrar Préstamo", new Color(155, 89, 182));
        btnRegistrarPrestamo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarRegistrarPrestamo();
            }
        });
        
        JButton btnRegistrarDevolucion = createActionButton("Registrar Devolución", new Color(231, 76, 60));
        btnRegistrarDevolucion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarRegistrarDevolucion();
            }
        });
        
        botonesPanel.add(btnRegistrarUsuario);
        botonesPanel.add(btnRegistrarLibro);
        botonesPanel.add(btnRegistrarPrestamo);
        botonesPanel.add(btnRegistrarDevolucion);
        
        add(botonesPanel, BorderLayout.CENTER);
    }
    
    private JButton createActionButton(String text, Color backgroundColor) {
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
    
    private void mostrarRegistrarUsuario() {
        panelCentral.removeAll();
        panelCentral.add(new RegistrarUsuario(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarRegistrarMaterial() {
        panelCentral.removeAll();
        panelCentral.add(new RegistrarMaterial(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarRegistrarPrestamo() {
        panelCentral.removeAll();
        panelCentral.add(new RegistrarPrestamo(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarRegistrarDevolucion() {
        panelCentral.removeAll();
        panelCentral.add(new RegistrarDevolucion());
        panelCentral.revalidate();
        panelCentral.repaint();
    }
}
