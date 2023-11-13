package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import clases.roles;
import conexion.conexion;

public class consultas_roles extends conexion {

	public boolean insertar(roles roles) {
		PreparedStatement ps = null;
		Connection con = conectar();
		String sql = "INSERT INTO roles (codigo_rol, nombre_rol) VALUES (?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, roles.getCodigo_rol());
			ps.setString(2, roles.getNombre_rol());
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

	public boolean actualizar(roles roles) {
		PreparedStatement ps = null;
		Connection con = conectar();

		String sql = "UPDATE roles SET codigo_rol=?, nombre_rol=? WHERE id_rol=?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, roles.getCodigo_rol());
			ps.setString(2, roles.getNombre_rol());
			ps.setInt(3, roles.getId_rol());
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

}
