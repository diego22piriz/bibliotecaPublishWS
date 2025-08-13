package datatypes;

public class DtDimension {

    int length;
    int width;
    int depth;

	public DtDimension(int length, int width, int depth) {
		this.length = length;
		this.width = width;
		this.depth = depth;
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
}
