package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.inventario;
import clases.productos;
import clases.roles;
import conexion.conexion;

public class consultas_inventario extends conexion {

	public boolean insertar(inventario inventario) {
		PreparedStatement ps = null;
		Connection con = conectar();
		
		String sql = "INSERT INTO inventario_empresa (inventario, cantidad, precio, tipo, existencia, observacion) VALUES (?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, inventario.getInventario());
			ps.setString(2, inventario.getCantidad());
			ps.setString(3, inventario.getPrecio());
			ps.setString(4, inventario.getTipo());
			ps.setString(5, inventario.getExistencia());
			ps.setString(6, inventario.getObservacion());
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

	public boolean actualizar(inventario inventario) {
		PreparedStatement ps = null;
		Connection con = conectar();

		String sql = "UPDATE inventario_empresa SET inventario=?, cantidad=?, precio=?, tipo=?, existencia=?, observacion=? WHERE id_inventario=?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, inventario.getInventario());
			ps.setString(2, inventario.getCantidad());
			ps.setString(3, inventario.getPrecio());
			ps.setString(4, inventario.getTipo());
			ps.setString(5, inventario.getExistencia());
			ps.setString(6, inventario.getObservacion());
			ps.setInt(7, inventario.getId_inventario());
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
