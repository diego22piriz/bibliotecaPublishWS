package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaces.Fabrica;
import interfaces.IControlador;

public class Principal extends JFrame {
    
    private JPanel contentPane;
    private JButton btnInicio;
    private JButton btnRegistros;
    private JButton btnConsultas;
    private JPanel panelCentral;
    private IControlador controlador;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal frame = new Principal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public Principal() {
        initialize();
    }
    
    private void initialize() {
        // Configuración de la ventana principal
        setTitle("Sistema de Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null); // Centrar en pantalla
        
        // Panel principal
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        // Inicializar controlador global
        controlador = Fabrica.getInstancia().getIControlador();

        // Crear barra de menú superior
        JPanel menuBar = createMenuBar();
        contentPane.add(menuBar, BorderLayout.NORTH);
        
        // Panel central para contenido
        panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBackground(new Color(240, 240, 240));
        contentPane.add(panelCentral, BorderLayout.CENTER);
        
        // Mostrar pantalla de inicio por defecto
        mostrarInicio();
    }
    
    private JPanel createMenuBar() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        menuPanel.setBackground(new Color(70, 130, 180)); // Azul acero
        menuPanel.setPreferredSize(new Dimension(800, 60));
        
        // Botón Inicio
        btnInicio = createMenuButton("Inicio", new Color(46, 204, 113));
        btnInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarInicio();
            }
        });
        
        // Botón Registros
        btnRegistros = createMenuButton("Registros", new Color(52, 152, 219));
        btnRegistros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarRegistros();
            }
        });
        
        // Botón Consultas
        btnConsultas = createMenuButton("Consultas", new Color(155, 89, 182));
        btnConsultas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarConsultas();
            }
        });
        
        menuPanel.add(btnInicio);
        menuPanel.add(btnRegistros);
        menuPanel.add(btnConsultas);
        
        return menuPanel;
    }
    
    private JButton createMenuButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
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
    
    private void mostrarInicio() {
        panelCentral.removeAll();
        
        JPanel inicioPanel = new JPanel();
        inicioPanel.setLayout(new BorderLayout());
        inicioPanel.setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("Bienvenido al Sistema de Biblioteca", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));
        inicioPanel.add(tituloLabel, BorderLayout.NORTH);
        
        // Panel central con información
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        
        JLabel infoLabel = new JLabel("<html><div style='text-align: center;'>" +
            "<h3>Sistema de Gestión de Biblioteca</h3>" +
            "<p>Este sistema permite gestionar:</p>" +
            "<ul>" +
            "<li>Registro de usuarios y bibliotecarios</li>" +
            "<li>Gestión de libros y materiales</li>" +
            "<li>Control de préstamos y devoluciones</li>" +
            "<li>Consultas y reportes</li>" +
            "</ul>" +
            "<p>Seleccione una opción del menú superior para comenzar.</p>" +
            "</div></html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        infoPanel.add(infoLabel);
        inicioPanel.add(infoPanel, BorderLayout.CENTER);
        
        panelCentral.add(inicioPanel);
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarRegistros() {
        panelCentral.removeAll();
        
        JPanel registrosPanel = new JPanel();
        registrosPanel.setLayout(new BorderLayout());
        registrosPanel.setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("Registros", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        registrosPanel.add(tituloLabel, BorderLayout.NORTH);
        
        // Panel de botones de registro
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new GridLayout(2, 2, 20, 20));
        botonesPanel.setBackground(Color.WHITE);
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Botones de registro
        JButton btnRegistrarUsuario = createActionButton("Registrar Usuario", new Color(46, 204, 113));
        btnRegistrarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
        
        JButton btnRegistrarLibro = createActionButton("Registrar Material", new Color(52, 152, 219));
        btnRegistrarLibro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarMaterial();
            }
        });
        JButton btnRegistrarPrestamo = createActionButton("Registrar Préstamo", new Color(155, 89, 182));
        btnRegistrarPrestamo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarPrestamo();
            }
        });
        JButton btnRegistrarDevolucion = createActionButton("Registrar Devolución", new Color(231, 76, 60));
        
        botonesPanel.add(btnRegistrarUsuario);
        botonesPanel.add(btnRegistrarLibro);
        botonesPanel.add(btnRegistrarPrestamo);
        botonesPanel.add(btnRegistrarDevolucion);
        
        registrosPanel.add(botonesPanel, BorderLayout.CENTER);
        
        panelCentral.add(registrosPanel);
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarConsultas() {
        panelCentral.removeAll();
        
        JPanel consultasPanel = new JPanel();
        consultasPanel.setLayout(new BorderLayout());
        consultasPanel.setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("Consultas", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        consultasPanel.add(tituloLabel, BorderLayout.NORTH);
        
        // Panel de botones de consulta
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new GridLayout(2, 2, 20, 20));
        botonesPanel.setBackground(Color.WHITE);
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Botones de consulta
        JButton btnConsultarUsuarios = createActionButton("Consultar Usuarios", new Color(46, 204, 113));
        JButton btnConsultarLibros = createActionButton("Consultar Libros", new Color(52, 152, 219));
        JButton btnConsultarPrestamos = createActionButton("Consultar Préstamos", new Color(155, 89, 182));
        JButton btnReportes = createActionButton("Generar Reportes", new Color(231, 76, 60));
        
        botonesPanel.add(btnConsultarUsuarios);
        botonesPanel.add(btnConsultarLibros);
        botonesPanel.add(btnConsultarPrestamos);
        botonesPanel.add(btnReportes);
        
        consultasPanel.add(botonesPanel, BorderLayout.CENTER);
        
        panelCentral.add(consultasPanel);
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private JButton createActionButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 50));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
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
    private void registrarUsuario() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del usuario:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            String correo = JOptionPane.showInputDialog(this, "Ingrese el correo del usuario:");
            if (correo != null && !correo.trim().isEmpty()) {
                String[] opciones = {"Lector", "Bibliotecario"};
                String tipo = (String) JOptionPane.showInputDialog(this,
                    "Seleccione el tipo de usuario (no existe Usuario genérico):",
                    "Tipo de Usuario",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

                if (tipo == null) return; // canceló

                try {
                    
                    if ("Lector".equals(tipo)) {
                        String direccion = JOptionPane.showInputDialog(this, "Dirección del lector:");
                        if (direccion == null || direccion.trim().isEmpty()) return;

                        String diaStr = JOptionPane.showInputDialog(this, "Fecha registro - día (1 - 31):");
                        String mesStr = JOptionPane.showInputDialog(this, "Fecha registro - mes (1 - 12):");
                        String anioStr = JOptionPane.showInputDialog(this, "Fecha registro - año (e.g., 2024):");
                        int dia = Integer.parseInt(diaStr);
                        int mes = Integer.parseInt(mesStr);
                        int anio = Integer.parseInt(anioStr);

                        int activoOption = JOptionPane.showConfirmDialog(this, "¿Lector activo?", "Estado", JOptionPane.YES_NO_OPTION);
                        boolean activo = (activoOption == JOptionPane.YES_OPTION);

                        String[] zonas = {
                            "BIBLIOTECA_CENTRAL",
                            "SUCURSAL_ESTE",
                            "SUCURSAL_OESTE",
                            "BIBLIOTECA_INFANTIL",
                            "ARCHIVO_GENERAL"
                        };
                        String zonaSel = (String) JOptionPane.showInputDialog(this, "Zona:", "Zona",
                                JOptionPane.QUESTION_MESSAGE, null, zonas, zonas[0]);
                        if (zonaSel == null) return;

                        datatypes.DtFecha fecha = new datatypes.DtFecha(dia, mes, anio);
                        datatypes.RedBiblioteca zona = datatypes.RedBiblioteca.valueOf(zonaSel);
                        datatypes.DtLector dtLector = new datatypes.DtLector(nombre, correo, direccion, fecha, activo, zona);
                        controlador.agregarLector(dtLector);
                    } else if ("Bibliotecario".equals(tipo)) {
                        // numeroEmpleado se genera automáticamente en la BD
                        datatypes.DtBibliotecario dtBibliotecario = new datatypes.DtBibliotecario(nombre, correo);
                        controlador.agregarBibliotecario(dtBibliotecario);
                    }

                    JOptionPane.showMessageDialog(this,
                        "Usuario registrado exitosamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                        "Error al registrar usuario: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }
    }

    // Método para manejar el registro de material
    private void registrarMaterial() {
        String diaStr = JOptionPane.showInputDialog(this, "Fecha ingreso - día (1 - 31):");
        if (diaStr == null || diaStr.trim().isEmpty()) return;
        String mesStr = JOptionPane.showInputDialog(this, "Fecha ingreso - mes (1 - 12):");
        if (mesStr == null || mesStr.trim().isEmpty()) return;
        String anioStr = JOptionPane.showInputDialog(this, "Fecha ingreso - año (e.g., 2024):");
        if (anioStr == null || anioStr.trim().isEmpty()) return;

        int dia = Integer.parseInt(diaStr);
        int mes = Integer.parseInt(mesStr);
        int anio = Integer.parseInt(anioStr);

        String[] opciones = {"Libro", "Articulo"};
        String tipo = (String) JOptionPane.showInputDialog(this,
            "Seleccione el tipo de material (no existe Material genérico):",
            "Tipo de Material",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]);

        if (tipo == null) return; // canceló

        try {
            datatypes.DtFecha fecha = new datatypes.DtFecha(dia, mes, anio);

            if ("Libro".equals(tipo)) {
                String titulo = JOptionPane.showInputDialog(this, "Título del libro:");
                if (titulo == null || titulo.trim().isEmpty()) return;
                
                String cantPaginas = JOptionPane.showInputDialog(this, "Cantidad de páginas:");
                if (cantPaginas == null || cantPaginas.trim().isEmpty()) return;

                datatypes.DtLibro dtLibro = new datatypes.DtLibro(fecha, titulo, cantPaginas);
                controlador.agregarLibro(dtLibro);
            } else if ("Articulo".equals(tipo)) {
                String descripcion = JOptionPane.showInputDialog(this, "Descripción del artículo:");
                if (descripcion == null || descripcion.trim().isEmpty()) return;
                
                String pesoStr = JOptionPane.showInputDialog(this, "Peso en kg:");
                if (pesoStr == null || pesoStr.trim().isEmpty()) return;
                float peso = Float.parseFloat(pesoStr);
                
                String dimensiones = JOptionPane.showInputDialog(this, "Dimensiones:");
                if (dimensiones == null || dimensiones.trim().isEmpty()) return;

                datatypes.DtArticulo dtArticulo = new datatypes.DtArticulo(fecha, descripcion, peso, dimensiones);
                controlador.agregarArticulo(dtArticulo);
            }

            JOptionPane.showMessageDialog(this,
                "Material registrado exitosamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Error: Los valores de fecha deben ser números válidos",
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

    // Método para manejar el registro de préstamo
    private void registrarPrestamo() {
        String correoUsuario = JOptionPane.showInputDialog(this, "Correo del lector:");
        if (correoUsuario == null || correoUsuario.trim().isEmpty()) return;

        String correoBibliotecario = JOptionPane.showInputDialog(this, "Correo del bibliotecario:");
        if (correoBibliotecario == null || correoBibliotecario.trim().isEmpty()) return;

        String materialIdStr = JOptionPane.showInputDialog(this, "ID del material:");
        if (materialIdStr == null || materialIdStr.trim().isEmpty()) return;

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
}