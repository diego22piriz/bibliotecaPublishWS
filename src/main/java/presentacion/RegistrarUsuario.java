package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaces.IControlador;

public class RegistrarUsuario extends JPanel {
    
    private IControlador controlador;
    private JPanel panelCentral;
    
    public RegistrarUsuario(IControlador controlador, JPanel panelCentral) {
        this.controlador = controlador;
        this.panelCentral = panelCentral;
        initialize();
    }
    
    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("Registro de Usuario", SwingConstants.CENTER);
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
        
        // Campo Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField txtNombre = new JTextField(20);
        
        // Campo Correo
        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField txtCorreo = new JTextField(20);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 12));
        JPasswordField txtPassword = new JPasswordField(20);
        
        // Campo Tipo de Usuario
        JLabel lblTipo = new JLabel("Tipo de Usuario:");
        lblTipo.setFont(new Font("Arial", Font.BOLD, 12));
        String[] opciones = {"Lector", "Bibliotecario"};
        JComboBox<String> cmbTipo = new JComboBox<>(opciones);
        
        // Agregar campos básicos
        camposBasicos.add(lblNombre);
        camposBasicos.add(txtNombre);
        camposBasicos.add(lblCorreo);
        camposBasicos.add(txtCorreo);
        camposBasicos.add(lblPassword);
        camposBasicos.add(txtPassword);
        camposBasicos.add(lblTipo);
        camposBasicos.add(cmbTipo);
        
        // Panel para campos específicos de Lector
        JPanel camposLector = new JPanel();
        camposLector.setLayout(new GridLayout(0, 2, 10, 10));
        camposLector.setBackground(Color.WHITE);
        
        // Campo Dirección (para Lector)
        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField txtDireccion = new JTextField(20);
        
        // Campo Fecha
        JLabel lblFecha = new JLabel("Fecha de Registro:");
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
        
        // Campo Activo (para Lector)
        JLabel lblActivo = new JLabel("¿Activo?");
        lblActivo.setFont(new Font("Arial", Font.BOLD, 12));
        JCheckBox chkActivo = new JCheckBox("Sí");
        
        // Campo Zona (para Lector)
        JLabel lblZona = new JLabel("Zona:");
        lblZona.setFont(new Font("Arial", Font.BOLD, 12));
        String[] zonas = {"BIBLIOTECA_CENTRAL", "SUCURSAL_ESTE", "SUCURSAL_OESTE", "BIBLIOTECA_INFANTIL", "ARCHIVO_GENERAL"};
        JComboBox<String> cmbZona = new JComboBox<>(zonas);
        
        // Agregar campos específicos de Lector
        camposLector.add(lblDireccion);
        camposLector.add(txtDireccion);
        camposLector.add(lblFecha);
        camposLector.add(fechaPanel);
        camposLector.add(lblActivo);
        camposLector.add(chkActivo);
        camposLector.add(lblZona);
        camposLector.add(cmbZona);
        
        // Configurar listener para cambiar campos según tipo
        cmbTipo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tipoSeleccionado = (String) cmbTipo.getSelectedItem();
                if ("Lector".equals(tipoSeleccionado)) {
                    camposLector.setVisible(true);
                } else {
                    camposLector.setVisible(false);
                }
                revalidate();
                repaint();
            }
        });
        
        // Configurar estado inicial
        camposLector.setVisible(true);
        
        // Agregar componentes al formPanel
        formPanel.add(camposBasicos);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(camposLector);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        // Botón para registrar usuario
        JButton btnRegistrar = createActionButton("Registrar Usuario", new Color(46, 204, 113));
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarUsuario(txtNombre.getText(), txtCorreo.getText(), new String(txtPassword.getPassword()),
                               (String)cmbTipo.getSelectedItem(), txtDireccion.getText(),
                               txtDia.getText(), txtMes.getText(), txtAnio.getText(),
                               chkActivo.isSelected(), (String)cmbZona.getSelectedItem());
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
    
    // Método para manejar el registro de usuario
    private void registrarUsuario(String nombre, String correo, String password, String tipo, String direccion, 
                                String diaStr, String mesStr, String anioStr, boolean activo, String zona) {
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (correo == null || correo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El correo es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La contraseña es obligatoria", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if ("Lector".equals(tipo)) {
                if (direccion == null || direccion.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "La dirección es obligatoria para lectores", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int dia = Integer.parseInt(diaStr);
                int mes = Integer.parseInt(mesStr);
                int anio = Integer.parseInt(anioStr);

                datatypes.DtFecha fecha = new datatypes.DtFecha(dia, mes, anio);
                datatypes.RedBiblioteca zonaEnum = datatypes.RedBiblioteca.valueOf(zona);
                datatypes.DtLector dtLector = new datatypes.DtLector(nombre, correo, password, direccion, fecha, activo, zonaEnum);
                controlador.agregarLector(dtLector);
            } else if ("Bibliotecario".equals(tipo)) {
                // numeroEmpleado se genera automáticamente en la BD
                datatypes.DtBibliotecario dtBibliotecario = new datatypes.DtBibliotecario(nombre, correo, password);
                controlador.agregarBibliotecario(dtBibliotecario);
            }

            JOptionPane.showMessageDialog(this,
                "Usuario registrado exitosamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Error: Los valores de fecha deben ser números válidos",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al registrar usuario: " + ex.getMessage(),
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
