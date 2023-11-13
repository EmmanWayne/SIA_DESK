package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import clases.configuraciones;
import clases.roles;
import conexion.conexion;
import consultas.consultas_configuraciones;
import consultas.consultas_roles;

import java.awt.Component;
import java.awt.Event;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class ventana_configuracion extends JFrame {

	private JPanel contentPane;
	public static JLabel lblVentana;
	public JTextField txtNombre;
	public JTextField txtTelefono;
	public JTextField txtDireccion;
	public JTextField txtRTN;
	public JDateChooser JdateFechaLimite;
	public JTextFieldDateEditor txtFechaLimite;
	public JLabel lblId;
	public JTextField txtRutaLogo;
	public JTextField txtCAI;
	public JTextField txtRI;
	public JTextField txtRF;

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

	public TableRowSorter<TableModel> trsfiltroCodigo;
	String filtroCodigo;
	public JButton btnGuardar, btnActualizar, btnLogo;
	public JLabel lblLogo;

	public ventana_configuracion() {
		setResizable(false);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Ventana CONFIGURACIONES");
		final ImageIcon logo = new ImageIcon(getClass().getResource("/recursos/logo_sistema.png"));
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/recursos/logo_sistema.png")));

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				close();
			}
		});

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(10, 11, 731, 33);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnAtras = new JButton("Atr\u00E1s ");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_principal principal = new ventana_principal();
				ventana_login login = new ventana_login();
				principal.setLocationRelativeTo(null);
				principal.setVisible(true);
				Timer time = new Timer();
				time.schedule(principal.tarea, 0, 1000);
				principal.lblLogoSistema.requestFocusInWindow();
				login.establecerDatosInicioSesionUsuario();
				principal.verificarConfiguraciones();
				dispose();
			}
		});
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnAtras.setBackground(new Color(0, 0, 255));
		btnAtras.setBounds(612, 8, 109, 23);
		panel.add(btnAtras);

		lblVentana = new JLabel("Configuraci\u00F3n");
		lblVentana.setForeground(Color.WHITE);
		lblVentana.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblVentana.setBounds(10, 0, 590, 33);
		panel.add(lblVentana);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(10, 46, 731, 504);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMenPrincipal = new JLabel("Registrar Configuraci\u00F3n");
		lblMenPrincipal.setForeground(Color.WHITE);
		lblMenPrincipal.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblMenPrincipal.setBounds(10, 11, 350, 27);
		panel_1.add(lblMenPrincipal);

		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBackground(Color.WHITE);
		panel_2_1_1.setBounds(10, 39, 350, 454);
		panel_1.add(panel_2_1_1);
		panel_2_1_1.setLayout(null);

		JLabel lblNombre = new JLabel("Tel\u00E9fono:");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNombre.setBounds(10, 103, 205, 27);
		panel_2_1_1.add(lblNombre);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtTelefono.setToolTipText("");
		txtTelefono.setBounds(10, 130, 331, 27);
		panel_2_1_1.add(txtTelefono);
		txtTelefono.setColumns(10);

		JLabel lblN = new JLabel("N\u00B0 Registro: ");
		lblN.setForeground(Color.BLACK);
		lblN.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblN.setBounds(10, 12, 124, 27);
		panel_2_1_1.add(lblN);

		lblId = new JLabel("");
		lblId.setBounds(109, 12, 38, 27);
		panel_2_1_1.add(lblId);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));

		JLabel lblCodigoDelRol = new JLabel("Nombre:");
		lblCodigoDelRol.setForeground(Color.BLACK);
		lblCodigoDelRol.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblCodigoDelRol.setBounds(10, 50, 205, 27);
		panel_2_1_1.add(lblCodigoDelRol);

		txtNombre = new JTextField();
		txtNombre.setToolTipText("");
		txtNombre.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBounds(10, 77, 331, 27);
		panel_2_1_1.add(txtNombre);

		JLabel lblVisinDeLa = new JLabel("Direcci\u00F3n:");
		lblVisinDeLa.setForeground(Color.BLACK);
		lblVisinDeLa.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblVisinDeLa.setBounds(10, 157, 205, 27);
		panel_2_1_1.add(lblVisinDeLa);

		txtDireccion = new JTextField();
		txtDireccion.setToolTipText("");
		txtDireccion.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(10, 184, 331, 27);
		panel_2_1_1.add(txtDireccion);

		JLabel lblRtnDeLa = new JLabel("RTN:");
		lblRtnDeLa.setForeground(Color.BLACK);
		lblRtnDeLa.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblRtnDeLa.setBounds(10, 211, 205, 27);
		panel_2_1_1.add(lblRtnDeLa);

		txtRTN = new JTextField();
		txtRTN.setToolTipText("");
		txtRTN.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtRTN.setColumns(10);
		txtRTN.setBounds(10, 238, 331, 27);
		panel_2_1_1.add(txtRTN);

		JLabel lblCai = new JLabel("CAI:");
		lblCai.setForeground(Color.BLACK);
		lblCai.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblCai.setBounds(10, 264, 205, 27);
		panel_2_1_1.add(lblCai);

		txtCAI = new JTextField();
		txtCAI.setToolTipText("");
		txtCAI.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtCAI.setColumns(10);
		txtCAI.setBounds(10, 291, 331, 27);
		panel_2_1_1.add(txtCAI);

		JLabel lblRangoInicialDe = new JLabel("Rango inicial de facturas:");
		lblRangoInicialDe.setBounds(10, 318, 331, 27);
		panel_2_1_1.add(lblRangoInicialDe);
		lblRangoInicialDe.setForeground(Color.BLACK);
		lblRangoInicialDe.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));

		txtRI = new JTextField();
		txtRI.setBounds(10, 345, 331, 27);
		panel_2_1_1.add(txtRI);
		txtRI.setToolTipText("");
		txtRI.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtRI.setColumns(10);

		JLabel lblRangoFinalDe = new JLabel("Rango final de facturas:");
		lblRangoFinalDe.setBounds(10, 371, 331, 27);
		panel_2_1_1.add(lblRangoFinalDe);
		lblRangoFinalDe.setForeground(Color.BLACK);
		lblRangoFinalDe.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));

		txtRF = new JTextField();
		txtRF.setBounds(10, 398, 331, 27);
		panel_2_1_1.add(txtRF);
		txtRF.setToolTipText("");
		txtRF.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtRF.setColumns(10);

		JPanel panel_2_1_1_1 = new JPanel();
		panel_2_1_1_1.setBackground(Color.WHITE);
		panel_2_1_1_1.setBounds(370, 39, 350, 454);
		panel_1.add(panel_2_1_1_1);
		panel_2_1_1_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBounds(11, 114, 330, 265);
		panel_2_1_1_1.add(panel_2);
		panel_2.setLayout(null);

		lblLogo = new JLabel("");
		lblLogo.setBounds(47, 25, 237, 216);
		panel_2.add(lblLogo);
		final ImageIcon icono = new ImageIcon(
				logo.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
		lblLogo.setIcon(icono);

		JLabel lblLogoDeLa = new JLabel("Logo de la empresa:");
		lblLogoDeLa.setBounds(11, 76, 225, 27);
		panel_2_1_1_1.add(lblLogoDeLa);
		lblLogoDeLa.setForeground(Color.BLACK);
		lblLogoDeLa.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));

		btnLogo = new JButton("Seleccionar");
		btnLogo.setBounds(197, 78, 144, 27);
		panel_2_1_1_1.add(btnLogo);
		btnLogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarLogo();
			}
		});
		btnLogo.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));

		txtRutaLogo = new JTextField();
		txtRutaLogo.setBounds(11, 382, 330, 27);
		panel_2_1_1_1.add(txtRutaLogo);
		txtRutaLogo.setEditable(false);
		txtRutaLogo.setToolTipText("");
		txtRutaLogo.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtRutaLogo.setColumns(10);

		JLabel lblRtnDeLa_1_1_1 = new JLabel("Fecha l\u00EDmite de emisi\u00F3n de facturas:");
		lblRtnDeLa_1_1_1.setForeground(Color.BLACK);
		lblRtnDeLa_1_1_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblRtnDeLa_1_1_1.setBounds(10, 11, 331, 27);
		panel_2_1_1_1.add(lblRtnDeLa_1_1_1);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(10, 420, 109, 23);
		panel_2_1_1_1.add(btnGuardar);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(new Color(0, 128, 0));
		btnGuardar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(232, 420, 109, 23);
		panel_2_1_1_1.add(btnActualizar);
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setBackground(new Color(0, 128, 128));
		btnActualizar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));

		JdateFechaLimite = new JDateChooser();
		JdateFechaLimite.setBounds(11, 38, 330, 27);
		JdateFechaLimite.setDateFormatString("dd-MM-yyyy");
		panel_2_1_1_1.add(JdateFechaLimite);
		txtFechaLimite = (JTextFieldDateEditor) JdateFechaLimite.getDateEditor();
		txtFechaLimite.setEditable(false);
		txtFechaLimite.setHorizontalAlignment(SwingConstants.CENTER);

		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtNombre.getText().isEmpty() && txtTelefono.getText().isEmpty() && txtDireccion.getText().isEmpty()
						&& txtRTN.getText().isEmpty() && txtCAI.getText().isEmpty() && txtRutaLogo.getText().isEmpty()
						&& txtRI.getText().isEmpty() && txtRF.getText().isEmpty()
						&& txtFechaLimite.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Porfavor llene los campos requeridos para actualizar el registro!");
				} else {
					configuraciones clase = new configuraciones();
					consultas_configuraciones consulta = new consultas_configuraciones();
					clase.setNombre_empresa(txtNombre.getText().toString());
					clase.setTelefono_empresa(txtTelefono.getText().toString());
					clase.setDireccion_empresa(txtDireccion.getText().toString());
					clase.setRtn_empresa(txtRTN.getText().toString());
					clase.setCai_empresa(txtCAI.getText().toString());
					clase.setLogo_empresa(txtRutaLogo.getText().toString());
					clase.setRi_facturas(txtRI.getText().toString());
					clase.setRf_facturas(txtRF.getText().toString());
					clase.setFecha_limite_facturas(txtFechaLimite.getText().toString());
					clase.setId_empresa(Integer.parseInt(lblId.getText().toString()));

					if (consulta.actualizar(clase)) {
						JOptionPane.showMessageDialog(null, "¡Actualización Exitosa!");
					} else {
						JOptionPane.showMessageDialog(null, "¡Error de Actualización!");
					}
				}
			}

		});
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtNombre.getText().isEmpty() && txtTelefono.getText().isEmpty() && txtDireccion.getText().isEmpty()
						&& txtRTN.getText().isEmpty() && txtCAI.getText().isEmpty() && txtRutaLogo.getText().isEmpty()
						&& txtRI.getText().isEmpty() && txtRF.getText().isEmpty()
						&& txtFechaLimite.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Porfavor llene los campos requeridos para guardar el registro!");
				} else {
					configuraciones clase = new configuraciones();
					consultas_configuraciones consulta = new consultas_configuraciones();
					clase.setNombre_empresa(txtNombre.getText().toString());
					clase.setTelefono_empresa(txtTelefono.getText().toString());
					clase.setDireccion_empresa(txtDireccion.getText().toString());
					clase.setRtn_empresa(txtRTN.getText().toString());
					clase.setCai_empresa(txtCAI.getText().toString());
					clase.setLogo_empresa(txtRutaLogo.getText().toString());
					clase.setRi_facturas(txtRI.getText().toString());
					clase.setRf_facturas(txtRF.getText().toString());
					clase.setFecha_limite_facturas(txtFechaLimite.getText().toString());

					if (consulta.insertar(clase)) {
						JOptionPane.showMessageDialog(null, "¡Registro Exitoso!");
						btnActualizar.setVisible(true);
						btnGuardar.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "¡Error de registro!");
					}
				}
			}

		});

	}

	private void close() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	public void selecionarLogo() {
		JFileChooser archivo = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG)", "jpg",
				"jpeg");
		archivo.addChoosableFileFilter(filtro);
		archivo.setDialogTitle("Abrir Archivo");
		File ruta = new File("\\\\" + conexion.urlGlobal + "\\C:\\");
		archivo.setCurrentDirectory(ruta);
		int ventana = archivo.showOpenDialog(null);
		if (ventana == JFileChooser.APPROVE_OPTION) {
			File file = archivo.getSelectedFile();
			txtRutaLogo.setText(String.valueOf(file));
			Image foto = getToolkit().getImage(txtRutaLogo.getText());
			foto = foto.getScaledInstance(lblLogo.getHeight(), lblLogo.getWidth(), Image.SCALE_DEFAULT);
			lblLogo.setIcon(new ImageIcon(foto));
		}
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

				txtNombre.setText(nombre_empresa);
				txtTelefono.setText(telefono_empresa);
				txtDireccion.setText(direccion_empresa);
				txtRTN.setText(rtn_empresa);
				txtCAI.setText(cai_empresa);
				txtRutaLogo.setText(logo_empresa);
				txtRI.setText(ri_facturas);
				txtRF.setText(rf_facturas);
				txtFechaLimite.setText(fecha_limite_facturas);

				Image foto = getToolkit().getImage(txtRutaLogo.getText());
				foto = foto.getScaledInstance(lblLogo.getHeight(), lblLogo.getWidth(), Image.SCALE_DEFAULT);
				lblLogo.setIcon(new ImageIcon(foto));
				lblId.setText(id_empresa);

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
