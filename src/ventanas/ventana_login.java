package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Event;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import clases.roles;
import clases.usuarios;
import conexion.conexion;
import consultas.consultas_roles;
import consultas.consultas_usuarios;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ventana_login extends JFrame {

	public JPanel contentPane;
	public static JTextField txtUsuario;
	public static JPasswordField txtContrasena;
	public JLabel lblAlerta;
	public JButton btnIngresar;
	public static JLabel lblNombreDelSistema;
	public static JLabel lblFecha;
	public static JLabel lblHora;

	public static JLabel lblLOGO;

	public static JRadioButton rdbtnPass;
	public static JLabel lblestadocontrasena;

	public static String usuario;
	public static String contrasena;
	public static String identidad;
	public static String rol;
	public static String nombreCompleto;
	public static String nombreRol;
	public static String id_usuario;
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

	public ventana_login() {
		setResizable(false);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Ventana LOGIN");
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/recursos/logo_sistema.png")));
		final ImageIcon icono1 = new ImageIcon(getClass().getResource("/recursos/ver.png"));
		final ImageIcon icono2 = new ImageIcon(getClass().getResource("/recursos/ocultar.png"));

		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(586, 426, 98, 34);
		contentPane.add(btnSalir);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 681, 339);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setForeground(new Color(0, 0, 0));
		lblUsuario.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblUsuario.setBounds(10, 114, 326, 30);
		panel.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a :");
		lblContrasea.setForeground(new Color(0, 0, 0));
		lblContrasea.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblContrasea.setBounds(10, 174, 326, 30);
		panel.add(lblContrasea);

		lblestadocontrasena = new JLabel("");
		lblestadocontrasena.setBackground(Color.WHITE);
		lblestadocontrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblestadocontrasena.setBounds(315, 184, 21, 20);
		panel.add(lblestadocontrasena);
		final ImageIcon iconoocultar = new ImageIcon(icono2.getImage().getScaledInstance(lblestadocontrasena.getWidth(),
				lblestadocontrasena.getHeight(), Image.SCALE_DEFAULT));
		lblestadocontrasena.setIcon(iconoocultar);

		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		txtUsuario.setBounds(10, 143, 326, 30);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		InputMap map4 = txtUsuario.getInputMap(JComponent.WHEN_FOCUSED);
		map4.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtUsuario.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (txtUsuario.getText().length() == 15)
					ke.consume();

				if (txtUsuario.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtUsuario.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent ke) {
			}
		});

		btnIngresar = new JButton("INGRESAR");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();
			}
		});
		btnIngresar.setForeground(Color.WHITE);
		btnIngresar.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnIngresar.setBackground(new Color(0, 139, 139));
		btnIngresar.setBounds(10, 249, 326, 33);
		panel.add(btnIngresar);

		txtContrasena = new JPasswordField();
		txtContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		txtContrasena.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		txtContrasena.setBounds(10, 205, 326, 33);
		panel.add(txtContrasena);
		InputMap map5 = txtContrasena.getInputMap(JComponent.WHEN_FOCUSED);
		map5.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtContrasena.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtContrasena.getText().length() == 15)
					e.consume();

				if (txtContrasena.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtContrasena.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					iniciarSesion();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

		lblAlerta = new JLabel("\u00A1Bienvenido Estimado Usuario!");
		lblAlerta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlerta.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblAlerta.setBounds(10, 295, 326, 33);
		panel.add(lblAlerta);

		rdbtnPass = new JRadioButton("");
		rdbtnPass.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnPass.setBackground(Color.WHITE);
		rdbtnPass.setBounds(294, 184, 21, 20);
		panel.add(rdbtnPass);
		rdbtnPass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnPass.isSelected()) {
					txtContrasena.setEchoChar((char) 0);
					final ImageIcon iconover = new ImageIcon(icono1.getImage().getScaledInstance(
							lblestadocontrasena.getWidth(), lblestadocontrasena.getHeight(), Image.SCALE_DEFAULT));
					lblestadocontrasena.setIcon(iconover);
				} else {
					txtContrasena.setEchoChar('*');
					final ImageIcon iconoocultar = new ImageIcon(icono2.getImage().getScaledInstance(
							lblestadocontrasena.getWidth(), lblestadocontrasena.getHeight(), Image.SCALE_DEFAULT));
					lblestadocontrasena.setIcon(iconoocultar);
					setBackground(Color.BLACK);
				}
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(346, 11, 326, 317);
		panel.add(panel_1);
		panel_1.setLayout(null);

		lblLOGO = new JLabel("");
		lblLOGO.setBounds(10, 11, 306, 295);
		panel_1.add(lblLOGO);
		final ImageIcon logoSIA = new ImageIcon(getClass().getResource("/recursos/logo_sistema.png"));
		final ImageIcon icono = new ImageIcon(
				logoSIA.getImage().getScaledInstance(lblLOGO.getWidth(), lblLOGO.getHeight(), Image.SCALE_DEFAULT));
		lblLOGO.setIcon(icono);

		lblNombreDelSistema = new JLabel("SIA");
		lblNombreDelSistema.setForeground(new Color(0, 128, 128));
		lblNombreDelSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDelSistema.setBounds(10, 81, 326, 30);
		panel.add(lblNombreDelSistema);
		lblNombreDelSistema.setBackground(Color.WHITE);
		lblNombreDelSistema.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));

		lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setForeground(Color.BLACK);
		lblFecha.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblFecha.setBackground(Color.WHITE);
		lblFecha.setBounds(10, 11, 326, 30);
		panel.add(lblFecha);
		lblFecha.setText(getFecha());

		lblHora = new JLabel("Hora");
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setForeground(Color.BLACK);
		lblHora.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblHora.setBackground(Color.WHITE);
		lblHora.setBounds(10, 40, 326, 30);
		panel.add(lblHora);

	}

	public void consultarDatosInicioSesion() {
		conexion conex = new conexion();
		try {
			Statement estatuto = conex.conectar().createStatement();
			ResultSet rs = estatuto
					.executeQuery("SELECT * FROM usuarios WHERE usuario='" + txtUsuario.getText().toString() + "'");
			if (rs.next()) {
				usuario = (rs.getString("usuario"));
				contrasena = (rs.getString("contrasena"));
				rol = (rs.getString("codigo_rol"));
			}
			rs.close();
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	public void consultarDatosEmpleado() {
		conexion conex = new conexion();
		try {
			Statement estatuto = conex.conectar().createStatement();
			ResultSet rs = estatuto
					.executeQuery("SELECT * FROM empleados WHERE identidad='" + txtUsuario.getText().toString() + "'");
			if (rs.next()) {
				String nombres = (rs.getString("nombres"));
				String apellidos = (rs.getString("apellidos"));
				nombreCompleto = (nombres + " " + apellidos);
			}
			rs.close();
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	public void obtenerNombreCompletoEmpleado() {
		conexion objCon = new conexion();
		Connection conn = objCon.conectar();
		try {
			PreparedStatement stmtr = conn.prepareStatement(
					"SELECT * FROM empleados where identidad = '" + txtUsuario.getText().toString() + "'");
			ResultSet rsr = stmtr.executeQuery();
			while (rsr.next()) {
				nombres = rsr.getString("nombres");
				apellidos = rsr.getString("apellidos");
			}
			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void consultarNombreRolEmpleado() {
		conexion conex = new conexion();
		try {
			Statement estatuto = conex.conectar().createStatement();
			ResultSet rs = estatuto.executeQuery("SELECT * FROM roles WHERE codigo_rol='" + rol + "'");
			if (rs.next()) {
				nombreRol = (rs.getString("nombre_rol"));
			}
			rs.close();
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	public void consultarCredenciales() {
		conexion conex = new conexion();
		try {
			Statement estatuto = conex.conectar().createStatement();
			ResultSet rs = estatuto.executeQuery("SELECT * FROM usuarios WHERE id_usuario = 1");
			if (rs.next()) {
				id_usuario = (rs.getString("id_usuario"));
			}
			rs.close();
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	public void establecerDatosInicioSesionUsuario() {
		ventana_principal.lblUsuario.setText(usuario);
		ventana_principal.lblRol.setText(nombreRol);
		ventana_principal.lblNombre.setText(nombres+" "+apellidos);
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
					lblNombreDelSistema.setText("SIA");
				} else {
					lblNombreDelSistema.setText(nombre_empresa);

					Image foto = getToolkit().getImage(logo_empresa);
					foto = foto.getScaledInstance(lblLOGO.getHeight(), lblLOGO.getWidth(), Image.SCALE_DEFAULT);
					lblLOGO.setIcon(new ImageIcon(foto));
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

	@SuppressWarnings("unlikely-arg-type")
	public void iniciarSesion() {
		ventana_principal principal = new ventana_principal();
		String user = String.valueOf(txtUsuario.getText().toString());
		String pass = String.valueOf(txtContrasena.getText().toString());
		if (user.equals("") && pass.equals("")) {
			lblAlerta.setText("Los campos (Usuario) y (Contraseña) estan vacios.");
			lblAlerta.setForeground(Color.RED);
		} else {
			if (user.equals("")) {
				lblAlerta.setText("El campo de (Usuario) esta vacio.");
				lblAlerta.setForeground(Color.RED);
			} else {
				if (pass.equals("")) {
					lblAlerta.setText("El campo de (Contraseña) esta vacio.");
					lblAlerta.setForeground(Color.RED);
				} else {
					consultas_usuarios consulta = new consultas_usuarios();
					usuarios clase = new usuarios();
					clase.setUsuario(txtUsuario.getText().toString());
					clase.setContrasena(txtContrasena.getText().toString());
					if (consulta.buscarUsuario(clase)) {
						principal.setLocationRelativeTo(null);
						principal.setVisible(true);
						Timer time = new Timer();
						time.schedule(principal.tarea, 0, 1000);
						principal.lblLogoSistema.requestFocusInWindow();
						consultarDatosInicioSesion();
						// consultarDatosEmpleado();
						consultarNombreRolEmpleado();
						obtenerNombreCompletoEmpleado();
						establecerDatosInicioSesionUsuario();
						principal.verificarConfiguraciones();
						dispose();
					} else {
						lblAlerta.setText("El usuario y contraseña son incorrectas");
						lblAlerta.setForeground(Color.RED);
					}
				}
			}
		}

	}
}
