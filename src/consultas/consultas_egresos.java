package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.egresos;
import clases.ingresos;
import clases.roles;
import conexion.conexion;

public class consultas_egresos extends conexion {

	public boolean insertar(egresos egresos) {
		PreparedStatement ps = null;
		Connection con = conectar();
		
		String sql = "INSERT INTO egresos (monto, razon, fecha_hora_egreso, empleado) VALUES (?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setDouble(1, egresos.getMonto());
			ps.setString(2, egresos.getRazon());
			ps.setString(3, egresos.getFecha_hora_egreso());
			ps.setString(4, egresos.getEmpleado());
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

	public boolean actualizar(egresos egresos) {
		PreparedStatement ps = null;
		Connection con = conectar();

		String sql = "UPDATE egresos SET monto=?, razon=? WHERE id_egreso=?";

		try {
			ps = con.prepareStatement(sql);
			ps.setDouble(1, egresos.getMonto());
			ps.setString(2, egresos.getRazon());
			ps.setInt(3, egresos.getId_egreso());
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
