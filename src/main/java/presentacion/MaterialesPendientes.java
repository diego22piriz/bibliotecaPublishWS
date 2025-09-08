package presentacion;

import interfaces.IControlador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MaterialesPendientes extends JPanel {
    
    private IControlador controlador;
    private JTable tablaPendientes;
    private DefaultTableModel modelPendientes;
    
    public MaterialesPendientes(IControlador controlador, JPanel panelCentral) {
        this.controlador = controlador;
        inicializarComponentes();
        configurarVentana();
    }
    
    private void inicializarComponentes() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("Materiales con 3+ Préstamos Pendientes - Análisis Prioritario", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(tituloLabel, BorderLayout.NORTH);
        
        // Panel central con la tabla
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(Color.WHITE);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Título de la tabla
        JLabel tituloTabla = new JLabel("Materiales con 3 o más Préstamos Pendientes (Ordenados por Prioridad)");
        tituloTabla.setFont(new Font("Arial", Font.BOLD, 14));
        tituloTabla.setHorizontalAlignment(SwingConstants.CENTER);
        panelCentral.add(tituloTabla, BorderLayout.NORTH);
        
        // Tabla
        String[] columnas = {"ID", "Tipo", "Nombre", "Préstamos Pendientes"};
        modelPendientes = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaPendientes = new JTable(modelPendientes);
        tablaPendientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaPendientes.getTableHeader().setReorderingAllowed(false);
        
        // Configurar ancho de columnas
        tablaPendientes.getColumnModel().getColumn(0).setPreferredWidth(50);
        tablaPendientes.getColumnModel().getColumn(1).setPreferredWidth(80);
        tablaPendientes.getColumnModel().getColumn(2).setPreferredWidth(300);
        tablaPendientes.getColumnModel().getColumn(3).setPreferredWidth(120);
        
        JScrollPane scrollPane = new JScrollPane(tablaPendientes);
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        
        add(panelCentral, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnActualizar = new JButton("Actualizar Datos");
        JButton btnVolver = new JButton("Volver");
        
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDatos();
            }
        });
        
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });
        
        panelBotones.add(btnActualizar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);
    }
    
    private void actualizarDatos() {
        // Limpiar tabla
        modelPendientes.setRowCount(0);
        
        try {
            // Cargar datos de préstamos pendientes
            List<String> materialesPendientes = controlador.obtenerMaterialesConPrestamosPendientes();
            
            if (materialesPendientes.isEmpty()) {
                modelPendientes.addRow(new Object[]{"", "", "No hay materiales con 3 o más préstamos pendientes", ""});
            } else {
                for (String info : materialesPendientes) {
                    String[] partes = info.split(" \\| ");
                    String id = partes[0].replace("ID: ", "");
                    String tipo = partes[1].replace("Tipo: ", "");
                    String nombre = partes[2].replace("Nombre: ", "");
                    String cantidad = partes[3].replace("Préstamos Pendientes: ", "");
                    
                    modelPendientes.addRow(new Object[]{id, tipo, nombre, cantidad});
                }
            }
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void volver() {
        Container parent = getParent();
        if (parent != null) {
            parent.removeAll();
            parent.add(new Inicio());
            parent.revalidate();
            parent.repaint();
        }
    }
    
    private void configurarVentana() {
        // Cargar datos iniciales
        actualizarDatos();
    }
}
