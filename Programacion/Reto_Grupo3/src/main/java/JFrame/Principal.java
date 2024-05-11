/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package JFrame;

import Clases.Departamentos;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

//Importacion de mi Clases
import Clases.Profesor;
import Clases.Solicitud;
import Clases.Usuario;
import DAOImp.DepartamentoDAOImp;
import DAOImp.UsuarioDAOImp;
import DAOImp.ProfesorDAOImp;
import DAOImp.SolicitudDAOImp;
import Enums.Estado;
import Enums.Perfil;
import Enums.TipoActividad;
import Enums.Estado;
import Ficheros.MetodoFichero;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gus y Joaco
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    //ATRIBUTOS
    UsuarioDAOImp auxUsuarioDAOImp = new UsuarioDAOImp();
    ProfesorDAOImp auxProfesorDAOImp = new ProfesorDAOImp();
    SolicitudDAOImp auxSolicitudDAOImp = new SolicitudDAOImp();
    DepartamentoDAOImp auxdepartamentoDAOImp = new DepartamentoDAOImp();
    Profesor profesor;
    Departamentos departamento;
    Solicitud solicitud;

    //FORMATO DE FECHA Y HORA
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

    //Atributos para las tablas
    DefaultTableModel modeloSolicitudes;
    DefaultTableModel modeloSolicitudesAprobadas;
    DefaultTableModel modeloTodasLasSolicitudes;
    private String[] cabeceraSolicitudes = {"Nombre de la Actividad", "Tipo de Actividad", "Departamento", "Prevista", "Transporte", "Fecha Inicial", "Fecha Fibal",
        "Hora Inicial", "Hora Final", "Alojamiento", "Comentario", "Estado", "Consulta Estado"};
    private String[] cabeceraSolicitudesAprobadas = {"Nombre de la Actividad", "Tipo de Actividad", "Departamento", "Prevista", "Transporte", "Fecha Inicial", "Fecha Fibal",
        "Hora Inicial", "Hora Final", "Alojamiento", "Comentario", "Estado", "Consulta Estado"};
    private String[] cabeceraTodasLasSolicitudes = {"Nombre de la Actividad", "Tipo de Actividad", "Departamento", "Prevista", "Transporte", "Fecha Inicial", "Fecha Fibal",
        "Hora Inicial", "Hora Final", "Alojamiento", "Comentario", "Estado", "Consulta Estado"};

    //METODOS
    //Icono de la ventana
    @Override
    public Image getIconImage() {

        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("logo.jpg"));

        return retValue;
    }

    private void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, "<html><p style = \" font: 16px \">" + mensaje + "</p></html>");
    }

    //METODO QUE ME REGRESA EL USUARIO(PROFESOR) LOGEADO
    private Profesor getProfesor() {
        return profesor;
    }

    private void muestraCabeceraTablaSolicitudesProfesor() {

        modeloSolicitudes = new DefaultTableModel(cabeceraSolicitudes, 0);
        jTableSolicitudes.setModel(modeloSolicitudes);

        modeloSolicitudesAprobadas = new DefaultTableModel(cabeceraSolicitudesAprobadas, 0);
        jTableSolicitudesAprobadas.setModel(modeloSolicitudesAprobadas);

    }

    private void muestraCabeceraTablaTodasLasSolicitudes() {

        modeloTodasLasSolicitudes = new DefaultTableModel(cabeceraTodasLasSolicitudes, 0);
        jTableTodasLasSolicitudes.setModel(modeloTodasLasSolicitudes);

    }

    private void cargarSolicitudesDelProfesor() {

        List<Solicitud> auxS = auxSolicitudDAOImp.listar();

        modeloSolicitudes.setRowCount(0);

        for (Solicitud s : auxS) {

            if (s.getSolicitante() == getProfesor().getId_Prof()) {

                // Creamos un arreglo con los datos extraidos del ArrayList
                Object Fila[] = {s.getNombreActividad(), s.getTipoActividad(), s.getDepartamento(), devuelveSiNo(s.isPrevista()), devuelveSiNo(s.isTransporte()),
                    s.formatoFechaInicial(), s.formatoFechaFinal(), s.formatoHoraInicial(), s.formatoHoraFinal(),
                    devuelveSiNo(s.isAlojamiento()), s.getComentarioAdicional(), s.getEstado(), s.getConsultaEstado()};
                // Agregamos el arreglo creado al modelo de datos del JTable
                modeloSolicitudes.addRow(Fila);

            }

        }

    }

    private void cargarTodasLasSolicitudes() {

        List<Solicitud> auxS = auxSolicitudDAOImp.listar();

        modeloTodasLasSolicitudes.setRowCount(0);

        for (Solicitud s : auxS) {

            // Creamos un arreglo con los datos extraidos del ArrayList
            Object Fila[] = {s.getNombreActividad(), s.getTipoActividad(), s.getDepartamento(), devuelveSiNo(s.isPrevista()), devuelveSiNo(s.isTransporte()),
                s.formatoFechaInicial(), s.formatoFechaFinal(), s.formatoHoraInicial(), s.formatoHoraFinal(),
                devuelveSiNo(s.isAlojamiento()), s.getComentarioAdicional(), s.getEstado(), s.getConsultaEstado()};
            // Agregamos el arreglo creado al modelo de datos del JTable
            modeloTodasLasSolicitudes.addRow(Fila);

        }

    }

    private void comboBoxTipoActividad() {

        //variable para tomar el nombre
        String nombre;
        TipoActividad tipo;
        //se limpia el combo
        jComboBoxTipoActividad.removeAllItems();//limpia el combobox
        //try por si fallara al momento de rellenar
        try {
            //Se recorre con un for la lista de conductores
            for (TipoActividad t : TipoActividad.values()) {

                nombre = t.toString();
                //Se arega un nuevo ítem al combobox
                jComboBoxTipoActividad.addItem(nombre.substring(0, 1) + nombre.substring(1, nombre.length()).toLowerCase());

            }

        } catch (Exception e) { //capta el error y lo muestra
            mensaje("Error al cargar ComboBoxTipoActividad" + e);
        }

    }

    private void comboBoxDepartamento() {

        //variable para tomar el nombre
        String nombre;
        TipoActividad tipo;
        //se limpia el combo
        jComboBoxDepartamento.removeAllItems();//limpia el combobox
        List<Departamentos> auxDepartamentoses = auxdepartamentoDAOImp.listar();
        //try por si fallara al momento de rellenar
        try {
            //Se recorre con un for la lista de conductores

            for (Departamentos auxD : auxDepartamentoses) {

                if (auxD.getId() != 20) {

                    nombre = auxD.getNombre();
                    jComboBoxDepartamento.addItem(nombre);

                }

            }

        } catch (Exception e) { //capta el error y lo muestra
            mensaje("Error al cargar ComboBoxDepartamento" + e);
        }

    }

    private void comboBoxEstado() {

        //variable para tomar el nombre
        String nombre;
        TipoActividad tipo;
        //se limpia el combo
        jComboBoxMSEstado.removeAllItems();//limpia el combobox
        //try por si fallara al momento de rellenar
        try {
            //Se recorre con un for la lista de conductores
            for (Estado e : Estado.values()) {

                nombre = e.toString();
                //Se arega un nuevo ítem al combobox
                jComboBoxMSEstado.addItem(nombre.substring(0, 1) + nombre.substring(1, nombre.length()).toLowerCase());

            }

        } catch (Exception e) { //capta el error y lo muestra
            mensaje("Error al cargar ComboBoxMSEstado" + e);
        }

    }

    private String devuelveSiNo(boolean aux) {
        String siNo = "No";

        if (aux) {
            siNo = "Si";
        }

        return siNo;
    }

    private boolean prevista() {

        boolean prevista = false;

        if (jRadioButtonPreVistaSi.isSelected()) {
            prevista = true;
        } else if (jRadioButtonPreVistaNo.isSelected()) {
            prevista = false;
        }

        return prevista;
    }

    private boolean transporte() {

        boolean transporte = false;

        if (jRadioButtonTransporteSi.isSelected()) {
            transporte = true;
        } else if (jRadioButtonTransporteNo.isSelected()) {
            transporte = false;
        }

        return transporte;
    }

    private boolean alojamiento() {

        boolean alojamiento = false;

        if (jRadioButtonAlojamientoSi.isSelected()) {
            alojamiento = true;
        } else if (jRadioButtonAlojamientoNo.isSelected()) {
            alojamiento = false;
        }

        return alojamiento;
    }

    private void limpiarJtextFieldDeSolicitud() {

        jTextNombreActividad.setText("");
        jComboBoxTipoActividad.setSelectedIndex(0);
        jComboBoxDepartamento.setSelectedIndex(0);
        jRadioButtonPreVistaSi.setSelected(false);
        jRadioButtonPreVistaNo.setSelected(false);
        jRadioButtonTransporteSi.setSelected(false);
        jRadioButtonTransporteNo.setSelected(false);
        jTextFieldFechaInicial.setText("00-00-0000");
        jTextFieldFechaFinal.setText("00-00-0000");
        jTextFieldHoraInicial.setText("00:00");
        jTextFieldHoraFinal.setText("00:00");
        jRadioButtonAlojamientoSi.setSelected(false);
        jRadioButtonAlojamientoNo.setSelected(false);
        jTextFieldComentarioAdicional.setText("");
        jTextFieldCantidadAlumnos.setText("");
        jTextNombreActividad.requestFocus();

    }

    public void mousePressed(MouseEvent Mouse_evt) {

        JTable table = (JTable) Mouse_evt.getSource();
        Point point = (Point) Mouse_evt.getSource();
        int row = table.rowAtPoint(point);

        if (Mouse_evt.getClickCount() == 1) {
            jTextFieldMSNombreActividad.setText(jTableTodasLasSolicitudes.getValueAt(jTableTodasLasSolicitudes.getSelectedRow(), 1).toString());
        }

    }

    //PRINCIPAL
    public Principal() {
        initComponents();
        setTitle("Login");
        setIconImage(getIconImage());
        setLocationRelativeTo(null);
        setResizable(false);
        setExtendedState(this.MAXIMIZED_HORIZ);
        jPanelVentanaProfesor.setVisible(false);
        jPanelVentanaSuperUsuarioAdministrador.setVisible(false);
        jPanelSolicitud.setVisible(false);
        jPanelModificarSolicitud.setVisible(false);
        jTableTodasLasSolicitudes.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent Mouse_evt) {

                JTable table = (JTable) Mouse_evt.getSource();
                Point point = (Point) Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);

                if (Mouse_evt.getClickCount() == 1) {
                    jTextFieldMSNombreActividad.setText(jTableTodasLasSolicitudes.getValueAt(jTableTodasLasSolicitudes.getSelectedRow(), 0).toString());
                }

            }

        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelLogin = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jlLabelImagen = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        jTextCorreo = new javax.swing.JTextField();
        jLabelPassword = new javax.swing.JLabel();
        jPasswordContraseña = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jButtonIngresar = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jPanelVentanaProfesor = new javax.swing.JPanel();
        jLabelSolicitud = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSolicitudes = new javax.swing.JTable();
        jLabelSolicitudesAprobadas = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSolicitudesAprobadas = new javax.swing.JTable();
        jButtonNuevaSolicitud = new javax.swing.JButton();
        jButtonSalirVentanaProfesor = new javax.swing.JButton();
        jPanelVentanaSuperUsuarioAdministrador = new javax.swing.JPanel();
        jLabelTituloVentanaSuperUsuarioAdministrador = new javax.swing.JLabel();
        jButtonActualizarCSVprofesor = new javax.swing.JButton();
        jButtonActualizarJefesDepartamentos = new javax.swing.JButton();
        jButtonCerrarVentanaSuperAdaministrador = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableTodasLasSolicitudes = new javax.swing.JTable();
        jPanelModificarSolicitud = new javax.swing.JPanel();
        jLabelCerrarModificarionSolicitud = new javax.swing.JLabel();
        jButtonActualizarModificarSolicitud = new javax.swing.JButton();
        jLabelMSNombreActividad = new javax.swing.JLabel();
        jTextFieldMSNombreActividad = new javax.swing.JTextField();
        jLabelMSEstado = new javax.swing.JLabel();
        jComboBoxMSEstado = new javax.swing.JComboBox<>();
        jLabelMSComentarioEstado = new javax.swing.JLabel();
        jTextFieldMSComentarioEstado = new javax.swing.JTextField();
        jPanelSolicitud = new javax.swing.JPanel();
        jLabelNuevaSolicitud = new javax.swing.JLabel();
        jLabelNombreActividad = new javax.swing.JLabel();
        jTextNombreActividad = new javax.swing.JTextField();
        jLabelTipoActividad = new javax.swing.JLabel();
        jComboBoxTipoActividad = new javax.swing.JComboBox<>();
        jLabelDepartamento = new javax.swing.JLabel();
        jComboBoxDepartamento = new javax.swing.JComboBox<>();
        jLabelPreVista = new javax.swing.JLabel();
        jRadioButtonPreVistaSi = new javax.swing.JRadioButton();
        jRadioButtonPreVistaNo = new javax.swing.JRadioButton();
        jLabelTransporte = new javax.swing.JLabel();
        jRadioButtonTransporteSi = new javax.swing.JRadioButton();
        jRadioButtonTransporteNo = new javax.swing.JRadioButton();
        jLabelFechaInicial = new javax.swing.JLabel();
        jTextFieldFechaInicial = new javax.swing.JTextField();
        jLabelFechaFinal = new javax.swing.JLabel();
        jTextFieldFechaFinal = new javax.swing.JTextField();
        jLabelHoraInicial = new javax.swing.JLabel();
        jTextFieldHoraInicial = new javax.swing.JTextField();
        jLabelHoraFinal = new javax.swing.JLabel();
        jTextFieldHoraFinal = new javax.swing.JTextField();
        jLabelAlojamiento = new javax.swing.JLabel();
        jRadioButtonAlojamientoSi = new javax.swing.JRadioButton();
        jRadioButtonAlojamientoNo = new javax.swing.JRadioButton();
        jLabelComentarioAdicional = new javax.swing.JLabel();
        jTextFieldComentarioAdicional = new javax.swing.JTextField();
        jLabelCantidadAlumnos = new javax.swing.JLabel();
        jTextFieldCantidadAlumnos = new javax.swing.JTextField();
        jButtonCrearSolicitud = new javax.swing.JButton();
        jButtonCerrarSolicitud = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelLogin.setDoubleBuffered(false);

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabelTitulo.setText("Bienvenido - RET MH");

        jlLabelImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N

        jLabelUsuario.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelUsuario.setText("Correo : ");
        jLabelUsuario.setToolTipText("");

        jTextCorreo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextCorreo.setText("nombre.apellido");
        jTextCorreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextCorreoMouseClicked(evt);
            }
        });
        jTextCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextCorreoKeyTyped(evt);
            }
        });

        jLabelPassword.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelPassword.setText("Contraseña :");

        jPasswordContraseña.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jPasswordContraseña.setText("jPasswordField1");
        jPasswordContraseña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordContraseñaMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("@educantabria.es");

        jButtonIngresar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButtonIngresar.setText("Ingresar");
        jButtonIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIngresarActionPerformed(evt);
            }
        });

        jButtonSalir.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLoginLayout = new javax.swing.GroupLayout(jPanelLogin);
        jPanelLogin.setLayout(jPanelLoginLayout);
        jPanelLoginLayout.setHorizontalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLoginLayout.createSequentialGroup()
                .addGap(0, 414, Short.MAX_VALUE)
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLoginLayout.createSequentialGroup()
                        .addComponent(jLabelPassword)
                        .addGap(18, 18, 18)
                        .addComponent(jPasswordContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLoginLayout.createSequentialGroup()
                        .addComponent(jLabelUsuario)
                        .addGap(62, 62, 62)
                        .addComponent(jTextCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addGap(335, 335, 335))
            .addGroup(jPanelLoginLayout.createSequentialGroup()
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLoginLayout.createSequentialGroup()
                        .addGap(479, 479, 479)
                        .addComponent(jLabelTitulo))
                    .addGroup(jPanelLoginLayout.createSequentialGroup()
                        .addGap(500, 500, 500)
                        .addComponent(jlLabelImagen))
                    .addGroup(jPanelLoginLayout.createSequentialGroup()
                        .addGap(511, 511, 511)
                        .addComponent(jButtonIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(184, 184, 184)
                        .addComponent(jButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLoginLayout.setVerticalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jLabelTitulo)
                .addGap(53, 53, 53)
                .addComponent(jlLabelImagen)
                .addGap(127, 127, 127)
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76)
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword)
                    .addComponent(jPasswordContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(152, 152, 152)
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIngresar)
                    .addComponent(jButtonSalir))
                .addContainerGap(329, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1470, 1170));

        jLabelSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelSolicitud.setText("Solicitudes");

        jTableSolicitudes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableSolicitudes);

        jLabelSolicitudesAprobadas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelSolicitudesAprobadas.setText("Solicitudes Aprobadas");

        jTableSolicitudesAprobadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTableSolicitudesAprobadas);

        jButtonNuevaSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButtonNuevaSolicitud.setText("Nueva Solicitud");
        jButtonNuevaSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevaSolicitudActionPerformed(evt);
            }
        });

        jButtonSalirVentanaProfesor.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButtonSalirVentanaProfesor.setText("Salir");
        jButtonSalirVentanaProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirVentanaProfesorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelVentanaProfesorLayout = new javax.swing.GroupLayout(jPanelVentanaProfesor);
        jPanelVentanaProfesor.setLayout(jPanelVentanaProfesorLayout);
        jPanelVentanaProfesorLayout.setHorizontalGroup(
            jPanelVentanaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVentanaProfesorLayout.createSequentialGroup()
                .addGroup(jPanelVentanaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVentanaProfesorLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanelVentanaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelSolicitudesAprobadas)
                            .addComponent(jLabelSolicitud)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanelVentanaProfesorLayout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(jButtonNuevaSolicitud)
                        .addGap(106, 106, 106)
                        .addComponent(jButtonSalirVentanaProfesor)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanelVentanaProfesorLayout.setVerticalGroup(
            jPanelVentanaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVentanaProfesorLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabelSolicitud)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabelSolicitudesAprobadas)
                .addGroup(jPanelVentanaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVentanaProfesorLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelVentanaProfesorLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 378, Short.MAX_VALUE)
                        .addGroup(jPanelVentanaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSalirVentanaProfesor)
                            .addComponent(jButtonNuevaSolicitud))
                        .addGap(341, 341, 341))))
        );

        getContentPane().add(jPanelVentanaProfesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1470, 1170));

        jLabelTituloVentanaSuperUsuarioAdministrador.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabelTituloVentanaSuperUsuarioAdministrador.setText("Bienvenido ");

        jButtonActualizarCSVprofesor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonActualizarCSVprofesor.setText("Actualizar CSV - Profesor y Creacion de contraseñas");
        jButtonActualizarCSVprofesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarCSVprofesorActionPerformed(evt);
            }
        });

        jButtonActualizarJefesDepartamentos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonActualizarJefesDepartamentos.setText("Actualizar Jefes de los Departamentos");
        jButtonActualizarJefesDepartamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarJefesDepartamentosActionPerformed(evt);
            }
        });

        jButtonCerrarVentanaSuperAdaministrador.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonCerrarVentanaSuperAdaministrador.setText("Cerrar");
        jButtonCerrarVentanaSuperAdaministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarVentanaSuperAdaministradorActionPerformed(evt);
            }
        });

        jTableTodasLasSolicitudes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableTodasLasSolicitudes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTodasLasSolicitudesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableTodasLasSolicitudes);

        jPanelModificarSolicitud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelCerrarModificarionSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelCerrarModificarionSolicitud.setText(" X ");
        jLabelCerrarModificarionSolicitud.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelCerrarModificarionSolicitud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCerrarModificarionSolicitudMouseClicked(evt);
            }
        });

        jButtonActualizarModificarSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonActualizarModificarSolicitud.setText("Actulizar");

        jLabelMSNombreActividad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelMSNombreActividad.setText("Nombre de la Actividad : ");

        jTextFieldMSNombreActividad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextFieldMSNombreActividad.setEnabled(false);

        jLabelMSEstado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelMSEstado.setText("Estado :");
        jLabelMSEstado.setToolTipText("");

        jComboBoxMSEstado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBoxMSEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelMSComentarioEstado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelMSComentarioEstado.setText("Comentario Estado :");

        jTextFieldMSComentarioEstado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanelModificarSolicitudLayout = new javax.swing.GroupLayout(jPanelModificarSolicitud);
        jPanelModificarSolicitud.setLayout(jPanelModificarSolicitudLayout);
        jPanelModificarSolicitudLayout.setHorizontalGroup(
            jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelModificarSolicitudLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMSNombreActividad)
                    .addComponent(jLabelMSEstado)
                    .addComponent(jLabelMSComentarioEstado))
                .addGap(18, 18, 18)
                .addGroup(jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldMSNombreActividad)
                    .addComponent(jComboBoxMSEstado, 0, 245, Short.MAX_VALUE)
                    .addComponent(jTextFieldMSComentarioEstado))
                .addGap(58, 235, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelModificarSolicitudLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelModificarSolicitudLayout.createSequentialGroup()
                        .addComponent(jButtonActualizarModificarSolicitud)
                        .addGap(68, 68, 68))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelModificarSolicitudLayout.createSequentialGroup()
                        .addComponent(jLabelCerrarModificarionSolicitud)
                        .addContainerGap())))
        );
        jPanelModificarSolicitudLayout.setVerticalGroup(
            jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModificarSolicitudLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabelCerrarModificarionSolicitud)
                .addGap(21, 21, 21)
                .addGroup(jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelModificarSolicitudLayout.createSequentialGroup()
                        .addGroup(jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMSNombreActividad)
                            .addComponent(jTextFieldMSNombreActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59))
                    .addGroup(jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxMSEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelMSEstado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMSComentarioEstado)
                    .addComponent(jTextFieldMSComentarioEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88)
                .addComponent(jButtonActualizarModificarSolicitud)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanelVentanaSuperUsuarioAdministradorLayout = new javax.swing.GroupLayout(jPanelVentanaSuperUsuarioAdministrador);
        jPanelVentanaSuperUsuarioAdministrador.setLayout(jPanelVentanaSuperUsuarioAdministradorLayout);
        jPanelVentanaSuperUsuarioAdministradorLayout.setHorizontalGroup(
            jPanelVentanaSuperUsuarioAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createSequentialGroup()
                .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelModificarSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createSequentialGroup()
                            .addGap(106, 106, 106)
                            .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonActualizarCSVprofesor)
                                .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createSequentialGroup()
                                    .addComponent(jButtonActualizarJefesDepartamentos)
                                    .addGap(232, 232, 232)
                                    .addComponent(jButtonCerrarVentanaSuperAdaministrador))))
                        .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createSequentialGroup()
                            .addGap(73, 73, 73)
                            .addComponent(jLabelTituloVentanaSuperUsuarioAdministrador))
                        .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanelVentanaSuperUsuarioAdministradorLayout.setVerticalGroup(
            jPanelVentanaSuperUsuarioAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabelTituloVentanaSuperUsuarioAdministrador)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanelModificarSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addComponent(jButtonActualizarCSVprofesor)
                .addGap(26, 26, 26)
                .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonActualizarJefesDepartamentos)
                    .addComponent(jButtonCerrarVentanaSuperAdaministrador))
                .addGap(95, 95, 95))
        );

        getContentPane().add(jPanelVentanaSuperUsuarioAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1470, 1170));

        jLabelNuevaSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabelNuevaSolicitud.setText("Creación de Solicitud");

        jLabelNombreActividad.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelNombreActividad.setText("Nombre de la Actividad :");

        jTextNombreActividad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabelTipoActividad.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTipoActividad.setText("Tipo de Actividad :");

        jComboBoxTipoActividad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBoxTipoActividad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelDepartamento.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelDepartamento.setText("Departamento :");

        jComboBoxDepartamento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBoxDepartamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelPreVista.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelPreVista.setText("Pre - Vista :");

        jRadioButtonPreVistaSi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jRadioButtonPreVistaSi.setText("Si");
        jRadioButtonPreVistaSi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonPreVistaSiMouseClicked(evt);
            }
        });

        jRadioButtonPreVistaNo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jRadioButtonPreVistaNo.setText("No");
        jRadioButtonPreVistaNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonPreVistaNoMouseClicked(evt);
            }
        });

        jLabelTransporte.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTransporte.setText("Transporte : ");

        jRadioButtonTransporteSi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jRadioButtonTransporteSi.setText("Si");
        jRadioButtonTransporteSi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonTransporteSiMouseClicked(evt);
            }
        });

        jRadioButtonTransporteNo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jRadioButtonTransporteNo.setText("No");
        jRadioButtonTransporteNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonTransporteNoMouseClicked(evt);
            }
        });

        jLabelFechaInicial.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelFechaInicial.setText("Fecha Inicial :");

        jTextFieldFechaInicial.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextFieldFechaInicial.setText("00-00-0000");
        jTextFieldFechaInicial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldFechaInicialMouseClicked(evt);
            }
        });

        jLabelFechaFinal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelFechaFinal.setText("Fecha Final : ");

        jTextFieldFechaFinal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextFieldFechaFinal.setText("00-00-0000");
        jTextFieldFechaFinal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldFechaFinalMouseClicked(evt);
            }
        });

        jLabelHoraInicial.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelHoraInicial.setText("Hora Inicial : ");

        jTextFieldHoraInicial.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextFieldHoraInicial.setText("00:00");
        jTextFieldHoraInicial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldHoraInicialMouseClicked(evt);
            }
        });

        jLabelHoraFinal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelHoraFinal.setText("Hora Final : ");

        jTextFieldHoraFinal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextFieldHoraFinal.setText("00:00");
        jTextFieldHoraFinal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldHoraFinalMouseClicked(evt);
            }
        });

        jLabelAlojamiento.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelAlojamiento.setText("Alojamiento : ");

        jRadioButtonAlojamientoSi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jRadioButtonAlojamientoSi.setText("Si");
        jRadioButtonAlojamientoSi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonAlojamientoSiMouseClicked(evt);
            }
        });

        jRadioButtonAlojamientoNo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jRadioButtonAlojamientoNo.setText("No");
        jRadioButtonAlojamientoNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonAlojamientoNoMouseClicked(evt);
            }
        });

        jLabelComentarioAdicional.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelComentarioAdicional.setText("Comentario Adicional :");

        jTextFieldComentarioAdicional.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabelCantidadAlumnos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelCantidadAlumnos.setText("Cantidad de Alumnos :");

        jTextFieldCantidadAlumnos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jButtonCrearSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonCrearSolicitud.setText("Crear Solicitud");
        jButtonCrearSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearSolicitudActionPerformed(evt);
            }
        });

        jButtonCerrarSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonCerrarSolicitud.setText("Cerrar");
        jButtonCerrarSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarSolicitudActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSolicitudLayout = new javax.swing.GroupLayout(jPanelSolicitud);
        jPanelSolicitud.setLayout(jPanelSolicitudLayout);
        jPanelSolicitudLayout.setHorizontalGroup(
            jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabelNuevaSolicitud))
                    .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombreActividad)
                            .addComponent(jLabelTipoActividad)
                            .addComponent(jLabelDepartamento)
                            .addComponent(jLabelPreVista)
                            .addComponent(jLabelTransporte)
                            .addComponent(jLabelFechaInicial)
                            .addComponent(jLabelFechaFinal)
                            .addComponent(jLabelHoraInicial)
                            .addComponent(jLabelHoraFinal)
                            .addComponent(jLabelAlojamiento)
                            .addComponent(jLabelComentarioAdicional)
                            .addComponent(jLabelCantidadAlumnos))
                        .addGap(62, 62, 62)
                        .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldHoraFinal)
                            .addComponent(jTextNombreActividad)
                            .addComponent(jComboBoxTipoActividad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxDepartamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                    .addComponent(jRadioButtonTransporteSi)
                                    .addGap(63, 63, 63)
                                    .addComponent(jRadioButtonTransporteNo))
                                .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                    .addComponent(jRadioButtonPreVistaSi)
                                    .addGap(63, 63, 63)
                                    .addComponent(jRadioButtonPreVistaNo)))
                            .addComponent(jTextFieldFechaInicial)
                            .addComponent(jTextFieldFechaFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(jTextFieldHoraInicial)
                            .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                .addComponent(jRadioButtonAlojamientoSi)
                                .addGap(63, 63, 63)
                                .addComponent(jRadioButtonAlojamientoNo))
                            .addComponent(jTextFieldComentarioAdicional)
                            .addComponent(jTextFieldCantidadAlumnos)))
                    .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(jButtonCrearSolicitud)
                        .addGap(120, 120, 120)
                        .addComponent(jButtonCerrarSolicitud)))
                .addContainerGap(750, Short.MAX_VALUE))
        );
        jPanelSolicitudLayout.setVerticalGroup(
            jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabelNuevaSolicitud)
                .addGap(52, 52, 52)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombreActividad)
                    .addComponent(jTextNombreActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTipoActividad)
                    .addComponent(jComboBoxTipoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDepartamento)
                    .addComponent(jComboBoxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPreVista)
                    .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButtonPreVistaSi)
                        .addComponent(jRadioButtonPreVistaNo)))
                .addGap(47, 47, 47)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTransporte)
                    .addComponent(jRadioButtonTransporteSi)
                    .addComponent(jRadioButtonTransporteNo))
                .addGap(59, 59, 59)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaInicial)
                    .addComponent(jTextFieldFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaFinal)
                    .addComponent(jTextFieldFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHoraInicial)
                    .addComponent(jTextFieldHoraInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHoraFinal)
                    .addComponent(jTextFieldHoraFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAlojamiento)
                    .addComponent(jRadioButtonAlojamientoSi)
                    .addComponent(jRadioButtonAlojamientoNo))
                .addGap(45, 45, 45)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelComentarioAdicional)
                    .addComponent(jTextFieldComentarioAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCantidadAlumnos)
                    .addComponent(jTextFieldCantidadAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCrearSolicitud)
                    .addComponent(jButtonCerrarSolicitud))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1470, 1170));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextCorreoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextCorreoMouseClicked
        // TODO add your handling code here:
        jTextCorreo.setText("");
    }//GEN-LAST:event_jTextCorreoMouseClicked

    private void jPasswordContraseñaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordContraseñaMouseClicked
        // TODO add your handling code here:
        jPasswordContraseña.setText("");
    }//GEN-LAST:event_jPasswordContraseñaMouseClicked

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        // TODO add your handling code here:
        mensaje("Hasta Luego!!!");
        System.exit(0);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jTextCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextCorreoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextCorreoKeyTyped

    private void jButtonIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIngresarActionPerformed
        // TODO add your handling code here:
        final String correo = "@educantabria.es";
        String email = jTextCorreo.getText() + correo;
        String password = jPasswordContraseña.getText();

        Usuario auxUsu = auxUsuarioDAOImp.buscarUsuarioEmail(email, password);

        if (auxUsu != null) {

            Profesor auxProfesor = auxProfesorDAOImp.buscar(auxUsu.getIdUsuario());

            if (auxProfesor != null) {

                if (auxProfesor.getPerfil().equals(Perfil.PROFESOR)) {

                    mensaje("Bienvenido " + auxProfesor.getNombre().toUpperCase() + " " + auxProfesor.getApellidos().toUpperCase());
                    jPanelLogin.setVisible(false);
                    jPanelVentanaProfesor.setVisible(true);
                    setTitle("Bienvenido " + auxProfesor.getNombre().toUpperCase() + " " + auxProfesor.getApellidos().toUpperCase());
                    profesor = auxProfesor;
                    muestraCabeceraTablaSolicitudesProfesor();
                    cargarSolicitudesDelProfesor();

                } else if (auxProfesor.getPerfil().equals(Perfil.SUPERUSUARIO) || auxProfesor.getPerfil().equals(Perfil.ADMINISTRADOR)) {

                    mensaje("Bienvenido " + auxProfesor.getNombre().toUpperCase() + " " + auxProfesor.getApellidos().toUpperCase());
                    jPanelLogin.setVisible(false);
                    jPanelVentanaSuperUsuarioAdministrador.setVisible(true);
                    setTitle("Bienvenido " + auxProfesor.getNombre().toUpperCase() + " " + auxProfesor.getApellidos().toUpperCase());
                    profesor = auxProfesor;
                    jLabelTituloVentanaSuperUsuarioAdministrador.setText("Bienvenido " + profesor.getPerfil().toString().substring(0, 1) + profesor.getPerfil().toString().substring(1, profesor.getPerfil().toString().length()).toLowerCase());
                    muestraCabeceraTablaTodasLasSolicitudes();
                    cargarTodasLasSolicitudes();
                    comboBoxEstado();
                }

            }

        } else {

            mensaje("Usuario o Contraseña incorrecta");

        }
    }//GEN-LAST:event_jButtonIngresarActionPerformed

    private void jRadioButtonPreVistaSiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonPreVistaSiMouseClicked
        // TODO add your handling code here:
        jRadioButtonPreVistaNo.setSelected(false);
    }//GEN-LAST:event_jRadioButtonPreVistaSiMouseClicked

    private void jRadioButtonPreVistaNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonPreVistaNoMouseClicked
        // TODO add your handling code here:
        jRadioButtonPreVistaSi.setSelected(false);
    }//GEN-LAST:event_jRadioButtonPreVistaNoMouseClicked

    private void jRadioButtonTransporteSiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonTransporteSiMouseClicked
        // TODO add your handling code here:
        jRadioButtonTransporteNo.setSelected(false);
    }//GEN-LAST:event_jRadioButtonTransporteSiMouseClicked

    private void jRadioButtonTransporteNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonTransporteNoMouseClicked
        // TODO add your handling code here:
        jRadioButtonTransporteSi.setSelected(false);
    }//GEN-LAST:event_jRadioButtonTransporteNoMouseClicked

    private void jRadioButtonAlojamientoSiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonAlojamientoSiMouseClicked
        // TODO add your handling code here:
        jRadioButtonAlojamientoNo.setSelected(false);
    }//GEN-LAST:event_jRadioButtonAlojamientoSiMouseClicked

    private void jRadioButtonAlojamientoNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonAlojamientoNoMouseClicked
        // TODO add your handling code here:
        jRadioButtonAlojamientoSi.setSelected(false);
    }//GEN-LAST:event_jRadioButtonAlojamientoNoMouseClicked

    private void jButtonCrearSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearSolicitudActionPerformed
        // TODO add your handling code here:

        String nombreActividad = jTextNombreActividad.getText();
        TipoActividad tipoActividad = TipoActividad.valueOf(jComboBoxTipoActividad.getSelectedItem().toString().toUpperCase());
        departamento = auxdepartamentoDAOImp.buscarID(jComboBoxDepartamento.getSelectedItem().toString());
        int numeroDepartamento = departamento.getId();
        boolean prevista = prevista();
        boolean transporte = transporte();
        LocalDate fecInicial = LocalDate.parse(jTextFieldFechaInicial.getText(), formatoFecha);
        LocalDate fecFinal = LocalDate.parse(jTextFieldFechaFinal.getText(), formatoFecha);
        LocalTime horaInicial = LocalTime.parse(jTextFieldHoraInicial.getText(), formatoHora);
        LocalTime horaFinal = LocalTime.parse(jTextFieldHoraFinal.getText(), formatoHora);
        boolean alojamiento = alojamiento();
        String comentadioAdicional = jTextFieldComentarioAdicional.getText();
        Estado estado = Estado.SOLICITADA;
        int cantidadAlumnos = Integer.parseInt(jTextFieldCantidadAlumnos.getText());

        solicitud = new Solicitud(0, getProfesor().getId_Prof(), nombreActividad, tipoActividad, numeroDepartamento, prevista, transporte, fecInicial, fecFinal, horaInicial, horaFinal, alojamiento, comentadioAdicional, estado, "-", cantidadAlumnos);

        if (auxSolicitudDAOImp.guardarSolicitud(solicitud)) {
            mensaje("Solicitud Guardada Correctamente!!!");
        } else {
            mensaje("No se a podido Guardar Solicitud!!!");
        }

        limpiarJtextFieldDeSolicitud();
    }//GEN-LAST:event_jButtonCrearSolicitudActionPerformed

    private void jButtonCerrarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarSolicitudActionPerformed
        // TODO add your handling code here:
        jPanelSolicitud.setVisible(false);
        jPanelVentanaProfesor.setVisible(true);
        cargarSolicitudesDelProfesor();

    }//GEN-LAST:event_jButtonCerrarSolicitudActionPerformed

    private void jButtonNuevaSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevaSolicitudActionPerformed
        // TODO add your handling code here:
        jPanelVentanaProfesor.setVisible(false);
        jPanelSolicitud.setVisible(true);
        comboBoxTipoActividad();
        comboBoxDepartamento();

    }//GEN-LAST:event_jButtonNuevaSolicitudActionPerformed

    private void jButtonSalirVentanaProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirVentanaProfesorActionPerformed
        // TODO add your handling code here:
        mensaje("Hasta Luego!!!");
        System.exit(0);
    }//GEN-LAST:event_jButtonSalirVentanaProfesorActionPerformed

    private void jTextFieldFechaInicialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldFechaInicialMouseClicked
        // TODO add your handling code here:
        jTextFieldFechaInicial.setText("");
    }//GEN-LAST:event_jTextFieldFechaInicialMouseClicked

    private void jTextFieldFechaFinalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldFechaFinalMouseClicked
        // TODO add your handling code here:
        jTextFieldFechaFinal.setText("");
    }//GEN-LAST:event_jTextFieldFechaFinalMouseClicked

    private void jTextFieldHoraInicialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldHoraInicialMouseClicked
        // TODO add your handling code here:
        jTextFieldHoraInicial.setText("");
    }//GEN-LAST:event_jTextFieldHoraInicialMouseClicked

    private void jTextFieldHoraFinalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldHoraFinalMouseClicked
        // TODO add your handling code here:
        jTextFieldHoraFinal.setText("");
    }//GEN-LAST:event_jTextFieldHoraFinalMouseClicked

    private void jButtonActualizarCSVprofesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarCSVprofesorActionPerformed
        // TODO add your handling code here:
        File archivoProfesor = new File("src/main/resources/archivosCSV/profesores.csv");
        boolean guardadoP = false;

        ArrayList<Profesor> auxProfesores = MetodoFichero.listarProfesores(archivoProfesor);

        for (Profesor auxP : auxProfesores) {

            auxProfesorDAOImp.guardar(auxP);
            auxUsuarioDAOImp.crearUsuariosContraseñas(auxP);
            guardadoP = true;

        }

        if (guardadoP == true) {

            mensaje("Archivo Actualizado correctamente!!!");

        } else {

            mensaje("No hay actualizaciones nuevas!!!");

        }

    }//GEN-LAST:event_jButtonActualizarCSVprofesorActionPerformed

    private void jButtonActualizarJefesDepartamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarJefesDepartamentosActionPerformed
        // TODO add your handling code here:
        boolean guardadoD = false;

        List<Departamentos> auxDepartamentoses = auxdepartamentoDAOImp.listar();

        for (Departamentos auxD : auxDepartamentoses) {

            if (auxD.getId() != 20) {

                auxdepartamentoDAOImp.modificar(auxD);
                guardadoD = true;

            } else {

                mensaje("Jefe de Departamento, Actualizado correctamente!!!");
                return;

            }

        }

    }//GEN-LAST:event_jButtonActualizarJefesDepartamentosActionPerformed

    private void jButtonCerrarVentanaSuperAdaministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarVentanaSuperAdaministradorActionPerformed
        // TODO add your handling code here:
        mensaje("Hasta Luego!!!");
        System.exit(0);
    }//GEN-LAST:event_jButtonCerrarVentanaSuperAdaministradorActionPerformed

    private void jLabelCerrarModificarionSolicitudMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCerrarModificarionSolicitudMouseClicked
        // TODO add your handling code here:
        jPanelModificarSolicitud.setVisible(false);
    }//GEN-LAST:event_jLabelCerrarModificarionSolicitudMouseClicked

    private void jTableTodasLasSolicitudesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTodasLasSolicitudesMouseClicked
        // TODO add your handling code here:
        jPanelModificarSolicitud.setVisible(true);
    }//GEN-LAST:event_jTableTodasLasSolicitudesMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizarCSVprofesor;
    private javax.swing.JButton jButtonActualizarJefesDepartamentos;
    private javax.swing.JButton jButtonActualizarModificarSolicitud;
    private javax.swing.JButton jButtonCerrarSolicitud;
    private javax.swing.JButton jButtonCerrarVentanaSuperAdaministrador;
    private javax.swing.JButton jButtonCrearSolicitud;
    private javax.swing.JButton jButtonIngresar;
    private javax.swing.JButton jButtonNuevaSolicitud;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonSalirVentanaProfesor;
    private javax.swing.JComboBox<String> jComboBoxDepartamento;
    private javax.swing.JComboBox<String> jComboBoxMSEstado;
    private javax.swing.JComboBox<String> jComboBoxTipoActividad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelAlojamiento;
    private javax.swing.JLabel jLabelCantidadAlumnos;
    private javax.swing.JLabel jLabelCerrarModificarionSolicitud;
    private javax.swing.JLabel jLabelComentarioAdicional;
    private javax.swing.JLabel jLabelDepartamento;
    private javax.swing.JLabel jLabelFechaFinal;
    private javax.swing.JLabel jLabelFechaInicial;
    private javax.swing.JLabel jLabelHoraFinal;
    private javax.swing.JLabel jLabelHoraInicial;
    private javax.swing.JLabel jLabelMSComentarioEstado;
    private javax.swing.JLabel jLabelMSEstado;
    private javax.swing.JLabel jLabelMSNombreActividad;
    private javax.swing.JLabel jLabelNombreActividad;
    private javax.swing.JLabel jLabelNuevaSolicitud;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelPreVista;
    private javax.swing.JLabel jLabelSolicitud;
    private javax.swing.JLabel jLabelSolicitudesAprobadas;
    private javax.swing.JLabel jLabelTipoActividad;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTituloVentanaSuperUsuarioAdministrador;
    private javax.swing.JLabel jLabelTransporte;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPanel jPanelLogin;
    private javax.swing.JPanel jPanelModificarSolicitud;
    private javax.swing.JPanel jPanelSolicitud;
    private javax.swing.JPanel jPanelVentanaProfesor;
    private javax.swing.JPanel jPanelVentanaSuperUsuarioAdministrador;
    private javax.swing.JPasswordField jPasswordContraseña;
    private javax.swing.JRadioButton jRadioButtonAlojamientoNo;
    private javax.swing.JRadioButton jRadioButtonAlojamientoSi;
    private javax.swing.JRadioButton jRadioButtonPreVistaNo;
    private javax.swing.JRadioButton jRadioButtonPreVistaSi;
    private javax.swing.JRadioButton jRadioButtonTransporteNo;
    private javax.swing.JRadioButton jRadioButtonTransporteSi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableSolicitudes;
    private javax.swing.JTable jTableSolicitudesAprobadas;
    private javax.swing.JTable jTableTodasLasSolicitudes;
    private javax.swing.JTextField jTextCorreo;
    private javax.swing.JTextField jTextFieldCantidadAlumnos;
    private javax.swing.JTextField jTextFieldComentarioAdicional;
    private javax.swing.JTextField jTextFieldFechaFinal;
    private javax.swing.JTextField jTextFieldFechaInicial;
    private javax.swing.JTextField jTextFieldHoraFinal;
    private javax.swing.JTextField jTextFieldHoraInicial;
    private javax.swing.JTextField jTextFieldMSComentarioEstado;
    private javax.swing.JTextField jTextFieldMSNombreActividad;
    private javax.swing.JTextField jTextNombreActividad;
    private javax.swing.JLabel jlLabelImagen;
    // End of variables declaration//GEN-END:variables
}
