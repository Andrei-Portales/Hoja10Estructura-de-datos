
public class Alteracion {
	
	private String causa;
	private Relacion relacion;
	
	
	public Alteracion(String causa, Relacion relacion) {
		this.causa = causa;
		this.relacion = relacion;

	}


	public String getCausa() {
		return causa;
	}


	public void setCausa(String causa) {
		this.causa = causa;
	}


	public Relacion getRelacion() {
		return relacion;
	}


	public void setRelacion(Relacion relacion) {
		this.relacion = relacion;
	}
	

	public boolean equals(Alteracion obj) {
		return causa.equals(obj.causa) && relacion.equals(obj.relacion);
	}
	
	
	
	

}
