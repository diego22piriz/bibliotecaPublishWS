package presentacion;

import javax.swing.*;
import java.awt.*;

public class Inicio extends JPanel {
    
    public Inicio() {
        initialize();
    }
    
    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("Bienvenido al Sistema de Biblioteca", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));
        add(tituloLabel, BorderLayout.NORTH);
        
        // Panel central con información
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        
        JLabel infoLabel = new JLabel("<html><div style='text-align: center;'>" +
            "<h3>Sistema de Gestión de Biblioteca</h3>" +
            "<p>Este sistema permite gestionar:</p>" +
            "<ul>" +
            "<li>Registro de usuarios y bibliotecarios</li>" +
            "<li>Gestión de libros y materiales</li>" +
            "<li>Control de préstamos y devoluciones</li>" +
            "<li>Consultas y reportes</li>" +
            "</ul>" +
            "<p>Seleccione una opción del menú superior para comenzar.</p>" +
            "</div></html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        infoPanel.add(infoLabel);
        add(infoPanel, BorderLayout.CENTER);
    }
}
