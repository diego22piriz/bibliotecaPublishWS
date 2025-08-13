package datatypes;

import javax.persistence.Embeddable;

@Embeddable
public class DtDimension {

    private int length;
    private int width;
    private int depth;

	public DtDimension(int length, int width, int depth) {
		this.length = length;
		this.width = width;
		this.depth = depth;
	}
	
	// Constructor por defecto requerido para JPA
	public DtDimension() {
	}

    public int getLength() {
        return this.length;
    }
    public int getWidth() {
        return this.width;
    }
    public int getDepth() {
        return this.depth;
    }
    
    public void setLength(int length) {
        this.length = length;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setDepth(int depth) {
        this.depth = depth;
    }
}
