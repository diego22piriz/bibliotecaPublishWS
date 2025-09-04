package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaces.IControlador;

public class RegistrarPrestamo extends JPanel {
    
    private IControlador controlador;
    private JPanel panelCentral;
    
    public RegistrarPrestamo(IControlador controlador, JPanel panelCentral) {
        this.controlador = controlador;
        this.panelCentral = panelCentral;
        initialize();
    }
    
    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("Registro de Préstamo", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(tituloLabel, BorderLayout.NORTH);
        
        // Panel central con formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Campos del formulario
        JPanel camposPanel = new JPanel();
        camposPanel.setLayout(new GridLayout(0, 2, 10, 10));
        camposPanel.setBackground(Color.WHITE);
        
        // Campo Correo del Lector
        JLabel lblCorreoLector = new JLabel("Correo del Lector:");
        lblCorreoLector.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField txtCorreoLector = new JTextField(20);
        
        // Campo Correo del Bibliotecario
        JLabel lblCorreoBiblio = new JLabel("Correo del Bibliotecario:");
        lblCorreoBiblio.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField txtCorreoBiblio = new JTextField(20);
        
        // Campo ID del Material
        JLabel lblMaterialId = new JLabel("ID del Material:");
        lblMaterialId.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField txtMaterialId = new JTextField(10);
        
        // Agregar campos al panel
        camposPanel.add(lblCorreoLector);
        camposPanel.add(txtCorreoLector);
        camposPanel.add(lblCorreoBiblio);
        camposPanel.add(txtCorreoBiblio);
        camposPanel.add(lblMaterialId);
        camposPanel.add(txtMaterialId);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        // Botón para registrar préstamo
        JButton btnRegistrar = createActionButton("Registrar Préstamo", new Color(155, 89, 182));
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarPrestamo(txtCorreoLector.getText(), txtCorreoBiblio.getText(), txtMaterialId.getText());
            }
        });
        
        // Botón para volver
        JButton btnVolver = createActionButton("Volver", new Color(128, 128, 128));
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });
        
        buttonPanel.add(btnRegistrar);
        buttonPanel.add(btnVolver);
        
        formPanel.add(camposPanel);
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
    
    // Método para manejar el registro de préstamo
    private void registrarPrestamo(String correoUsuario, String correoBibliotecario, String materialIdStr) {
        if (correoUsuario == null || correoUsuario.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El correo del lector es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (correoBibliotecario == null || correoBibliotecario.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El correo del bibliotecario es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (materialIdStr == null || materialIdStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El ID del material es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int materialId = Integer.parseInt(materialIdStr);
            controlador.agregarPrestamo(correoUsuario, correoBibliotecario, materialId);

            JOptionPane.showMessageDialog(this,
                "Préstamo registrado exitosamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Error: El ID de material debe ser numérico",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al registrar préstamo: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void volver() {
        panelCentral.removeAll();
        panelCentral.add(new PanelRegistros(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
}
