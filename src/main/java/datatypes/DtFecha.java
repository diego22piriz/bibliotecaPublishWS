package datatypes;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Embeddable
@XmlRootElement(name = "dtFecha")
@XmlAccessorType(XmlAccessType.FIELD)
public class DtFecha {
    
    @Column(name = "day")
    @XmlElement(name = "dia")
    private int dia;
    
    @Column(name = "month")
    @XmlElement(name = "mes")
    private int mes;
    
    @Column(name = "year")
    @XmlElement(name = "anio")
    private int anio;

    public DtFecha(int dia, int mes, int anio) {
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}
	
	// Constructor por defecto requerido para JPA
	public DtFecha() {
	}

    public int getDia() {
        return this.dia;
    }
    public int getMes() {
        return this.mes;
    }
    public int getAnio() {
        return this.anio;
    }
    
    public void setDia(int dia) {
        this.dia = dia;
    }
    
    public void setMes(int mes) {
        this.mes = mes;
    }
    
    public void setAnio(int anio) {
        this.anio = anio;
    }
    
    // Métodos de compatibilidad con el código existente (usando @Transient para evitar mapeo a BD)
    @javax.persistence.Transient
    public int getDay() {
        return this.dia;
    }
    
    @javax.persistence.Transient
    public int getMonth() {
        return this.mes;
    }
    
    @javax.persistence.Transient
    public int getYear() {
        return this.anio;
    }
    
    @javax.persistence.Transient
    public void setDay(int day) {
        this.dia = day;
    }
    
    @javax.persistence.Transient
    public void setMonth(int month) {
        this.mes = month;
    }
    
    @javax.persistence.Transient
    public void setYear(int year) {
        this.anio = year;
    }
}

