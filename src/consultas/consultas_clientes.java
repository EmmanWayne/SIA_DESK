package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.clientes;
import clases.usuarios;
import conexion.conexion;

public class consultas_clientes extends conexion {

	public boolean insertar(clientes clientes) {
		PreparedStatement ps = null;
		Connection con = conectar();
		String sql = "INSERT INTO clientes (nombres, apellidos, identidad, sexo, telefono, direccion, rtn) VALUES(?,?,?,?,?,?,?)";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, clientes.getNombres());
			ps.setString(2, clientes.getApellidos());
			ps.setString(3, clientes.getIdentidad());
			ps.setString(4, clientes.getSexo());
			ps.setString(5, clientes.getTelefono());
			ps.setString(6, clientes.getDireccion());
			ps.setString(7, clientes.getRtn());
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

	public boolean actualizar(clientes clientes) {
		PreparedStatement ps = null;
		Connection con = conectar();

		String sql = "UPDATE clientes SET nombres=?, apellidos=?, identidad=?, sexo=?, telefono=?, direccion=?, rtn=? WHERE id_cliente=?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, clientes.getNombres());
			ps.setString(2, clientes.getApellidos());
			ps.setString(3, clientes.getIdentidad());
			ps.setString(4, clientes.getSexo());
			ps.setString(5, clientes.getTelefono());
			ps.setString(6, clientes.getDireccion());
			ps.setString(7, clientes.getRtn());
			ps.setInt(8, clientes.getId_cliente());
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
