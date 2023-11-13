package clases;

public class ingresos {

	int id_ingreso;
	double monto;
	String razon;
	String fecha_hora_ingreso;
	String empleado;

	public int getId_ingreso() {
		return id_ingreso;
	}

	public void setId_ingreso(int id_ingreso) {
		this.id_ingreso = id_ingreso;
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

	public String getFecha_hora_ingreso() {
		return fecha_hora_ingreso;
	}

	public void setFecha_hora_ingreso(String fecha_hora_ingreso) {
		this.fecha_hora_ingreso = fecha_hora_ingreso;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void set_Empleado(String empleado) {
		this.empleado = empleado;
	}

}
