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

import clases.ingresos;
import clases.roles;
import conexion.conexion;
import consultas.consultas_ingresos;
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

public class ventana_ingresos extends JFrame {

	private JPanel contentPane;
	public static JLabel lblVentana;
	public JTextField txtMonto;
	public JLabel lblId;
	public static String totalDatos;
	public JTable tabla;
	public JScrollPane barra;
	public TableRowSorter<TableModel> trsfiltroCodigo;
	String filtroCodigo;
	public JTextField txtBuscar;
	public JTextArea txtRazon;
	public JButton btnGuardar, btnActualizar, btnEliminar, btnEditar, btnLimpiar;
	ventana_login login = new ventana_login();

	public ventana_ingresos() {
		setResizable(false);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Ventana INGRESOS");
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
		panel.setBounds(10, 11, 685, 33);
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
		btnAtras.setBounds(566, 8, 109, 23);
		panel.add(btnAtras);

		lblVentana = new JLabel("INGRESOS");
		lblVentana.setForeground(Color.WHITE);
		lblVentana.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblVentana.setBounds(10, 0, 240, 33);
		panel.add(lblVentana);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(10, 46, 685, 504);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMenPrincipal = new JLabel("Registrar Ingresos");
		lblMenPrincipal.setForeground(Color.WHITE);
		lblMenPrincipal.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblMenPrincipal.setBounds(10, 11, 241, 27);
		panel_1.add(lblMenPrincipal);

		JLabel lblRegistros = new JLabel("Ingresos Registrados");
		lblRegistros.setForeground(Color.WHITE);
		lblRegistros.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblRegistros.setBounds(261, 11, 412, 27);
		panel_1.add(lblRegistros);

		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBackground(Color.WHITE);
		panel_2_1_1.setBounds(10, 39, 241, 454);
		panel_1.add(panel_2_1_1);
		panel_2_1_1.setLayout(null);

		JLabel lblNombre = new JLabel("Raz\u00F3n:");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNombre.setBounds(10, 103, 205, 27);
		panel_2_1_1.add(lblNombre);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(new Color(0, 128, 0));
		btnGuardar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnGuardar.setBounds(10, 420, 109, 23);
		panel_2_1_1.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtMonto.getText().isEmpty() && txtRazon.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Porfavor llene los campos requeridos para guardar el registro!");
				} else {
					ingresos clase = new ingresos();
					consultas_ingresos consulta = new consultas_ingresos();
					clase.setMonto(Double.parseDouble(txtMonto.getText().toString()));
					clase.setRazon(txtRazon.getText().toString());
					clase.setFecha_hora_ingreso(getFechaYHora());
					String empleado = login.nombres +" "+login.apellidos;
					System.out.println(empleado);
					clase.set_Empleado(empleado);

					if (consulta.insertar(clase)) {
						JOptionPane.showMessageDialog(null, "¡Registro Exitoso!");
						limpiar();
						construirTabla();
						obtenerUltimoId();
						btnGuardar.setEnabled(true);
						btnActualizar.setEnabled(false);
						btnEliminar.setEnabled(true);
						btnLimpiar.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "¡Error de registro!");
						limpiar();
						construirTabla();
						obtenerUltimoId();
						btnGuardar.setEnabled(true);
						btnActualizar.setEnabled(false);
						btnEliminar.setEnabled(true);
						btnLimpiar.setEnabled(true);

					}
				}
			}

		});

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setBackground(new Color(0, 128, 128));
		btnActualizar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnActualizar.setBounds(122, 420, 109, 23);
		panel_2_1_1.add(btnActualizar);
		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtMonto.getText().isEmpty() && txtRazon.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Porfavor llene los campos requeridos para actualizar el registro!");
				} else {
					ingresos clase = new ingresos();
					consultas_ingresos consulta = new consultas_ingresos();
					clase.setMonto(Double.parseDouble(txtMonto.getText().toString()));
					clase.setRazon(txtRazon.getText().toString());

					if (consulta.actualizar(clase)) {
						JOptionPane.showMessageDialog(null, "¡Actualización Exitosa!");
						limpiar();
						construirTabla();
						obtenerUltimoId();
						btnGuardar.setEnabled(true);
						btnActualizar.setEnabled(false);
						btnEliminar.setEnabled(true);
						btnLimpiar.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "¡Error de Actualización!");
						limpiar();
						construirTabla();
						obtenerUltimoId();
						btnGuardar.setEnabled(true);
						btnActualizar.setEnabled(false);
						btnEliminar.setEnabled(true);
						btnLimpiar.setEnabled(true);

					}
				}
			}

		});

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.BLACK);
		btnLimpiar.setBackground(Color.WHITE);
		btnLimpiar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnLimpiar.setBounds(122, 394, 109, 23);
		panel_2_1_1.add(btnLimpiar);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				construirTabla();
				obtenerUltimoId();
			}
		});

		JLabel lblN = new JLabel("N\u00B0 Registro: ");
		lblN.setForeground(Color.BLACK);
		lblN.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblN.setBounds(10, 12, 124, 27);
		panel_2_1_1.add(lblN);

		lblId = new JLabel("");
		lblId.setBounds(121, 13, 38, 27);
		panel_2_1_1.add(lblId);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));

		JLabel lblCodigoDelRol = new JLabel("Monto:");
		lblCodigoDelRol.setForeground(Color.BLACK);
		lblCodigoDelRol.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblCodigoDelRol.setBounds(10, 50, 205, 27);
		panel_2_1_1.add(lblCodigoDelRol);

		txtMonto = new JTextField();
		txtMonto.setToolTipText("");
		txtMonto.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtMonto.setColumns(10);
		txtMonto.setBounds(10, 77, 221, 27);
		panel_2_1_1.add(txtMonto);

		txtRazon = new JTextArea();
		txtRazon.setBackground(Color.LIGHT_GRAY);
		txtRazon.setBounds(10, 130, 221, 253);
		panel_2_1_1.add(txtRazon);

		JPanel panel_2_1_1_1 = new JPanel();
		panel_2_1_1_1.setBackground(Color.WHITE);
		panel_2_1_1_1.setBounds(261, 39, 412, 454);
		panel_1.add(panel_2_1_1_1);
		panel_2_1_1_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 390, 400);
		panel_2_1_1_1.add(panel_2);
		panel_2.setLayout(null);

		barra = new JScrollPane(tabla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		barra.setBounds(10, 45, 369, 342);
		panel_2.add(barra);
		tabla = new JTable();
		barra.setViewportView(tabla);

		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setForeground(Color.WHITE);
		btnImprimir.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnImprimir.setBackground(new Color(255, 140, 0));
		btnImprimir.setBounds(270, 11, 109, 23);
		panel_2.add(btnImprimir);
		btnImprimir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				obtenerTotalDatosReporte();
				if (totalDatos == null) {
					JOptionPane.showMessageDialog(null, "No hay registros disponibles para imprimir un reporte");
				} else {
					String ampm;
					Calendar cal = new GregorianCalendar();
					ampm = cal.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
					String fecha = getFechaYHora() + ampm;
					int total = Integer.valueOf(totalDatos);
					String i = null;
					if (total <= 46) {
						i = "1";
					} else {
						if (total > 46 && total <= 92) {
							i = "2";
						} else {
							if (total > 92 && total <= 138) {
								i = "3";
							} else {
								if (total > 138 && total <= 184) {
									i = "4";
								} else {
									if (total > 184 && total <= 230) {
										i = "5";
									} else {
										if (total > 230 && total <= 276) {
											i = "6";
										} else {
											if (total > 276 && total <= 322) {
												i = "7";
											} else {
												if (total > 322 && total <= 368) {
													i = "8";
												} else {
													if (total > 368 && total <= 414) {
														i = "9";
													} else {
														if (total > 414 && total <= 460) {
															i = "10";
														} else {
															i = "Mas de 10 paginas";

														}

													}

												}
											}
										}
									}
								}
							}
						}
					}

					String encabezado = "Reporte de INGRESOS registrados";

					utilJTablePrint(tabla, encabezado, "Pagina {0} de " + i + "          SIA 2021          " + fecha,
							true);

				}
			}
		});

		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setForeground(Color.BLACK);
		lblBuscar.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblBuscar.setBounds(10, 11, 97, 23);
		panel_2.add(lblBuscar);

		txtBuscar = new JTextField();
		txtBuscar.setToolTipText("");
		txtBuscar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(80, 12, 180, 22);
		panel_2.add(txtBuscar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(10, 422, 109, 23);
		panel_2_1_1_1.add(btnEliminar);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnEliminar.setBackground(new Color(178, 34, 34));

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(291, 422, 109, 23);
		panel_2_1_1_1.add(btnEditar);
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnEditar.setBackground(new Color(255, 215, 0));
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int filaseleccionada;
				try {
					filaseleccionada = tabla.getSelectedRow();
					if (filaseleccionada == -1) {
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
					} else {
						String id = tabla.getValueAt(filaseleccionada, 0).toString();
						String monto = tabla.getValueAt(filaseleccionada, 1).toString();
						String razon = tabla.getValueAt(filaseleccionada, 2).toString();

						lblId.setText(id);
						txtMonto.setText(monto);
						txtRazon.setText(razon);

						btnGuardar.setEnabled(false);
						btnActualizar.setEnabled(true);
						btnEliminar.setEnabled(false);
						btnEditar.setEnabled(true);
						btnLimpiar.setEnabled(false);

					}

				} catch (HeadlessException ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente",
							" .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PreparedStatement ps = null;
				int filaseleccionada;
				try {
					filaseleccionada = tabla.getSelectedRow();
					if (filaseleccionada == -1) {
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
					} else {
						conexion objCon = new conexion();
						Connection conn = objCon.conectar();
						int Fila = tabla.getSelectedRow();
						String codigo = tabla.getValueAt(Fila, 0).toString();
						ps = conn.prepareStatement("DELETE FROM ingresos WHERE id_ingreso=?");
						ps.setString(1, codigo);
						ps.execute();
						JOptionPane.showMessageDialog(null, "¡Registro Eliminado!");
						limpiar();
						construirTabla();
						obtenerUltimoId();
					}

				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al Eliminar");
					System.out.println(ex.toString());
				}
			}
		});
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
		String titulos[] = { "N°", "Monto", "Razón", "Fecha - Hora", "Registrado por" };
		String informacion[][] = obtenerMatriz();
		tabla = new JTable(informacion, titulos);
		barra.setViewportView(tabla);
		for (int c = 0; c < tabla.getColumnCount(); c++) {
			Class<?> col_class = tabla.getColumnClass(c);
			tabla.setDefaultEditor(col_class, null);
			tabla.getTableHeader().setReorderingAllowed(false);
			tabla.getColumnModel().getColumn(0).setPreferredWidth(20);

		}
	}

	public static ArrayList<ingresos> buscarUsuariosConMatriz() {
		conexion conex = new conexion();
		ArrayList<ingresos> miLista = new ArrayList<ingresos>();
		ingresos ingresos;
		try {
			Statement estatuto = conex.conectar().createStatement();
			ResultSet rs = estatuto.executeQuery("select * from ingresos");

			while (rs.next()) {
				ingresos = new ingresos();
				ingresos.setId_ingreso(Integer.parseInt(rs.getString("id_ingreso")));
				ingresos.setMonto(rs.getDouble("monto"));
				ingresos.setRazon(rs.getString("razon"));
				ingresos.setFecha_hora_ingreso(rs.getString("fecha_hora_ingreso"));
				ingresos.set_Empleado(rs.getString("empleado"));
				miLista.add(ingresos);
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
		ArrayList<ingresos> miLista = buscarUsuariosConMatriz();
		String matrizInfo[][] = new String[miLista.size()][5];
		for (int i = 0; i < miLista.size(); i++) {
			matrizInfo[i][0] = miLista.get(i).getId_ingreso() + "";
			matrizInfo[i][1] = miLista.get(i).getMonto() + "";
			matrizInfo[i][2] = miLista.get(i).getRazon() + "";
			matrizInfo[i][3] = miLista.get(i).getFecha_hora_ingreso() + "";
			matrizInfo[i][4] = miLista.get(i).getEmpleado() + "";

		}
		return matrizInfo;
	}

	public void filtro() {
		filtroCodigo = txtBuscar.getText().toString();
		trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText().toString(), 0, 1, 2, 3, 4));
	}

	public void utilJTablePrint(JTable jTable, String header, String footer, boolean showPrintDialog) {
		boolean fitWidth = true;
		boolean interactive = true;
		JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL;
		try {
			boolean complete = jTable.print(mode, new MessageFormat(header), new MessageFormat(footer), showPrintDialog,
					null, interactive);
			if (complete) {
				JOptionPane.showMessageDialog(jTable, "Print complete (Impresión completa)",
						"Print result (Resultado de la impresión)", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(jTable, "Print canceled (Impresión cancelada)",
						"Print result (Resultado de la impresión)", JOptionPane.WARNING_MESSAGE);
			}
		} catch (PrinterException pe) {
			JOptionPane.showMessageDialog(jTable, "Print fail (Fallo de impresión): " + pe.getMessage(),
					"Print result (Resultado de la impresión)", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void obtenerUltimoId() {
		String ultimoValor = null;
		int valor;
		String id = null;
		conexion objCon = new conexion();
		Connection conn = objCon.conectar();
		try {
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM ingresos ORDER BY id_ingreso DESC");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				ultimoValor = rsr.getString("id_ingreso");
				valor = Integer.parseInt(ultimoValor);
				valor = valor + 1;
				id = String.valueOf(valor);
			}
			lblId.setText(id);
			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void obtenerTotalDatosReporte() {
		conexion objCon = new conexion();
		Connection conn = objCon.conectar();
		try {
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM ingresos ORDER BY id_ingreso DESC");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				totalDatos = rsr.getString("id_ingreso");
			}
			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getFechaYHora() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		SimpleDateFormat df = new SimpleDateFormat("dd'/'MMMMM'/'yyyy HH:mm:ss ");
		date = cal.getTime();
		return df.format(date);
	}

	private void close() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	private void limpiar() {
		txtMonto.setText("");
		txtRazon.setText("");
	}
}
