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
    private JButton btnGestiones;
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
        menuPanel.setLayout(new GridLayout(1, 4, 0, 0));
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

        btnGestiones = createMenuButton("Gestiones", new Color(231, 76, 60));
        btnGestiones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarGestiones();
            }
        });
        
        menuPanel.add(btnInicio);
        menuPanel.add(btnRegistros);
        menuPanel.add(btnConsultas);
        menuPanel.add(btnGestiones);
        
        return menuPanel;
    }
    
    private JButton createMenuButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(160, 40));
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
        panelCentral.add(new Inicio());
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarRegistros() {
        panelCentral.removeAll();
        panelCentral.add(new PanelRegistros(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarConsultas() {
        panelCentral.removeAll();
        panelCentral.add(new Consultas());
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarGestiones() {
        panelCentral.removeAll();
        panelCentral.add(new Gestionar(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }


}
