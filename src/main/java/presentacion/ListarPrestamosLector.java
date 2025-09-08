package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaces.IControlador;
import logica.Prestamo;
import datatypes.EstadoPrestamo;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListarPrestamosLector extends JPanel{
    private IControlador controlador;
    private JPanel panelCentral;
    private JTable tablaResultados;
    private DefaultTableModel tablaModelo;
    private JComboBox<String> cmbCorreoLector;
    
    
    public ListarPrestamosLector(IControlador controlador, JPanel panelCentral) {
        this.controlador = controlador;
        this.panelCentral = panelCentral;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        // Título
        JLabel tituloLabel = new JLabel("Listar Prestamos de lector", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(tituloLabel, BorderLayout.NORTH);
        // Panel central con formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Panel para campos básicos
        JPanel camposBasicos = new JPanel();
        camposBasicos.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        camposBasicos.setBackground(Color.WHITE);

        // Campo Correo (selector)
        JLabel lblCorreo = new JLabel("Correo del Lector:");
        lblCorreo.setFont(new Font("Arial", Font.BOLD, 12));
        cmbCorreoLector = new JComboBox<>();
        cmbCorreoLector.setPreferredSize(new Dimension(250, 25));
        cargarCorreosLectores(cmbCorreoLector);

        camposBasicos.add(lblCorreo);
        camposBasicos.add(cmbCorreoLector);

        formPanel.add(camposBasicos);
        
        // Tabla de resultados
        String[] columnas = new String[] {"Lector", "Bibliotecario", "Material", "Fecha Solicitud", "Fecha Estimada Devol.", "Estado"};
        tablaModelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaResultados = new JTable(tablaModelo);
        JScrollPane scrollPane = new JScrollPane(tablaResultados);
        scrollPane.setPreferredSize(new Dimension(700, 250));
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(scrollPane);

        add(formPanel, BorderLayout.CENTER);

        // Botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(52, 152, 219));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));
        btnBuscar.setPreferredSize(new Dimension(120, 35));
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String correo = (String)cmbCorreoLector.getSelectedItem();
                if (correo == null || correo.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(ListarPrestamosLector.this, "Seleccione un lector.");
                    return;
                }
                cargarPrestamos(correo);
            }
        });

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(149, 165, 166));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 12));
        btnVolver.setPreferredSize(new Dimension(100, 35));
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelCentral.removeAll();
                panelCentral.add(new GestionarPrestamo(controlador, panelCentral));
                panelCentral.revalidate();
                panelCentral.repaint();
            }
        });

        buttonPanel.add(btnBuscar);
        buttonPanel.add(btnVolver);
        add(buttonPanel, BorderLayout.SOUTH);
    }     

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

    private void cargarPrestamos(String correo) {
        // Limpiar tabla
        tablaModelo.setRowCount(0);
        List<Prestamo> prestamos = controlador.listarPrestamosLector(correo);
        for (Prestamo p : prestamos) {
            // Mostrar solo préstamos activos (no devueltos)
            if (p.getEstado() == null || p.getEstado() != EstadoPrestamo.DEVUELTO) {
                String fechaSol = formatearFecha(p.getFechaSolicitud());
                String fechaEst = formatearFecha(p.getFechaEstDev());
                tablaModelo.addRow(new Object[] {
                    p.getLectorCorreo(),
                    p.getBibliotecarioCorreo(),
                    p.getMaterialId(),
                    fechaSol,
                    fechaEst,
                    p.getEstado() != null ? p.getEstado().name() : ""
                });
            }
        }
    }

    private String formatearFecha(datatypes.DtFecha f) {
        if (f == null) return "";
        return String.format("%02d/%02d/%04d", f.getDay(), f.getMonth(), f.getYear());
    }

}
