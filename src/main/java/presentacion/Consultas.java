package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Consultas extends JPanel {
    
    public Consultas() {
        initialize();
    }
    
    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("Consultas", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(tituloLabel, BorderLayout.NORTH);
        
        // Panel de botones de consulta
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new GridLayout(2, 2, 20, 20));
        botonesPanel.setBackground(Color.WHITE);
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Botones de consulta
        JButton btnConsultarUsuarios = createActionButton("Consultar Usuarios", new Color(46, 204, 113));
        JButton btnConsultarLibros = createActionButton("Consultar Libros", new Color(52, 152, 219));
        JButton btnConsultarPrestamos = createActionButton("Consultar Préstamos", new Color(155, 89, 182));
        JButton btnReportes = createActionButton("Generar Reportes", new Color(231, 76, 60));
        
        botonesPanel.add(btnConsultarUsuarios);
        botonesPanel.add(btnConsultarLibros);
        botonesPanel.add(btnConsultarPrestamos);
        botonesPanel.add(btnReportes);
        
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
}
