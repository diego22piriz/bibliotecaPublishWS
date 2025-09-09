package presentacion;

import interfaces.IControlador;
import logica.Prestamo;
import logica.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PrestamosPorZonaPanel extends JPanel {
    private IControlador controlador;
    private JComboBox<String> zonaComboBox;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnBuscar;

    public PrestamosPorZonaPanel(IControlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout());

        zonaComboBox = new JComboBox<>();
        List<String> zonas = controlador.obtenerTodasLasZonas();
        for (String zona : zonas) zonaComboBox.addItem(zona);

        btnBuscar = new JButton("Buscar préstamos de zona");

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Zona:"));
        topPanel.add(zonaComboBox);
        topPanel.add(btnBuscar);

        add(topPanel, BorderLayout.NORTH);

        // Define los nombres de las columnas en mayúsculas
        String[] columnas = {
            "LECTOR CORREO", "BIBLIOTECARIO CORREO", "MATERIAL ID",
            "FECHA SOLICITUD", "FECHA ESTIMADA DEVOLUCIÓN", "ESTADO",
            "LECTOR NOMBRE", "MATERIAL TÍTULO", "BIBLIOTECARIO NOMBRE"
        };
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> {
            String zona = (String) zonaComboBox.getSelectedItem();
            List<Prestamo> prestamos = controlador.obtenerPrestamosDeZona(zona);
            modeloTabla.setRowCount(0);
            for (Prestamo p : prestamos) {
                String titulo = "";
                if (p.getMaterial() != null && p.getMaterial() instanceof Libro) {
                    titulo = ((Libro) p.getMaterial()).getTitulo();
                }
                String nombreBibliotecario = "";
                if (p.getBibliotecario() != null) {
                    nombreBibliotecario = p.getBibliotecario().getNombre();
                }
                modeloTabla.addRow(new Object[]{
                    p.getLectorCorreo(),
                    p.getBibliotecarioCorreo(),
                    p.getMaterialId(),
                    p.getFechaSolicitud() != null ? p.getFechaSolicitud().toString() : "",
                    p.getFechaEstDev() != null ? p.getFechaEstDev().toString() : "",
                    p.getEstado() != null ? p.getEstado().name() : "",
                    p.getLector() != null ? p.getLector().getNombre() : "",
                    titulo,
                    nombreBibliotecario
                });
            }
        });
    }
}