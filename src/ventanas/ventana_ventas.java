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
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import clases.productos;
import clases.roles;
import conexion.conexion;
import consultas.consultas_roles;

import java.awt.Component;
import java.awt.Event;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
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
import java.util.TimerTask;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputMap;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JTextArea;

public class ventana_ventas extends JFrame {

	private JPanel contentPane;
	public static JLabel lblVentana;
	public static String totalDatos;
	public JTable tabla;
	public JScrollPane barra;
	public JTable tabla2;
	public JScrollPane barra2;
	public TableRowSorter<TableModel> trsfiltroCodigo;
	String filtroCodigo;
	public JTextField txtBuscar;
	public JTextField txtExistencia;
	public static String fechaFactura = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	public static int numero;
	public static String producto;
	public static int cantidad;
	public static double precio;
	public static double impuesto = 0.15;
	public static double subtotal;
	public static double total;

	public ventana_ventas() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1130, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Ventana VENTAS");
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
		panel.setBounds(10, 11, 1093, 33);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnAtras = new JButton("Atr\u00E1s");
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
		btnAtras.setBounds(969, 8, 109, 23);
		panel.add(btnAtras);

		lblVentana = new JLabel("VENTAS");
		lblVentana.setForeground(Color.WHITE);
		lblVentana.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblVentana.setBounds(10, 0, 871, 33);
		panel.add(lblVentana);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(10, 46, 1093, 504);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMenPrincipal = new JLabel("Productos:");
		lblMenPrincipal.setForeground(Color.WHITE);
		lblMenPrincipal.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblMenPrincipal.setBounds(10, 11, 350, 27);
		panel_1.add(lblMenPrincipal);

		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBackground(Color.WHITE);
		panel_2_1_1.setBounds(370, 39, 713, 454);
		panel_1.add(panel_2_1_1);
		panel_2_1_1.setLayout(null);

