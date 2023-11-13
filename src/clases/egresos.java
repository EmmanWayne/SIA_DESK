package clases;

public class egresos {

	int id_egreso;
	double monto;
	String razon;
	String fecha_hora_egreso;
	String empleado;

	public int getId_egreso() {
		return id_egreso;
	}

	public void setId_egreso(int id_egreso) {
		this.id_egreso = id_egreso;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	public String getFecha_hora_egreso() {
		return fecha_hora_egreso;
	}

	public void setFecha_hora_egreso(String fecha_hora_egreso) {
		this.fecha_hora_egreso = fecha_hora_egreso;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

}
