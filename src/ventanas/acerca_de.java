package ventanas;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class acerca_de extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					acerca_de frame = new acerca_de();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public acerca_de() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 387, 365);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAcercaDe = new JLabel("Acerca de.");
		lblAcercaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcercaDe.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblAcercaDe.setBounds(0, 11, 371, 14);
		contentPane.add(lblAcercaDe);

		JLabel lblSistemaAdministrativoBy = new JLabel("SIA by Lic. Emmanuel D\u00EDaz");
		lblSistemaAdministrativoBy.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaAdministrativoBy.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblSistemaAdministrativoBy.setBounds(0, 24, 371, 26);
		contentPane.add(lblSistemaAdministrativoBy);

		JTextArea txtrTodosLosDerechos = new JTextArea();
		txtrTodosLosDerechos.setEditable(false);
		txtrTodosLosDerechos.setText(
				"             Todos los derechos reservados a:\r\n                      Lic. Emmanuel D\u00EDaz\r\n                  Programador y Dise\u00F1ador:\r\n           Lic. en Inform\u00E1tica Administrativa.\r\n          Cristian Emmanuel D\u00EDaz Rodr\u00EDguez\r\n       Contacto: krizemandiaz11@gmail.com\r\n                Tel\u00E9fono: +504 8883-9089 \r\n        El Para\u00EDso, El Para\u00EDso - Honduras 2021.\r\n");
		txtrTodosLosDerechos.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		txtrTodosLosDerechos.setBounds(42, 158, 319, 157);
		contentPane.add(txtrTodosLosDerechos);

		JLabel label = new JLabel("");
		label.setBounds(127, 49, 119, 111);
		contentPane.add(label);
		final ImageIcon logo2 = new ImageIcon(getClass().getResource("/recursos/logo_sistema.png"));
		final ImageIcon icono2 = new ImageIcon(
				logo2.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		label.setIcon(icono2);
	}
}
