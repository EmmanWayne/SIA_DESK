package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.productos;
import clases.roles;
import conexion.conexion;

public class consultas_productos extends conexion {

	public boolean insertar(productos productos) {
		PreparedStatement ps = null;
		Connection con = conectar();
		
		String sql = "INSERT INTO productos (nombre_producto, codigo_producto, descripcion_producto, cantidad_producto, costo_producto, valor_producto, utilidad_producto, fecha_producto, imagen_producto) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, productos.getNombre_producto());
			ps.setInt(2, productos.getCodigo_producto());
			ps.setString(3, productos.getDescripcion_producto());
			ps.setInt(4, productos.getCantidad_producto());
			ps.setDouble(5, productos.getCosto_producto());
			ps.setDouble(6, productos.getValor_producto());
			ps.setDouble(7, productos.getUtilidad_producto());
			ps.setString(8, productos.getFecha_producto());
			ps.setString(9, productos.getImagen_producto());
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

	public boolean actualizar(productos productos) {
		PreparedStatement ps = null;
		Connection con = conectar();

		String sql = "UPDATE productos SET nombre_producto=?, codigo_producto=?, descripcion_producto=?, cantidad_producto=?, costo_producto=?, valor_producto=?, utilidad_producto=?, fecha_producto=?, imagen_producto=? WHERE id_producto=?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, productos.getNombre_producto());
			ps.setInt(2, productos.getCodigo_producto());
			ps.setString(3, productos.getDescripcion_producto());
			ps.setInt(4, productos.getCantidad_producto());
			ps.setDouble(5, productos.getCosto_producto());
			ps.setDouble(6, productos.getValor_producto());
			ps.setDouble(7, productos.getUtilidad_producto());
			ps.setString(8, productos.getFecha_producto());
			ps.setString(9, productos.getImagen_producto());
			ps.setInt(10, productos.getId_producto());
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
