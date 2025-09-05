package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import interfaces.IControlador;

public class ConsultarMateriales extends JPanel {
    
    private IControlador controlador;
    private JList<String> listaMateriales;
    private DefaultListModel<String> modeloLista;
    private JButton btnActualizar;
    private JButton btnVolver;
    
    public ConsultarMateriales(IControlador controlador, JPanel panelPadre) {
        this.controlador = controlador;
        initialize();
    }
    
    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("Consultar Materiales Registrados", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(tituloLabel, BorderLayout.NORTH);
        
        // Panel central con la lista
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(Color.WHITE);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Modelo de lista
        modeloLista = new DefaultListModel<>();
        listaMateriales = new JList<>(modeloLista);
        listaMateriales.setFont(new Font("Arial", Font.PLAIN, 14));
        listaMateriales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaMateriales.setBackground(new Color(255, 255, 255));
        listaMateriales.setSelectionBackground(new Color(52, 152, 219));
        listaMateriales.setSelectionForeground(Color.WHITE);
        listaMateriales.setFixedCellHeight(40);
        listaMateriales.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        // Scroll pane para la lista
        JScrollPane scrollPane = new JScrollPane(listaMateriales);
        scrollPane.setPreferredSize(new Dimension(750, 450));
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                "Lista de Materiales (Libros y Artículos)",
                0, 0,
                new Font("Arial", Font.BOLD, 14),
                new Color(44, 62, 80)
            ),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        scrollPane.setBackground(Color.WHITE);
        
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 15));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        btnActualizar = new JButton("Actualizar Lista");
        btnActualizar.setBackground(new Color(52, 152, 219));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 13));
        btnActualizar.setPreferredSize(new Dimension(160, 40));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnActualizar.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        
        btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(231, 76, 60));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
        btnVolver.setPreferredSize(new Dimension(120, 40));
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        
        // Efectos hover para los botones
        addHoverEffect(btnActualizar, new Color(52, 152, 219));
        addHoverEffect(btnVolver, new Color(231, 76, 60));
        
        panelBotones.add(btnActualizar);
        panelBotones.add(btnVolver);
        
        panelCentral.add(panelBotones, BorderLayout.SOUTH);
        add(panelCentral, BorderLayout.CENTER);
        
        // Panel de información
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInfo.setBackground(new Color(248, 249, 250));
        panelInfo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        
        JLabel infoLabel = new JLabel("Total de materiales registrados: 0");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 13));
        infoLabel.setForeground(new Color(44, 62, 80));
        panelInfo.add(infoLabel);
        
        add(panelInfo, BorderLayout.SOUTH);
        
        // Event listeners
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarLista();
            }
        });
        
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volverAtras();
            }
        });
        
        // Cargar datos iniciales
        actualizarLista();
    }
    
    private void addHoverEffect(JButton button, Color originalColor) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(originalColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(originalColor);
            }
        });
    }
    
    private void actualizarLista() {
        try {
            modeloLista.clear();
            List<String> materiales = controlador.listarMateriales();
            
            if (materiales.isEmpty()) {
                modeloLista.addElement("No hay materiales registrados en el sistema.");
            } else {
                for (String material : materiales) {
                    // Mejorar la presentación de cada elemento con mejor formato
                    String elementoMejorado = material
                        .replace("Tipo: LIBRO", "[LIBRO]")
                        .replace("Tipo: ARTÍCULO", "[ARTÍCULO]");
                    modeloLista.addElement(elementoMejorado);
                }
            }
            
            // Actualizar contador
            JPanel panelInfo = (JPanel) getComponent(2); // El panel de información está en la posición 2
            JLabel infoLabel = (JLabel) panelInfo.getComponent(0);
            infoLabel.setText("Total de materiales registrados: " + materiales.size());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar los materiales: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void volverAtras() {
        // Limpiar el panel central y volver a la pantalla de consultas
        Container parent = getParent();
        if (parent != null) {
            parent.removeAll();
            parent.add(new Consultas());
            parent.revalidate();
            parent.repaint();
        }
    }
}
