import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends JFrame {
    JPanel panelIzquierda, panelAbajo, panelDerecha;
    JPanel primeraT, segundaT, terceraT, cuartaT, quintaT;
    CardLayout tarjetasLayout;
    JLabel img;
    JTextField correo, nombre;
    JLabel errorCorreo;
    JLabel errorContra;
    JLabel nom, cor, con, auxLabel;
    JPasswordField contr;
    JComboBox pais, provincia;
    JEditorPane panelResultado;
    JFileChooser selecArchivo;
    JCheckBox checkArchivo;
    File file;

    public Main() throws IOException {
        setLayout(new BorderLayout());

        initCambiarTarjetas();
        initTarjetas();
        initJPanel();
        initPantalla();
    }

    private void initJPanel() {
        panelIzquierda = new JPanel();
        panelIzquierda.setPreferredSize(new Dimension(200, 600));
        panelIzquierda.setBackground(Color.decode("#006d77"));

        img = new JLabel();
        img.setIcon(new ImageIcon("src/img/logo.png"));
        img.setBounds(550, 200, 250, 200);
        panelIzquierda.add(img);
        add(panelIzquierda, BorderLayout.WEST);
    }

    public void initTarjetas() throws IOException {
        tarjetasLayout = new CardLayout();
        panelDerecha = new JPanel();
        panelDerecha.setLayout(tarjetasLayout);

        primeraT = new JPanel();
        segundaT = new JPanel();
        terceraT = new JPanel();
        cuartaT = new JPanel();
        quintaT = new JPanel();

        setPrimeraT();
        setSegundaT();
        setTerceraT();
        setCuartaT();
        setQuintaT();

        panelDerecha.add(primeraT);
        panelDerecha.add(segundaT);
        panelDerecha.add(terceraT);
        panelDerecha.add(cuartaT);
        panelDerecha.add(quintaT);
        add(panelDerecha, BorderLayout.CENTER);

    }

    public void setPrimeraT() {
        primeraT.setLayout(new BorderLayout());
        JEditorPane je = new JEditorPane();
        je.setContentType("text/html");
        je.setEditable(false);
        je.setText(
                "<font size=10 face=Verdana>" +
                        "<br><p marginwidth=30>Con este programa rellenaras un formulario que posteriomente de permitira enviar todos tus datos a un fichero que se almacenara en tu disco duro.</p>" +
                        "<br><ul>" +
                        "<li>Datos personales</li>" +
                        "<li>Direccion y residencia</li>" +
                        "<li>Comprobacion de los datos introducidos</li>" +
                        "<li>Fin del programa</li>" +
                        "</ul>" +
                        "</font"
        );
        je.setBackground(Color.decode("#94d2bd"));
        primeraT.add(je, BorderLayout.CENTER);
    }

    public void setSegundaT() {
        segundaT.setLayout(new GridBagLayout());

        GridBagConstraints correoE = new GridBagConstraints();
        correoE.gridx = 0;
        correoE.gridy = 3;
        GridBagConstraints correoC = new GridBagConstraints();
        correoC.gridx = 0;
        correoC.gridy = 4;
        correoC.gridwidth = 2;

        GridBagConstraints[] contraints = new GridBagConstraints[6];

        int y = 0;
        int x = 0;

        for (int i = 0; i < contraints.length; i++) {
            contraints[i] = new GridBagConstraints();
            contraints[i].anchor = GridBagConstraints.WEST;
            if (x < 2) {
                contraints[i].gridx = x;
                contraints[i].gridy = y;
            } else {
                y++;
                x = 0;
                contraints[i].gridx = x;
                contraints[i].gridy = y;
            }
            x++;
        }
        nom = new JLabel("Nombre: ");
        nom.setFont(new Font("Arial", Font.PLAIN, 18));
        cor = new JLabel("Correo electrónico: ");
        cor.setFont(new Font("Arial", Font.PLAIN, 18));
        con = new JLabel("Contraseña: ");
        con.setFont(new Font("Arial", Font.PLAIN, 18));
        correo = new JTextField();
        correo.setPreferredSize(new Dimension(350, 40));
        errorCorreo = new JLabel("*** Introduzca un correo valido");
        errorCorreo.setVisible(false);
        nombre = new JTextField();
        nombre.setPreferredSize(new Dimension(200, 40));
        contr = new JPasswordField();
        //TODO TODO TODO
        correo.setText("h@gmail.com");
        contr.setText("Holaholita12?");
        nombre.setText("pepe");
        contr.setPreferredSize(new Dimension(150, 40));
        errorContra = new JLabel("*** Contraseña entre 8-16 caractéres, 1 mayúscula, 1 miúscula y 1 dígito como mínimo. ");
        errorContra.setVisible(false);
        segundaT.add(nom, contraints[0]);
        segundaT.add(nombre, contraints[1]);
        segundaT.add(cor, contraints[2]);
        segundaT.add(correo, contraints[3]);
        segundaT.add(con, contraints[4]);
        segundaT.add(contr, contraints[5]);
        segundaT.add(errorCorreo, correoE);
        segundaT.add(errorContra, correoC);
        segundaT.setBackground(Color.decode("#94d2bd"));
    }

    public void setTerceraT() throws IOException {
        terceraT.setLayout(new GridBagLayout());
        JLabel texto = new JLabel("Selecciona tu pais y provincia/estado");
        texto.setFont(new Font("Verdana", Font.PLAIN, 24));
        GridBagConstraints c1 = new GridBagConstraints();
        GridBagConstraints c2 = new GridBagConstraints();
        GridBagConstraints c3 = new GridBagConstraints();

        c1.gridx = 0;
        c1.gridy = 0;
        c1.anchor = GridBagConstraints.CENTER;
        c2.gridx = 0;
        c2.gridy = 1;
        c2.anchor = GridBagConstraints.CENTER;
        c3.gridx = 0;
        c3.gridy = 2;
        c3.anchor = GridBagConstraints.CENTER;

        pais = new JComboBox();
        pais.setFont(new Font("Verdana", Font.PLAIN, 18));
        pais.addItem("España");
        pais.addItem("EE.UU.");
        pais.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    insertarProvEst((String) pais.getSelectedItem());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        terceraT.add(texto, c1);
        terceraT.add(pais, c2);

        provincia = new JComboBox();
        provincia.setFont(new Font("Verdana", Font.PLAIN, 18));
        insertarProvEst((String) pais.getSelectedItem());
        terceraT.setBackground(Color.decode("#94d2bd"));
        terceraT.add(provincia, c3);
    }

    public void setCuartaT() {
        cuartaT.setLayout(new BorderLayout());
        panelResultado = new JEditorPane();
        panelResultado.setContentType("text/html");
        panelResultado.setBackground(Color.decode("#94d2bd"));
        panelResultado.setText(
                "<h1 align=center>Resultado Formulario</h1><br>" +
                        "<h3>Nombre: " + nombre.getText() + "</h3>" +
                        "<h3>Correo electronico: " + correo.getText() + "</h3>" +
                        "<h3>Contraseña: " + contr.getText() + "</h3>" +
                        "<h3>Pais: " + pais.getSelectedItem().toString() + "</h3>" +
                        "<h3>Provincia/Estado: " + provincia.getSelectedItem().toString() + "</h3>"
        );
        cuartaT.add(panelResultado, BorderLayout.NORTH);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BorderLayout());
        panelInferior.setVisible(false);

        selecArchivo = new JFileChooser();
        selecArchivo.setApproveButtonText("Guardar");
        selecArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    file = selecArchivo.getSelectedFile();
                    String ruta = file.getAbsolutePath();
                    PrintWriter pw = new PrintWriter(file);
                    pw.println("Nombre: " + nombre.getText());
                    pw.println("Correo electronico: " + correo.getText());
                    pw.println("Contraseña: " + contr.getText());
                    pw.println("Pais: " + pais.getSelectedItem().toString());
                    pw.println("Provincia/Estado: " + provincia.getSelectedItem().toString());
                    pw.close();
                    selecArchivo.setVisible(false);
                    if (!(ruta.endsWith(".txt"))) {
                        File temp = new File(ruta + ".txt");
                        file.renameTo(temp);
                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panelInferior.add(selecArchivo);

        checkArchivo = new JCheckBox("¿Quiéres enviar los datos a un fichero?");
        checkArchivo.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                panelInferior.setVisible(true);
            }
        });
        cuartaT.add(checkArchivo, BorderLayout.SOUTH);
        cuartaT.add(panelInferior, BorderLayout.WEST);
        cuartaT.setBackground(Color.decode("#94d2bd"));
    }

    public void setQuintaT(){
        quintaT.setLayout(new BorderLayout());
        JLabel labelGuardado = new JLabel("Se ha guardado correctamente el archivo");
        quintaT.add(labelGuardado,BorderLayout.NORTH);
    }

    //TODO poner bonito la tarjeta 5
    public void auxQuintaT(){
        auxLabel = new JLabel();
        file = selecArchivo.getSelectedFile();
        String ruta = file.getAbsolutePath();
        auxLabel.setText(ruta);
        System.out.println(ruta);
        quintaT.add(auxLabel,BorderLayout.CENTER);
    }

    public void insertarProvEst(String pais) throws IOException {
        provincia.removeAllItems();
        File f = new File("src/textos/espania.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        if (pais.equals("España")) {
            String cadena;
            while ((cadena = br.readLine()) != null) {
                provincia.addItem(cadena);
                cadena = br.readLine();

            }
            br.close();
        } else if (pais.equals("EE.UU.")) {
            String cadena;
            File f2 = new File("src/textos/estadosUnidos.txt");
            FileReader fr2 = new FileReader(f2);
            BufferedReader br2 = new BufferedReader(fr2);
            while ((cadena = br2.readLine()) != null) {
                provincia.addItem(cadena);
                cadena = br2.readLine();
            }
        }
    }

    public boolean comprobarCorreo(String correo) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(correo);

        if (mather.find()) {
            cor.setForeground(Color.BLACK);
            errorCorreo.setVisible(false);
            return true;
        } else {
            cor.setForeground(Color.RED);
            errorCorreo.setVisible(true);
            return false;
        }
    }

    public boolean comprobarContrasenia(String contrasenia) {
        final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&?*+=]).{8,16}$";
        final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
        if (PASSWORD_PATTERN.matcher(contrasenia).matches()) {
            con.setForeground(Color.BLACK);
            errorContra.setVisible(false);
            return true;
        } else {
            con.setForeground(Color.RED);
            errorContra.setVisible(true);
            return false;
        }
    }

    public void initCambiarTarjetas() {

        panelAbajo = new JPanel();
        panelAbajo.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        JButton sig = new JButton("Siguiente");
        JButton atras = new JButton("Atrás");
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelDerecha.getComponent(0).isShowing())
                    tarjetasLayout.previous(panelDerecha);
            }
        });
        sig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panelDerecha.getComponent(1).isShowing()) {
                    if (!comprobarCorreo(correo.getText()))
                        return;
                    if (!comprobarContrasenia(contr.getText()))
                        return;
                }
                if (!panelDerecha.getComponent(4).isShowing())
                    tarjetasLayout.next(panelDerecha);
                if (panelDerecha.getComponent(4).isShowing())
                    auxQuintaT();
            }
        });
        panelAbajo.add(atras, BorderLayout.EAST);
        panelAbajo.add(sig, BorderLayout.EAST);
        add(panelAbajo, BorderLayout.SOUTH);

    }

    //TODO Degradado en la parte de la izquierda
    /*public void paint(Graphics g){
        GradientPaint gp = new GradientPaint(500, 100, Color.CYAN, 700, 700, Color.PINK);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gp);
        g2d.fillRect(500,100,200,600);
    }*/

    private void initPantalla() {

        setTitle("Formulario"); //Título del JFrame
        setBounds(500, 100, 900, 800); //Dimensiones del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar proceso al cerrar ventana
        setVisible(true); //Mostrar JFrame
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }
}

