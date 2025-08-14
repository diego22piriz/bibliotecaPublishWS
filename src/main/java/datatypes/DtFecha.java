package datatypes;

import javax.persistence.Embeddable;
import javax.persistence.Column;

@Embeddable
public class DtFecha {
    
    @Column(name = "day")
    private int day;
    
    @Column(name = "month")
    private int month;
    
    @Column(name = "year")
    private int year;

    public DtFecha(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	// Constructor por defecto requerido para JPA
	public DtFecha() {
	}

    public int getDay() {
        return this.day;
    }
    public int getMonth() {
        return this.month;
    }
    public int getYear() {
        return this.year;
    }
    
    public void setDay(int day) {
        this.day = day;
    }
    
    public void setMonth(int month) {
        this.month = month;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
}

