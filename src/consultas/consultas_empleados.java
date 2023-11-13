package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.clientes;
import clases.empleados;
import clases.usuarios;
import conexion.conexion;

public class consultas_empleados extends conexion {

	public boolean insertar(empleados empleados) {
		PreparedStatement ps = null;
		Connection con = conectar();
		String sql = "INSERT INTO empleados (nombres, apellidos, identidad, sexo, edad, cargo, salario, telefono, direccion, estado) VALUES(?,?,?,?,?,?,?,?,?,?)";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, empleados.getNombres());
			ps.setString(2, empleados.getApellidos());
			ps.setString(3, empleados.getIdentidad());
			ps.setString(4, empleados.getSexo());
			ps.setString(5, empleados.getEdad());
			ps.setString(6, empleados.getCargo());
			ps.setString(7, empleados.getSalario());
			ps.setString(8, empleados.getTelefono());
			ps.setString(9, empleados.getDireccion());
			ps.setString(10, empleados.getEstado());
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

	public boolean actualizar(empleados empleados) {
		PreparedStatement ps = null;
		Connection con = conectar();

		String sql = "UPDATE empleados SET nombres=?, apellidos=?, identidad=?, sexo=?, edad=?, cargo=?, salario=?, telefono=?, direccion=?, estado=? WHERE id_empleado=?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, empleados.getNombres());
			ps.setString(2, empleados.getApellidos());
			ps.setString(3, empleados.getIdentidad());
			ps.setString(4, empleados.getSexo());
			ps.setString(5, empleados.getEdad());
			ps.setString(6, empleados.getCargo());
			ps.setString(7, empleados.getSalario());
			ps.setString(8, empleados.getTelefono());
			ps.setString(9, empleados.getDireccion());
			ps.setString(10, empleados.getEstado());
			ps.setInt(11, empleados.getId_empleado());
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
