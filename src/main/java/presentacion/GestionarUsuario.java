package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaces.IControlador;

public class GestionarUsuario extends JPanel{
        
    private IControlador controlador;
    private JPanel panelCentral;
    
    public GestionarUsuario(IControlador controlador, JPanel panelCentral) {
        this.controlador = controlador;
        this.panelCentral = panelCentral;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Título
        JLabel tituloLabel = new JLabel("GestionarUsr", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(44, 62, 80));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(tituloLabel, BorderLayout.NORTH);

        // Panel de botones de gestionar Usuario
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new GridLayout(2, 2, 20, 20));
        botonesPanel.setBackground(Color.WHITE);
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Botones de registro
        JButton btnSuspenderUsuario = createActionButton("Suspender Usuario", new Color(46, 204, 113));
        btnSuspenderUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarSuspenderUsuario();
            }
        });
        
        JButton btnCambiarZona = createActionButton("Cambiar zona", new Color(52, 152, 219));
        btnCambiarZona.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarCambiarZona();
            }
        });

        botonesPanel.add(btnSuspenderUsuario);
        botonesPanel.add(btnCambiarZona);

        add(botonesPanel, BorderLayout.CENTER);
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
    
    private void mostrarSuspenderUsuario() {
        panelCentral.removeAll();
        panelCentral.add(new SuspenderUsuario(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    private void mostrarCambiarZona() {
        panelCentral.removeAll();
        panelCentral.add(new CambiarZona(controlador, panelCentral));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
}
