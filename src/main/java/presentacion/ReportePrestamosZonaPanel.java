package presentacion;

import logica.ManejadorPrestamo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReportePrestamosZonaPanel extends JPanel {
    private JComboBox<String> zonaComboBox;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnVerZona, btnVerTodas;

    public ReportePrestamosZonaPanel() {
        setLayout(new BorderLayout());

        zonaComboBox = new JComboBox<>();
        List<String> zonas = ManejadorPrestamo.getInstancia().obtenerTodasLasZonas();
        for (String zona : zonas) zonaComboBox.addItem(zona);

        btnVerZona = new JButton("Ver cantidad en zona");
        btnVerTodas = new JButton("Ver todas las zonas");

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Zona:"));
        topPanel.add(zonaComboBox);
        topPanel.add(btnVerZona);
        topPanel.add(btnVerTodas);

        add(topPanel, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new Object[]{"Zona", "Cantidad Préstamos"}, 0);
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnVerZona.addActionListener(e -> {
            String zona = (String) zonaComboBox.getSelectedItem();
            ManejadorPrestamo.ZonaPrestamosDTO dto = ManejadorPrestamo.getInstancia().obtenerPrestamosPorZona(zona);
            modeloTabla.setRowCount(0);
            modeloTabla.addRow(new Object[]{dto.getZona(), dto.getCantidad()});
        });

        btnVerTodas.addActionListener(e -> {
            List<ManejadorPrestamo.ZonaPrestamosDTO> lista = ManejadorPrestamo.getInstancia().obtenerPrestamosPorZona();
            modeloTabla.setRowCount(0);
            for (ManejadorPrestamo.ZonaPrestamosDTO dto : lista) {
                modeloTabla.addRow(new Object[]{dto.getZona(), dto.getCantidad()});
            }
        });
    }
}