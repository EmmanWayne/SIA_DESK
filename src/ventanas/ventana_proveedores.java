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

import clases.clientes;
import clases.proveedores;
import clases.roles;
import clases.usuarios;
import conexion.conexion;
import consultas.consultas_clientes;
import consultas.consultas_proveedores;
import consultas.consultas_roles;
import consultas.consultas_usuarios;

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

public class ventana_proveedores extends JFrame {

	private JPanel contentPane;
	public static JLabel lblVentana;
	public JTextField txtNombre;
	public JTextField txtDireccion;
	public JTextField txtTelefono;
	public JComboBox<?> cbxProveedor;
	public JLabel lblId;
	public static String totalDatos;
	public JTable tabla;
	public JScrollPane barra;
	public TableRowSorter<TableModel> trsfiltroCodigo;
	String filtroCodigo;
	public JTextField txtBuscar;
	public JButton btnGuardar, btnActualizar, btnEliminar, btnEditar, btnLimpiar;
	public static String rol;
	public JTextField txtProdServ;
	public JTextField txtDetalles;

	public ventana_proveedores() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Ventana PROVEEDORES");
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
		panel.setBounds(10, 11, 1164, 33);
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
		btnAtras.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnAtras.setBackground(new Color(0, 0, 255));
		btnAtras.setBounds(1045, 7, 109, 23);
		panel.add(btnAtras);

		lblVentana = new JLabel("PROVEEDORES");
		lblVentana.setForeground(Color.WHITE);
		lblVentana.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblVentana.setBounds(10, 0, 1144, 33);
		panel.add(lblVentana);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(10, 46, 1164, 504);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMenPrincipal = new JLabel("Registrar Proveedores");
		lblMenPrincipal.setForeground(Color.WHITE);
		lblMenPrincipal.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblMenPrincipal.setBounds(10, 11, 350, 27);
		panel_1.add(lblMenPrincipal);

		JLabel lblRegistros = new JLabel("Proveedores Registrados");
		lblRegistros.setForeground(Color.WHITE);
		lblRegistros.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblRegistros.setBounds(370, 11, 784, 27);
		panel_1.add(lblRegistros);

		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBackground(Color.WHITE);
		panel_2_1_1.setBounds(10, 39, 350, 454);
		panel_1.add(panel_2_1_1);
		panel_2_1_1.setLayout(null);

