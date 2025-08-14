package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame {
    
    private JPanel contentPane;
    private JButton btnInicio;
    private JButton btnRegistros;
    private JButton btnConsultas;
    private JPanel panelCentral;
    
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
        JButton btnRegistrarLibro = createActionButton("Registrar Libro", new Color(52, 152, 219));
        JButton btnRegistrarPrestamo = createActionButton("Registrar Préstamo", new Color(155, 89, 182));
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
}