		JButton btnGuardar_1_1_1 = new JButton("FACTURAR");
		btnGuardar_1_1_1.setForeground(Color.WHITE);
		btnGuardar_1_1_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnGuardar_1_1_1.setBackground(new Color(255, 215, 0));
		btnGuardar_1_1_1.setBounds(592, 365, 111, 78);
		panel_2_1_1.add(btnGuardar_1_1_1);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 11, 693, 343);
		panel_2_1_1.add(panel_6);
		panel_6.setLayout(null);
		
		barra2 = new JScrollPane(tabla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		barra2.setBounds(10, 11, 673, 321);
		panel_6.add(barra2);
		tabla2 = new JTable();
		barra2.setViewportView(tabla2);

		JPanel panel_6_1 = new JPanel();
		panel_6_1.setLayout(null);
		panel_6_1.setBounds(10, 365, 210, 78);
		panel_2_1_1.add(panel_6_1);

		JPanel panel_2_1_1_1 = new JPanel();
		panel_2_1_1_1.setBackground(Color.WHITE);
		panel_2_1_1_1.setBounds(10, 39, 350, 454);
		panel_1.add(panel_2_1_1_1);
		panel_2_1_1_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 330, 341);
		panel_2_1_1_1.add(panel_2);
		panel_2.setLayout(null);

		barra = new JScrollPane(tabla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		barra.setBounds(10, 45, 310, 285);
		panel_2.add(barra);
		tabla = new JTable();
		barra.setViewportView(tabla);

		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setForeground(Color.BLACK);
		lblBuscar.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblBuscar.setBounds(10, 11, 97, 23);
		panel_2.add(lblBuscar);

		txtBuscar = new JTextField();
		txtBuscar.setToolTipText("");
		txtBuscar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(80, 11, 240, 23);
		panel_2.add(txtBuscar);

		JButton btnVender = new JButton("VENDER");
		btnVender.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int filaseleccionada;
				try {
					filaseleccionada = tabla.getSelectedRow();
					if (filaseleccionada == -1) {
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
					} else {
						String producto = tabla.getValueAt(filaseleccionada, 1).toString();
						String cantidad = tabla.getValueAt(filaseleccionada, 2).toString();
						String precio = tabla.getValueAt(filaseleccionada, 3).toString();
						
						String cantidadVenta = JOptionPane.showInputDialog("Escriba la cantidad que desea vender:");
						

					}

				} catch (HeadlessException ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInt�ntelo nuevamente",
							" .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		btnVender.setBounds(224, 363, 116, 80);
		panel_2_1_1_1.add(btnVender);
		btnVender.setForeground(Color.WHITE);
		btnVender.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnVender.setBackground(new Color(0, 128, 128));

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 363, 204, 80);
		panel_2_1_1_1.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblExistencia = new JLabel("Existencia:");
		lblExistencia.setBounds(10, 11, 97, 26);
		panel_4.add(lblExistencia);
		lblExistencia.setForeground(Color.BLACK);
		lblExistencia.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));

		JButton btnObtener = new JButton("Obtener");
		btnObtener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtBuscar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Debe buscar el producto para conocer su existencia total.");
				} else {
					txtExistencia.setText("");
					totalizarExistencia();
				}
			}
		});
		btnObtener.setBounds(95, 11, 99, 28);
		panel_4.add(btnObtener);
		btnObtener.setForeground(Color.WHITE);
		btnObtener.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnObtener.setBackground(new Color(0, 128, 128));

		txtExistencia = new JTextField();
		txtExistencia.setHorizontalAlignment(SwingConstants.CENTER);
		txtExistencia.setBounds(10, 43, 184, 26);
		panel_4.add(txtExistencia);
		txtExistencia.setEditable(false);
		txtExistencia.setToolTipText("");
		txtExistencia.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtExistencia.setColumns(10);

		JLabel lblDetalleDeLa = new JLabel("Venta:");
		lblDetalleDeLa.setForeground(Color.WHITE);
		lblDetalleDeLa.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblDetalleDeLa.setBounds(370, 11, 350, 27);
		panel_1.add(lblDetalleDeLa);
		InputMap map6 = txtBuscar.getInputMap(JComponent.WHEN_FOCUSED);
		map6.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtBuscar.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				trsfiltroCodigo = new TableRowSorter(tabla.getModel());
				tabla.setRowSorter(trsfiltroCodigo);

				if (txtBuscar.getText().length() == 25)
					ke.consume();

				if (txtBuscar.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtBuscar.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent ke) {

			}

			@Override
			public void keyReleased(KeyEvent ke) {
				String cadena = (txtBuscar.getText().toString());
				txtBuscar.setText(cadena);
				repaint();
				filtro();
			}
		});

	}

	public void construirTabla() {
		String titulos[] = {"C�digo", "Nombre", "Valor", "Cantidad" };
		String informacion[][] = obtenerMatriz();
		tabla = new JTable(informacion, titulos);
		barra.setViewportView(tabla);
		for (int c = 0; c < tabla.getColumnCount(); c++) {
			Class<?> col_class = tabla.getColumnClass(c);
			tabla.setDefaultEditor(col_class, null);
			tabla.getTableHeader().setReorderingAllowed(false);

		}
	}
	
	public void construirTabla2() {
		String titulos[] = { "N�", "Producto", "Cantidad", "Precio"};
		String informacion[][] = obtenerMatriz2();
		tabla2 = new JTable(informacion,titulos);
		barra2.setViewportView(tabla2);
		for (int c = 0; c < tabla2.getColumnCount(); c++) {
			Class<?> col_class = tabla2.getColumnClass(c);
			tabla2.setDefaultEditor(col_class, null);
			tabla2.getTableHeader().setReorderingAllowed(false);
		}
	}

	public static ArrayList<productos> buscarUsuariosConMatriz() {
		conexion conex = new conexion();
		ArrayList<productos> miLista = new ArrayList<productos>();
		productos productos;
		try {
			Statement estatuto = conex.conectar().createStatement();
			ResultSet rs = estatuto.executeQuery("select * from productos");

			while (rs.next()) {
				productos = new productos();
				productos.setCodigo_producto(rs.getInt("codigo_producto"));
				productos.setNombre_producto(rs.getString("nombre_producto"));
				productos.setValor_producto(rs.getDouble("valor_producto"));
				productos.setCantidad_producto(rs.getInt("cantidad_producto"));
				miLista.add(productos);
			}
			rs.close();
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);

		}
		return miLista;
	}

	public static String[][] obtenerMatriz() {
		ArrayList<productos> miLista = buscarUsuariosConMatriz();
		String matrizInfo[][] = new String[miLista.size()][4];
		for (int i = 0; i < miLista.size(); i++) {
			matrizInfo[i][0] = miLista.get(i).getCodigo_producto() + "";
			matrizInfo[i][1] = miLista.get(i).getNombre_producto() + "";
			matrizInfo[i][2] = miLista.get(i).getValor_producto() + "";
			matrizInfo[i][3] = miLista.get(i).getCantidad_producto() + "";
		}
		return matrizInfo;
	}
	
	public static String[][] obtenerMatriz2() {
		ArrayList<?> miLista = new ArrayList<>();
		String matrizInfo[][] = new String[miLista.size()][4];

		for (int i = 0; i < miLista.size(); i++) {
			matrizInfo[i][0] = miLista.get(i).equals(numero) + "";
			matrizInfo[i][1] = miLista.get(i).equals(producto) + "";
			matrizInfo[i][2] = miLista.get(i).equals(cantidad) + "";
			matrizInfo[i][3] = miLista.get(i).equals(precio) + "";
		}
		return matrizInfo;
	}

	public void filtro() {
		filtroCodigo = txtBuscar.getText().toString();
		trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText().toString(), 1, 2));
	}

	public void utilJTablePrint(JTable jTable, String header, String footer, boolean showPrintDialog) {
		boolean fitWidth = true;
		boolean interactive = true;
		JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL;
		try {
			boolean complete = jTable.print(mode, new MessageFormat(header), new MessageFormat(footer), showPrintDialog,
					null, interactive);
			if (complete) {
				JOptionPane.showMessageDialog(jTable, "Print complete (Impresi�n completa)",
						"Print result (Resultado de la impresi�n)", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(jTable, "Print canceled (Impresi�n cancelada)",
						"Print result (Resultado de la impresi�n)", JOptionPane.WARNING_MESSAGE);
			}
		} catch (PrinterException pe) {
			JOptionPane.showMessageDialog(jTable, "Print fail (Fallo de impresi�n): " + pe.getMessage(),
					"Print result (Resultado de la impresi�n)", JOptionPane.ERROR_MESSAGE);
		}
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

			lblHoraFactura.setText(horas + ":" + minutos + ":" + segundos + " " + ampm);
		}
	};

	public void totalizarExistencia() {
		int t = 0;
		int p = 0;
		if (tabla.getRowCount() > 0) {
			for (int i = 0; i < tabla.getRowCount(); i++) {
				p = Integer.parseInt(tabla.getValueAt(i, 4).toString());
				t += p;
			}
			txtExistencia.setText(String.valueOf(t) + " Unidades");
		} else {
			JOptionPane.showMessageDialog(null, "No hay datos que totalizar");
		}
	}

	private void close() {
		if (JOptionPane.showConfirmDialog(rootPane, "�Desea realmente salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
}
