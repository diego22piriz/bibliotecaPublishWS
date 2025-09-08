package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaces.Fabrica;
import interfaces.IControlador;

public class Principal extends JFrame {
    
    private JPanel contentPane;
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
        
        // Crear barra de menú superior con botones
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
        menuPanel.setBackground(new Color(250, 250, 250)); // Fondo más sutil
        menuPanel.setPreferredSize(new Dimension(800, 50)); // Altura más compacta
        
        // Botón Inicio
        JButton btnInicio = createMenuButton("Inicio", new Color(46, 204, 113));
        btnInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarInicio();
            }
        });
        
        // Botón Registros con menú desplegable
        JButton btnRegistros = createMenuButton("Registros", new Color(52, 152, 219));
        JPopupMenu popupRegistros = createRegistrosPopup();
        
        btnRegistros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (popupRegistros.isVisible()) {
                    popupRegistros.setVisible(false);
                } else {
                    popupRegistros.show(btnRegistros, 0, btnRegistros.getHeight());
                }
            }
        });
        
        // Botón Consultas
        JButton btnConsultas = createMenuButton("Consultas", new Color(155, 89, 182));
        btnConsultas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarConsultas();
            }
        });
        
        // Botón Gestiones con menú desplegable
        JButton btnGestiones = createMenuButton("Gestiones", new Color(231, 76, 60));
        JPopupMenu popupGestiones = createGestionesPopup();
        
        btnGestiones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (popupGestiones.isVisible()) {
                    popupGestiones.setVisible(false);
                } else {
                    popupGestiones.show(btnGestiones, 0, btnGestiones.getHeight());
                }
            }
        });
        
        menuPanel.add(btnInicio);
        menuPanel.add(btnRegistros);
        menuPanel.add(btnConsultas);
        menuPanel.add(btnGestiones);
        
        return menuPanel;
    }
    
    private JButton createMenuButton(String text, Color backgroundColor) {
        JButton button = new JButton(text); // Texto normal (Title Case)
        button.setPreferredSize(new Dimension(160, 40));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente bold como en la imagen
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto hover más sutil
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });
        
        return button;
    }
    
    private JPopupMenu createRegistrosPopup() {
        JPopupMenu popup = new JPopupMenu();
        popup.setBackground(Color.WHITE);
        popup.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        
        JMenuItem itemRegistrarUsuario = createPopupMenuItem("Registrar Usuario", new Color(52, 152, 219));
        itemRegistrarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarRegistrarUsuario();
            }
        });
        
        JMenuItem itemRegistrarMaterial = createPopupMenuItem("Registrar Material", new Color(52, 152, 219));
        itemRegistrarMaterial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarRegistrarMaterial();
            }
        });
        
        JMenuItem itemRegistrarPrestamo = createPopupMenuItem("Registrar Préstamo", new Color(52, 152, 219));
        itemRegistrarPrestamo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarRegistrarPrestamo();
            }
        });
        
        popup.add(itemRegistrarUsuario);
        popup.add(itemRegistrarMaterial);
        popup.add(itemRegistrarPrestamo);
        
        return popup;
    }
    
    private JPopupMenu createGestionesPopup() {
        JPopupMenu popup = new JPopupMenu();
        popup.setBackground(Color.WHITE);
        popup.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        
        JMenuItem itemGestionarUsuario = createPopupMenuItem("Gestionar Usuario", new Color(231, 76, 60));
        itemGestionarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarGestionarUsuario();
            }
        });
        
        JMenuItem itemGestionarMaterial = createPopupMenuItem("Gestionar Material", new Color(231, 76, 60));
        itemGestionarMaterial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarGestionarMaterial();
            }
        });
        
        JMenuItem itemGestionarPrestamo = createPopupMenuItem("Gestionar Préstamo", new Color(231, 76, 60));
        itemGestionarPrestamo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarGestionarPrestamo();
            }
        });
        
        JMenuItem itemGestionarDevolucion = createPopupMenuItem("Gestionar Devolución", new Color(231, 76, 60));
        itemGestionarDevolucion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarGestionarDevolucion();
            }
        });
        
        JMenuItem itemReporteZona = createPopupMenuItem("Reporte Préstamos por Zona", new Color(155, 89, 182));
        itemReporteZona.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarReportePrestamosZona();
            }
        });

        popup.add(itemGestionarUsuario);
        popup.add(itemGestionarMaterial);
        popup.add(itemGestionarPrestamo);
        popup.add(itemGestionarDevolucion);
        popup.add(itemReporteZona);

        return popup;
    }
    
    private JMenuItem createPopupMenuItem(String text, Color highlightColor) {
        JMenuItem item = new JMenuItem(text);
        item.setBackground(Color.WHITE);
        item.setForeground(new Color(50, 50, 50)); // Texto más oscuro para mejor legibilidad
        item.setFont(new Font("Arial", Font.PLAIN, 13)); // Fuente un poco más grande
        item.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15)); // Más padding para mejor espaciado
        
        // Efecto hover más sutil
        item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                item.setBackground(new Color(245, 245, 245));
                item.setForeground(highlightColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                item.setBackground(Color.WHITE);
                item.setForeground(new Color(50, 50, 50));
            }
        });
        
        return item;
    }
    
    private void mostrarInicio() {
        panelCentral.removeAll();
        panelCentral.add(new Inicio());
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarConsultas() {
        panelCentral.removeAll();
        panelCentral.add(new Consultas());
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarRegistrarUsuario() {
        panelCentral.removeAll();
        panelCentral.add(new RegistrarUsuario(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarRegistrarMaterial() {
        panelCentral.removeAll();
        panelCentral.add(new RegistrarMaterial(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarRegistrarPrestamo() {
        panelCentral.removeAll();
        panelCentral.add(new RegistrarPrestamo(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarGestionarUsuario() {
        panelCentral.removeAll();
        panelCentral.add(new GestionarUsuario(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarGestionarMaterial() {
        panelCentral.removeAll();
        panelCentral.add(new GestionarMaterial(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarGestionarPrestamo() {
        panelCentral.removeAll();
        panelCentral.add(new GestionarPrestamo(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarGestionarDevolucion() {
        panelCentral.removeAll();
        panelCentral.add(new GestionarDevolucion(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void mostrarReportePrestamosZona() {
        panelCentral.removeAll();
        panelCentral.add(new ReportePrestamosZonaPanel());
        panelCentral.revalidate();
        panelCentral.repaint();
    }
}
