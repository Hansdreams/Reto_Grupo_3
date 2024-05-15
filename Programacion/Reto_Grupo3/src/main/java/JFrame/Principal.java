/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package JFrame;

//Importacion de Acceso Base Datos
import Acceso_BaseDatos.AccesoBaseDatos;

//Importacion de mi Clases
import Clases.Profesor;
import Clases.Solicitud;
import Clases.Usuario;
import Clases.Cursos;
import Clases.Grupos;
import Clases.Transporte;
import Clases.ActividadProgramada;
import Clases.Cursos;
import Clases.Departamentos;
import Clases.Fotos;

//Importacion de DAOImp
import DAOImp.ActividadProgramadaDAOImp;
import DAOImp.DepartamentoDAOImp;
import DAOImp.UsuarioDAOImp;
import DAOImp.ProfesorDAOImp;
import DAOImp.SolicitudDAOImp;
import DAOImp.CursosDAOImp;
import DAOImp.GruposDAOImp;
import DAOImp.TransporteDAOImp;
import DAOImp.FotosDAOImp;

//Importacion Enums
import Enums.Estado;
import Enums.Perfil;
import Enums.TipoActividad;
import Enums.Estado;
import Enums.Rol;

//Importacion de Fichero
import Ficheros.MetodoFichero;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author Gus y Joaco
 */
public class Principal extends javax.swing.JFrame {

