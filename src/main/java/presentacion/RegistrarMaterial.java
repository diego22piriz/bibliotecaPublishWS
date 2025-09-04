package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaces.IControlador;

public class RegistrarMaterial extends JPanel {
    
    private IControlador controlador;
    private JPanel panelCentral;
    
    public RegistrarMaterial(IControlador controlador, JPanel panelCentral) {
        this.controlador = controlador;
        this.panelCentral = panelCentral;
        initialize();
    }
    
    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("Registro de Material", SwingConstants.CENTER);
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
        camposBasicos.setLayout(new GridLayout(0, 2, 10, 10));
        camposBasicos.setBackground(Color.WHITE);
        
        // Campo Tipo de Material
        JLabel lblTipo = new JLabel("Tipo de Material:");
        lblTipo.setFont(new Font("Arial", Font.BOLD, 12));
        String[] opciones = {"Libro", "Articulo"};
        JComboBox<String> cmbTipo = new JComboBox<>(opciones);
        
        // Campo Fecha
        JLabel lblFecha = new JLabel("Fecha de Ingreso:");
        lblFecha.setFont(new Font("Arial", Font.BOLD, 12));
        JPanel fechaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fechaPanel.setBackground(Color.WHITE);
        JTextField txtDia = new JTextField(3);
        JTextField txtMes = new JTextField(3);
        JTextField txtAnio = new JTextField(5);
        fechaPanel.add(new JLabel("Día:"));
        fechaPanel.add(txtDia);
        fechaPanel.add(new JLabel("Mes:"));
        fechaPanel.add(txtMes);
        fechaPanel.add(new JLabel("Año:"));
        fechaPanel.add(txtAnio);
        
        // Agregar campos básicos
        camposBasicos.add(lblTipo);
        camposBasicos.add(cmbTipo);
        camposBasicos.add(lblFecha);
        camposBasicos.add(fechaPanel);
        
        // Panel para campos específicos de Libro
        JPanel camposLibro = new JPanel();
        camposLibro.setLayout(new GridLayout(0, 2, 10, 10));
        camposLibro.setBackground(Color.WHITE);
        
        // Campo Título (para Libro)
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField txtTitulo = new JTextField(20);
        
        // Campo Cantidad de Páginas (para Libro)
        JLabel lblPaginas = new JLabel("Cantidad de Páginas:");
        lblPaginas.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField txtPaginas = new JTextField(10);
        
        // Agregar campos específicos de Libro
        camposLibro.add(lblTitulo);
        camposLibro.add(txtTitulo);
        camposLibro.add(lblPaginas);
        camposLibro.add(txtPaginas);
        
        // Panel para campos específicos de Artículo
        JPanel camposArticulo = new JPanel();
        camposArticulo.setLayout(new GridLayout(0, 2, 10, 10));
        camposArticulo.setBackground(Color.WHITE);
        
        // Campo Descripción (para Artículo)
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField txtDescripcion = new JTextField(20);
        
        // Campo Peso (para Artículo)
        JLabel lblPeso = new JLabel("Peso (kg):");
        lblPeso.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField txtPeso = new JTextField(10);
        
        // Campo Dimensiones (para Artículo)
        JLabel lblDimensiones = new JLabel("Dimensiones:");
        lblDimensiones.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField txtDimensiones = new JTextField(20);
        
        // Agregar campos específicos de Artículo
        camposArticulo.add(lblDescripcion);
        camposArticulo.add(txtDescripcion);
        camposArticulo.add(lblPeso);
        camposArticulo.add(txtPeso);
        camposArticulo.add(lblDimensiones);
        camposArticulo.add(txtDimensiones);
        
        // Configurar listener para cambiar campos según tipo
        cmbTipo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tipoSeleccionado = (String) cmbTipo.getSelectedItem();
                if ("Libro".equals(tipoSeleccionado)) {
                    camposLibro.setVisible(true);
                    camposArticulo.setVisible(false);
                } else {
                    camposLibro.setVisible(false);
                    camposArticulo.setVisible(true);
                }
                revalidate();
                repaint();
            }
        });
        
        // Configurar estado inicial
        camposLibro.setVisible(true);
        camposArticulo.setVisible(false);
        
        // Agregar componentes al formPanel
        formPanel.add(camposBasicos);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(camposLibro);
        formPanel.add(camposArticulo);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        // Botón para registrar material
        JButton btnRegistrar = createActionButton("Registrar Material", new Color(52, 152, 219));
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarMaterial((String)cmbTipo.getSelectedItem(), txtDia.getText(), txtMes.getText(), txtAnio.getText(),
                               txtTitulo.getText(), txtPaginas.getText(), txtDescripcion.getText(),
                               txtPeso.getText(), txtDimensiones.getText());
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
        
        // camposPanel ya no se usa, se agregaron directamente los componentes
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
    
    // Método para manejar el registro de material
    private void registrarMaterial(String tipo, String diaStr, String mesStr, String anioStr, 
                                 String titulo, String cantPaginas, String descripcion, 
                                 String pesoStr, String dimensiones) {
        if (diaStr == null || diaStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El día es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (mesStr == null || mesStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El mes es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (anioStr == null || anioStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El año es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int dia = Integer.parseInt(diaStr);
            int mes = Integer.parseInt(mesStr);
            int anio = Integer.parseInt(anioStr);
            datatypes.DtFecha fecha = new datatypes.DtFecha(dia, mes, anio);

            if ("Libro".equals(tipo)) {
                if (titulo == null || titulo.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El título es obligatorio para libros", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (cantPaginas == null || cantPaginas.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "La cantidad de páginas es obligatoria para libros", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                datatypes.DtLibro dtLibro = new datatypes.DtLibro(fecha, titulo, cantPaginas);
                controlador.agregarLibro(dtLibro);
            } else if ("Articulo".equals(tipo)) {
                if (descripcion == null || descripcion.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "La descripción es obligatoria para artículos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (pesoStr == null || pesoStr.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El peso es obligatorio para artículos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (dimensiones == null || dimensiones.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Las dimensiones son obligatorias para artículos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                float peso = Float.parseFloat(pesoStr);
                datatypes.DtArticulo dtArticulo = new datatypes.DtArticulo(fecha, descripcion, peso, dimensiones);
                controlador.agregarArticulo(dtArticulo);
            }

            JOptionPane.showMessageDialog(this,
                "Material registrado exitosamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Error: Los valores de fecha y peso deben ser números válidos",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al registrar material: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void volver() {
        panelCentral.removeAll();
        panelCentral.add(new Inicio());
        panelCentral.revalidate();
        panelCentral.repaint();
    }
}
