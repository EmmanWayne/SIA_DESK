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

import clases.productos;
import conexion.conexion;
import consultas.consultas_productos;
import recursos.visor_imagen;

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
import javax.swing.JTextPane;
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
import java.awt.event.KeyAdapter;
import java.awt.Panel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class ventana_productos extends JFrame {

	private JPanel contentPane;
	public static JLabel lblVentana;
	public JTextField txtNombre;
	public JTextPane txtDescripcion;
	public JLabel lblId;
	public static String totalDatos;
	public static String codigo;
	public static String nombre;
	public static String descripcion;
	public JTable tabla;
	public JScrollPane barra;
	public TableRowSorter<TableModel> trsfiltroCodigo;
	String filtroCodigo;
	public TableRowSorter<TableModel> trsfiltroCodigo1;
	String filtroCodigo1;
	public JTextField txtBuscar;
	public JButton btnGuardar, btnActualizar, btnEliminar, btnEditar, btnLimpiar;
	public JLabel lblCantidadDelProducto;
	public JTextField txtCantidad;
	public JLabel lblCosto;
	public JTextField txtCosto;
	public JTextField txtExistencia;
	public JLabel lblImagen;
	public JTextField txtRutaImagen;
	public static String ruta;
	public static ImageIcon imagen;
	public JLabel lblImagenProducto;
	public JTextField txtCodigo;
	private JLabel lblValorDeVenta;
	public JTextField txtValor;
	private JLabel lblUtilidad;
	public JTextField txtUtilidad;
	public JLabel lblFechaProducto;
	public JButton btnAdjuntarImagen;
	private JLabel lblExistencia;
	private JButton btnObtener;
	private JLabel lblPorFecha;
	public JDateChooser JdateFecha;
	public JTextFieldDateEditor txtFecha;

	public ventana_productos() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Ventana PRODUCTOS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/recursos/logo_sistema.png")));
		final ImageIcon producto = new ImageIcon(getClass().getResource("/recursos/productos.png"));

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

		JButton btnAtras = new JButton("Atras");
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

		lblVentana = new JLabel("PRODUCTOS");
		lblVentana.setForeground(Color.WHITE);
		lblVentana.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblVentana.setBounds(10, 0, 1144, 33);
		panel.add(lblVentana);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(10, 46, 1164, 554);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMenPrincipal = new JLabel("Registrar Productos");
		lblMenPrincipal.setForeground(Color.WHITE);
		lblMenPrincipal.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblMenPrincipal.setBounds(10, 11, 350, 27);
		panel_1.add(lblMenPrincipal);

		JLabel lblRegistros = new JLabel("Productos Registrados");
		lblRegistros.setForeground(Color.WHITE);
		lblRegistros.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblRegistros.setBounds(380, 11, 774, 27);
		panel_1.add(lblRegistros);

		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBackground(Color.WHITE);
		panel_2_1_1.setBounds(10, 39, 360, 504);
		panel_1.add(panel_2_1_1);
		panel_2_1_1.setLayout(null);

		JLabel lblNombre = new JLabel("Descripci\u00F3n:");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNombre.setBounds(10, 103, 166, 27);
		panel_2_1_1.add(lblNombre);

		txtDescripcion = new JTextPane();
		txtDescripcion.setBackground(Color.LIGHT_GRAY);
		txtDescripcion.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtDescripcion.setToolTipText("");
		txtDescripcion.setBounds(10, 130, 340, 51);
		panel_2_1_1.add(txtDescripcion);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(new Color(0, 128, 0));
		btnGuardar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnGuardar.setBounds(10, 466, 109, 27);
		panel_2_1_1.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtNombre.getText().isEmpty() && txtDescripcion.getText().isEmpty()
						&& txtCantidad.getText().isEmpty() && txtCosto.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Porfavor llene los campos requeridos para guardar el registro!");
				} else {
					productos clase = new productos();
					consultas_productos consulta = new consultas_productos();
					clase.setNombre_producto(txtNombre.getText().toString());
					clase.setCodigo_producto(Integer.parseInt(txtCodigo.getText().toString()));
					clase.setDescripcion_producto(txtDescripcion.getText().toString());
					clase.setCantidad_producto(Integer.parseInt(txtCantidad.getText().toString()));
					clase.setCosto_producto(Double.parseDouble(txtCosto.getText().toString()));
					clase.setValor_producto(Double.parseDouble(txtValor.getText().toString()));
					clase.setUtilidad_producto(Double.parseDouble(txtUtilidad.getText().toString()));
					clase.setFecha_producto(getFechaYHora());
					clase.setImagen_producto(txtRutaImagen.getText().toString());

					if (consulta.insertar(clase)) {
						JOptionPane.showMessageDialog(null, "¡Registro Exitoso!");
						limpiar();
						construirTabla();
						obtenerUltimoId();
						BloquearCampos();
						txtCodigo.setEditable(true);
						final ImageIcon icono = new ImageIcon(producto.getImage().getScaledInstance(
								lblImagenProducto.getWidth(), lblImagenProducto.getHeight(), Image.SCALE_DEFAULT));
						lblImagenProducto.setIcon(icono);
						btnGuardar.setEnabled(true);
						btnActualizar.setEnabled(false);
						btnEliminar.setEnabled(true);
						btnLimpiar.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "¡Error de registro!");
						limpiar();
						construirTabla();
						obtenerUltimoId();
						BloquearCampos();
						txtCodigo.setEditable(true);
						final ImageIcon icono = new ImageIcon(producto.getImage().getScaledInstance(
								lblImagenProducto.getWidth(), lblImagenProducto.getHeight(), Image.SCALE_DEFAULT));
						lblImagenProducto.setIcon(icono);
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
		btnActualizar.setBounds(129, 466, 109, 27);
		panel_2_1_1.add(btnActualizar);
		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtNombre.getText().isEmpty() && txtDescripcion.getText().isEmpty()
						&& txtCantidad.getText().isEmpty() && txtCosto.getText().isEmpty()
						&& txtRutaImagen.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Porfavor llene los campos requeridos para actualizar el registro!");
				} else {
					productos clase = new productos();
					consultas_productos consulta = new consultas_productos();
					clase.setNombre_producto(txtNombre.getText().toString());
					clase.setCodigo_producto(Integer.parseInt(txtCodigo.getText().toString()));
					clase.setDescripcion_producto(txtDescripcion.getText().toString());
					clase.setCantidad_producto(Integer.parseInt(txtCantidad.getText().toString()));
					clase.setCosto_producto(Double.parseDouble(txtCosto.getText().toString()));
					clase.setValor_producto(Double.parseDouble(txtValor.getText().toString()));
					clase.setUtilidad_producto(Double.parseDouble(txtUtilidad.getText().toString()));
					clase.setFecha_producto(getFechaYHora());
					clase.setImagen_producto(txtRutaImagen.getText().toString());
					clase.setId_producto(Integer.parseInt(lblId.getText().toString()));

					if (consulta.actualizar(clase)) {
						JOptionPane.showMessageDialog(null, "¡Actualización Exitosa!");
						limpiar();
						construirTabla();
						obtenerUltimoId();
						BloquearCampos();
						txtCodigo.setEditable(true);
						lblFechaProducto.setText("");
						final ImageIcon icono = new ImageIcon(producto.getImage().getScaledInstance(
								lblImagenProducto.getWidth(), lblImagenProducto.getHeight(), Image.SCALE_DEFAULT));
						lblImagenProducto.setIcon(icono);
						btnGuardar.setEnabled(true);
						btnActualizar.setEnabled(false);
						btnEliminar.setEnabled(true);
						btnLimpiar.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "¡Error de Actualización!");
						limpiar();
						construirTabla();
						obtenerUltimoId();
						BloquearCampos();
						txtCodigo.setEditable(true);
						final ImageIcon icono = new ImageIcon(producto.getImage().getScaledInstance(
								lblImagenProducto.getWidth(), lblImagenProducto.getHeight(), Image.SCALE_DEFAULT));
						lblImagenProducto.setIcon(icono);
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
		btnLimpiar.setBounds(241, 466, 109, 27);
		panel_2_1_1.add(btnLimpiar);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				construirTabla();
				obtenerUltimoId();
				BloquearCampos();
				txtCodigo.setEditable(true);
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

		JLabel lblCodigoDelRol = new JLabel("Nombre:");
		lblCodigoDelRol.setForeground(Color.BLACK);
		lblCodigoDelRol.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblCodigoDelRol.setBounds(184, 50, 166, 27);
		panel_2_1_1.add(lblCodigoDelRol);

		txtNombre = new JTextField();
		txtNombre.setToolTipText("");
		txtNombre.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBounds(184, 77, 166, 27);
		panel_2_1_1.add(txtNombre);

		lblCantidadDelProducto = new JLabel("Cantidad:");
		lblCantidadDelProducto.setForeground(Color.BLACK);
		lblCantidadDelProducto.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblCantidadDelProducto.setBounds(10, 223, 166, 27);
		panel_2_1_1.add(lblCantidadDelProducto);

		txtCantidad = new JTextField();
		txtCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtCantidad.setToolTipText("");
		txtCantidad.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(10, 250, 166, 27);
		panel_2_1_1.add(txtCantidad);

		lblCosto = new JLabel("Costo de compra:");
		lblCosto.setForeground(Color.BLACK);
		lblCosto.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblCosto.setBounds(10, 276, 166, 27);
		panel_2_1_1.add(lblCosto);

		txtCosto = new JTextField();
		txtCosto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCosto.setToolTipText("");
		txtCosto.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtCosto.setColumns(10);
		txtCosto.setBounds(10, 303, 166, 27);
		panel_2_1_1.add(txtCosto);

		lblImagen = new JLabel("Imagen:");
		lblImagen.setForeground(Color.BLACK);
		lblImagen.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblImagen.setBounds(10, 192, 166, 25);
		panel_2_1_1.add(lblImagen);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(187, 250, 163, 171);
		panel_2_1_1.add(panel_3);
		panel_3.setLayout(null);

		lblImagenProducto = new JLabel("");
		lblImagenProducto.setBounds(10, 11, 143, 149);
		panel_3.add(lblImagenProducto);
		final ImageIcon icono = new ImageIcon(producto.getImage().getScaledInstance(lblImagenProducto.getWidth(),
				lblImagenProducto.getHeight(), Image.SCALE_DEFAULT));
		lblImagenProducto.setIcon(icono);

		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verFotoEmpleado();
			}
		});
		btnMostrar.setForeground(Color.BLACK);
		btnMostrar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnMostrar.setBackground(Color.WHITE);
		btnMostrar.setBounds(186, 230, 164, 20);
		panel_2_1_1.add(btnMostrar);

		btnAdjuntarImagen = new JButton("Adjuntar imagen");
		btnAdjuntarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarImagenProducto();
			}
		});
		btnAdjuntarImagen.setForeground(Color.WHITE);
		btnAdjuntarImagen.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnAdjuntarImagen.setBackground(new Color(0, 128, 0));
		btnAdjuntarImagen.setBounds(186, 192, 164, 27);
		panel_2_1_1.add(btnAdjuntarImagen);

		txtRutaImagen = new JTextField();
		txtRutaImagen.setToolTipText("");
		txtRutaImagen.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtRutaImagen.setEditable(false);
		txtRutaImagen.setColumns(10);
		txtRutaImagen.setBounds(187, 422, 163, 17);
		panel_2_1_1.add(txtRutaImagen);

		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setForeground(Color.BLACK);
		lblCdigo.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblCdigo.setBounds(10, 50, 97, 27);
		panel_2_1_1.add(lblCdigo);

		txtCodigo = new JTextField();
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setToolTipText("");
		txtCodigo.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(10, 77, 166, 27);
		panel_2_1_1.add(txtCodigo);

		lblValorDeVenta = new JLabel("Valor de venta:");
		lblValorDeVenta.setForeground(Color.BLACK);
		lblValorDeVenta.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblValorDeVenta.setBounds(10, 330, 166, 27);
		panel_2_1_1.add(lblValorDeVenta);

		txtValor = new JTextField();
		txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					double costo = Double.valueOf(txtCosto.getText().toString());
					double valor = Double.valueOf(txtValor.getText().toString());
					double utilidad = (valor - costo);
					txtUtilidad.setText(String.valueOf(utilidad));
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});
		txtValor.setToolTipText("");
		txtValor.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtValor.setColumns(10);
		txtValor.setBounds(10, 357, 166, 27);
		panel_2_1_1.add(txtValor);

		lblUtilidad = new JLabel("Utilidad:");
		lblUtilidad.setForeground(Color.BLACK);
		lblUtilidad.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblUtilidad.setBounds(10, 385, 166, 27);
		panel_2_1_1.add(lblUtilidad);

		txtUtilidad = new JTextField();
		txtUtilidad.setHorizontalAlignment(SwingConstants.RIGHT);
		txtUtilidad.setEditable(false);
		txtUtilidad.setToolTipText("");
		txtUtilidad.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtUtilidad.setColumns(10);
		txtUtilidad.setBounds(10, 412, 166, 27);
		panel_2_1_1.add(txtUtilidad);

		JButton btnBuscarProducto = new JButton("Buscar");
		btnBuscarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCodigo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor ingrese el código del producto.");
				} else {
					obtenerCodigoDelProducto();
					establecerDatosDeProducto();
				}

			}
		});
		btnBuscarProducto.setBackground(new Color(34, 139, 34));
		btnBuscarProducto.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnBuscarProducto.setBounds(79, 55, 97, 20);
		panel_2_1_1.add(btnBuscarProducto);

		lblFechaProducto = new JLabel("");
		lblFechaProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaProducto.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblFechaProducto.setBounds(184, 12, 166, 23);
		panel_2_1_1.add(lblFechaProducto);

		JPanel panel_2_1_1_1 = new JPanel();
		panel_2_1_1_1.setBackground(Color.WHITE);
		panel_2_1_1_1.setBounds(380, 39, 774, 504);
		panel_1.add(panel_2_1_1_1);
		panel_2_1_1_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 754, 482);
		panel_2_1_1_1.add(panel_2);
		panel_2.setLayout(null);

		barra = new JScrollPane(tabla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		barra.setBounds(10, 45, 729, 392);
		panel_2.add(barra);
		tabla = new JTable();
		barra.setViewportView(tabla);

		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setForeground(Color.WHITE);
		btnImprimir.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnImprimir.setBackground(new Color(255, 140, 0));
		btnImprimir.setBounds(630, 10, 109, 23);
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

					String encabezado = "Reporte de PRODUCTOS registrados";

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
		txtBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscar.setToolTipText("");
		txtBuscar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(82, 11, 284, 23);
		panel_2.add(txtBuscar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(511, 448, 109, 27);
		panel_2.add(btnEliminar);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnEliminar.setBackground(new Color(178, 34, 34));

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(630, 448, 109, 27);
		panel_2.add(btnEditar);
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnEditar.setBackground(new Color(255, 215, 0));

		txtExistencia = new JTextField();
		txtExistencia.setBounds(217, 448, 126, 27);
		panel_2.add(txtExistencia);
		txtExistencia.setHorizontalAlignment(SwingConstants.CENTER);
		txtExistencia.setEditable(false);
		txtExistencia.setToolTipText("");
		txtExistencia.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtExistencia.setColumns(10);

		lblExistencia = new JLabel("Existencia:");
		lblExistencia.setForeground(Color.BLACK);
		lblExistencia.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblExistencia.setBounds(10, 446, 126, 27);
		panel_2.add(lblExistencia);

		btnObtener = new JButton("Obtener");
		btnObtener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtBuscar.equals("")) {
					JOptionPane.showMessageDialog(null, "Debe buscar el producto para conocer su existencia total.");
				} else {
					totalizarExistencia();
				}
			}
		});
		btnObtener.setForeground(Color.WHITE);
		btnObtener.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnObtener.setBackground(new Color(0, 128, 0));
		btnObtener.setBounds(110, 448, 97, 27);
		panel_2.add(btnObtener);
		
		lblPorFecha = new JLabel("Por fecha:");
		lblPorFecha.setForeground(Color.BLACK);
		lblPorFecha.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblPorFecha.setBounds(376, 10, 97, 23);
		panel_2.add(lblPorFecha);
		
		JdateFecha = new JDateChooser();
		JdateFecha.setBounds(461, 10, 159, 23);
		JdateFecha.setDateFormatString("dd-MM-yyyy");
		panel_2.add(JdateFecha);
		txtFecha = (JTextFieldDateEditor) JdateFecha.getDateEditor();
		//txtFecha.setEditable(false);
		txtFecha.setHorizontalAlignment(SwingConstants.CENTER);
		InputMap map61 = txtFecha.getInputMap(JComponent.WHEN_FOCUSED);
		map61.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtFecha.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				trsfiltroCodigo1 = new TableRowSorter(tabla.getModel());
				tabla.setRowSorter(trsfiltroCodigo1);
			}

			@Override
			public void keyPressed(KeyEvent ke) {

			}

			@Override
			public void keyReleased(KeyEvent ke) {
				String cadena = (txtFecha.getText().toString());
				txtFecha.setText(cadena);
				repaint();
				filtroFecha();
			}
		});
		
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
						String nombre = tabla.getValueAt(filaseleccionada, 1).toString();
						String codigo = tabla.getValueAt(filaseleccionada, 2).toString();
						String descrip = tabla.getValueAt(filaseleccionada, 3).toString();
						String cantidad = tabla.getValueAt(filaseleccionada, 4).toString();
						String costo = tabla.getValueAt(filaseleccionada, 5).toString();
						String valor = tabla.getValueAt(filaseleccionada, 6).toString();
						String utilidad = tabla.getValueAt(filaseleccionada, 7).toString();
						String fecha = tabla.getValueAt(filaseleccionada, 8).toString();
						String imagen = tabla.getValueAt(filaseleccionada, 9).toString();

						lblId.setText(id);
						txtNombre.setText(nombre);
						txtCodigo.setText(codigo);
						txtDescripcion.setText(descrip);
						txtCantidad.setText(cantidad);
						txtCosto.setText(costo);
						txtValor.setText(valor);
						txtUtilidad.setText(utilidad);
						lblFechaProducto.setText(fecha);

						txtRutaImagen.setText(imagen);
						Image foto = getToolkit().getImage(txtRutaImagen.getText());
						foto = foto.getScaledInstance(lblImagenProducto.getHeight(), lblImagenProducto.getWidth(),
								Image.SCALE_DEFAULT);
						lblImagenProducto.setIcon(new ImageIcon(foto));
						DesbloquearCampos();

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
						ps = conn.prepareStatement("DELETE FROM productos WHERE id_producto=?");
						ps.setString(1, codigo);
						ps.execute();
						JOptionPane.showMessageDialog(null, "¡Registro Eliminado!");
						txtCodigo.setEditable(true);
						construirTabla();
						obtenerUltimoId();
						limpiar();
						BloquearCampos();

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
		String titulos[] = { "N°", "Nombre", "Código", "Descripción", "Cantidad", "Costo", "Valor", "Utilidad", "Fecha",
				"Imagen" };
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

	public static ArrayList<productos> buscarUsuariosConMatriz() {
		conexion conex = new conexion();
		ArrayList<productos> miLista = new ArrayList<productos>();
		productos productos;
		try {
			Statement estatuto = conex.conectar().createStatement();
			ResultSet rs = estatuto.executeQuery("select * from productos");

			while (rs.next()) {
				productos = new productos();
				productos.setId_producto(Integer.parseInt(rs.getString("id_producto")));
				productos.setNombre_producto(rs.getString("nombre_producto"));
				productos.setCodigo_producto(rs.getInt("codigo_producto"));
				productos.setDescripcion_producto(rs.getString("descripcion_producto"));
				productos.setCantidad_producto(rs.getInt("cantidad_producto"));
				productos.setCosto_producto(rs.getDouble("costo_producto"));
				productos.setValor_producto(rs.getDouble("valor_producto"));
				productos.setUtilidad_producto(rs.getDouble("utilidad_producto"));
				productos.setFecha_producto(rs.getString("fecha_producto"));
				productos.setImagen_producto(rs.getString("imagen_producto"));
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
		String matrizInfo[][] = new String[miLista.size()][10];
		for (int i = 0; i < miLista.size(); i++) {
			matrizInfo[i][0] = miLista.get(i).getId_producto() + "";
			matrizInfo[i][1] = miLista.get(i).getNombre_producto() + "";
			matrizInfo[i][2] = miLista.get(i).getCodigo_producto() + "";
			matrizInfo[i][3] = miLista.get(i).getDescripcion_producto() + "";
			matrizInfo[i][4] = miLista.get(i).getCantidad_producto() + "";
			matrizInfo[i][5] = miLista.get(i).getCosto_producto() + "";
			matrizInfo[i][6] = miLista.get(i).getValor_producto() + "";
			matrizInfo[i][7] = miLista.get(i).getUtilidad_producto() + "";
			matrizInfo[i][8] = miLista.get(i).getFecha_producto() + "";
			matrizInfo[i][9] = miLista.get(i).getImagen_producto() + "";

		}
		return matrizInfo;
	}

	public void filtro() {
		filtroCodigo = txtBuscar.getText().toString();
		trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText().toString(), 1, 2));
	}
	
	public void filtroFecha() {
		filtroCodigo1 = txtFecha.getText().toString();
		trsfiltroCodigo1.setRowFilter(RowFilter.regexFilter("(?i)" + txtFecha.getText().toString(), 8));
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
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM productos ORDER BY id_producto DESC");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				ultimoValor = rsr.getString("id_producto");
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
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM productos ORDER BY id_producto DESC");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				totalDatos = rsr.getString("id_producto");
			}
			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void obtenerCodigoDelProducto() {
		conexion objCon = new conexion();
		Connection conn = objCon.conectar();
		try {
			PreparedStatement stmtr = conn.prepareStatement(
					"SELECT * FROM productos where codigo_producto = '" + txtCodigo.getText().toString() + "'");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				codigo = rsr.getString("codigo_producto");
				nombre = rsr.getString("nombre_producto");
				descripcion = rsr.getString("descripcion_producto");
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
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		date = cal.getTime();
		return df.format(date);
	}

	public void totalizarExistencia() {
		int t = 0;
		int p = 0;
		if (tabla.getRowCount() > 0) {
			for (int i = 0; i < tabla.getRowCount(); i++) {
				p = Integer.parseInt(tabla.getValueAt(i, 4).toString());
				t += p;
			}
			txtExistencia.setText(String.valueOf(t));
		} else {
			JOptionPane.showMessageDialog(null, "No hay datos que totalizar");
		}
	}

	private void close() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	public void establecerDatosDeProducto() {
		if (codigo == null) {
			JOptionPane.showMessageDialog(null,
					"¡Producto NO encontrado!\nNota: Parece ser un nuevo producto.\nIngrese los datos del nuevo producto.");
			DesbloquearCampos();
			txtCodigo.setEditable(false);

		} else {
			if (JOptionPane.showConfirmDialog(rootPane,
					"¡Producto encontrado!\nDatos del producto:\nCódigo: '" + codigo + "'\nNombre: '" + nombre
							+ "'\nDescripcion:: '" + descripcion + "'\n¿Deseas abastecer más unidades del producto?",
					"AGREGAR MAS UNIDADES", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				DesbloquearCampos();
				txtCodigo.setText(codigo);
				txtNombre.setText(nombre);
				txtDescripcion.setText(descripcion);
			}else {
				
			}

		}

	}

	public void BloquearCampos() {
		txtNombre.setEnabled(false);
		txtDescripcion.setEnabled(false);
		txtCosto.setEnabled(false);
		txtValor.setEnabled(false);
		txtUtilidad.setEnabled(false);
		txtCantidad.setEnabled(false);
		btnAdjuntarImagen.setEnabled(false);
	}

	public void DesbloquearCampos() {
		txtNombre.setEnabled(true);
		txtDescripcion.setEnabled(true);
		txtCosto.setEnabled(true);
		txtValor.setEnabled(true);
		txtUtilidad.setEnabled(true);
		txtCantidad.setEnabled(true);
		btnAdjuntarImagen.setEnabled(true);
	}

	private void limpiar() {
		txtNombre.setText("");
		txtDescripcion.setText("");
		txtCantidad.setText("");
		txtCosto.setText("");
		txtExistencia.setText("");
		txtUtilidad.setText("");
		txtValor.setText("");
		txtExistencia.setText("");
		txtRutaImagen.setText("");
		txtCodigo.setText("");
	}

	public void selecionarImagenProducto() {
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
			txtRutaImagen.setText(String.valueOf(file));
			Image foto = getToolkit().getImage(txtRutaImagen.getText());
			foto = foto.getScaledInstance(lblImagenProducto.getHeight(), lblImagenProducto.getWidth(),
					Image.SCALE_DEFAULT);
			lblImagenProducto.setIcon(new ImageIcon(foto));
		}
	}

	public void verFotoEmpleado() {
		if (txtRutaImagen.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay imagen que mostrar");
		} else {
			visor_imagen visor = new visor_imagen();
			ruta = txtRutaImagen.getText().toString();
			visor.txtRutaImagen.setText(ruta);
			visor.setVisible(true);
			visor.setLocationRelativeTo(null);
			imagen = new ImageIcon(ruta);
			visor_imagen.lblImagen.setIcon(imagen);
		}
	}
}