    //CONEXICION
    private Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }

    /**
     * Creates new form Principal
     */
    //ATRIBUTOS
    UsuarioDAOImp auxUsuarioDAOImp = new UsuarioDAOImp();
    ProfesorDAOImp auxProfesorDAOImp = new ProfesorDAOImp();
    SolicitudDAOImp auxSolicitudDAOImp = new SolicitudDAOImp();
    DepartamentoDAOImp auxdepartamentoDAOImp = new DepartamentoDAOImp();
    CursosDAOImp auxCursoDAOImp = new CursosDAOImp();
    GruposDAOImp auxGrupoDAOImp = new GruposDAOImp();
    TransporteDAOImp auxTransporteDAOImp = new TransporteDAOImp();
    ActividadProgramadaDAOImp auxActividadProgramadaDAOImp = new ActividadProgramadaDAOImp();
    FotosDAOImp auxFotosDAOImp = new FotosDAOImp();
    Profesor profesor;
    Departamentos departamento;
    Solicitud solicitud;
    ActividadProgramada actividaProgramada;
    Fotos fotos;

    //ATRIBUTOS SOLICITUD
    int idSolicitud, idProfesor;

    //FORMATO DE FECHA Y HORA
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

    //Atributos para las tablas
    DefaultTableModel modeloSolicitudes;
    DefaultTableModel modeloSolicitudesAprobadas;
    DefaultTableModel modeloTodasLasSolicitudes;
    DefaultTableModel modeloFotos;
    private String[] cabeceraSolicitudes = {"ID", "Nombre de la Actividad", "Tipo de Actividad", "Departamento", "Prevista", "Transporte", "Fecha Inicial", "Fecha Final",
        "Hora Inicial", "Hora Final", "Alojamiento", "Comentario", "Estado", "Consulta Estado"};
    private String[] cabeceraSolicitudesAprobadas = {"ID", "Nombre de la Actividad", "Tipo de Actividad", "Departamento", "Prevista", "Transporte", "Fecha Inicial", "Fecha Final",
        "Hora Inicial", "Hora Final", "Alojamiento", "Comentario", "Estado", "Consulta Estado"};
    private String[] cabeceraTodasLasSolicitudes = {"ID", "Solicitante", "Nombre de la Actividad", "Tipo de Actividad", "Departamento", "Prevista", "Transporte", "Fecha Inicial", "Fecha Final",
        "Hora Inicial", "Hora Final", "Alojamiento", "Comentario", "Estado", "Consulta Estado"};
    private String[] cabeceraTablaFotos = {"URL", "Descripcion"};

    //Atributos para las listas
    DefaultListModel modeloListaProfesoresParticipantes;
    DefaultListModel modeloListaGrupos;
    DefaultListModel modeloListaTransporte;

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

    private void muestraCabeceraTablaFotos() {

        modeloFotos = new DefaultTableModel(cabeceraTablaFotos, 0);
        jTableFotos.setModel(modeloFotos);

    }

    private void cargarModeloListaProfesorParticipantes() {

        modeloListaProfesoresParticipantes = new DefaultListModel();
        jListProfesoresParticipantes.setModel(modeloListaProfesoresParticipantes);

        modeloListaProfesoresParticipantes.removeAllElements();

    }

    private void cargarModeloListaGrupos() {

        modeloListaGrupos = new DefaultListModel();
        jListGrupos.setModel(modeloListaGrupos);

        modeloListaGrupos.removeAllElements();

    }

    private void cargarModeloListaTransporte() {

        modeloListaTransporte = new DefaultListModel();
        jListTransportes.setModel(modeloListaTransporte);

        modeloListaTransporte.removeAllElements();

    }

    private void cargarSolicitudesDelProfesor() {

        List<Solicitud> auxS = auxSolicitudDAOImp.listar();

        modeloSolicitudes.setRowCount(0);

        for (Solicitud s : auxS) {

            if (s.getSolicitante() == getProfesor().getId_Prof()) {

                // Creamos un arreglo con los datos extraidos del ArrayList
                Object Fila[] = {s.getIdSolicitud(), s.getNombreActividad(), s.getTipoActividad(), s.getDepartamento(), devuelveSiNo(s.isPrevista()), devuelveSiNo(s.isTransporte()),
                    s.formatoFechaInicial(), s.formatoFechaFinal(), s.formatoHoraInicial(), s.formatoHoraFinal(),
                    devuelveSiNo(s.isAlojamiento()), s.getComentarioAdicional(), s.getEstado(), s.getConsultaEstado()};
                // Agregamos el arreglo creado al modelo de datos del JTable
                modeloSolicitudes.addRow(Fila);

            }

        }

    }

    private void cargarSolicitudesAprobadaDelProfesor() {

        List<Solicitud> auxS = auxSolicitudDAOImp.listar();

        modeloSolicitudesAprobadas.setRowCount(0);

        for (Solicitud s : auxS) {

            if (s.getSolicitante() == getProfesor().getId_Prof() && s.getEstado().equals(Estado.APROBADA)) {

                // Creamos un arreglo con los datos extraidos del ArrayList
                Object Fila[] = {s.getIdSolicitud(), s.getNombreActividad(), s.getTipoActividad(), s.getDepartamento(), devuelveSiNo(s.isPrevista()), devuelveSiNo(s.isTransporte()),
                    s.formatoFechaInicial(), s.formatoFechaFinal(), s.formatoHoraInicial(), s.formatoHoraFinal(),
                    devuelveSiNo(s.isAlojamiento()), s.getComentarioAdicional(), s.getEstado(), s.getConsultaEstado()};
                // Agregamos el arreglo creado al modelo de datos del JTable
                modeloSolicitudesAprobadas.addRow(Fila);

            }

        }

    }

    private void cargarTodasLasSolicitudes() {

        List<Solicitud> auxS = auxSolicitudDAOImp.listar();

        modeloTodasLasSolicitudes.setRowCount(0);

        for (Solicitud s : auxS) {

            // Creamos un arreglo con los datos extraidos del ArrayList
            Object Fila[] = {s.getIdSolicitud(), s.getSolicitante(), s.getNombreActividad(), s.getTipoActividad(), s.getDepartamento(), devuelveSiNo(s.isPrevista()), devuelveSiNo(s.isTransporte()),
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

    private void comboBoxPerfilProfesor() {

        //variable para tomar el nombre
        String nombre;
        TipoActividad tipo;
        //se limpia el combo
        jComboBoxPerfilNuevoProfesor.removeAllItems();//limpia el combobox
        //try por si fallara al momento de rellenar
        try {
            //Se recorre con un for la lista de conductores
            for (Perfil p : Perfil.values()) {

                nombre = p.toString();
                //Se arega un nuevo ítem al combobox
                jComboBoxPerfilNuevoProfesor.addItem(nombre.substring(0, 1) + nombre.substring(1, nombre.length()).toLowerCase());

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

    private void comboBoxDepartamentoNuevoProfesor() {

        //variable para tomar el nombre
        String nombre;
        TipoActividad tipo;
        //se limpia el combo
        jComboBoxDepartamentoNuevoProfesor.removeAllItems();//limpia el combobox
        List<Departamentos> auxDepartamentoses = auxdepartamentoDAOImp.listar();
        //try por si fallara al momento de rellenar
        try {
            //Se recorre con un for la lista de conductores

            for (Departamentos auxD : auxDepartamentoses) {

                if (auxD.getId() != 20) {

                    nombre = auxD.getNombre();
                    jComboBoxDepartamentoNuevoProfesor.addItem(nombre);

                }

            }

        } catch (Exception e) { //capta el error y lo muestra
            mensaje("Error al cargar ComboBoxDepartamento" + e);
        }

    }

    private void comboBoxProfesorResponsable() {

        //variable para tomar el nombre
        String nombre;
        TipoActividad tipo;
        //se limpia el combo
        jComboBoxProfesorResponsable.removeAllItems();//limpia el combobox
        List<Profesor> auxProfesores = auxProfesorDAOImp.listar();
        //try por si fallara al momento de rellenar
        try {
            //Se recorre con un for la lista de conductores

            for (Profesor auxP : auxProfesores) {

                if (auxP.getId_Prof() != 999) {

                    nombre = auxP.getNombre() + " " + auxP.getApellidos();
                    jComboBoxProfesorResponsable.addItem(nombre);

                }

            }

        } catch (Exception e) { //capta el error y lo muestra
            mensaje("Error al cargar ComboBoxProfesorResponsable" + e);
        }

    }

    private void comboBoxProfesoresParticipantes() {

        //variable para tomar el nombre
        String nombre;
        TipoActividad tipo;
        //se limpia el combo
        jComboBoxProfesorParticipantes.removeAllItems();//limpia el combobox
        List<Profesor> auxProfesores = auxProfesorDAOImp.listar();
        //try por si fallara al momento de rellenar
        try {
            //Se recorre con un for la lista de conductores

            for (Profesor auxP : auxProfesores) {

                if (auxP.getId_Prof() != 999) {

                    nombre = auxP.getNombre() + " " + auxP.getApellidos();
                    jComboBoxProfesorParticipantes.addItem(nombre);

                }

            }

        } catch (Exception e) { //capta el error y lo muestra
            mensaje("Error al cargar ComboBoxProfesorResponsable" + e);
        }

    }

    private void comboBoxCursos() {

        //variable para tomar el nombre
        String nombre;
        TipoActividad tipo;
        //se limpia el combo
        jComboBoxCursos.removeAllItems();//limpia el combobox
        List<Cursos> auxCursos = auxCursoDAOImp.listar();
        //try por si fallara al momento de rellenar
        try {
            //Se recorre con un for la lista de conductores

            for (Cursos auxC : auxCursos) {

                nombre = auxC.getCodCurso();
                jComboBoxCursos.addItem(nombre);

            }

        } catch (Exception e) { //capta el error y lo muestra
            mensaje("Error al cargar ComboBoxCursos" + e);
        }

    }

    private void comboBoxGrupos() {

        //variable para tomar el nombre
        String nombre;
        TipoActividad tipo;
        //se limpia el combo
        jComboBoxGrupos.removeAllItems();//limpia el combobox
        List<Grupos> auxGrupos = auxGrupoDAOImp.listar();
        //try por si fallara al momento de rellenar
        try {
            //Se recorre con un for la lista de conductores

            for (Grupos auxG : auxGrupos) {

                nombre = auxG.getCodgrupo();
                jComboBoxGrupos.addItem(nombre);

            }

        } catch (Exception e) { //capta el error y lo muestra
            mensaje("Error al cargar ComboBoxCursos" + e);
        }

    }

    private void comboBoxTransporteContratado() {

        //variable para tomar el nombre
        String nombre;
        TipoActividad tipo;
        //se limpia el combo
        jComboBoxTransporteContrado.removeAllItems();//limpia el combobox
        List<Transporte> auxTransportes = auxTransporteDAOImp.listar();
        //try por si fallara al momento de rellenar
        try {
            //Se recorre con un for la lista de conductores

            for (Transporte auxT : auxTransportes) {

                nombre = auxT.getTipoTransporte();
                jComboBoxTransporteContrado.addItem(nombre);

            }

        } catch (Exception e) { //capta el error y lo muestra
            mensaje("Error al cargar ComboBoxCursos" + e);
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

    private void limpiarPanelSolicitud() {

        jTextFieldNumeroActividad.setText("");
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
        jComboBoxProfesorResponsable.setSelectedIndex(0);
        jComboBoxProfesorParticipantes.setSelectedIndex(0);
        jRadioButtonCursos.setSelected(false);
        jComboBoxCursos.setSelectedIndex(0);
        jRadioButtonGrupos.setSelected(false);
        jComboBoxGrupos.setSelectedIndex(0);
        modeloListaProfesoresParticipantes.removeAllElements();
        modeloListaGrupos.removeAllElements();
        jTextNombreActividad.requestFocus();

    }

    private void limpiarPanelModificarSolicitud() {

        jTextFieldMSNombreActividad.setText("");
        jComboBoxMSEstado.removeAllItems();
        jComboBoxMSEstado.enable(false);
        jTextFieldMSComentarioEstado.setText("");
        jTextFieldMSComentarioEstado.enable(false);
        comboBoxEstado();

    }

    private void limpiarPanelNuevoProfesor() {

        jTextFieldNombreNuevoProfesor.setText("");
        jTextFieldApellidosNuevoProfesor.setText("");
        jTextFieldDniNuevoProfesor.setText("");
        jComboBoxDepartamentoNuevoProfesor.removeAllItems();;
        jComboBoxPerfilNuevoProfesor.removeAllItems();
        comboBoxDepartamentoNuevoProfesor();
        comboBoxPerfilProfesor();
        jTextFieldNombreNuevoProfesor.requestFocus();

    }

    private String devuelveIdDepartamento(String aux) {

        String idDep = "";

        List<Departamentos> auxDep = auxdepartamentoDAOImp.listar();

        for (Departamentos d : auxDep) {

            if (d.getNombre().equalsIgnoreCase(aux)) {

                idDep += d.getId();

            }

        }
        return idDep;

    }

    private void cargarCantidadAlumnosCurso() {

        String codigoCurso = jComboBoxCursos.getSelectedItem().toString();

        List<Cursos> auxCursos = auxCursoDAOImp.listar();

        for (Cursos auxC : auxCursos) {

            if (auxC.getCodCurso().equalsIgnoreCase(codigoCurso)) {

                jTextFieldCantidadAlumnos.setText("" + auxC.getTotalAlumnos());
                return;

            }

        }

    }

    private int totalAlumnosGrupos(String aux) {

        int cantidad = 0;

        List<Grupos> auxGrupos = auxGrupoDAOImp.listar();

        for (Grupos auxG : auxGrupos) {

            if (auxG.getCodgrupo().equals(aux)) {

                cantidad = auxG.getAlumnos();

            }

        }

        return cantidad;

    }

    private void guardarProfesorResponsable(int actividad, int profesor) {

        final String sql = "INSERT INTO profesorparticipante(Actividad,IdProfesor,Rol)values ('" + actividad + "','" + profesor + "','" + Rol.RESPONSABLE.toString().toLowerCase() + "')";

        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            int salida = stmt.executeUpdate(sql);

            if (salida != 1) {
                mensaje("No se ha guardado Profesor Responsable!!!".toUpperCase());
            } else {
                mensaje("Se ha guardado Correctamente Profesor Responsable!!!".toUpperCase());
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private int devuelveIdProfesor(String aux) {

        List<Profesor> auxProfesores = auxProfesorDAOImp.listar();

        for (Profesor auxP : auxProfesores) {

            String nombreApellido = auxP.getNombre() + " " + auxP.getApellidos();

            if (nombreApellido.equals(aux)) {

                return auxP.getId_Prof();

            }

        }

        return 0;
    }

    private void ProfesoresParticipantes(int actividad, int profesor) {

        final String sql = "INSERT INTO profesorparticipante(Actividad,IdProfesor,Rol)values ('" + actividad + "','" + profesor + "','" + Rol.PARTICIPANTE.toString().toLowerCase() + "')";

        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            int salida = stmt.executeUpdate(sql);

            if (salida != 1) {
                System.out.println("No se ha guardado Profesor Participante!!!");
            } else {
                System.out.println("Se ha guardado Correctamente Profesor Participante!!!");
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private boolean guardarProfesoresParticipantes() {

        boolean guardado = false;

        List<Profesor> auxProfesores = auxProfesorDAOImp.listar();

        for (int i = 0; i < modeloListaProfesoresParticipantes.getSize(); i++) {

            for (Profesor auxP : auxProfesores) {

                String nombreApellido = auxP.getNombre() + " " + auxP.getApellidos();

                int idSol = Integer.parseInt(jTextFieldNumeroActividad.getText());

                if (nombreApellido.equals(modeloListaProfesoresParticipantes.getElementAt(i))) {

                    ProfesoresParticipantes(idSol, auxP.getId_Prof());
                    guardado = true;

                } else {
                    guardado = false;
                }

            }

        }

        return guardado;

    }

    private int devuelveIdCurso(String aux) {

        List<Cursos> auxCurso = auxCursoDAOImp.listar();

        for (Cursos auxC : auxCurso) {

            String codCurso = auxC.getCodCurso();

            if (codCurso.equals(aux)) {

                return auxC.getIdCurso();

            }

        }

        return 0;
    }

    private boolean guardarCursoActividad(int actividad, int curso) {

        boolean guardado = false;

        final String sql = "INSERT INTO cursosact (IdAct,idCur) values('" + actividad + "','" + curso + "')";

        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            int salida = stmt.executeUpdate(sql);

            if (salida != 1) {
                guardado = false;
            } else {
                guardado = true;
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return guardado;

    }

    private void grupoActividad(int actividad, int grupo) {

        final String sql = "INSERT INTO gruposact(IdAct,idGrupo) values ('" + actividad + "','" + grupo + "')";

        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            int salida = stmt.executeUpdate(sql);

            if (salida != 1) {
                System.out.println("No se ha guardado Grupo Actividad!!!");
            } else {
                System.out.println("Se ha guardado Grupo Actividad correctamente!!!");
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private boolean guardarGruposActividad() {

        boolean guardado = false;

        List<Grupos> auxGrupos = auxGrupoDAOImp.listar();

        for (int i = 0; i < modeloListaGrupos.getSize(); i++) {

            for (Grupos auxG : auxGrupos) {

                String codGru = auxG.getCodgrupo();

                int idSol = Integer.parseInt(jTextFieldNumeroActividad.getText());

                if (codGru.equals(modeloListaGrupos.getElementAt(i))) {

                    grupoActividad(idSol, auxG.getIdGrupo());
                    guardado = true;

                } else {
                    guardado = false;
                }

            }

        }

        return guardado;

    }

    private void transporteActividad(int actividad, int transporte) {

        final String sql = "INSERT INTO transporteact (IdActividad,tipoTransporte) values ('" + actividad + "','" + transporte + "')";

        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            int salida = stmt.executeUpdate(sql);

            if (salida != 1) {
                mensaje("No se ha guardado Transporte Actividad!!!");
            } else {
                mensaje("Se ha guardado Correctamente Transporte Actividad!!!");
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void guardarTransporteActividad() {

        boolean guardado = false;

        List<Transporte> auxTransportes = auxTransporteDAOImp.listar();

        for (int i = 0; i < modeloListaTransporte.getSize(); i++) {

            for (Transporte auxT : auxTransportes) {

                String tipoTransporte = auxT.getTipoTransporte();
                int idSol = Integer.parseInt(jTextFieldModificacionIdSolicitud.getText());

                if (tipoTransporte.equals(modeloListaTransporte.getElementAt(i))) {

                    transporteActividad(idSol, auxT.getIdTransporte());
                    guardado = true;

                }

            }

        }

        if (guardado) {
            mensaje("Se guardo correctamente el Transporte".toUpperCase());
        } else {
            mensaje("No se ha Guardado Transporte".toUpperCase());
        }

    }

    //PRINCIPAL
    public Principal() {
        initComponents();
        setTitle("Login");
  //      setIconImage(getIconImage());
        setLocationRelativeTo(null);
        setResizable(false);
        //setExtendedState(this.MAXIMIZED_HORIZ);
        jPanelVentanaProfesor.setVisible(false);
        jPanelVentanaSuperUsuarioAdministrador.setVisible(false);
        jPanelSolicitud.setVisible(false);
        jPanelModificarSolicitud.setVisible(false);
        jPanelFotosActividad.setVisible(false);
        jTableTodasLasSolicitudes.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent Mouse_evt) {

                JTable table = (JTable) Mouse_evt.getSource();
                Point point = (Point) Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);

                if (Mouse_evt.getClickCount() == 1) {

                    idSolicitud = Integer.parseInt(jTableTodasLasSolicitudes.getValueAt(jTableTodasLasSolicitudes.getSelectedRow(), 0).toString());
                    idProfesor = Integer.parseInt(jTableTodasLasSolicitudes.getValueAt(jTableTodasLasSolicitudes.getSelectedRow(), 1).toString());
                    String aux = jTableTodasLasSolicitudes.getValueAt(jTableTodasLasSolicitudes.getSelectedRow(), 13).toString();
                    String msEstado = aux.substring(0, 1) + aux.substring(1, aux.length()).toLowerCase();

                    jTextFieldMSNombreActividad.setText(jTableTodasLasSolicitudes.getValueAt(jTableTodasLasSolicitudes.getSelectedRow(), 2).toString());
                    jComboBoxMSEstado.enable(true);
                    jComboBoxMSEstado.setSelectedItem(msEstado);
                    jTextFieldMSComentarioEstado.enable(true);

                }

            }

        });
        jPanelNuevoProfesor.setVisible(false);
        jPanelModificacionSolicitud.setVisible(false);
        jTableSolicitudesAprobadas.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent Mouse_evt) {

                JTable table = (JTable) Mouse_evt.getSource();
                Point point = (Point) Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);

                if (Mouse_evt.getClickCount() == 1) {

                    jTextFieldModificacionIdSolicitud.setText(jTableSolicitudesAprobadas.getValueAt(jTableSolicitudesAprobadas.getSelectedRow(), 0).toString());

                }

            }

        });

        jTableSolicitudes.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent Mouse_evt) {

                JTable table = (JTable) Mouse_evt.getSource();
                Point point = (Point) Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);

                if (Mouse_evt.getClickCount() == 1) {

                    jTextFieldNumeroDeLaActividad.setText(jTableSolicitudes.getValueAt(jTableSolicitudes.getSelectedRow(), 0).toString());

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
        jLabelCorreo = new javax.swing.JLabel();
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
        jPanelModificacionSolicitud = new javax.swing.JPanel();
        jLabelModificacionSolicitud = new javax.swing.JLabel();
        jLabelModificacionNombreActividad = new javax.swing.JLabel();
        jTextFieldModificacionNombreActividad = new javax.swing.JTextField();
        jLabelModificacionTipoActividad = new javax.swing.JLabel();
        jTextFieldModificacionTipoActividad = new javax.swing.JTextField();
        jLabelModificacionDepartamento = new javax.swing.JLabel();
        jTextFieldModificacionDepartamento = new javax.swing.JTextField();
        jLabelModificacionPreVista = new javax.swing.JLabel();
        jTextFieldModificacionPreVista = new javax.swing.JTextField();
        jLabelModificacionTransporte = new javax.swing.JLabel();
        jTextFieldModificacionTransporte = new javax.swing.JTextField();
        jLabelModificacionFechaInicial = new javax.swing.JLabel();
        jTextFieldModificacionFechaInicial = new javax.swing.JTextField();
        jLabelModificacionFechaFinal = new javax.swing.JLabel();
        jTextFieldModificacionFechaFinal = new javax.swing.JTextField();
        jLabelModificacionHoraInicial = new javax.swing.JLabel();
        jTextFieldModificacionHoraInicial = new javax.swing.JTextField();
        jLabelModificacionHoraFinal = new javax.swing.JLabel();
        jTextFieldModificacionHoraFinal = new javax.swing.JTextField();
        jLabelModificacionAlojamiento = new javax.swing.JLabel();
        jTextFieldModificacionAlojamiento = new javax.swing.JTextField();
        jLabelModificacionComentarioAdicional = new javax.swing.JLabel();
        jTextFieldModificacionComentarioAdicional = new javax.swing.JTextField();
        jLabelModificacionCantidadAlumnos = new javax.swing.JLabel();
        jTextFieldModificacionCantidadAlumnos = new javax.swing.JTextField();
        jButtonModificacionrSolicitudAprobada = new javax.swing.JButton();
        jButtonCerrarModificacionSolicitud = new javax.swing.JButton();
        jTextFieldModificacionIdSolicitud = new javax.swing.JTextField();
        jLabelTransporteContratado = new javax.swing.JLabel();
        jComboBoxTransporteContrado = new javax.swing.JComboBox<>();
        jLabelPresupuesto = new javax.swing.JLabel();
        jTextFieldPresupuesto = new javax.swing.JTextField();
        jLabelComentarioOpcional = new javax.swing.JLabel();
        jTextFieldComentarioOpcional = new javax.swing.JTextField();
        jButtonSeleccionarTranspote = new javax.swing.JButton();
        jLabelListaTransporte = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jListTransportes = new javax.swing.JList<>();
        jLabelAlumnoAsistencia = new javax.swing.JLabel();
        jTextFieldAlumnosAsistencia = new javax.swing.JTextField();
        jPanelFotosActividad = new javax.swing.JPanel();
        jLabelNumeroDeLaActividad = new javax.swing.JLabel();
        jTextFieldNumeroDeLaActividad = new javax.swing.JTextField();
        jLabelUrlFotos = new javax.swing.JLabel();
        jTextFieldURL = new javax.swing.JTextField();
        jLabelDescripcionFotos = new javax.swing.JLabel();
        jTextFieldDescripcionFotos = new javax.swing.JTextField();
        jLabelListaFotos = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableFotos = new javax.swing.JTable();
        jButtonAgregarListaFotos = new javax.swing.JButton();
        jButtonGurdarFotos = new javax.swing.JButton();
        jButtonCerrarVentanaFotos = new javax.swing.JButton();
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
        jButtonCerrarVentana = new javax.swing.JButton();
        jLabelVentanaModificarEstadoSolicitud = new javax.swing.JLabel();
        jPanelNuevoProfesor = new javax.swing.JPanel();
        jLabelCerrarNuevoProfesor = new javax.swing.JLabel();
        jLabelTituloNuevoProfesor = new javax.swing.JLabel();
        jLabelNombreNuevoProfesor = new javax.swing.JLabel();
        jTextFieldNombreNuevoProfesor = new javax.swing.JTextField();
        jLabelApellidosNuevoProfesor = new javax.swing.JLabel();
        jTextFieldApellidosNuevoProfesor = new javax.swing.JTextField();
        jLabelDniNuevoProfesor = new javax.swing.JLabel();
        jTextFieldDniNuevoProfesor = new javax.swing.JTextField();
        jLabelDepartamentoNuevoProfesor = new javax.swing.JLabel();
        jComboBoxDepartamentoNuevoProfesor = new javax.swing.JComboBox<>();
        jLabelPerfilNuevoProfesor = new javax.swing.JLabel();
        jComboBoxPerfilNuevoProfesor = new javax.swing.JComboBox<>();
        jButtonGuardarNuevoProfesor = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButtonNuevoProfesor = new javax.swing.JButton();
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
        jLabelProfesorResponsable = new javax.swing.JLabel();
        jComboBoxProfesorResponsable = new javax.swing.JComboBox<>();
        jLabelProfesorParticipante = new javax.swing.JLabel();
        jComboBoxProfesorParticipantes = new javax.swing.JComboBox<>();
        jButtonSeleccionarProfesorParticipante = new javax.swing.JButton();
        jRadioButtonCursos = new javax.swing.JRadioButton();
        jComboBoxCursos = new javax.swing.JComboBox<>();
        jButtonSeleccionarCurso = new javax.swing.JButton();
        jRadioButtonGrupos = new javax.swing.JRadioButton();
        jComboBoxGrupos = new javax.swing.JComboBox<>();
        jButtonSeleccionarGrupos = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jListProfesoresParticipantes = new javax.swing.JList<>();
        jLabelListaProfesoresParticipantes = new javax.swing.JLabel();
        jLabelListaGrupos = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jListGrupos = new javax.swing.JList<>();
        jLabelNumeroActividad = new javax.swing.JLabel();
        jTextFieldNumeroActividad = new javax.swing.JTextField();

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
        jPasswordContraseña.setText("contraseña");
        jPasswordContraseña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordContraseñaMouseClicked(evt);
            }
        });
        jPasswordContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordContraseñaActionPerformed(evt);
            }
        });

        jLabelCorreo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelCorreo.setText("@educantabria.es");

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
                        .addComponent(jLabelCorreo)))
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
                    .addComponent(jLabelCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jTableSolicitudes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
        jTableSolicitudes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSolicitudesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableSolicitudes);

        jLabelSolicitudesAprobadas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelSolicitudesAprobadas.setText("Solicitudes Aprobadas");

        jTableSolicitudesAprobadas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
        jTableSolicitudesAprobadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSolicitudesAprobadasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableSolicitudesAprobadas);

        jButtonNuevaSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButtonNuevaSolicitud.setText("Nueva Solicitud");
        jButtonNuevaSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevaSolicitudActionPerformed(evt);
            }
        });

        jButtonSalirVentanaProfesor.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButtonSalirVentanaProfesor.setText("Cerrar Sesion");
        jButtonSalirVentanaProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirVentanaProfesorActionPerformed(evt);
            }
        });

        jPanelModificacionSolicitud.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelModificacionSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelModificacionSolicitud.setText("Modificacion de Solicitud  Aprobada Nª : ");

        jLabelModificacionNombreActividad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelModificacionNombreActividad.setText("Nombre de la Actividad :");

        jTextFieldModificacionNombreActividad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldModificacionNombreActividad.setEnabled(false);

        jLabelModificacionTipoActividad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelModificacionTipoActividad.setText("Tipo de Actividad :");

        jTextFieldModificacionTipoActividad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldModificacionTipoActividad.setEnabled(false);

        jLabelModificacionDepartamento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelModificacionDepartamento.setText("Departamento :");

        jTextFieldModificacionDepartamento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldModificacionDepartamento.setEnabled(false);

        jLabelModificacionPreVista.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelModificacionPreVista.setText("Pre - Vista :");

        jTextFieldModificacionPreVista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldModificacionPreVista.setEnabled(false);

        jLabelModificacionTransporte.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelModificacionTransporte.setText("Transporte : ");

        jTextFieldModificacionTransporte.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldModificacionTransporte.setEnabled(false);

        jLabelModificacionFechaInicial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelModificacionFechaInicial.setText("Fecha Inicial :");

        jTextFieldModificacionFechaInicial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldModificacionFechaInicial.setEnabled(false);
        jTextFieldModificacionFechaInicial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldModificacionFechaInicialMouseClicked(evt);
            }
        });

        jLabelModificacionFechaFinal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelModificacionFechaFinal.setText("Fecha Final : ");

        jTextFieldModificacionFechaFinal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldModificacionFechaFinal.setEnabled(false);
        jTextFieldModificacionFechaFinal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldModificacionFechaFinalMouseClicked(evt);
            }
        });

        jLabelModificacionHoraInicial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelModificacionHoraInicial.setText("Hora Inicial : ");

        jTextFieldModificacionHoraInicial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldModificacionHoraInicial.setEnabled(false);
        jTextFieldModificacionHoraInicial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldModificacionHoraInicialMouseClicked(evt);
            }
        });

        jLabelModificacionHoraFinal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelModificacionHoraFinal.setText("Hora Final : ");

        jTextFieldModificacionHoraFinal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldModificacionHoraFinal.setEnabled(false);
        jTextFieldModificacionHoraFinal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldModificacionHoraFinalMouseClicked(evt);
            }
        });

        jLabelModificacionAlojamiento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelModificacionAlojamiento.setText("Alojamiento : ");

        jTextFieldModificacionAlojamiento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldModificacionAlojamiento.setEnabled(false);

        jLabelModificacionComentarioAdicional.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelModificacionComentarioAdicional.setText("Comentario Adicional :");

        jTextFieldModificacionComentarioAdicional.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldModificacionComentarioAdicional.setEnabled(false);

        jLabelModificacionCantidadAlumnos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelModificacionCantidadAlumnos.setText("Cant. de Alumnos :");

        jTextFieldModificacionCantidadAlumnos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldModificacionCantidadAlumnos.setEnabled(false);

        jButtonModificacionrSolicitudAprobada.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonModificacionrSolicitudAprobada.setText("Modificar Solicitud Aprobada");
        jButtonModificacionrSolicitudAprobada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificacionrSolicitudAprobadaActionPerformed(evt);
            }
        });

        jButtonCerrarModificacionSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonCerrarModificacionSolicitud.setText("Cerrar Ventana");
        jButtonCerrarModificacionSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarModificacionSolicitudActionPerformed(evt);
            }
        });

        jTextFieldModificacionIdSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextFieldModificacionIdSolicitud.setEnabled(false);

        jLabelTransporteContratado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTransporteContratado.setText("Transporte :");

        jComboBoxTransporteContrado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxTransporteContrado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelPresupuesto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPresupuesto.setText("Presupuesto : ");

        jTextFieldPresupuesto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabelComentarioOpcional.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelComentarioOpcional.setText("Comentario (Opcional) :");

        jTextFieldComentarioOpcional.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButtonSeleccionarTranspote.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonSeleccionarTranspote.setText("Seleccionar Transporte");
        jButtonSeleccionarTranspote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarTranspoteActionPerformed(evt);
            }
        });

        jLabelListaTransporte.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelListaTransporte.setText("Lista Transporte :");

        jListTransportes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(jListTransportes);

        jLabelAlumnoAsistencia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAlumnoAsistencia.setText("Alumnos que Asisten :");

        jTextFieldAlumnosAsistencia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanelModificacionSolicitudLayout = new javax.swing.GroupLayout(jPanelModificacionSolicitud);
        jPanelModificacionSolicitud.setLayout(jPanelModificacionSolicitudLayout);
        jPanelModificacionSolicitudLayout.setHorizontalGroup(
            jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModificacionSolicitudLayout.createSequentialGroup()
                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelModificacionSolicitudLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelModificacionSolicitudLayout.createSequentialGroup()
                                .addComponent(jLabelModificacionSolicitud)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldModificacionIdSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelModificacionSolicitudLayout.createSequentialGroup()
                                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelModificacionNombreActividad, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelModificacionTransporte)
                                    .addComponent(jLabelModificacionDepartamento)
                                    .addComponent(jLabelModificacionFechaInicial)
                                    .addComponent(jLabelModificacionFechaFinal)
                                    .addComponent(jLabelModificacionComentarioAdicional))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldModificacionTransporte, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                    .addComponent(jTextFieldModificacionDepartamento)
                                    .addComponent(jTextFieldModificacionNombreActividad))
                                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelModificacionSolicitudLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextFieldModificacionHoraInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanelModificacionSolicitudLayout.createSequentialGroup()
                                                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabelModificacionPreVista)
                                                    .addComponent(jLabelModificacionTipoActividad)
                                                    .addComponent(jLabelModificacionAlojamiento))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextFieldModificacionPreVista, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                                    .addComponent(jTextFieldModificacionAlojamiento)
                                                    .addComponent(jTextFieldModificacionTipoActividad)))))
                                    .addGroup(jPanelModificacionSolicitudLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelModificacionCantidadAlumnos))))))
                    .addGroup(jPanelModificacionSolicitudLayout.createSequentialGroup()
                        .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelModificacionSolicitudLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabelPresupuesto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelModificacionSolicitudLayout.createSequentialGroup()
                                .addContainerGap(24, Short.MAX_VALUE)
                                .addComponent(jLabelComentarioOpcional)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonModificacionrSolicitudAprobada)
                            .addGroup(jPanelModificacionSolicitudLayout.createSequentialGroup()
                                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldPresupuesto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                    .addComponent(jTextFieldModificacionFechaFinal, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldModificacionFechaInicial, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldModificacionComentarioAdicional, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelModificacionHoraInicial)
                                    .addComponent(jLabelModificacionHoraFinal)
                                    .addComponent(jLabelAlumnoAsistencia))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldModificacionHoraFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldModificacionCantidadAlumnos, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                    .addComponent(jTextFieldAlumnosAsistencia)))
                            .addComponent(jTextFieldComentarioOpcional))))
                .addGap(27, 27, 27)
                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelModificacionSolicitudLayout.createSequentialGroup()
                        .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTransporteContratado)
                            .addComponent(jLabelListaTransporte))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonSeleccionarTranspote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxTransporteContrado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane6)))
                    .addComponent(jButtonCerrarModificacionSolicitud))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanelModificacionSolicitudLayout.setVerticalGroup(
            jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelModificacionSolicitudLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelModificacionSolicitud)
                    .addComponent(jTextFieldModificacionIdSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldModificacionNombreActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelModificacionTipoActividad)
                    .addComponent(jTextFieldModificacionTipoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelModificacionNombreActividad)
                    .addComponent(jLabelTransporteContratado)
                    .addComponent(jComboBoxTransporteContrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldModificacionDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelModificacionPreVista)
                        .addComponent(jTextFieldModificacionPreVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSeleccionarTranspote))
                    .addComponent(jLabelModificacionDepartamento))
                .addGap(26, 26, 26)
                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelModificacionSolicitudLayout.createSequentialGroup()
                        .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldModificacionTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelModificacionAlojamiento)
                            .addComponent(jTextFieldModificacionAlojamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelModificacionTransporte)
                            .addComponent(jLabelListaTransporte))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldModificacionHoraInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelModificacionHoraInicial)
                            .addComponent(jTextFieldModificacionFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelModificacionFechaInicial)))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldModificacionFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelModificacionHoraFinal)
                    .addComponent(jTextFieldModificacionHoraFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelModificacionFechaFinal))
                .addGap(18, 18, 18)
                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldModificacionComentarioAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldModificacionCantidadAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelModificacionComentarioAdicional)
                    .addComponent(jLabelModificacionCantidadAlumnos))
                .addGap(18, 18, 18)
                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPresupuesto)
                    .addComponent(jTextFieldAlumnosAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAlumnoAsistencia))
                .addGap(18, 18, 18)
                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldComentarioOpcional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelComentarioOpcional))
                .addGap(38, 38, 38)
                .addGroup(jPanelModificacionSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonModificacionrSolicitudAprobada)
                    .addComponent(jButtonCerrarModificacionSolicitud))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanelFotosActividad.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelNumeroDeLaActividad.setText("Numero de la Actividad : ");

        jTextFieldNumeroDeLaActividad.setEnabled(false);

        jLabelUrlFotos.setText("URL :");

        jLabelDescripcionFotos.setText("Descripcion :");

        jLabelListaFotos.setText("Lista de Fotos :");

        jTableFotos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane5.setViewportView(jTableFotos);

        jButtonAgregarListaFotos.setText("Agregar a la Lista Fotos");
        jButtonAgregarListaFotos.setToolTipText("");
        jButtonAgregarListaFotos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarListaFotosActionPerformed(evt);
            }
        });

        jButtonGurdarFotos.setText("Guardar Fotos");
        jButtonGurdarFotos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGurdarFotosActionPerformed(evt);
            }
        });

        jButtonCerrarVentanaFotos.setText("Cerrar Ventana");
        jButtonCerrarVentanaFotos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarVentanaFotosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFotosActividadLayout = new javax.swing.GroupLayout(jPanelFotosActividad);
        jPanelFotosActividad.setLayout(jPanelFotosActividadLayout);
        jPanelFotosActividadLayout.setHorizontalGroup(
            jPanelFotosActividadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFotosActividadLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanelFotosActividadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelListaFotos)
                    .addComponent(jLabelDescripcionFotos)
                    .addComponent(jLabelUrlFotos)
                    .addComponent(jTextFieldDescripcionFotos)
                    .addComponent(jTextFieldURL)
                    .addGroup(jPanelFotosActividadLayout.createSequentialGroup()
                        .addComponent(jLabelNumeroDeLaActividad)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldNumeroDeLaActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonAgregarListaFotos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButtonGurdarFotos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCerrarVentanaFotos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanelFotosActividadLayout.setVerticalGroup(
            jPanelFotosActividadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFotosActividadLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelFotosActividadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNumeroDeLaActividad)
                    .addComponent(jTextFieldNumeroDeLaActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelUrlFotos)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelDescripcionFotos)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldDescripcionFotos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonAgregarListaFotos)
                .addGap(31, 31, 31)
                .addComponent(jLabelListaFotos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jButtonGurdarFotos)
                .addGap(18, 18, 18)
                .addComponent(jButtonCerrarVentanaFotos)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelVentanaProfesorLayout = new javax.swing.GroupLayout(jPanelVentanaProfesor);
        jPanelVentanaProfesor.setLayout(jPanelVentanaProfesorLayout);
        jPanelVentanaProfesorLayout.setHorizontalGroup(
            jPanelVentanaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVentanaProfesorLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanelVentanaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelSolicitud)
                    .addComponent(jLabelSolicitudesAprobadas)
                    .addGroup(jPanelVentanaProfesorLayout.createSequentialGroup()
                        .addComponent(jButtonNuevaSolicitud)
                        .addGap(30, 30, 30)
                        .addComponent(jButtonSalirVentanaProfesor))
                    .addGroup(jPanelVentanaProfesorLayout.createSequentialGroup()
                        .addComponent(jPanelModificacionSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jPanelFotosActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1390, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanelVentanaProfesorLayout.setVerticalGroup(
            jPanelVentanaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVentanaProfesorLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabelSolicitud)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelSolicitudesAprobadas)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanelVentanaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelFotosActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelModificacionSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanelVentanaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNuevaSolicitud)
                    .addComponent(jButtonSalirVentanaProfesor))
                .addContainerGap(118, Short.MAX_VALUE))
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
        jButtonCerrarVentanaSuperAdaministrador.setText("Cerrar Sesion");
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
        jButtonActualizarModificarSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarModificarSolicitudActionPerformed(evt);
            }
        });

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

        jButtonCerrarVentana.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonCerrarVentana.setText("Cerrar Ventana");
        jButtonCerrarVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarVentanaActionPerformed(evt);
            }
        });

        jLabelVentanaModificarEstadoSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelVentanaModificarEstadoSolicitud.setText("Ventana Modificar Estado de la Solicitud :");

        javax.swing.GroupLayout jPanelModificarSolicitudLayout = new javax.swing.GroupLayout(jPanelModificarSolicitud);
        jPanelModificarSolicitud.setLayout(jPanelModificarSolicitudLayout);
        jPanelModificarSolicitudLayout.setHorizontalGroup(
            jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModificarSolicitudLayout.createSequentialGroup()
                .addGroup(jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelModificarSolicitudLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelCerrarModificarionSolicitud))
                    .addGroup(jPanelModificarSolicitudLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelVentanaModificarEstadoSolicitud)
                            .addGroup(jPanelModificarSolicitudLayout.createSequentialGroup()
                                .addGroup(jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelMSNombreActividad)
                                    .addComponent(jLabelMSEstado)
                                    .addComponent(jLabelMSComentarioEstado)
                                    .addComponent(jButtonActualizarModificarSolicitud, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(59, 59, 59)
                                .addGroup(jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxMSEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldMSNombreActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldMSComentarioEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonCerrarVentana))))
                        .addGap(0, 28, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelModificarSolicitudLayout.setVerticalGroup(
            jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModificarSolicitudLayout.createSequentialGroup()
                .addGroup(jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelModificarSolicitudLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabelCerrarModificarionSolicitud)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelModificarSolicitudLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelVentanaModificarEstadoSolicitud)
                        .addGap(18, 18, 18)))
                .addGroup(jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMSNombreActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMSNombreActividad))
                .addGap(18, 18, 18)
                .addGroup(jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxMSEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMSEstado))
                .addGap(18, 18, 18)
                .addGroup(jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMSComentarioEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMSComentarioEstado))
                .addGap(18, 18, 18)
                .addGroup(jPanelModificarSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonActualizarModificarSolicitud)
                    .addComponent(jButtonCerrarVentana))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanelNuevoProfesor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelCerrarNuevoProfesor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelCerrarNuevoProfesor.setText(" X ");
        jLabelCerrarNuevoProfesor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelCerrarNuevoProfesor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCerrarNuevoProfesorMouseClicked(evt);
            }
        });

        jLabelTituloNuevoProfesor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTituloNuevoProfesor.setText("Nuevo Profesor :");

        jLabelNombreNuevoProfesor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelNombreNuevoProfesor.setText("Nombre :");

        jTextFieldNombreNuevoProfesor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabelApellidosNuevoProfesor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelApellidosNuevoProfesor.setText("Apellidos :");

        jTextFieldApellidosNuevoProfesor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabelDniNuevoProfesor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDniNuevoProfesor.setText("Dni :");

        jTextFieldDniNuevoProfesor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabelDepartamentoNuevoProfesor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDepartamentoNuevoProfesor.setText("Departamento :");

        jComboBoxDepartamentoNuevoProfesor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxDepartamentoNuevoProfesor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelPerfilNuevoProfesor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPerfilNuevoProfesor.setText("Perfil :");

        jComboBoxPerfilNuevoProfesor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxPerfilNuevoProfesor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonGuardarNuevoProfesor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonGuardarNuevoProfesor.setText("Guardar");
        jButtonGuardarNuevoProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarNuevoProfesorActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Cerrar Ventana Nuevo Profesor");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelNuevoProfesorLayout = new javax.swing.GroupLayout(jPanelNuevoProfesor);
        jPanelNuevoProfesor.setLayout(jPanelNuevoProfesorLayout);
        jPanelNuevoProfesorLayout.setHorizontalGroup(
            jPanelNuevoProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNuevoProfesorLayout.createSequentialGroup()
                .addGroup(jPanelNuevoProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNuevoProfesorLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelNuevoProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombreNuevoProfesor)
                            .addComponent(jLabelApellidosNuevoProfesor)
                            .addComponent(jLabelTituloNuevoProfesor)))
                    .addGroup(jPanelNuevoProfesorLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jButtonGuardarNuevoProfesor)
                        .addGap(70, 70, 70)
                        .addComponent(jButton1))
                    .addGroup(jPanelNuevoProfesorLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabelDepartamentoNuevoProfesor)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelNuevoProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNombreNuevoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelNuevoProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelCerrarNuevoProfesor)
                                .addGroup(jPanelNuevoProfesorLayout.createSequentialGroup()
                                    .addGroup(jPanelNuevoProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelNuevoProfesorLayout.createSequentialGroup()
                                            .addComponent(jTextFieldApellidosNuevoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabelDniNuevoProfesor))
                                        .addGroup(jPanelNuevoProfesorLayout.createSequentialGroup()
                                            .addComponent(jComboBoxDepartamentoNuevoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabelPerfilNuevoProfesor)))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanelNuevoProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBoxPerfilNuevoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldDniNuevoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanelNuevoProfesorLayout.setVerticalGroup(
            jPanelNuevoProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNuevoProfesorLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelNuevoProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCerrarNuevoProfesor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTituloNuevoProfesor))
                .addGap(18, 18, 18)
                .addGroup(jPanelNuevoProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombreNuevoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombreNuevoProfesor))
                .addGap(18, 18, 18)
                .addGroup(jPanelNuevoProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDniNuevoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelApellidosNuevoProfesor)
                    .addComponent(jTextFieldApellidosNuevoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDniNuevoProfesor))
                .addGap(18, 18, 18)
                .addGroup(jPanelNuevoProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxDepartamentoNuevoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDepartamentoNuevoProfesor)
                    .addComponent(jLabelPerfilNuevoProfesor)
                    .addComponent(jComboBoxPerfilNuevoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanelNuevoProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardarNuevoProfesor)
                    .addComponent(jButton1))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jButtonNuevoProfesor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonNuevoProfesor.setText("Nuevo Profesor");
        jButtonNuevoProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoProfesorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelVentanaSuperUsuarioAdministradorLayout = new javax.swing.GroupLayout(jPanelVentanaSuperUsuarioAdministrador);
        jPanelVentanaSuperUsuarioAdministrador.setLayout(jPanelVentanaSuperUsuarioAdministradorLayout);
        jPanelVentanaSuperUsuarioAdministradorLayout.setHorizontalGroup(
            jPanelVentanaSuperUsuarioAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createSequentialGroup()
                        .addComponent(jButtonNuevoProfesor)
                        .addGap(74, 74, 74)
                        .addComponent(jButtonCerrarVentanaSuperAdaministrador)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createSequentialGroup()
                        .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonActualizarCSVprofesor)
                            .addComponent(jButtonActualizarJefesDepartamentos)
                            .addComponent(jLabelTituloVentanaSuperUsuarioAdministrador)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1400, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(36, Short.MAX_VALUE))
                    .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createSequentialGroup()
                        .addComponent(jPanelNuevoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelModificarSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))))
        );
        jPanelVentanaSuperUsuarioAdministradorLayout.setVerticalGroup(
            jPanelVentanaSuperUsuarioAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabelTituloVentanaSuperUsuarioAdministrador)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelNuevoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelModificarSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanelVentanaSuperUsuarioAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNuevoProfesor)
                    .addComponent(jButtonCerrarVentanaSuperAdaministrador))
                .addGap(18, 18, 18)
                .addComponent(jButtonActualizarCSVprofesor)
                .addGap(18, 18, 18)
                .addComponent(jButtonActualizarJefesDepartamentos)
                .addContainerGap(252, Short.MAX_VALUE))
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
        jComboBoxTipoActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoActividadActionPerformed(evt);
            }
        });

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
        jRadioButtonPreVistaSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPreVistaSiActionPerformed(evt);
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
        jTextFieldCantidadAlumnos.setEnabled(false);

        jButtonCrearSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonCrearSolicitud.setText("Crear Solicitud");
        jButtonCrearSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearSolicitudActionPerformed(evt);
            }
        });

        jButtonCerrarSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonCerrarSolicitud.setText("Cerrar Ventana");
        jButtonCerrarSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarSolicitudActionPerformed(evt);
            }
        });

        jLabelProfesorResponsable.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelProfesorResponsable.setText("Profesor Responsable :");

        jComboBoxProfesorResponsable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxProfesorResponsable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelProfesorParticipante.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelProfesorParticipante.setText("Profesor Participante : ");

        jComboBoxProfesorParticipantes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxProfesorParticipantes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxProfesorParticipantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxProfesorParticipantesMouseClicked(evt);
            }
        });

        jButtonSeleccionarProfesorParticipante.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonSeleccionarProfesorParticipante.setText("Seleccionar Profesores Participante");
        jButtonSeleccionarProfesorParticipante.setToolTipText("");
        jButtonSeleccionarProfesorParticipante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarProfesorParticipanteActionPerformed(evt);
            }
        });

        jRadioButtonCursos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButtonCursos.setText("Curso :");
        jRadioButtonCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonCursosMouseClicked(evt);
            }
        });
        jRadioButtonCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCursosActionPerformed(evt);
            }
        });

        jComboBoxCursos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxCursos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCursos.setEnabled(false);
        jComboBoxCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxCursosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboBoxCursosMousePressed(evt);
            }
        });

        jButtonSeleccionarCurso.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonSeleccionarCurso.setText("Seleccionar Curso");
        jButtonSeleccionarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarCursoActionPerformed(evt);
            }
        });

        jRadioButtonGrupos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButtonGrupos.setText("Grupos");
        jRadioButtonGrupos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonGruposMouseClicked(evt);
            }
        });

        jComboBoxGrupos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxGrupos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxGrupos.setEnabled(false);

        jButtonSeleccionarGrupos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonSeleccionarGrupos.setText("Seleccionar Grupos");
        jButtonSeleccionarGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarGruposActionPerformed(evt);
            }
        });

        jListProfesoresParticipantes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jListProfesoresParticipantes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane7.setViewportView(jListProfesoresParticipantes);

        jLabelListaProfesoresParticipantes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelListaProfesoresParticipantes.setText("Lista de Profesores Participantes :");

        jLabelListaGrupos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelListaGrupos.setText("Lista Grupos :");

        jListGrupos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(jListGrupos);

        jLabelNumeroActividad.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelNumeroActividad.setText("Nª de la Actividad : ");

        jTextFieldNumeroActividad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextFieldNumeroActividad.setEnabled(false);

        javax.swing.GroupLayout jPanelSolicitudLayout = new javax.swing.GroupLayout(jPanelSolicitud);
        jPanelSolicitud.setLayout(jPanelSolicitudLayout);
        jPanelSolicitudLayout.setHorizontalGroup(
            jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                .addComponent(jLabelNuevaSolicitud)
                                .addGap(55, 55, 55)
                                .addComponent(jButtonCrearSolicitud)
                                .addGap(35, 35, 35)
                                .addComponent(jButtonCerrarSolicitud))
                            .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNombreActividad)
                                    .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelTipoActividad)
                                            .addComponent(jLabelPreVista)
                                            .addComponent(jLabelTransporte)
                                            .addComponent(jLabelDepartamento)
                                            .addComponent(jLabelFechaInicial)
                                            .addComponent(jLabelFechaFinal)))
                                    .addComponent(jLabelNumeroActividad))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                        .addComponent(jTextNombreActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelProfesorResponsable)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxProfesorResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                        .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                                .addComponent(jRadioButtonPreVistaSi)
                                                .addGap(18, 18, 18)
                                                .addComponent(jRadioButtonPreVistaNo))
                                            .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                                .addComponent(jRadioButtonTransporteSi)
                                                .addGap(18, 18, 18)
                                                .addComponent(jRadioButtonTransporteNo))
                                            .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextFieldFechaFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextFieldFechaInicial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(191, 191, 191)
                                        .addComponent(jComboBoxGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonSeleccionarGrupos))
                                    .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                        .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                                .addComponent(jComboBoxTipoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabelProfesorParticipante))
                                            .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                                .addComponent(jComboBoxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jRadioButtonGrupos)
                                                    .addComponent(jRadioButtonCursos))))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                                .addComponent(jComboBoxCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonSeleccionarCurso))
                                            .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                                .addComponent(jComboBoxProfesorParticipantes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonSeleccionarProfesorParticipante))))
                                    .addComponent(jTextFieldNumeroActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelHoraInicial)
                                    .addComponent(jLabelHoraFinal)
                                    .addComponent(jLabelAlojamiento)
                                    .addComponent(jLabelComentarioAdicional)
                                    .addComponent(jLabelCantidadAlumnos))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldCantidadAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldHoraInicial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldHoraFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                            .addComponent(jRadioButtonAlojamientoSi)
                                            .addGap(18, 18, 18)
                                            .addComponent(jRadioButtonAlojamientoNo))
                                        .addComponent(jTextFieldComentarioAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                        .addGap(607, 607, 607)
                        .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelListaProfesoresParticipantes)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelListaGrupos))))
                .addContainerGap(280, Short.MAX_VALUE))
        );
        jPanelSolicitudLayout.setVerticalGroup(
            jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNuevaSolicitud)
                    .addComponent(jButtonCrearSolicitud)
                    .addComponent(jButtonCerrarSolicitud))
                .addGap(18, 18, 18)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNumeroActividad)
                    .addComponent(jTextFieldNumeroActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombreActividad)
                    .addComponent(jTextNombreActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelProfesorResponsable)
                    .addComponent(jComboBoxProfesorResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTipoActividad)
                    .addComponent(jLabelProfesorParticipante)
                    .addComponent(jComboBoxProfesorParticipantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSeleccionarProfesorParticipante))
                .addGap(18, 18, 18)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDepartamento)
                    .addComponent(jRadioButtonCursos)
                    .addComponent(jComboBoxCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSeleccionarCurso))
                .addGap(18, 18, 18)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButtonPreVistaSi)
                        .addComponent(jRadioButtonPreVistaNo)
                        .addComponent(jLabelPreVista)
                        .addComponent(jRadioButtonGrupos))
                    .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSeleccionarGrupos)))
                .addGap(18, 18, 18)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonTransporteSi)
                    .addComponent(jRadioButtonTransporteNo)
                    .addComponent(jLabelTransporte))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelListaProfesoresParticipantes)
                    .addComponent(jLabelListaGrupos))
                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFechaInicial))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFechaFinal)))
                    .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSolicitudLayout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldHoraInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelHoraInicial))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldHoraFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelHoraFinal))
                                .addGap(26, 26, 26)
                                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jRadioButtonAlojamientoSi)
                                    .addComponent(jRadioButtonAlojamientoNo)
                                    .addComponent(jLabelAlojamiento))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldComentarioAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelComentarioAdicional))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldCantidadAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelCantidadAlumnos)))
                            .addGroup(jPanelSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(125, Short.MAX_VALUE))
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
                    cargarSolicitudesAprobadaDelProfesor();

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

        }else if (auxUsu == null){
                 mensaje("Usuario no existe en la base de datos!!!");
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

        int idSol = Integer.parseInt(jTextFieldNumeroActividad.getText());

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

        solicitud = new Solicitud(idSol, getProfesor().getId_Prof(), nombreActividad, tipoActividad, numeroDepartamento, prevista, transporte, fecInicial, fecFinal, horaInicial, horaFinal, alojamiento, comentadioAdicional, estado, "-", cantidadAlumnos);

        if (auxSolicitudDAOImp.guardarSolicitud(solicitud)) {
            mensaje("Solicitud Guardada Correctamente!!!".toUpperCase());
        } else {
            mensaje("No se a podido Guardar Solicitud!!!".toUpperCase());
        }

        int idProf = devuelveIdProfesor(jComboBoxProfesorResponsable.getSelectedItem().toString()); //FUNCIONA CORRECTAMENTE

        guardarProfesorResponsable(idSol, idProf); //FUNCIONA CORRECTAMENTE

        if (guardarProfesoresParticipantes()) {
            mensaje("ERROR: NO SE HA GUARDADO los PROFESORES PARTICIPANTES!!!");
        } else {
            mensaje("PROFESORES PARTICIPANTES GUARDADO CORRECTAMENTE!!!");
        }

        int idCur = devuelveIdCurso(jComboBoxCursos.getSelectedItem().toString());

        if (jRadioButtonCursos.isSelected()) {

            if (guardarCursoActividad(idSol, idCur)) {//FUNCIONA CORRECTAMENTE
                mensaje("CURSO DE LA ACTIVADAD GUARDADO CORRECTEAMENTE!!!");
            } else {
                mensaje("ERROR: NO SE HA GUARDADO EL CURSO DE LA ACTIVIDAD !!!");
            }

        } else if (jRadioButtonGrupos.isSelected()) {

            if (guardarGruposActividad()) {//FUNCIONA CORRECTAMENTE
                mensaje("ERROR: NO SE HA GUARDADO EL GRUPO DE LA ACTIVIDAD !!!");
            } else {
                mensaje("GRUPO DE LA ACTIVIDAD GUARDADO CORRECTAMENTE!!!");
            }

        }

        limpiarPanelSolicitud();

    }//GEN-LAST:event_jButtonCrearSolicitudActionPerformed

    private void jButtonCerrarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarSolicitudActionPerformed
        // TODO add your handling code here:
        jPanelSolicitud.setVisible(false);
        jPanelVentanaProfesor.setVisible(true);
        cargarSolicitudesDelProfesor();
        cargarSolicitudesAprobadaDelProfesor();

    }//GEN-LAST:event_jButtonCerrarSolicitudActionPerformed

    private void jButtonNuevaSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevaSolicitudActionPerformed
        // TODO add your handling code here:
        jPanelVentanaProfesor.setVisible(false);
        jPanelSolicitud.setVisible(true);
        int idSolici = auxSolicitudDAOImp.listar().size() + 1;
        jTextFieldNumeroActividad.setText("" + idSolici);
        comboBoxTipoActividad();
        comboBoxDepartamento();
        comboBoxCursos();
        comboBoxGrupos();
        comboBoxTransporteContratado();
        comboBoxProfesorResponsable();
        comboBoxProfesoresParticipantes();
        cargarModeloListaProfesorParticipantes();
        cargarModeloListaGrupos();
        cargarModeloListaTransporte();

    }//GEN-LAST:event_jButtonNuevaSolicitudActionPerformed

    private void jButtonSalirVentanaProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirVentanaProfesorActionPerformed
        // TODO add your handling code here:
        mensaje("Hasta Luego!!!");
        jPanelVentanaProfesor.setVisible(false);
        jPanelLogin.setVisible(true);
        jTextCorreo.setText("nombre.apellido");
        jPasswordContraseña.setText("contraseña");
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
        jPanelVentanaSuperUsuarioAdministrador.setVisible(false);
        jPanelLogin.setVisible(true);
        jTextCorreo.setText("nombre.apellido");
        jPasswordContraseña.setText("contraseña");
    }//GEN-LAST:event_jButtonCerrarVentanaSuperAdaministradorActionPerformed

    private void jLabelCerrarModificarionSolicitudMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCerrarModificarionSolicitudMouseClicked
        // TODO add your handling code here:
        jPanelModificarSolicitud.setVisible(false);
    }//GEN-LAST:event_jLabelCerrarModificarionSolicitudMouseClicked

    private void jTableTodasLasSolicitudesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTodasLasSolicitudesMouseClicked
        // TODO add your handling code here:
        jPanelModificarSolicitud.setVisible(true);
    }//GEN-LAST:event_jTableTodasLasSolicitudesMouseClicked

    private void jButtonActualizarModificarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarModificarSolicitudActionPerformed
        // TODO add your handling code here:

        String estadoActualizado = jComboBoxMSEstado.getSelectedItem().toString().toString().toLowerCase();
        String comentario = jTextFieldMSComentarioEstado.getText();

        final String sql = "update solicitud set Estado= '" + estadoActualizado + "', ConsultaEstado='" + comentario + "' where idSolicitud= '" + idSolicitud + "'";

        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            int salida = stmt.executeUpdate();

            if (salida != 1) {
                mensaje("Error en la Actualizacion!!!");
            } else {
                mensaje("Actualizacion Correctamente!!!");
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        limpiarPanelModificarSolicitud();
        cargarTodasLasSolicitudes();

    }//GEN-LAST:event_jButtonActualizarModificarSolicitudActionPerformed

    private void jComboBoxTipoActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoActividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoActividadActionPerformed

    private void jButtonCerrarVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarVentanaActionPerformed
        // TODO add your handling code here:
        jPanelModificarSolicitud.setVisible(false);
    }//GEN-LAST:event_jButtonCerrarVentanaActionPerformed

    private void jLabelCerrarNuevoProfesorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCerrarNuevoProfesorMouseClicked
        // TODO add your handling code here:
        jPanelNuevoProfesor.setVisible(false);
    }//GEN-LAST:event_jLabelCerrarNuevoProfesorMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jPanelNuevoProfesor.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonGuardarNuevoProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarNuevoProfesorActionPerformed
        // TODO add your handling code here:
        List<Profesor> aux = auxProfesorDAOImp.listar();

        int cantidadProfesores = aux.size();
        String nombreP = jTextFieldNombreNuevoProfesor.getText();
        String apellidosP = jTextFieldApellidosNuevoProfesor.getText();
        String dni = jTextFieldDniNuevoProfesor.getText();
        String correo = (nombreP + "." + apellidosP + "@educantabria.es").replaceAll(" ", "").toLowerCase();
        String departamento = jComboBoxDepartamentoNuevoProfesor.getSelectedItem().toString();
        String contraseña = (nombreP.substring(0, 3) + apellidosP.substring(0, 3)).toLowerCase();
        String idDepartamento = devuelveIdDepartamento(departamento);
        String perfil = jComboBoxPerfilNuevoProfesor.getSelectedItem().toString().toLowerCase();

        final String sqlProfesor = "insert into profesores(ID_Prof,Nombre,Apellidos,DNI,Email,Estado,Departamento,Perfil) "
                + "values('" + cantidadProfesores + "','" + nombreP + "','" + apellidosP + "','" + dni + "','" + correo + "','" + 1 + "','" + idDepartamento + "','" + perfil + "')";

        try ( PreparedStatement stmt = getConnection().prepareStatement(sqlProfesor);) {

            int salida = stmt.executeUpdate(sqlProfesor);

            if (salida != 1) {

                mensaje(" Error: No se ha guardado Profesor!!!");

            } else {

                mensaje("Se Guardp Correctamente Profesor!!!");

            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        final String sqlUsuario = "insert into usuarios (idUsuarios,Email,PWD) values('" + cantidadProfesores + "','" + correo + "','" + contraseña + "')";

        try ( PreparedStatement stmt = getConnection().prepareStatement(sqlUsuario);) {

            int salida = stmt.executeUpdate(sqlUsuario);

            if (salida != 1) {

                mensaje(" Error: No se ha guardado Usuario!!!");

            } else {

                mensaje("Se Guardp Correctamente Usuario!!!");

            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        limpiarPanelNuevoProfesor();

    }//GEN-LAST:event_jButtonGuardarNuevoProfesorActionPerformed

    private void jButtonNuevoProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoProfesorActionPerformed
        // TODO add your handling code here:
        jPanelNuevoProfesor.setVisible(true);
        comboBoxDepartamentoNuevoProfesor();
        comboBoxPerfilProfesor();

    }//GEN-LAST:event_jButtonNuevoProfesorActionPerformed

    private void jTextFieldModificacionFechaInicialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldModificacionFechaInicialMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldModificacionFechaInicialMouseClicked

    private void jTextFieldModificacionFechaFinalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldModificacionFechaFinalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldModificacionFechaFinalMouseClicked

    private void jTextFieldModificacionHoraInicialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldModificacionHoraInicialMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldModificacionHoraInicialMouseClicked

    private void jTextFieldModificacionHoraFinalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldModificacionHoraFinalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldModificacionHoraFinalMouseClicked


    private void jButtonModificacionrSolicitudAprobadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificacionrSolicitudAprobadaActionPerformed
        // TODO add your handling code here:

        int idSol = Integer.parseInt(jTextFieldModificacionIdSolicitud.getText());

        guardarTransporteActividad();

        Solicitud auxSol = auxSolicitudDAOImp.buscar(idSol);
        double presupuesto = Double.parseDouble(jTextFieldPresupuesto.getText());
        int totalAlumnos = Integer.parseInt(jTextFieldAlumnosAsistencia.getText());
        String comentarios = jTextFieldComentarioOpcional.getText();

        actividaProgramada = new ActividadProgramada(auxSol.isTransporte(), presupuesto, totalAlumnos, comentarios, auxSol.getIdSolicitud(), auxSol.getSolicitante(), auxSol.getNombreActividad(), auxSol.getTipoActividad(),
                auxSol.getDepartamento(), auxSol.isPrevista(), auxSol.isTransporte(), auxSol.getFechaInicial(), auxSol.getFechaFinal(), auxSol.getHoraInicial(), auxSol.getHoraFinal(), auxSol.isAlojamiento(), auxSol.getComentarioAdicional(),
                auxSol.getEstado(), auxSol.getConsultaEstado(), auxSol.getMaximoAlumnos());

        if (auxActividadProgramadaDAOImp.guardarActividadProgramada(actividaProgramada)) {
            mensaje("Activada " + actividaProgramada.getIdSolicitud() + " Aprobada Guardada!!!");
        } else {
            mensaje("Error al Guardar Actividad Programada!!!");
        }


    }//GEN-LAST:event_jButtonModificacionrSolicitudAprobadaActionPerformed

    private void jButtonCerrarModificacionSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarModificacionSolicitudActionPerformed
        // TODO add your handling code here:
        jPanelModificacionSolicitud.setVisible(false);
    }//GEN-LAST:event_jButtonCerrarModificacionSolicitudActionPerformed

    private void jTableSolicitudesAprobadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSolicitudesAprobadasMouseClicked
        // TODO add your handling code here:
        jPanelModificacionSolicitud.setVisible(true);

        int idSoli = Integer.parseInt(jTextFieldModificacionIdSolicitud.getText());

        List<Solicitud> auxS = auxSolicitudDAOImp.listar();

        for (Solicitud s : auxS) {

            if (s.getIdSolicitud() == idSoli && s.getSolicitante() == getProfesor().getId_Prof() && s.getEstado().equals(Estado.APROBADA)) {

                jTextFieldModificacionNombreActividad.setText(s.getNombreActividad());
                jTextFieldModificacionTipoActividad.setText(s.getTipoActividad().toString());
                jTextFieldModificacionDepartamento.setText("" + s.getDepartamento());
                jTextFieldModificacionPreVista.setText(devuelveSiNo(s.isPrevista()));
                jTextFieldModificacionTransporte.setText(devuelveSiNo(s.isTransporte()));
                jTextFieldModificacionFechaInicial.setText(s.formatoFechaInicial());
                jTextFieldModificacionFechaFinal.setText(s.formatoFechaFinal());
                jTextFieldModificacionHoraInicial.setText(s.formatoHoraInicial());
                jTextFieldModificacionHoraFinal.setText(s.formatoHoraFinal());
                jTextFieldModificacionAlojamiento.setText(devuelveSiNo(s.isAlojamiento()));
                jTextFieldModificacionComentarioAdicional.setText(s.getComentarioAdicional());
                jTextFieldModificacionCantidadAlumnos.setText("" + s.getMaximoAlumnos());

            }

        }

        comboBoxCursos();
        comboBoxGrupos();
        comboBoxTransporteContratado();
        comboBoxProfesorResponsable();
        comboBoxProfesoresParticipantes();
        cargarModeloListaProfesorParticipantes();
        cargarModeloListaGrupos();
        cargarModeloListaTransporte();

    }//GEN-LAST:event_jTableSolicitudesAprobadasMouseClicked

    private void jPasswordContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordContraseñaActionPerformed

    private void jButtonSeleccionarTranspoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarTranspoteActionPerformed
        // TODO add your handling code here:

        try {

            String transporte = jComboBoxTransporteContrado.getSelectedItem().toString();

            boolean igual = false;

            for (int i = 0; i < modeloListaTransporte.getSize(); i++) {

                if (transporte.equals(modeloListaTransporte.getElementAt(i))) {

                    mensaje("El Transporte: " + transporte + ", ya esta seleccionado!!!");
                    igual = true;

                }

            }

            if (igual == false) {

                modeloListaTransporte.addElement(transporte);

            }

        } catch (NullPointerException e) {

            mensaje("Seleccione uno o varios Transportes!!!");

        }

    }//GEN-LAST:event_jButtonSeleccionarTranspoteActionPerformed

    private void jRadioButtonPreVistaSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPreVistaSiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonPreVistaSiActionPerformed

    private void jComboBoxProfesorParticipantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxProfesorParticipantesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxProfesorParticipantesMouseClicked

    private void jButtonSeleccionarProfesorParticipanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarProfesorParticipanteActionPerformed
        // TODO add your handling code here:
        try {

            String profesor = jComboBoxProfesorParticipantes.getSelectedItem().toString();

            if (!profesor.equalsIgnoreCase(jComboBoxProfesorResponsable.getSelectedItem().toString())) {

                boolean igual = false;

                for (int i = 0; i < modeloListaProfesoresParticipantes.getSize(); i++) {

                    if (profesor.equals(modeloListaProfesoresParticipantes.getElementAt(i))) {

                        mensaje("El Profesor " + profesor + ", ya esta seleccionado!!!");
                        igual = true;

                    }

                }

                if (igual == false) {

                    modeloListaProfesoresParticipantes.addElement(profesor);

                }

            } else {
                mensaje("No se puede seleccionar al Profesor Responsable como Participante!!!");
            }

        } catch (NullPointerException e) {

            mensaje("Seleccione uno o varios Profesores Participantes!!!");

        }
    }//GEN-LAST:event_jButtonSeleccionarProfesorParticipanteActionPerformed

    private void jRadioButtonCursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonCursosMouseClicked
        // TODO add your handling code here:
        jComboBoxCursos.enable(true);
        jComboBoxGrupos.enable(false);
        jRadioButtonGrupos.setSelected(false);
        jTextFieldCantidadAlumnos.setText("");
        modeloListaGrupos.removeAllElements();
        jComboBoxGrupos.setSelectedIndex(0);
    }//GEN-LAST:event_jRadioButtonCursosMouseClicked

    private void jComboBoxCursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxCursosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCursosMouseClicked

    private void jComboBoxCursosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxCursosMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCursosMousePressed

    private void jButtonSeleccionarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarCursoActionPerformed
        // TODO add your handling code here:
        if (jRadioButtonCursos.isSelected()) {

            cargarCantidadAlumnosCurso();

        } else {

            mensaje("Tiene que seleccionar CURSO!!!");

        }
    }//GEN-LAST:event_jButtonSeleccionarCursoActionPerformed

    private void jRadioButtonGruposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonGruposMouseClicked
        // TODO add your handling code here:
        jComboBoxGrupos.enable(true);
        jComboBoxCursos.enable(false);
        jRadioButtonCursos.setSelected(false);
        jTextFieldCantidadAlumnos.setText("");
        modeloListaGrupos.removeAllElements();
        jComboBoxCursos.setSelectedIndex(0);
    }//GEN-LAST:event_jRadioButtonGruposMouseClicked

    private void jButtonSeleccionarGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarGruposActionPerformed
        // TODO add your handling code here:
        int total = 0;
        String grupo = jComboBoxGrupos.getSelectedItem().toString();

        try {

            if (jRadioButtonGrupos.isSelected()) {

                boolean igual = false;

                for (int i = 0; i < modeloListaGrupos.getSize(); i++) {

                    if (grupo.equals(modeloListaGrupos.getElementAt(i))) {

                        mensaje("El Grupo: " + grupo + ", ya esta seleccionado!!!");
                        igual = true;

                    }

                }

                if (igual == false) {

                    modeloListaGrupos.addElement(grupo);

                }

            } else {

                mensaje("Tiene que seleccionar uno o Varios GRUPOS!!!");

            }

        } catch (NullPointerException e) {

            mensaje("Seleccione uno o varios Profesores Participantes!!!");

        }

        for (int i = 0; i < modeloListaGrupos.getSize(); i++) {

            total += totalAlumnosGrupos(modeloListaGrupos.getElementAt(i).toString());

        }

        jTextFieldCantidadAlumnos.setText("" + total);

    }//GEN-LAST:event_jButtonSeleccionarGruposActionPerformed

    private void jRadioButtonCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCursosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonCursosActionPerformed

    private void jTableSolicitudesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSolicitudesMouseClicked
        // TODO add your handling code here:
        muestraCabeceraTablaFotos();

        String est = jTableSolicitudes.getValueAt(jTableSolicitudes.getSelectedRow(), 12).toString();

        if (est.equalsIgnoreCase("realizada")) {
            jPanelFotosActividad.setVisible(true);
        } else {
            jTextFieldNumeroDeLaActividad.setText("");
            jPanelFotosActividad.setVisible(false);
        }

    }//GEN-LAST:event_jTableSolicitudesMouseClicked

    private void jButtonAgregarListaFotosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarListaFotosActionPerformed
        // TODO add your handling code here:

        boolean igual = false;

        if (jTextFieldURL.getText().equals("") && jTextFieldDescripcionFotos.getText().equals("")) {

            mensaje("Campos Vacios!!!");

        } else {

            for (int i = 0; i < modeloFotos.getRowCount(); i++) {

                if (jTableFotos.getValueAt(i, 0).toString().equals(jTextFieldURL.getText())) {

                    igual = true;
                }

            }

            if (igual) {
                
                mensaje("Url ya Ingresada!!!");
                jTextFieldURL.setText("");
                jTextFieldDescripcionFotos.setText("");
                
            } else {
                
                Object Fila[] = {jTextFieldURL.getText(), jTextFieldDescripcionFotos.getText()};
                modeloFotos.addRow(Fila);
                jTextFieldURL.setText("");
                jTextFieldDescripcionFotos.setText("");

            }

        }


    }//GEN-LAST:event_jButtonAgregarListaFotosActionPerformed

    private void jButtonGurdarFotosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGurdarFotosActionPerformed
        // TODO add your handling code here:
        
        boolean guardado = false;
        
        int idFoto = auxFotosDAOImp.listar().size()+1;
        int actividad = Integer.parseInt(jTextFieldNumeroDeLaActividad.getText());
        
        for (int i = 0; i < modeloFotos.getRowCount(); i++) {

            Fotos f = new Fotos(idFoto, jTableFotos.getValueAt(i, 0).toString(), jTableFotos.getValueAt(i, 1).toString(), actividad);
            auxFotosDAOImp.guardar(f);
            idFoto++;
            guardado = true;

        }
        
        if(guardado){
            mensaje("Fotos Guardadas");
            jTextFieldURL.setText("");
            jTextFieldDescripcionFotos.setText("");
            jTextFieldNumeroDeLaActividad.setText("");
            muestraCabeceraTablaFotos();
            jPanelFotosActividad.setVisible(false);
            jTextFieldURL.requestFocus();
                
        }else{
            mensaje("Error: con las Fotos!!!");
        }
        
    }//GEN-LAST:event_jButtonGurdarFotosActionPerformed

    private void jButtonCerrarVentanaFotosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarVentanaFotosActionPerformed
        // TODO add your handling code here:
        jPanelFotosActividad.setVisible(false);
    }//GEN-LAST:event_jButtonCerrarVentanaFotosActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonActualizarCSVprofesor;
    private javax.swing.JButton jButtonActualizarJefesDepartamentos;
    private javax.swing.JButton jButtonActualizarModificarSolicitud;
    private javax.swing.JButton jButtonAgregarListaFotos;
    private javax.swing.JButton jButtonCerrarModificacionSolicitud;
    private javax.swing.JButton jButtonCerrarSolicitud;
    private javax.swing.JButton jButtonCerrarVentana;
    private javax.swing.JButton jButtonCerrarVentanaFotos;
    private javax.swing.JButton jButtonCerrarVentanaSuperAdaministrador;
    private javax.swing.JButton jButtonCrearSolicitud;
    private javax.swing.JButton jButtonGuardarNuevoProfesor;
    private javax.swing.JButton jButtonGurdarFotos;
    private javax.swing.JButton jButtonIngresar;
    private javax.swing.JButton jButtonModificacionrSolicitudAprobada;
    private javax.swing.JButton jButtonNuevaSolicitud;
    private javax.swing.JButton jButtonNuevoProfesor;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonSalirVentanaProfesor;
    private javax.swing.JButton jButtonSeleccionarCurso;
    private javax.swing.JButton jButtonSeleccionarGrupos;
    private javax.swing.JButton jButtonSeleccionarProfesorParticipante;
    private javax.swing.JButton jButtonSeleccionarTranspote;
    private javax.swing.JComboBox<String> jComboBoxCursos;
    private javax.swing.JComboBox<String> jComboBoxDepartamento;
    private javax.swing.JComboBox<String> jComboBoxDepartamentoNuevoProfesor;
    private javax.swing.JComboBox<String> jComboBoxGrupos;
    private javax.swing.JComboBox<String> jComboBoxMSEstado;
    private javax.swing.JComboBox<String> jComboBoxPerfilNuevoProfesor;
    private javax.swing.JComboBox<String> jComboBoxProfesorParticipantes;
    private javax.swing.JComboBox<String> jComboBoxProfesorResponsable;
    private javax.swing.JComboBox<String> jComboBoxTipoActividad;
    private javax.swing.JComboBox<String> jComboBoxTransporteContrado;
    private javax.swing.JLabel jLabelAlojamiento;
    private javax.swing.JLabel jLabelAlumnoAsistencia;
    private javax.swing.JLabel jLabelApellidosNuevoProfesor;
    private javax.swing.JLabel jLabelCantidadAlumnos;
    private javax.swing.JLabel jLabelCerrarModificarionSolicitud;
    private javax.swing.JLabel jLabelCerrarNuevoProfesor;
    private javax.swing.JLabel jLabelComentarioAdicional;
    private javax.swing.JLabel jLabelComentarioOpcional;
    private javax.swing.JLabel jLabelCorreo;
    private javax.swing.JLabel jLabelDepartamento;
    private javax.swing.JLabel jLabelDepartamentoNuevoProfesor;
    private javax.swing.JLabel jLabelDescripcionFotos;
    private javax.swing.JLabel jLabelDniNuevoProfesor;
    private javax.swing.JLabel jLabelFechaFinal;
    private javax.swing.JLabel jLabelFechaInicial;
    private javax.swing.JLabel jLabelHoraFinal;
    private javax.swing.JLabel jLabelHoraInicial;
    private javax.swing.JLabel jLabelListaFotos;
    private javax.swing.JLabel jLabelListaGrupos;
    private javax.swing.JLabel jLabelListaProfesoresParticipantes;
    private javax.swing.JLabel jLabelListaTransporte;
    private javax.swing.JLabel jLabelMSComentarioEstado;
    private javax.swing.JLabel jLabelMSEstado;
    private javax.swing.JLabel jLabelMSNombreActividad;
    private javax.swing.JLabel jLabelModificacionAlojamiento;
    private javax.swing.JLabel jLabelModificacionCantidadAlumnos;
    private javax.swing.JLabel jLabelModificacionComentarioAdicional;
    private javax.swing.JLabel jLabelModificacionDepartamento;
    private javax.swing.JLabel jLabelModificacionFechaFinal;
    private javax.swing.JLabel jLabelModificacionFechaInicial;
    private javax.swing.JLabel jLabelModificacionHoraFinal;
    private javax.swing.JLabel jLabelModificacionHoraInicial;
    private javax.swing.JLabel jLabelModificacionNombreActividad;
    private javax.swing.JLabel jLabelModificacionPreVista;
    private javax.swing.JLabel jLabelModificacionSolicitud;
    private javax.swing.JLabel jLabelModificacionTipoActividad;
    private javax.swing.JLabel jLabelModificacionTransporte;
    private javax.swing.JLabel jLabelNombreActividad;
    private javax.swing.JLabel jLabelNombreNuevoProfesor;
    private javax.swing.JLabel jLabelNuevaSolicitud;
    private javax.swing.JLabel jLabelNumeroActividad;
    private javax.swing.JLabel jLabelNumeroDeLaActividad;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelPerfilNuevoProfesor;
    private javax.swing.JLabel jLabelPreVista;
    private javax.swing.JLabel jLabelPresupuesto;
    private javax.swing.JLabel jLabelProfesorParticipante;
    private javax.swing.JLabel jLabelProfesorResponsable;
    private javax.swing.JLabel jLabelSolicitud;
    private javax.swing.JLabel jLabelSolicitudesAprobadas;
    private javax.swing.JLabel jLabelTipoActividad;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTituloNuevoProfesor;
    private javax.swing.JLabel jLabelTituloVentanaSuperUsuarioAdministrador;
    private javax.swing.JLabel jLabelTransporte;
    private javax.swing.JLabel jLabelTransporteContratado;
    private javax.swing.JLabel jLabelUrlFotos;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JLabel jLabelVentanaModificarEstadoSolicitud;
    private javax.swing.JList<String> jListGrupos;
    private javax.swing.JList<String> jListProfesoresParticipantes;
    private javax.swing.JList<String> jListTransportes;
    private javax.swing.JPanel jPanelFotosActividad;
    private javax.swing.JPanel jPanelLogin;
    private javax.swing.JPanel jPanelModificacionSolicitud;
    private javax.swing.JPanel jPanelModificarSolicitud;
    private javax.swing.JPanel jPanelNuevoProfesor;
    private javax.swing.JPanel jPanelSolicitud;
    private javax.swing.JPanel jPanelVentanaProfesor;
    private javax.swing.JPanel jPanelVentanaSuperUsuarioAdministrador;
    private javax.swing.JPasswordField jPasswordContraseña;
    private javax.swing.JRadioButton jRadioButtonAlojamientoNo;
    private javax.swing.JRadioButton jRadioButtonAlojamientoSi;
    private javax.swing.JRadioButton jRadioButtonCursos;
    private javax.swing.JRadioButton jRadioButtonGrupos;
    private javax.swing.JRadioButton jRadioButtonPreVistaNo;
    private javax.swing.JRadioButton jRadioButtonPreVistaSi;
    private javax.swing.JRadioButton jRadioButtonTransporteNo;
    private javax.swing.JRadioButton jRadioButtonTransporteSi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTableFotos;
    private javax.swing.JTable jTableSolicitudes;
    private javax.swing.JTable jTableSolicitudesAprobadas;
    private javax.swing.JTable jTableTodasLasSolicitudes;
    private javax.swing.JTextField jTextCorreo;
    private javax.swing.JTextField jTextFieldAlumnosAsistencia;
    private javax.swing.JTextField jTextFieldApellidosNuevoProfesor;
    private javax.swing.JTextField jTextFieldCantidadAlumnos;
    private javax.swing.JTextField jTextFieldComentarioAdicional;
    private javax.swing.JTextField jTextFieldComentarioOpcional;
    private javax.swing.JTextField jTextFieldDescripcionFotos;
    private javax.swing.JTextField jTextFieldDniNuevoProfesor;
    private javax.swing.JTextField jTextFieldFechaFinal;
    private javax.swing.JTextField jTextFieldFechaInicial;
    private javax.swing.JTextField jTextFieldHoraFinal;
    private javax.swing.JTextField jTextFieldHoraInicial;
    private javax.swing.JTextField jTextFieldMSComentarioEstado;
    private javax.swing.JTextField jTextFieldMSNombreActividad;
    private javax.swing.JTextField jTextFieldModificacionAlojamiento;
    private javax.swing.JTextField jTextFieldModificacionCantidadAlumnos;
    private javax.swing.JTextField jTextFieldModificacionComentarioAdicional;
    private javax.swing.JTextField jTextFieldModificacionDepartamento;
    private javax.swing.JTextField jTextFieldModificacionFechaFinal;
    private javax.swing.JTextField jTextFieldModificacionFechaInicial;
    private javax.swing.JTextField jTextFieldModificacionHoraFinal;
    private javax.swing.JTextField jTextFieldModificacionHoraInicial;
    private javax.swing.JTextField jTextFieldModificacionIdSolicitud;
    private javax.swing.JTextField jTextFieldModificacionNombreActividad;
    private javax.swing.JTextField jTextFieldModificacionPreVista;
    private javax.swing.JTextField jTextFieldModificacionTipoActividad;
    private javax.swing.JTextField jTextFieldModificacionTransporte;
    private javax.swing.JTextField jTextFieldNombreNuevoProfesor;
    private javax.swing.JTextField jTextFieldNumeroActividad;
    private javax.swing.JTextField jTextFieldNumeroDeLaActividad;
    private javax.swing.JTextField jTextFieldPresupuesto;
    private javax.swing.JTextField jTextFieldURL;
    private javax.swing.JTextField jTextNombreActividad;
    private javax.swing.JLabel jlLabelImagen;
    // End of variables declaration//GEN-END:variables
}
