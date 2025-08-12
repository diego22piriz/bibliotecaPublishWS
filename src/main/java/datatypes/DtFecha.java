package datatypes;

public class DtFecha {
    
    int day;
    int month;
    int year;

    public DtFecha(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
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
}

