package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.configuraciones;
import clases.roles;
import conexion.conexion;

public class consultas_configuraciones extends conexion {

	public boolean insertar(configuraciones config) {
		PreparedStatement ps = null;
		Connection con = conectar();
		String sql = "INSERT INTO configuraciones (nombre_empresa, telefono_empresa, direccion_empresa, rtn_empresa, cai_empresa, logo_empresa, ri_facturas, rf_facturas, fecha_limite_facturas) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, config.getNombre_empresa());
			ps.setString(2, config.getTelefono_empresa());
			ps.setString(3, config.getDireccion_empresa());
			ps.setString(4, config.getRtn_empresa());
			ps.setString(5, config.getCai_empresa());
			ps.setString(6, config.getLogo_empresa());
			ps.setString(7, config.getRi_facturas());
			ps.setString(8, config.getRf_facturas());
			ps.setString(9, config.getFecha_limite_facturas());
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

	public boolean actualizar(configuraciones config) {
		PreparedStatement ps = null;
		Connection con = conectar();

		String sql = "UPDATE configuraciones SET nombre_empresa=?, telefono_empresa=?, direccion_empresa=?, rtn_empresa=?, cai_empresa=?, logo_empresa=?, ri_facturas=?, rf_facturas=?, fecha_limite_facturas=? WHERE id_empresa=?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, config.getNombre_empresa());
			ps.setString(2, config.getTelefono_empresa());
			ps.setString(3, config.getDireccion_empresa());
			ps.setString(4, config.getRtn_empresa());
			ps.setString(5, config.getCai_empresa());
			ps.setString(6, config.getLogo_empresa());
			ps.setString(7, config.getRi_facturas());
			ps.setString(8, config.getRf_facturas());
			ps.setString(9, config.getFecha_limite_facturas());
			ps.setInt(10, config.getId_empresa());
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
