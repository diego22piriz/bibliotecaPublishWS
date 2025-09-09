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
        
        // Botón Consultas con menú desplegable
        JButton btnConsultas = createMenuButton("Consultas", new Color(155, 89, 182));
        JPopupMenu popupConsultas = createConsultasPopup();
        
        btnConsultas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (popupConsultas.isVisible()) {
                    popupConsultas.setVisible(false);
                } else {
                    popupConsultas.show(btnConsultas, 0, btnConsultas.getHeight());
                }
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
    
    private JPopupMenu createConsultasPopup() {
        JPopupMenu popup = new JPopupMenu();
        popup.setBackground(Color.WHITE);
        popup.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        
        JMenuItem itemConsultarMateriales = createPopupMenuItem("Consultar Materiales", new Color(155, 89, 182));
        itemConsultarMateriales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarConsultarMateriales();
            }
        });
        
        JMenuItem itemListarPrestamosLector = createPopupMenuItem("Listar Préstamos de un Lector", new Color(155, 89, 182));
        itemListarPrestamosLector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarListarPrestamosLector();
            }
        });
        
        JMenuItem itemListarPrestamosBibliotecario = createPopupMenuItem("Listar Préstamos de un Bibliotecario", new Color(155, 89, 182));
        itemListarPrestamosBibliotecario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarListarPrestamosBibliotecario();
            }
        });
        
        JMenuItem itemAnalizarPrestamos = createPopupMenuItem("Materiales con muchos (+3) Préstamos Pendientes", new Color(155, 89, 182));
        itemAnalizarPrestamos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarAnalizarPrestamos();
            }
        });
        
        JMenuItem itemConsultarPorFechas = createPopupMenuItem("Consultar Materiales por Rango de Fechas", new Color(155, 89, 182));
        itemConsultarPorFechas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarConsultarPorFechas();
            }
        });
        
        popup.add(itemConsultarMateriales);
        popup.add(itemListarPrestamosLector);
        popup.add(itemListarPrestamosBibliotecario);
        popup.add(itemAnalizarPrestamos);
        popup.add(itemConsultarPorFechas);
        
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
        
        popup.add(itemGestionarUsuario);
        popup.add(itemGestionarMaterial);
        popup.add(itemGestionarPrestamo);
        
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
    
    
    private void mostrarConsultarMateriales() {
        panelCentral.removeAll();
        panelCentral.add(new ConsultarMateriales(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarListarPrestamosLector() {
        panelCentral.removeAll();
        panelCentral.add(new ListarPrestamosLector(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarListarPrestamosBibliotecario() {
        panelCentral.removeAll();
        panelCentral.add(new ListarPrestamosBibliotecario(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarAnalizarPrestamos() {
        panelCentral.removeAll();
        panelCentral.add(new MaterialesPendientes(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarConsultarPorFechas() {
        panelCentral.removeAll();
        panelCentral.add(new ConsultarMaterialesPorFechas(controlador, panelCentral));
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
        panelCentral.add(new ActualizarPrestamo(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    

}
