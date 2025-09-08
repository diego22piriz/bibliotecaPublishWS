package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaces.IControlador;
import datatypes.DtPrestamo;
import datatypes.DtFecha;
import datatypes.EstadoPrestamo;
import logica.Prestamo;
import java.util.List;

public class ActualizarPrestamo extends JPanel {
    
    private IControlador controlador;
    private JPanel panelCentral;
    private JComboBox<String> cmbPrestamos;
    private JTextField txtDiaSolicitud, txtMesSolicitud, txtAnioSolicitud;
    private JTextField txtDiaDevolucion, txtMesDevolucion, txtAnioDevolucion;
    private JComboBox<EstadoPrestamo> cmbEstado;
    private Prestamo prestamoSeleccionado;
    
    public ActualizarPrestamo(IControlador controlador, JPanel panelCentral) {
        this.controlador = controlador;
        this.panelCentral = panelCentral;
        initialize();
    }
    
    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("Actualizar Préstamo", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(tituloLabel, BorderLayout.NORTH);
        
        // Panel central con formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Campo Seleccionar Préstamo
        JPanel seleccionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        seleccionPanel.setBackground(Color.WHITE);
        JLabel lblSeleccionar = new JLabel("Seleccionar Préstamo:");
        lblSeleccionar.setFont(new Font("Arial", Font.BOLD, 12));
        cmbPrestamos = new JComboBox<>();
        cmbPrestamos.setPreferredSize(new Dimension(400, 25));
        cargarPrestamos();
        cmbPrestamos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarDatosPrestamo();
            }
        });
        seleccionPanel.add(lblSeleccionar);
        seleccionPanel.add(cmbPrestamos);
        
        // Panel de campos editables
        JPanel camposPanel = new JPanel();
        camposPanel.setLayout(new GridLayout(0, 2, 10, 10));
        camposPanel.setBackground(Color.WHITE);
        
        // Campo Fecha de Solicitud
        JLabel lblFechaSolicitud = new JLabel("Fecha de Solicitud:");
        lblFechaSolicitud.setFont(new Font("Arial", Font.BOLD, 12));
        JPanel fechaSolicitudPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fechaSolicitudPanel.setBackground(Color.WHITE);
        txtDiaSolicitud = new JTextField(3);
        txtMesSolicitud = new JTextField(3);
        txtAnioSolicitud = new JTextField(5);
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
        txtDiaDevolucion = new JTextField(3);
        txtMesDevolucion = new JTextField(3);
        txtAnioDevolucion = new JTextField(5);
        fechaDevolucionPanel.add(new JLabel("Día:"));
        fechaDevolucionPanel.add(txtDiaDevolucion);
        fechaDevolucionPanel.add(new JLabel("Mes:"));
        fechaDevolucionPanel.add(txtMesDevolucion);
        fechaDevolucionPanel.add(new JLabel("Año:"));
        fechaDevolucionPanel.add(txtAnioDevolucion);
        
        // Campo Estado
        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setFont(new Font("Arial", Font.BOLD, 12));
        cmbEstado = new JComboBox<>(EstadoPrestamo.values());
        
        // Agregar campos al panel
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
        
        // Botón para actualizar préstamo
        JButton btnActualizar = createActionButton("Actualizar Préstamo", new Color(46, 204, 113));
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarPrestamo();
            }
        });
        
        // Botón para volver
        JButton btnVolver = createActionButton("Volver", new Color(128, 128, 128));
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });
        
        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnVolver);
        
        formPanel.add(seleccionPanel);
        formPanel.add(Box.createVerticalStrut(20));
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
    
    private void cargarPrestamos() {
        try {
            List<Prestamo> prestamos = controlador.listarPrestamos();
            cmbPrestamos.removeAllItems();
            
            for (Prestamo prestamo : prestamos) {
                String info = String.format("Lector: %s | Material: %d | Estado: %s",
                    prestamo.getLectorCorreo().trim(),
                    prestamo.getMaterialId(),
                    prestamo.getEstado()
                );
                cmbPrestamos.addItem(info);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar préstamos: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarDatosPrestamo() {
        try {
            String seleccion = (String) cmbPrestamos.getSelectedItem();
            if (seleccion == null) return;
            
            // Extraer información del préstamo seleccionado
            String[] partes = seleccion.split(" \\| ");
            String lectorCorreo = partes[0].substring(8).trim(); // "Lector: " = 8 caracteres, trim() para quitar espacios
            String materialIdStr = partes[1].substring(9).trim(); // "Material: " = 9 caracteres, trim() para quitar espacios
            Long materialId = Long.parseLong(materialIdStr);
            
            // Buscar el préstamo completo
            prestamoSeleccionado = controlador.buscarPrestamo(lectorCorreo, "", materialId);
            if (prestamoSeleccionado == null) {
                // Si no se encuentra con bibliotecario vacío, buscar en la lista completa
                List<Prestamo> prestamos = controlador.listarPrestamos();
                for (Prestamo p : prestamos) {
                    if (p.getLectorCorreo().equals(lectorCorreo) && p.getMaterialId().equals(materialId)) {
                        prestamoSeleccionado = p;
                        break;
                    }
                }
            }
            
            if (prestamoSeleccionado != null) {
                // Cargar datos en los campos
                DtFecha fechaSolicitud = prestamoSeleccionado.getFechaSolicitud();
                txtDiaSolicitud.setText(String.valueOf(fechaSolicitud.getDay()));
                txtMesSolicitud.setText(String.valueOf(fechaSolicitud.getMonth()));
                txtAnioSolicitud.setText(String.valueOf(fechaSolicitud.getYear()));
                
                DtFecha fechaDevolucion = prestamoSeleccionado.getFechaEstDev();
                txtDiaDevolucion.setText(String.valueOf(fechaDevolucion.getDay()));
                txtMesDevolucion.setText(String.valueOf(fechaDevolucion.getMonth()));
                txtAnioDevolucion.setText(String.valueOf(fechaDevolucion.getYear()));
                
                cmbEstado.setSelectedItem(prestamoSeleccionado.getEstado());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos del préstamo: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarPrestamo() {
        if (prestamoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un préstamo", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Validar fechas
            int diaSolicitud = Integer.parseInt(txtDiaSolicitud.getText());
            int mesSolicitud = Integer.parseInt(txtMesSolicitud.getText());
            int anioSolicitud = Integer.parseInt(txtAnioSolicitud.getText());
            DtFecha fechaSolicitud = new DtFecha(diaSolicitud, mesSolicitud, anioSolicitud);
            
            int diaDevolucion = Integer.parseInt(txtDiaDevolucion.getText());
            int mesDevolucion = Integer.parseInt(txtMesDevolucion.getText());
            int anioDevolucion = Integer.parseInt(txtAnioDevolucion.getText());
            DtFecha fechaDevolucion = new DtFecha(diaDevolucion, mesDevolucion, anioDevolucion);
            
            EstadoPrestamo estado = (EstadoPrestamo) cmbEstado.getSelectedItem();
            
            // Crear DtPrestamo con los datos actualizados
            DtPrestamo dtPrestamo = new DtPrestamo(
                prestamoSeleccionado.getLectorCorreo(),
                prestamoSeleccionado.getBibliotecarioCorreo(),
                prestamoSeleccionado.getMaterialId(),
                fechaSolicitud,
                fechaDevolucion,
                estado
            );
            
            // Actualizar el préstamo
            controlador.actualizarPrestamo(dtPrestamo);
            
            // Recargar la lista de préstamos
            cargarPrestamos();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Error: Los valores de fecha deben ser números válidos",
                "Error de Fecha",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al actualizar préstamo: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void volver() {
        panelCentral.removeAll();
        panelCentral.add(new Inicio());
        panelCentral.revalidate();
        panelCentral.repaint();
    }
}
