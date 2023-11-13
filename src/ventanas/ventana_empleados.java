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
import clases.empleados;
import clases.roles;
import clases.usuarios;
import conexion.conexion;
import consultas.consultas_clientes;
import consultas.consultas_empleados;
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

public class ventana_empleados extends JFrame {

	private JPanel contentPane;
	public static JLabel lblVentana;

	public JLabel lblId;
	public static String totalDatos;
	public JTable tabla;
	public JScrollPane barra;
	public TableRowSorter<TableModel> trsfiltroCodigo;
	String filtroCodigo;
	public JTextField txtBuscar;
	public JButton btnGuardar, btnActualizar, btnEliminar, btnEditar, btnLimpiar;
	public static String rol;

	public JTextField txtNombres;
	public JTextField txtApellidos;
	public JTextField txtIdentidad;
	public JTextField txtEdad;
	public JTextField txtCargo;
	public JTextField txtSalario;
	public JTextField txtTelefono;
	public JTextField txtDireccion;
	public JComboBox<?> cbxEstado;
	public JComboBox<?> cbxSexo;

	public ventana_empleados() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Ventana EMPLEADOS");
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
		btnAtras.setBounds(1045, 8, 109, 23);
		panel.add(btnAtras);

		lblVentana = new JLabel("EMPLEADOS\r\n");
		lblVentana.setForeground(Color.WHITE);
		lblVentana.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblVentana.setBounds(10, 0, 1144, 33);
		panel.add(lblVentana);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(10, 46, 1164, 504);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMenPrincipal = new JLabel("Registrar Empleados");
		lblMenPrincipal.setForeground(Color.WHITE);
		lblMenPrincipal.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblMenPrincipal.setBounds(10, 11, 350, 27);
		panel_1.add(lblMenPrincipal);

		JLabel lblRegistros = new JLabel("Empleados Registrados");
		lblRegistros.setForeground(Color.WHITE);
		lblRegistros.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblRegistros.setBounds(491, 11, 663, 27);
		panel_1.add(lblRegistros);

		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBackground(Color.WHITE);
		panel_2_1_1.setBounds(10, 39, 471, 454);
		panel_1.add(panel_2_1_1);
		panel_2_1_1.setLayout(null);

