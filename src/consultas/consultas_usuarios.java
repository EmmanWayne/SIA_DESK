package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.usuarios;
import conexion.conexion;

public class consultas_usuarios extends conexion {
	public static String rol = null;
	public static String usuario = null;
	public static String contrasena = null;

	public boolean insertar(usuarios usuarios) {
		PreparedStatement ps = null;
		Connection con = conectar();
		String sql = "INSERT INTO usuarios (usuario, contrasena, codigo_rol) VALUES(?,?,?)";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuarios.getUsuario());
			ps.setString(2, usuarios.getContrasena());
			ps.setString(3, usuarios.getCodigo_rol());
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	public boolean actualizar(usuarios usuarios) {
		PreparedStatement ps = null;
		Connection con = conectar();

		String sql = "UPDATE usuarios SET usuario=?, contrasena=?, codigo_rol=? WHERE id_usuario=?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuarios.getUsuario());
			ps.setString(2, usuarios.getContrasena());
			ps.setString(3, usuarios.getCodigo_rol());
			ps.setInt(4, usuarios.getId_usuario());
			ps.execute();

			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}

	}
	
	public boolean buscarUsuario(usuarios usuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = conectar();

		String sql = "SELECT * FROM usuarios WHERE usuario=? and contrasena =?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getContrasena());
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

}
