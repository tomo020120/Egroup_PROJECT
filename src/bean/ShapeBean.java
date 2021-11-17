package bean;

import java.io.Serializable;

public class ShapeBean implements Serializable{
	private String shapeId;
	private String shapeName;
	public String getShapeId() {
		return shapeId;
	}
	public void setShapeId(String shapeId) {
		this.shapeId = shapeId;
	}
	public String getShapeName() {
		return shapeName;
	}
	public void setShapeName(String shapeName) {
		this.shapeName = shapeName;
	}
	
}