		JLabel lblNombre = new JLabel("Apellidos:");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNombre.setBounds(240, 42, 205, 17);
		panel_2_1_1.add(lblNombre);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(new Color(0, 128, 0));
		btnGuardar.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnGuardar.setBounds(10, 420, 109, 23);
		panel_2_1_1.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtNombres.getText().isEmpty() || txtApellidos.getText().isEmpty()
						|| txtIdentidad.getText().isEmpty() || txtEdad.getText().isEmpty()
						|| txtCargo.getText().isEmpty() || txtSalario.getText().isEmpty()
						|| txtTelefono.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Porfavor llene los campos para registrar el registro!");
				} else {
					empleados clase = new empleados();
					consultas_empleados consulta = new consultas_empleados();
					clase.setNombres(txtNombres.getText().toString());
					clase.setApellidos(txtApellidos.getText().toString());
					clase.setIdentidad(txtIdentidad.getText().toString());
					clase.setSexo(cbxSexo.getSelectedItem().toString());
					clase.setEdad(txtEdad.getText().toString());
					clase.setCargo(txtCargo.getText().toString());
					clase.setSalario(txtSalario.getText().toString());
					clase.setTelefono(txtTelefono.getText().toString());
					clase.setDireccion(txtDireccion.getText().toString());
					clase.setEstado(cbxEstado.getSelectedItem().toString());

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
				if (txtNombres.getText().isEmpty() || txtApellidos.getText().isEmpty()
						|| txtIdentidad.getText().isEmpty() || txtEdad.getText().isEmpty()
						|| txtCargo.getText().isEmpty() || txtSalario.getText().isEmpty()
						|| txtTelefono.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Porfavor llene los campos para actualizar el registro!");
				} else {
					empleados clase = new empleados();
					consultas_empleados consulta = new consultas_empleados();
					clase.setNombres(txtNombres.getText().toString());
					clase.setApellidos(txtApellidos.getText().toString());
					clase.setIdentidad(txtIdentidad.getText().toString());
					clase.setSexo(cbxSexo.getSelectedItem().toString());
					clase.setEdad(txtEdad.getText().toString());
					clase.setCargo(txtCargo.getText().toString());
					clase.setSalario(txtSalario.getText().toString());
					clase.setTelefono(txtTelefono.getText().toString());
					clase.setDireccion(txtDireccion.getText().toString());
					clase.setEstado(cbxEstado.getSelectedItem().toString());
					clase.setId_empleado(Integer.parseInt(lblId.getText().toString()));

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
		lblN.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblN.setBounds(10, 12, 124, 27);
		panel_2_1_1.add(lblN);

		lblId = new JLabel("");
		lblId.setBounds(121, 13, 38, 27);
		panel_2_1_1.add(lblId);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));

		JLabel lblCodigoDelRol = new JLabel("Nombres:");
		lblCodigoDelRol.setForeground(Color.BLACK);
		lblCodigoDelRol.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblCodigoDelRol.setBounds(10, 42, 205, 17);
		panel_2_1_1.add(lblCodigoDelRol);

		txtNombres = new JTextField();
		txtNombres.setToolTipText("");
		txtNombres.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtNombres.setColumns(10);
		txtNombres.setBounds(10, 60, 220, 27);
		panel_2_1_1.add(txtNombres);

		cbxEstado = new JComboBox();
		cbxEstado.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		cbxEstado.setModel(new DefaultComboBoxModel(new String[] { "ACTIVO", "INACTIVO" }));
		cbxEstado.setBounds(240, 284, 220, 27);
		panel_2_1_1.add(cbxEstado);

		txtApellidos = new JTextField();
		txtApellidos.setToolTipText("");
		txtApellidos.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(240, 60, 220, 27);
		panel_2_1_1.add(txtApellidos);

		JLabel lblIdentidad = new JLabel("Identidad:");
		lblIdentidad.setForeground(Color.BLACK);
		lblIdentidad.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblIdentidad.setBounds(10, 98, 205, 17);
		panel_2_1_1.add(lblIdentidad);

		txtIdentidad = new JTextField();
		txtIdentidad.setToolTipText("");
		txtIdentidad.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtIdentidad.setColumns(10);
		txtIdentidad.setBounds(10, 116, 220, 27);
		panel_2_1_1.add(txtIdentidad);

		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setForeground(Color.BLACK);
		lblEdad.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblEdad.setBounds(10, 266, 205, 17);
		panel_2_1_1.add(lblEdad);

		txtEdad = new JTextField();
		txtEdad.setToolTipText("");
		txtEdad.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtEdad.setColumns(10);
		txtEdad.setBounds(10, 284, 220, 27);
		panel_2_1_1.add(txtEdad);

		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setForeground(Color.BLACK);
		lblCargo.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblCargo.setBounds(10, 154, 205, 17);
		panel_2_1_1.add(lblCargo);

		txtCargo = new JTextField();
		txtCargo.setToolTipText("");
		txtCargo.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtCargo.setColumns(10);
		txtCargo.setBounds(10, 172, 220, 27);
		panel_2_1_1.add(txtCargo);

		JLabel lblSalario = new JLabel("Salario:");
		lblSalario.setForeground(Color.BLACK);
		lblSalario.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblSalario.setBounds(240, 154, 205, 17);
		panel_2_1_1.add(lblSalario);

		txtSalario = new JTextField();
		txtSalario.setToolTipText("");
		txtSalario.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtSalario.setColumns(10);
		txtSalario.setBounds(240, 172, 220, 27);
		panel_2_1_1.add(txtSalario);

		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setForeground(Color.BLACK);
		lblTelfono.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblTelfono.setBounds(10, 210, 205, 17);
		panel_2_1_1.add(lblTelfono);

		txtTelefono = new JTextField();
		txtTelefono.setToolTipText("");
		txtTelefono.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(10, 228, 220, 27);
		panel_2_1_1.add(txtTelefono);

		JLabel lblNombre_1_1 = new JLabel("Direcci\u00F3n:");
		lblNombre_1_1.setForeground(Color.BLACK);
		lblNombre_1_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNombre_1_1.setBounds(240, 210, 205, 17);
		panel_2_1_1.add(lblNombre_1_1);

		txtDireccion = new JTextField();
		txtDireccion.setToolTipText("");
		txtDireccion.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(240, 228, 220, 27);
		panel_2_1_1.add(txtDireccion);

		JLabel lblNombre_1_1_1 = new JLabel("Estado:");
		lblNombre_1_1_1.setForeground(Color.BLACK);
		lblNombre_1_1_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNombre_1_1_1.setBounds(240, 264, 220, 17);
		panel_2_1_1.add(lblNombre_1_1_1);

		JLabel lblNombre_1_1_1_1 = new JLabel("Sexo:");
		lblNombre_1_1_1_1.setForeground(Color.BLACK);
		lblNombre_1_1_1_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNombre_1_1_1_1.setBounds(240, 96, 220, 17);
		panel_2_1_1.add(lblNombre_1_1_1_1);

		cbxSexo = new JComboBox();
		cbxSexo.setModel(new DefaultComboBoxModel(new String[] { "F", "M" }));
		cbxSexo.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		cbxSexo.setBounds(240, 116, 220, 27);
		panel_2_1_1.add(cbxSexo);

		JPanel panel_2_1_1_1 = new JPanel();
		panel_2_1_1_1.setBackground(Color.WHITE);
		panel_2_1_1_1.setBounds(491, 39, 663, 454);
		panel_1.add(panel_2_1_1_1);
		panel_2_1_1_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 643, 432);
		panel_2_1_1_1.add(panel_2);
		panel_2.setLayout(null);

		barra = new JScrollPane(tabla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		barra.setBounds(10, 45, 623, 342);
		panel_2.add(barra);
		tabla = new JTable();
		barra.setViewportView(tabla);

		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setForeground(Color.WHITE);
		btnImprimir.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnImprimir.setBackground(new Color(255, 140, 0));
		btnImprimir.setBounds(524, 11, 109, 23);
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

					String encabezado = "Reporte de EMPLEADOS registrados";

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
		txtBuscar.setBounds(83, 12, 431, 22);
		panel_2.add(txtBuscar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(405, 398, 109, 23);
		panel_2.add(btnEliminar);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnEliminar.setBackground(new Color(178, 34, 34));

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(524, 398, 109, 23);
		panel_2.add(btnEditar);
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
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
						String nombres = tabla.getValueAt(filaseleccionada, 1).toString();
						String apellidos = tabla.getValueAt(filaseleccionada, 2).toString();
						String identidad = tabla.getValueAt(filaseleccionada, 3).toString();
						String sexo = tabla.getValueAt(filaseleccionada, 4).toString();
						String edad = tabla.getValueAt(filaseleccionada, 5).toString();
						String cargo = tabla.getValueAt(filaseleccionada, 6).toString();
						String salario = tabla.getValueAt(filaseleccionada, 7).toString();
						String telefono = tabla.getValueAt(filaseleccionada, 8).toString();
						String direccion = tabla.getValueAt(filaseleccionada, 9).toString();
						String estado = tabla.getValueAt(filaseleccionada, 10).toString();

						lblId.setText(id);
						txtNombres.setText(nombres);
						txtApellidos.setText(apellidos);
						txtIdentidad.setText(identidad);
						cbxSexo.setSelectedItem(sexo);
						txtEdad.setText(edad);
						txtCargo.setText(cargo);
						txtSalario.setText(salario);
						txtTelefono.setText(telefono);
						txtDireccion.setText(direccion);
						cbxEstado.setSelectedItem(estado);

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
						ps = conn.prepareStatement("DELETE FROM empleados WHERE id_empleado=?");
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
		String titulos[] = { "N°", "Nombres", "Apellidos", "Identidad", "Sexo", "Edad", "Cargo", "Salario", "Telefono",
				"Direccion", "Estado" };
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

	public static ArrayList<empleados> buscarUsuariosConMatriz() {
		conexion conex = new conexion();
		ArrayList<empleados> miLista = new ArrayList<empleados>();
		empleados empleados;
		try {
			Statement estatuto = conex.conectar().createStatement();
			ResultSet rs = estatuto.executeQuery("select * from empleados");

			while (rs.next()) {
				empleados = new empleados();
				empleados.setId_empleado(Integer.parseInt(rs.getString("id_empleado")));
				empleados.setNombres(rs.getString("nombres"));
				empleados.setApellidos(rs.getString("apellidos"));
				empleados.setIdentidad(rs.getString("identidad"));
				empleados.setSexo(rs.getString("sexo"));
				empleados.setEdad(rs.getString("edad"));
				empleados.setCargo(rs.getString("cargo"));
				empleados.setSalario(rs.getString("salario"));
				empleados.setTelefono(rs.getString("telefono"));
				empleados.setDireccion(rs.getString("direccion"));
				empleados.setEstado(rs.getString("estado"));
				miLista.add(empleados);
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
		ArrayList<empleados> miLista = buscarUsuariosConMatriz();
		String matrizInfo[][] = new String[miLista.size()][11];
		for (int i = 0; i < miLista.size(); i++) {
			matrizInfo[i][0] = miLista.get(i).getId_empleado() + "";
			matrizInfo[i][1] = miLista.get(i).getNombres() + "";
			matrizInfo[i][2] = miLista.get(i).getApellidos() + "";
			matrizInfo[i][3] = miLista.get(i).getIdentidad() + "";
			matrizInfo[i][4] = miLista.get(i).getSexo() + "";
			matrizInfo[i][5] = miLista.get(i).getEdad() + "";
			matrizInfo[i][6] = miLista.get(i).getCargo() + "";
			matrizInfo[i][7] = miLista.get(i).getSalario() + "";
			matrizInfo[i][8] = miLista.get(i).getTelefono() + "";
			matrizInfo[i][9] = miLista.get(i).getDireccion() + "";
			matrizInfo[i][10] = miLista.get(i).getEstado() + "";
		}
		return matrizInfo;
	}

	public void filtro() {
		filtroCodigo = txtBuscar.getText().toString();
		trsfiltroCodigo.setRowFilter(
				RowFilter.regexFilter("(?i)" + txtBuscar.getText().toString(), 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
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
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM empleados ORDER BY id_empleado DESC");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				ultimoValor = rsr.getString("id_empleado");
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
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM empleados ORDER BY id_empleado DESC");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				totalDatos = rsr.getString("id_empleado");
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
		txtNombres.setText("");
		txtApellidos.setText("");
		txtIdentidad.setText("");
		txtEdad.setText("");
		txtCargo.setText("");
		txtSalario.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
	}
}
