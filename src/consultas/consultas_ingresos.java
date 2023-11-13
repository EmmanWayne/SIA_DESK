package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.ingresos;
import clases.roles;
import conexion.conexion;

public class consultas_ingresos extends conexion {

	public boolean insertar(ingresos ingresos) {
		PreparedStatement ps = null;
		Connection con = conectar();
		
		String sql = "INSERT INTO ingresos (monto, razon, fecha_hora_ingreso, empleado) VALUES (?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setDouble(1, ingresos.getMonto());
			ps.setString(2, ingresos.getRazon());
			ps.setString(3, ingresos.getFecha_hora_ingreso());
			ps.setString(4, ingresos.getEmpleado());
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

	public boolean actualizar(ingresos ingresos) {
		PreparedStatement ps = null;
		Connection con = conectar();

		String sql = "UPDATE ingresos SET monto=?, razon=? WHERE id_ingreso=?";

		try {
			ps = con.prepareStatement(sql);
			ps.setDouble(1, ingresos.getMonto());
			ps.setString(2, ingresos.getRazon());
			ps.setInt(3, ingresos.getId_ingreso());
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