		JLabel lblNombre = new JLabel("Direcci\u00F3n:");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNombre.setBounds(10, 155, 205, 27);
		panel_2_1_1.add(lblNombre);

		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtDireccion.setToolTipText("");
		txtDireccion.setBounds(10, 182, 331, 27);
		panel_2_1_1.add(txtDireccion);
		txtDireccion.setColumns(10);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(new Color(0, 128, 0));
		btnGuardar.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnGuardar.setBounds(10, 420, 109, 23);
		panel_2_1_1.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtNombre.getText().isEmpty() || txtDireccion.getText().isEmpty()
						|| txtTelefono.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Porfavor llene los campos para registrar el registro!");
				} else {
					proveedores clase = new proveedores();
					consultas_proveedores consulta = new consultas_proveedores();
					clase.setTipo_proveedor(cbxProveedor.getSelectedItem().toString());
					clase.setNombre_proveedor(txtNombre.getText().toString());
					clase.setTelefono_proveedor(txtTelefono.getText().toString());
					clase.setDireccion_proveedor(txtDireccion.getText().toString());
					clase.setPs_proveedor(txtProdServ.getText().toString());
					clase.setDetalle_proveedor(txtDetalles.getText().toString());

					if (consulta.insertar(clase)) {
						JOptionPane.showMessageDialog(null, "¡Registro Exitoso!");
						limpiar();
						construirTabla();
						obtenerUltimoId();
						btnGuardar.setEnabled(true);
						btnActualizar.setEnabled(false);
						btnEliminar.setEnabled(true);
						btnEditar.setEnabled(true);
						btnLimpiar.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "¡Error de registro!");
						limpiar();
						construirTabla();
						obtenerUltimoId();
						btnGuardar.setEnabled(true);
						btnActualizar.setEnabled(false);
						btnEliminar.setEnabled(true);
						btnEditar.setEnabled(true);
						btnLimpiar.setEnabled(true);

					}
				}
			}

		});

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setBackground(new Color(0, 128, 128));
		btnActualizar.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnActualizar.setBounds(121, 420, 109, 23);
		panel_2_1_1.add(btnActualizar);
		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtNombre.getText().isEmpty() || txtDireccion.getText().isEmpty()
						|| txtTelefono.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Porfavor llene los campos para actualizar el registro!");
				} else {
					proveedores clase = new proveedores();
					consultas_proveedores consulta = new consultas_proveedores();
					clase.setTipo_proveedor(cbxProveedor.getSelectedItem().toString());
					clase.setNombre_proveedor(txtNombre.getText().toString());
					clase.setTelefono_proveedor(txtTelefono.getText().toString());
					clase.setDireccion_proveedor(txtDireccion.getText().toString());
					clase.setPs_proveedor(txtProdServ.getText().toString());
					clase.setDetalle_proveedor(txtDetalles.getText().toString());
					clase.setId_proveedor(Integer.parseInt(lblId.getText().toString()));

					if (consulta.actualizar(clase)) {
						JOptionPane.showMessageDialog(null, "¡Actualización Exitosa!");
						limpiar();
						construirTabla();
						obtenerUltimoId();
						btnGuardar.setEnabled(true);
						btnActualizar.setEnabled(false);
						btnEliminar.setEnabled(true);
						btnEditar.setEnabled(true);
						btnLimpiar.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "¡Error de Actualización!");
						limpiar();
						construirTabla();
						obtenerUltimoId();
						btnGuardar.setEnabled(true);
						btnActualizar.setEnabled(false);
						btnEliminar.setEnabled(true);
						btnEditar.setEnabled(true);
						btnLimpiar.setEnabled(true);

					}
				}
			}

		});

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.BLACK);
		btnLimpiar.setBackground(Color.WHITE);
		btnLimpiar.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnLimpiar.setBounds(232, 420, 109, 23);
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
		lblN.setBounds(10, 0, 124, 27);
		panel_2_1_1.add(lblN);

		lblId = new JLabel("");
		lblId.setBounds(122, 0, 38, 27);
		panel_2_1_1.add(lblId);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));

		JLabel lblCodigoDelRol = new JLabel("Nombre:");
		lblCodigoDelRol.setForeground(Color.BLACK);
		lblCodigoDelRol.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblCodigoDelRol.setBounds(10, 101, 205, 27);
		panel_2_1_1.add(lblCodigoDelRol);

		txtNombre = new JTextField();
		txtNombre.setToolTipText("");
		txtNombre.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBounds(10, 128, 331, 27);
		panel_2_1_1.add(txtNombre);

		JLabel lblIdentidad = new JLabel("Tel\u00E9fono:");
		lblIdentidad.setForeground(Color.BLACK);
		lblIdentidad.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblIdentidad.setBounds(10, 209, 205, 27);
		panel_2_1_1.add(lblIdentidad);

		txtTelefono = new JTextField();
		txtTelefono.setToolTipText("");
		txtTelefono.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(10, 236, 331, 27);
		panel_2_1_1.add(txtTelefono);

		JLabel lblSexo = new JLabel("Proveedor:");
		lblSexo.setForeground(Color.BLACK);
		lblSexo.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblSexo.setBounds(10, 36, 205, 27);
		panel_2_1_1.add(lblSexo);

		cbxProveedor = new JComboBox();
		cbxProveedor.setModel(new DefaultComboBoxModel(new String[] { "Persona", "Empresa" }));
		cbxProveedor.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		cbxProveedor.setBounds(10, 63, 331, 27);
		panel_2_1_1.add(cbxProveedor);

		JLabel lblProductos = new JLabel("Productos o servicios que presta:");
		lblProductos.setForeground(Color.BLACK);
		lblProductos.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblProductos.setBounds(10, 272, 331, 27);
		panel_2_1_1.add(lblProductos);

		txtProdServ = new JTextField();
		txtProdServ.setToolTipText("");
		txtProdServ.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtProdServ.setColumns(10);
		txtProdServ.setBounds(10, 299, 331, 27);
		panel_2_1_1.add(txtProdServ);

		JLabel lblServicios = new JLabel("Detalles del proveedor:");
		lblServicios.setForeground(Color.BLACK);
		lblServicios.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblServicios.setBounds(10, 336, 205, 27);
		panel_2_1_1.add(lblServicios);

		txtDetalles = new JTextField();
		txtDetalles.setToolTipText("");
		txtDetalles.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtDetalles.setColumns(10);
		txtDetalles.setBounds(10, 363, 331, 27);
		panel_2_1_1.add(txtDetalles);

		JPanel panel_2_1_1_1 = new JPanel();
		panel_2_1_1_1.setBackground(Color.WHITE);
		panel_2_1_1_1.setBounds(370, 39, 784, 454);
		panel_1.add(panel_2_1_1_1);
		panel_2_1_1_1.setLayout(null);

		btnEditar = new JButton("Editar");
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnEditar.setBackground(new Color(255, 215, 0));
		btnEditar.setBounds(654, 409, 109, 23);
		panel_2_1_1_1.add(btnEditar);
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
						String tipo = tabla.getValueAt(filaseleccionada, 1).toString();
						String nombre = tabla.getValueAt(filaseleccionada, 2).toString();
						String direccion = tabla.getValueAt(filaseleccionada, 3).toString();
						String telefono = tabla.getValueAt(filaseleccionada, 4).toString();
						String ps = tabla.getValueAt(filaseleccionada, 5).toString();
						String detalle = tabla.getValueAt(filaseleccionada, 6).toString();

						lblId.setText(id);
						cbxProveedor.setSelectedItem(tipo);
						txtNombre.setText(nombre);
						txtTelefono.setText(telefono);
						txtDireccion.setText(direccion);
						txtProdServ.setText(ps);
						txtDetalles.setText(detalle);

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

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnEliminar.setBackground(new Color(178, 34, 34));
		btnEliminar.setBounds(535, 409, 109, 23);
		panel_2_1_1_1.add(btnEliminar);
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
						ps = conn.prepareStatement("DELETE FROM proveedores WHERE id_proveedor=?");
						ps.setString(1, codigo);
						ps.execute();
						JOptionPane.showMessageDialog(null, "¡Registro Eliminado!");
						txtDireccion.setText("");
						construirTabla();
						obtenerUltimoId();
					}

				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al Eliminar");
					System.out.println(ex.toString());
				}
			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 764, 432);
		panel_2_1_1_1.add(panel_2);
		panel_2.setLayout(null);

		barra = new JScrollPane(tabla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		barra.setBounds(10, 45, 744, 342);
		panel_2.add(barra);
		tabla = new JTable();
		barra.setViewportView(tabla);

		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setForeground(Color.WHITE);
		btnImprimir.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnImprimir.setBackground(new Color(255, 140, 0));
		btnImprimir.setBounds(645, 11, 109, 23);
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

					String encabezado = "Reporte de PROVEEDORES registrados";

					utilJTablePrint(tabla, encabezado, "Pagina {0} de " + i + "          SIA 2021          " + fecha,
							true);

				}
			}
		});

		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setForeground(Color.BLACK);
		lblBuscar.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblBuscar.setBounds(10, 11, 97, 23);
		panel_2.add(lblBuscar);

		txtBuscar = new JTextField();
		txtBuscar.setToolTipText("");
		txtBuscar.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(80, 12, 555, 22);
		panel_2.add(txtBuscar);
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
		String titulos[] = { "N°", "Tipo", "Nombre", "Dirección", "Teléfono", "PyS que presta", "Detalles" };
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

	public static ArrayList<proveedores> buscarUsuariosConMatriz() {
		conexion conex = new conexion();
		ArrayList<proveedores> miLista = new ArrayList<proveedores>();
		proveedores proveedores;
		try {
			Statement estatuto = conex.conectar().createStatement();
			ResultSet rs = estatuto.executeQuery("select * from proveedores");

			while (rs.next()) {
				proveedores = new proveedores();
				proveedores.setId_proveedor(Integer.parseInt(rs.getString("id_proveedor")));
				proveedores.setTipo_proveedor(rs.getString("tipo_proveedor"));
				proveedores.setNombre_proveedor(rs.getString("nombre_proveedor"));
				proveedores.setDireccion_proveedor(rs.getString("direccion_proveedor"));
				proveedores.setTelefono_proveedor(rs.getString("telefono_proveedor"));
				proveedores.setPs_proveedor(rs.getString("ps_proveedor"));
				proveedores.setDetalle_proveedor(rs.getString("detalle_proveedor"));
				miLista.add(proveedores);
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
		ArrayList<proveedores> miLista = buscarUsuariosConMatriz();
		String matrizInfo[][] = new String[miLista.size()][7];
		for (int i = 0; i < miLista.size(); i++) {
			matrizInfo[i][0] = miLista.get(i).getId_proveedor() + "";
			matrizInfo[i][1] = miLista.get(i).getTipo_proveedor() + "";
			matrizInfo[i][2] = miLista.get(i).getNombre_proveedor() + "";
			matrizInfo[i][3] = miLista.get(i).getDireccion_proveedor() + "";
			matrizInfo[i][4] = miLista.get(i).getTelefono_proveedor() + "";
			matrizInfo[i][5] = miLista.get(i).getPs_proveedor() + "";
			matrizInfo[i][6] = miLista.get(i).getDetalle_proveedor() + "";
		}
		return matrizInfo;
	}

	public void filtro() {
		filtroCodigo = txtBuscar.getText().toString();
		trsfiltroCodigo
				.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText().toString(), 0, 1, 2, 3, 4, 5, 6));
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
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM proveedores ORDER BY id_proveedor DESC");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				ultimoValor = rsr.getString("id_proveedor");
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
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM proveedores ORDER BY id_proveedor DESC");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				totalDatos = rsr.getString("id_proveedor");
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

	public void limpiar() {
		txtNombre.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtProdServ.setText("");
		txtDetalles.setText("");
	}
}
