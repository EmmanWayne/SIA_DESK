package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.clientes;
import clases.proveedores;
import clases.usuarios;
import conexion.conexion;

public class consultas_proveedores extends conexion {

	public boolean insertar(proveedores prov) {
		PreparedStatement ps = null;
		Connection con = conectar();
		String sql = "INSERT INTO proveedores (tipo_proveedor, nombre_proveedor, direccion_proveedor, telefono_proveedor, ps_proveedor, detalle_proveedor) VALUES(?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, prov.getTipo_proveedor());
			ps.setString(2, prov.getNombre_proveedor());
			ps.setString(3, prov.getDireccion_proveedor());
			ps.setString(4, prov.getTelefono_proveedor());
			ps.setString(5, prov.getPs_proveedor());
			ps.setString(6, prov.getDetalle_proveedor());
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

	public boolean actualizar(proveedores prov) {
		PreparedStatement ps = null;
		Connection con = conectar();

		String sql = "UPDATE proveedores SET tipo_proveedor=?, nombre_proveedor=?, direccion_proveedor=?, telefono_proveedor=?, ps_proveedor=?, detalle_proveedor=? WHERE id_proveedor=?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, prov.getTipo_proveedor());
			ps.setString(2, prov.getNombre_proveedor());
			ps.setString(3, prov.getDireccion_proveedor());
			ps.setString(4, prov.getTelefono_proveedor());
			ps.setString(5, prov.getPs_proveedor());
			ps.setString(6, prov.getDetalle_proveedor());
			ps.setInt(7, prov.getId_proveedor());
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
