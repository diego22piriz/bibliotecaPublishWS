package presentacion;

import interfaces.IControlador;
import datatypes.DtFecha;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultarMaterialesPorFechas extends JPanel {
    
    private IControlador controlador;
    private JTable tablaMateriales;
    private DefaultTableModel modelMateriales;
    private JTextField txtDiaInicio, txtMesInicio, txtAñoInicio;
    private JTextField txtDiaFin, txtMesFin, txtAñoFin;
    private JButton btnConsultar;
    private JButton btnVolver;
    
    public ConsultarMaterialesPorFechas(IControlador controlador, JPanel panelCentral) {
        this.controlador = controlador;
        inicializarComponentes();
        configurarVentana();
    }
    
    private void inicializarComponentes() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("Consultar Materiales por Rango de Fechas - Trazabilidad del Inventario", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(tituloLabel, BorderLayout.NORTH);
        
        // Panel central
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(Color.WHITE);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Panel de selección de fechas
        JPanel panelFechas = crearPanelFechas();
        panelCentral.add(panelFechas, BorderLayout.NORTH);
        
        // Título de la tabla
        JLabel tituloTabla = new JLabel("Materiales Registrados en el Rango Seleccionado");
        tituloTabla.setFont(new Font("Arial", Font.BOLD, 14));
        tituloTabla.setHorizontalAlignment(SwingConstants.CENTER);
        tituloTabla.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        panelCentral.add(tituloTabla, BorderLayout.CENTER);
        
        // Tabla
        String[] columnas = {"ID", "Tipo", "Nombre", "Fecha Ingreso"};
        modelMateriales = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaMateriales = new JTable(modelMateriales);
        tablaMateriales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaMateriales.getTableHeader().setReorderingAllowed(false);
        
        // Configurar ancho de columnas
        tablaMateriales.getColumnModel().getColumn(0).setPreferredWidth(50);
        tablaMateriales.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablaMateriales.getColumnModel().getColumn(2).setPreferredWidth(300);
        tablaMateriales.getColumnModel().getColumn(3).setPreferredWidth(120);
        
        JScrollPane scrollPane = new JScrollPane(tablaMateriales);
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        
        add(panelCentral, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 15));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        btnConsultar = new JButton("Consultar Materiales");
        btnConsultar.setBackground(new Color(52, 152, 219));
        btnConsultar.setForeground(Color.WHITE);
        btnConsultar.setFont(new Font("Arial", Font.BOLD, 13));
        btnConsultar.setPreferredSize(new Dimension(180, 40));
        btnConsultar.setFocusPainted(false);
        btnConsultar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConsultar.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        
        btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(231, 76, 60));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
        btnVolver.setPreferredSize(new Dimension(120, 40));
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        
        // Efectos hover para los botones
        addHoverEffect(btnConsultar, new Color(52, 152, 219));
        addHoverEffect(btnVolver, new Color(231, 76, 60));
        
        panelBotones.add(btnConsultar);
        panelBotones.add(btnVolver);
        
        add(panelBotones, BorderLayout.SOUTH);
        
        // Action listeners
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarMateriales();
            }
        });
        
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });
    }
    
    private JPanel crearPanelFechas() {
        JPanel panelFechas = new JPanel(new GridBagLayout());
        panelFechas.setBackground(Color.WHITE);
        panelFechas.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            "Seleccionar Rango de Fechas",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            new Color(44, 62, 80)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Fecha de inicio
        gbc.gridx = 0; gbc.gridy = 0;
        panelFechas.add(new JLabel("Fecha Inicio:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        txtDiaInicio = new JTextField("1", 3);
        txtDiaInicio.setHorizontalAlignment(JTextField.CENTER);
        panelFechas.add(txtDiaInicio, gbc);
        
        gbc.gridx = 2; gbc.gridy = 0;
        panelFechas.add(new JLabel("/"), gbc);
        
        gbc.gridx = 3; gbc.gridy = 0;
        txtMesInicio = new JTextField("1", 3);
        txtMesInicio.setHorizontalAlignment(JTextField.CENTER);
        panelFechas.add(txtMesInicio, gbc);
        
        gbc.gridx = 4; gbc.gridy = 0;
        panelFechas.add(new JLabel("/"), gbc);
        
        gbc.gridx = 5; gbc.gridy = 0;
        txtAñoInicio = new JTextField("2024", 5);
        txtAñoInicio.setHorizontalAlignment(JTextField.CENTER);
        panelFechas.add(txtAñoInicio, gbc);
        
        // Fecha de fin
        gbc.gridx = 0; gbc.gridy = 1;
        panelFechas.add(new JLabel("Fecha Fin:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        txtDiaFin = new JTextField("31", 3);
        txtDiaFin.setHorizontalAlignment(JTextField.CENTER);
        panelFechas.add(txtDiaFin, gbc);
        
        gbc.gridx = 2; gbc.gridy = 1;
        panelFechas.add(new JLabel("/"), gbc);
        
        gbc.gridx = 3; gbc.gridy = 1;
        txtMesFin = new JTextField("12", 3);
        txtMesFin.setHorizontalAlignment(JTextField.CENTER);
        panelFechas.add(txtMesFin, gbc);
        
        gbc.gridx = 4; gbc.gridy = 1;
        panelFechas.add(new JLabel("/"), gbc);
        
        gbc.gridx = 5; gbc.gridy = 1;
        txtAñoFin = new JTextField("2024", 5);
        txtAñoFin.setHorizontalAlignment(JTextField.CENTER);
        panelFechas.add(txtAñoFin, gbc);
        
        return panelFechas;
    }
    
    private void consultarMateriales() {
        try {
            // Obtener fechas de los campos de texto
            int diaInicio = Integer.parseInt(txtDiaInicio.getText().trim());
            int mesInicio = Integer.parseInt(txtMesInicio.getText().trim());
            int añoInicio = Integer.parseInt(txtAñoInicio.getText().trim());
            
            int diaFin = Integer.parseInt(txtDiaFin.getText().trim());
            int mesFin = Integer.parseInt(txtMesFin.getText().trim());
            int añoFin = Integer.parseInt(txtAñoFin.getText().trim());
            
            
            // Validar fechas
            if (añoInicio > añoFin || 
                (añoInicio == añoFin && mesInicio > mesFin) ||
                (añoInicio == añoFin && mesInicio == mesFin && diaInicio > diaFin)) {
                JOptionPane.showMessageDialog(this, 
                    "La fecha de inicio debe ser anterior o igual a la fecha de fin.", 
                    "Error de Fechas", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Crear objetos DtFecha
            DtFecha fechaInicio = new DtFecha(diaInicio, mesInicio, añoInicio);
            DtFecha fechaFin = new DtFecha(diaFin, mesFin, añoFin);
            
            // Limpiar tabla
            modelMateriales.setRowCount(0);
            
            // Consultar materiales
            List<String> materiales = controlador.obtenerMaterialesPorRangoFechas(fechaInicio, fechaFin);
            
            if (materiales.isEmpty()) {
                modelMateriales.addRow(new Object[]{"", "", "No se encontraron materiales en el rango de fechas seleccionado", ""});
            } else {
                for (String info : materiales) {
                    String[] partes = info.split(" \\| ");
                    String id = partes[0].replace("ID: ", "");
                    String tipo = partes[1].replace("Tipo: ", "");
                    String nombre = partes[2].replace("Nombre: ", "");
                    String fechaIngreso = partes[3].replace("Fecha Ingreso: ", "");
                    
                    // Mejorar formato del tipo
                    if (tipo.equals("LIBRO")) {
                        tipo = "[LIBRO]";
                    } else if (tipo.equals("ARTÍCULO")) {
                        tipo = "[ARTÍCULO]";
                    }
                    
                    modelMateriales.addRow(new Object[]{id, tipo, nombre, fechaIngreso});
                }
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese solo números en los campos de fecha.", 
                "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al consultar materiales: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addHoverEffect(JButton button, Color originalColor) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(originalColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(originalColor);
            }
        });
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
        // Establecer fechas por defecto (último mes)
        java.util.Calendar cal = java.util.Calendar.getInstance();
        txtAñoFin.setText(String.valueOf(cal.get(java.util.Calendar.YEAR)));
        txtMesFin.setText(String.valueOf(cal.get(java.util.Calendar.MONTH) + 1));
        txtDiaFin.setText(String.valueOf(cal.get(java.util.Calendar.DAY_OF_MONTH)));
        
        // Fecha de inicio: primer día del mes actual
        txtAñoInicio.setText(String.valueOf(cal.get(java.util.Calendar.YEAR)));
        txtMesInicio.setText(String.valueOf(cal.get(java.util.Calendar.MONTH) + 1));
        txtDiaInicio.setText("1");
    }
}
