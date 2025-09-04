package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaces.IControlador;
import datatypes.DtPrestamo;
import datatypes.DtFecha;
import datatypes.EstadoPrestamo;
import excepciones.PrestamoDuplicadoException;
import java.util.List;

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
        JComboBox<String> cmbCorreoLector = new JComboBox<>();
        cargarCorreosLectores(cmbCorreoLector);
        
        // Campo Correo del Bibliotecario
        JLabel lblCorreoBiblio = new JLabel("Correo del Bibliotecario:");
        lblCorreoBiblio.setFont(new Font("Arial", Font.BOLD, 12));
        JComboBox<String> cmbCorreoBiblio = new JComboBox<>();
        cargarCorreosBibliotecarios(cmbCorreoBiblio);
        
        // Campo ID del Material
        JLabel lblMaterialId = new JLabel("ID del Material:");
        lblMaterialId.setFont(new Font("Arial", Font.BOLD, 12));
        JComboBox<String> cmbMaterialId = new JComboBox<>();
        cargarIdsMateriales(cmbMaterialId);
        
        // Campo Fecha de Solicitud
        JLabel lblFechaSolicitud = new JLabel("Fecha de Solicitud:");
        lblFechaSolicitud.setFont(new Font("Arial", Font.BOLD, 12));
        JPanel fechaSolicitudPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fechaSolicitudPanel.setBackground(Color.WHITE);
        JTextField txtDiaSolicitud = new JTextField(3);
        JTextField txtMesSolicitud = new JTextField(3);
        JTextField txtAnioSolicitud = new JTextField(5);
        fechaSolicitudPanel.add(new JLabel("Día:"));
        fechaSolicitudPanel.add(txtDiaSolicitud);
        fechaSolicitudPanel.add(new JLabel("Mes:"));
        fechaSolicitudPanel.add(txtMesSolicitud);
        fechaSolicitudPanel.add(new JLabel("Año:"));
        fechaSolicitudPanel.add(txtAnioSolicitud);
        
        // Campo Fecha de Devolución
        JLabel lblFechaDevolucion = new JLabel("Fecha de Devolución:");
        lblFechaDevolucion.setFont(new Font("Arial", Font.BOLD, 12));
        JPanel fechaDevolucionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fechaDevolucionPanel.setBackground(Color.WHITE);
        JTextField txtDiaDevolucion = new JTextField(3);
        JTextField txtMesDevolucion = new JTextField(3);
        JTextField txtAnioDevolucion = new JTextField(5);
        fechaDevolucionPanel.add(new JLabel("Día:"));
        fechaDevolucionPanel.add(txtDiaDevolucion);
        fechaDevolucionPanel.add(new JLabel("Mes:"));
        fechaDevolucionPanel.add(txtMesDevolucion);
        fechaDevolucionPanel.add(new JLabel("Año:"));
        fechaDevolucionPanel.add(txtAnioDevolucion);
        
        // Campo Estado
        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setFont(new Font("Arial", Font.BOLD, 12));
        JComboBox<EstadoPrestamo> cmbEstado = new JComboBox<>(EstadoPrestamo.values());
        
        // Agregar campos al panel
        camposPanel.add(lblCorreoLector);
        camposPanel.add(cmbCorreoLector);
        camposPanel.add(lblCorreoBiblio);
        camposPanel.add(cmbCorreoBiblio);
        camposPanel.add(lblMaterialId);
        camposPanel.add(cmbMaterialId);
        camposPanel.add(lblFechaSolicitud);
        camposPanel.add(fechaSolicitudPanel);
        camposPanel.add(lblFechaDevolucion);
        camposPanel.add(fechaDevolucionPanel);
        camposPanel.add(lblEstado);
        camposPanel.add(cmbEstado);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        // Botón para registrar préstamo
        JButton btnRegistrar = createActionButton("Registrar Préstamo", new Color(155, 89, 182));
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String correoLector = (String)cmbCorreoLector.getSelectedItem();
                String correoBibliotecario = (String)cmbCorreoBiblio.getSelectedItem();
                String idMaterial = (String)cmbMaterialId.getSelectedItem();
                
                if (correoLector == null || correoBibliotecario == null || idMaterial == null) {
                    JOptionPane.showMessageDialog(RegistrarPrestamo.this, 
                        "Debe seleccionar un lector, bibliotecario y material", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                registrarPrestamo(correoLector, correoBibliotecario, 
                    idMaterial,
                    txtDiaSolicitud.getText(), txtMesSolicitud.getText(), txtAnioSolicitud.getText(),
                    txtDiaDevolucion.getText(), txtMesDevolucion.getText(), txtAnioDevolucion.getText(),
                    (EstadoPrestamo)cmbEstado.getSelectedItem());
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
    private void registrarPrestamo(String correoUsuario, String correoBibliotecario, String materialIdStr, 
                                  String diaSolicitudStr, String mesSolicitudStr, String anioSolicitudStr,
                                  String diaDevolucionStr, String mesDevolucionStr, String anioDevolucionStr,
                                  EstadoPrestamo estado) {
        // Validaciones
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
        if (diaSolicitudStr == null || diaSolicitudStr.trim().isEmpty() ||
            mesSolicitudStr == null || mesSolicitudStr.trim().isEmpty() ||
            anioSolicitudStr == null || anioSolicitudStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La fecha de solicitud es obligatoria", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (diaDevolucionStr == null || diaDevolucionStr.trim().isEmpty() ||
            mesDevolucionStr == null || mesDevolucionStr.trim().isEmpty() ||
            anioDevolucionStr == null || anioDevolucionStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La fecha de devolución es obligatoria", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Long materialId = Long.parseLong(materialIdStr);
            
            // Parsear fechas
            int diaSolicitud = Integer.parseInt(diaSolicitudStr);
            int mesSolicitud = Integer.parseInt(mesSolicitudStr);
            int anioSolicitud = Integer.parseInt(anioSolicitudStr);
            DtFecha fechaSolicitud = new DtFecha(diaSolicitud, mesSolicitud, anioSolicitud);
            
            int diaDevolucion = Integer.parseInt(diaDevolucionStr);
            int mesDevolucion = Integer.parseInt(mesDevolucionStr);
            int anioDevolucion = Integer.parseInt(anioDevolucionStr);
            DtFecha fechaDevolucion = new DtFecha(diaDevolucion, mesDevolucion, anioDevolucion);
            
            // Crear DtPrestamo
            DtPrestamo dtPrestamo = new DtPrestamo(correoUsuario, correoBibliotecario, materialId, 
                                                  fechaSolicitud, fechaDevolucion, estado);
            
            controlador.agregarPrestamo(dtPrestamo);

            JOptionPane.showMessageDialog(this,
                "Préstamo registrado exitosamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Error: Los valores de fecha y ID de material deben ser números válidos",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        } catch (PrestamoDuplicadoException ex) {
            JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Préstamo Duplicado",
                JOptionPane.WARNING_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error inesperado al registrar préstamo: " + ex.getMessage(),
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
    
    // Métodos para cargar datos en los ComboBox
    private void cargarCorreosLectores(JComboBox<String> comboBox) {
        try {
            List<String> correos = controlador.listarLectores();
            comboBox.removeAllItems();
            
            for (String correo : correos) {
                comboBox.addItem(correo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar lectores: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarCorreosBibliotecarios(JComboBox<String> comboBox) {
        try {
            List<String> correos = controlador.listarBibliotecarios();
            comboBox.removeAllItems();
            
            for (String correo : correos) {
                comboBox.addItem(correo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar bibliotecarios: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarIdsMateriales(JComboBox<String> comboBox) {
        try {
            List<String> ids = controlador.listarMateriales();
            comboBox.removeAllItems();
            
            for (String id : ids) {
                comboBox.addItem(id);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar materiales: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
