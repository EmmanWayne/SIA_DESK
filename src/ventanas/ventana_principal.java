package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet.ColorAttribute;

import conexion.conexion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ventana_principal extends JFrame {

	private JPanel contentPane;
	public static JLabel lblFecha;
	public static JLabel lblNombre;
	public static JLabel lblHora;
	public static JLabel lblEmpresa;
	public static JLabel lblUsuario;
	public static JLabel lblRol;
	public JLabel lblLogoSistema;
	public static String nombres;
	public static String apellidos;

	public static String id_empresa;
	public static String nombre_empresa;
	public static String telefono_empresa;
	public static String direccion_empresa;
	public static String rtn_empresa;
	public static String cai_empresa;
	public static String logo_empresa;
	public static String ri_facturas;
	public static String rf_facturas;
	public static String fecha_limite_facturas;

	public ventana_principal() {
		setResizable(false);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Ventana PRINCIPAL");
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/recursos/logo_sistema.png")));
		final ImageIcon logo = new ImageIcon(getClass().getResource("/recursos/logo_sistema.png"));

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				close();
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(10, 11, 914, 589);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 11, 416, 567);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 36, 396, 192);
		panel_1.add(panel_2);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(104, 101, 282, 17);
		panel_2.add(lblUsuario);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));

		lblRol = new JLabel("Rol");
		lblRol.setBounds(104, 73, 282, 17);
		panel_2.add(lblRol);
		lblRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblRol.setForeground(Color.BLACK);
		lblRol.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));

		lblHora = new JLabel("Hora");
		lblHora.setBounds(104, 129, 282, 21);
		panel_2.add(lblHora);
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setForeground(Color.BLACK);
		lblHora.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));

		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(104, 161, 282, 19);
		panel_2.add(lblFecha);
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setForeground(Color.BLACK);
		lblFecha.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblFecha.setText("domingo 12 de diciembre del 2021");

		lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNombre.setBounds(104, 43, 282, 17);
		panel_2.add(lblNombre);

		JLabel lblRol_1_1 = new JLabel("FECHA");
		lblRol_1_1.setForeground(new Color(0, 128, 128));
		lblRol_1_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblRol_1_1.setBackground(Color.WHITE);
		lblRol_1_1.setBounds(10, 159, 103, 19);
		panel_2.add(lblRol_1_1);

		JLabel lblRol_1_1_1 = new JLabel("HORA");
		lblRol_1_1_1.setForeground(new Color(0, 128, 128));
		lblRol_1_1_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblRol_1_1_1.setBackground(Color.WHITE);
		lblRol_1_1_1.setBounds(10, 129, 103, 21);
		panel_2.add(lblRol_1_1_1);

		JLabel lblRol_1_1_1_1 = new JLabel("PERMISO");
		lblRol_1_1_1_1.setForeground(new Color(0, 128, 128));
		lblRol_1_1_1_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblRol_1_1_1_1.setBackground(Color.WHITE);
		lblRol_1_1_1_1.setBounds(10, 71, 103, 19);
		panel_2.add(lblRol_1_1_1_1);

		JLabel lblRol_1_1_2 = new JLabel("USUARIO");
		lblRol_1_1_2.setForeground(new Color(0, 128, 128));
		lblRol_1_1_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblRol_1_1_2.setBackground(Color.WHITE);
		lblRol_1_1_2.setBounds(10, 101, 103, 17);
		panel_2.add(lblRol_1_1_2);

		JLabel lblRol_1_1_2_1 = new JLabel("EMPLEADO");
		lblRol_1_1_2_1.setForeground(new Color(0, 128, 128));
		lblRol_1_1_2_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblRol_1_1_2_1.setBackground(Color.WHITE);
		lblRol_1_1_2_1.setBounds(10, 43, 103, 17);
		panel_2.add(lblRol_1_1_2_1);

		lblEmpresa = new JLabel("SIA");
		lblEmpresa.setBounds(104, 13, 282, 19);
		panel_2.add(lblEmpresa);
		lblEmpresa.setBackground(Color.WHITE);
		lblEmpresa.setForeground(Color.BLACK);
		lblEmpresa.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblEmpresa.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblRol_1_1_2_1_1 = new JLabel("EMPRESA");
		lblRol_1_1_2_1_1.setForeground(new Color(0, 128, 128));
		lblRol_1_1_2_1_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblRol_1_1_2_1_1.setBackground(Color.WHITE);
		lblRol_1_1_2_1_1.setBounds(10, 13, 103, 19);
		panel_2.add(lblRol_1_1_2_1_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 239, 396, 310);
		panel_1.add(panel_3);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(null);

		lblLogoSistema = new JLabel("");
		lblLogoSistema.setBounds(40, 11, 313, 288);
		panel_3.add(lblLogoSistema);
		final ImageIcon icono = new ImageIcon(logo.getImage().getScaledInstance(lblLogoSistema.getWidth(),
				lblLogoSistema.getHeight(), Image.SCALE_DEFAULT));
		lblLogoSistema.setIcon(icono);

		JLabel lblAcercaDe = new JLabel("Acerca de.");
		lblAcercaDe.setBounds(10, 546, 396, 21);
		panel_1.add(lblAcercaDe);
		lblAcercaDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				acerca_de informacion_sistema = new acerca_de();
				informacion_sistema.setLocationRelativeTo(null);
				informacion_sistema.setVisible(true);
			}
		});
		lblAcercaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcercaDe.setForeground(Color.BLACK);
		lblAcercaDe.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));

		JLabel lblBienvenidoAlSistema = new JLabel("BIENVENIDO AL SISTEMA ADMINISTRATIVO");
		lblBienvenidoAlSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenidoAlSistema.setForeground(Color.BLACK);
		lblBienvenidoAlSistema.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblBienvenidoAlSistema.setBounds(10, 11, 396, 21);
		panel_1.add(lblBienvenidoAlSistema);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(675, 382, 229, 196);
		panel.add(panel_5);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_5.setLayout(null);

		JButton btnCerrarSesion = new JButton("CERRAR SESI\u00D3N");
		btnCerrarSesion.setBounds(10, 92, 207, 41);
		panel_5.add(btnCerrarSesion);
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_login login = new ventana_login();
				login.setLocationRelativeTo(null);
				login.setVisible(true);
				Timer time = new Timer();
				time.schedule(login.tarea, 0, 1000);
				login.verificarConfiguraciones();
				dispose();
			}
		});
		btnCerrarSesion.setForeground(Color.WHITE);
		btnCerrarSesion.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnCerrarSesion.setBackground(new Color(255, 140, 0));

		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnSalir.setBounds(10, 143, 207, 41);
		panel_5.add(btnSalir);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnSalir.setBackground(Color.RED);

		JLabel lblMduloDeEmpresa = new JLabel("SISTEMA:");
		lblMduloDeEmpresa.setBounds(10, 10, 207, 21);
		panel_5.add(lblMduloDeEmpresa);
		lblMduloDeEmpresa.setForeground(Color.BLACK);
		lblMduloDeEmpresa.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));

		JButton btnConfiguracion = new JButton("CONFIGURACI\u00D3N");
		btnConfiguracion.setBounds(10, 41, 207, 41);
		panel_5.add(btnConfiguracion);
		btnConfiguracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_configuracion config = new ventana_configuracion();
				config.setLocationRelativeTo(null);
				config.setVisible(true);
				config.verificarConfiguraciones();
				if (config.id_empresa == null) {
					config.btnActualizar.setEnabled(false);
					config.btnGuardar.setEnabled(true);
				} else {
					config.btnActualizar.setEnabled(true);
					config.btnGuardar.setEnabled(false);
				}
				dispose();
			}
		});
		btnConfiguracion.setForeground(Color.WHITE);
		btnConfiguracion.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnConfiguracion.setBackground(new Color(255, 182, 193));

		JPanel panel_4_3 = new JPanel();
		panel_4_3.setBounds(675, 11, 229, 361);
		panel.add(panel_4_3);
		panel_4_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_4_3.setBackground(Color.WHITE);
		panel_4_3.setLayout(null);

		JLabel lblMduloDeContabilidad = new JLabel("CONTABILIDAD:");
		lblMduloDeContabilidad.setForeground(Color.BLACK);
		lblMduloDeContabilidad.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblMduloDeContabilidad.setBounds(10, 10, 211, 23);
		panel_4_3.add(lblMduloDeContabilidad);

		JButton btnFacturasClientes = new JButton("INFORME MESUAL");
		btnFacturasClientes.setBounds(10, 145, 209, 41);
		panel_4_3.add(btnFacturasClientes);
		btnFacturasClientes.setForeground(Color.WHITE);
		btnFacturasClientes.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnFacturasClientes.setBackground(new Color(60, 179, 113));

		JButton btnReportes = new JButton("REPORTES");
		btnReportes.setBounds(10, 196, 209, 41);
		panel_4_3.add(btnReportes);
		btnReportes.setForeground(Color.WHITE);
		btnReportes.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnReportes.setBackground(new Color(60, 179, 113));

		JButton btnIngresos = new JButton("INGRESOS");
		btnIngresos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_ingresos ingresos = new ventana_ingresos();
				ingresos.setLocationRelativeTo(null);
				ingresos.setVisible(true);
				ingresos.construirTabla();
				ingresos.obtenerUltimoId();
				ingresos.btnActualizar.setEnabled(false);
				dispose();
			}
		});
		btnIngresos.setForeground(Color.WHITE);
		btnIngresos.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnIngresos.setBackground(new Color(60, 179, 113));
		btnIngresos.setBounds(10, 247, 209, 41);
		panel_4_3.add(btnIngresos);

		JButton btnEgresos = new JButton("EGRESOS");
		btnEgresos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_egresos egresos = new ventana_egresos();
				egresos.setLocationRelativeTo(null);
				egresos.setVisible(true);
				egresos.construirTabla();
				egresos.obtenerUltimoId();
				egresos.btnActualizar.setEnabled(false);
				dispose();
			}
		});
		btnEgresos.setForeground(Color.WHITE);
		btnEgresos.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnEgresos.setBackground(new Color(60, 179, 113));
		btnEgresos.setBounds(10, 298, 209, 41);
		panel_4_3.add(btnEgresos);

		JButton btnCierreDiario = new JButton("FACTURAS");
		btnCierreDiario.setBounds(10, 94, 209, 41);
		panel_4_3.add(btnCierreDiario);
		btnCierreDiario.setForeground(Color.WHITE);
		btnCierreDiario.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnCierreDiario.setBackground(new Color(60, 179, 113));

		JButton btnCierreDiario_1 = new JButton("CIERRE DIARIO");
		btnCierreDiario_1.setForeground(Color.WHITE);
		btnCierreDiario_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnCierreDiario_1.setBackground(new Color(60, 179, 113));
		btnCierreDiario_1.setBounds(10, 43, 209, 41);
		panel_4_3.add(btnCierreDiario_1);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(436, 11, 229, 151);
		panel.add(panel_4);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_4.setBackground(Color.WHITE);
		panel_4.setLayout(null);

		JLabel lblMduloDeVentas = new JLabel("VENTAS:");
		lblMduloDeVentas.setForeground(Color.BLACK);
		lblMduloDeVentas.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblMduloDeVentas.setBounds(10, 11, 209, 21);
		panel_4.add(lblMduloDeVentas);

		JButton btnVentas = new JButton("VENTAS");
		btnVentas.setBounds(10, 43, 209, 97);
		panel_4.add(btnVentas);
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_ventas ventas = new ventana_ventas();
				ventas.setLocationRelativeTo(null);
				ventas.setVisible(true);
				ventas.construirTabla();
				ventas.construirTabla2();
				Timer time = new Timer();
				time.schedule(ventas.tarea, 0, 1000);
				ventas.txtBuscar.requestFocusInWindow();
				dispose();
			}
		});
		btnVentas.setForeground(Color.WHITE);
		btnVentas.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnVentas.setBackground(new Color(255, 215, 0));

		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBounds(436, 173, 229, 405);
		panel.add(panel_4_1);
		panel_4_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_4_1.setBackground(Color.WHITE);
		panel_4_1.setLayout(null);

		JButton btnInventario = new JButton("INVENTARIO");
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_inventario inventario = new ventana_inventario();
				inventario.setLocationRelativeTo(null);
				inventario.setVisible(true);
				inventario.construirTabla();
				inventario.obtenerUltimoId();
				inventario.btnActualizar.setEnabled(false);
				dispose();
			}
		});
		btnInventario.setBounds(10, 353, 209, 41);
		panel_4_1.add(btnInventario);
		btnInventario.setForeground(new Color(255, 255, 255));
		btnInventario.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnInventario.setBackground(new Color(100, 149, 237));

		JButton btnProductos = new JButton("PRODUCTOS");
		btnProductos.setBounds(10, 41, 209, 41);
		panel_4_1.add(btnProductos);
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_productos productos = new ventana_productos();
				productos.setLocationRelativeTo(null);
				productos.setVisible(true);
				productos.construirTabla();
				productos.obtenerUltimoId();
				productos.BloquearCampos();
				productos.btnActualizar.setEnabled(false);
				dispose();
			}
		});
		btnProductos.setForeground(new Color(255, 255, 255));
		btnProductos.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnProductos.setBackground(new Color(100, 149, 237));

		JButton btnProveedores = new JButton("PROVEEDORES");
		btnProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_proveedores proveedores = new ventana_proveedores();
				proveedores.setLocationRelativeTo(null);
				proveedores.setVisible(true);
				proveedores.construirTabla();
				proveedores.obtenerUltimoId();
				proveedores.btnActualizar.setEnabled(false);
				dispose();
			}
		});
		btnProveedores.setBounds(10, 145, 209, 41);
		panel_4_1.add(btnProveedores);
		btnProveedores.setForeground(new Color(255, 255, 255));
		btnProveedores.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnProveedores.setBackground(new Color(100, 149, 237));

		JButton btnEmpleados = new JButton("EMPLEADOS");
		btnEmpleados.setBounds(10, 93, 209, 41);
		panel_4_1.add(btnEmpleados);
		btnEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_empleados empleados = new ventana_empleados();
				empleados.setLocationRelativeTo(null);
				empleados.setVisible(true);
				empleados.construirTabla();
				empleados.obtenerUltimoId();
				empleados.btnActualizar.setEnabled(false);
				dispose();
			}
		});
		btnEmpleados.setForeground(new Color(255, 255, 255));
		btnEmpleados.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnEmpleados.setBackground(new Color(100, 149, 237));

		JButton btnUsuarios = new JButton("USUARIOS");
		btnUsuarios.setBounds(10, 249, 209, 41);
		panel_4_1.add(btnUsuarios);
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_usuarios usuarios = new ventana_usuarios();
				usuarios.setLocationRelativeTo(null);
				usuarios.setVisible(true);
				usuarios.construirTabla();
				usuarios.obtenerUltimoId();
				usuarios.obtenerRoles();
				usuarios.obtenerEmpleados();
				usuarios.btnActualizar.setEnabled(false);
				dispose();
			}
		});
		btnUsuarios.setForeground(new Color(255, 255, 255));
		btnUsuarios.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnUsuarios.setBackground(new Color(100, 149, 237));

		JButton btnClientes = new JButton("CLIENTES");
		btnClientes.setBounds(10, 197, 209, 41);
		panel_4_1.add(btnClientes);
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_clientes clientes = new ventana_clientes();
				clientes.setLocationRelativeTo(null);
				clientes.setVisible(true);
				clientes.construirTabla();
				clientes.obtenerUltimoId();
				clientes.btnActualizar.setEnabled(false);
				dispose();
			}
		});
		btnClientes.setForeground(new Color(255, 255, 255));
		btnClientes.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnClientes.setBackground(new Color(100, 149, 237));

		JButton btnRoles = new JButton("ROLES");
		btnRoles.setBounds(10, 301, 209, 41);
		panel_4_1.add(btnRoles);
		btnRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_roles roles = new ventana_roles();
				roles.setLocationRelativeTo(null);
				roles.setVisible(true);
				roles.construirTabla();
				roles.obtenerUltimoId();
				roles.btnActualizar.setEnabled(false);
				dispose();
			}
		});
		btnRoles.setForeground(new Color(255, 255, 255));
		btnRoles.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnRoles.setBackground(new Color(100, 149, 237));

		JLabel lblEmpresa_1 = new JLabel("EMPRESA:");
		lblEmpresa_1.setForeground(Color.BLACK);
		lblEmpresa_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblEmpresa_1.setBounds(10, 11, 209, 21);
		panel_4_1.add(lblEmpresa_1);
	}

	Timer time = new Timer();
	public TimerTask tarea = new TimerTask() {
		@Override
		public void run() {
			Calendar calendario = new GregorianCalendar();
			Date fechaHoraActual = new Date();
			calendario.setTime(fechaHoraActual);
			String horas;
			String minutos;
			String segundos;
			String ampm;
			Thread hilo = null;
			Thread hilo2;
			hilo2 = Thread.currentThread();
			hilo = new Thread();
			hilo.start();
			ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
			if (ampm.equals("PM")) {
				int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
				horas = h > 9 ? "" + h : "0" + h;
			} else {
				horas = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY)
						: "0" + calendario.get(Calendar.HOUR_OF_DAY);
			}
			minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE)
					: "0" + calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND)
					: "0" + calendario.get(Calendar.SECOND);

			lblHora.setText(horas + ":" + minutos + ":" + segundos + " " + ampm);
		}
	};

	public static String getFecha() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		SimpleDateFormat df = new SimpleDateFormat("EEEEEEEEE dd 'de' MMMMM 'del' yyyy");
		date = cal.getTime();
		return df.format(date);
	}

	public static void getHora() {
		Calendar cal = Calendar.getInstance();
		Date fecha = cal.getTime();
		DateFormat formatter = DateFormat.getTimeInstance();
		lblFecha.setText(formatter.format(fecha));
	}

	private void close() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	public void verificarConfiguraciones() {
		conexion objCon = new conexion();
		Connection conn = objCon.conectar();
		try {
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM configuraciones WHERE id_empresa=1");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				id_empresa = rsr.getString("id_empresa");
				nombre_empresa = rsr.getString("nombre_empresa");
				telefono_empresa = rsr.getString("telefono_empresa");
				direccion_empresa = rsr.getString("direccion_empresa");
				rtn_empresa = rsr.getString("rtn_empresa");
				cai_empresa = rsr.getString("cai_empresa");
				logo_empresa = rsr.getString("logo_empresa");
				ri_facturas = rsr.getString("ri_facturas");
				rf_facturas = rsr.getString("rf_facturas");
				fecha_limite_facturas = rsr.getString("fecha_limite_facturas");

				if (id_empresa == null) {
					lblEmpresa.setText("SIA");
				} else {
					lblEmpresa.setText(nombre_empresa);

					Image foto = getToolkit().getImage(logo_empresa);
					foto = foto.getScaledInstance(lblLogoSistema.getHeight(), lblLogoSistema.getWidth(),
							Image.SCALE_DEFAULT);
					lblLogoSistema.setIcon(new ImageIcon(foto));
				}
			}
			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
