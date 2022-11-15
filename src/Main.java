import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    JPanel panel, panelTarjetas, panelDerecha;
    JLabel img;

    public Main(){
        setLayout(new BorderLayout());

        panelDerecha = new JPanel();
        panelDerecha.setLayout(new BorderLayout());
        initJPanel();
        initCambiarTarjetas();
        initPantalla();
    }

    private void initJPanel(){
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 600));
        panel.setBackground(Color.decode("#006d77"));

        img = new JLabel();
        img.setIcon(new ImageIcon("src/img/logo.png"));
        img.setBounds(550,200,250,200);
        panel.add(img);
        add(panel,BorderLayout.WEST);
    }

    public void initCambiarTarjetas(){

        panelTarjetas = new JPanel();
        JButton boton = new JButton("Siguiente");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panelTarjetas.add(boton);
        add(panelTarjetas, BorderLayout.SOUTH);

    }

    /*public void paint(Graphics g){
        GradientPaint gp = new GradientPaint(500, 100, Color.CYAN, 700, 700, Color.PINK);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gp);
        g2d.fillRect(500,100,200,600);
    }*/

    private void initPantalla() {

        setTitle("Formulario"); //Título del JFrame
        setBounds(500,100,900,800); //Dimensiones del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar proceso al cerrar ventana
        setVisible(true); //Mostrar JFrame
        getContentPane().setBackground(Color.decode("#94d2bd"));
    }

    public static void main(String[] args) {
        new Main();
    }
}

