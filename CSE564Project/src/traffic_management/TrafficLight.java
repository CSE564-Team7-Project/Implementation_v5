package traffic_management;

public class TrafficLight {
	private Color myColor;

	public TrafficLight() {
		myColor = Color.RED;
	}
	
	public void setColor(Color in) {
		myColor =  in;
	}
	
	public void changeLight(Color current, Color wanted) {
		if(current == wanted) {
			myColor = wanted;
		}else {
			if(current == Color.GREEN && wanted == Color.RED) {
				//green->yellow->red
				myColor = Color.YELLOW;
			}else if(current == Color.YELLOW) {
				//yellow->red
				myColor = Color.RED;
			}else if(current == Color.RED && wanted == Color.GREEN) {
				//red -> green
				myColor = wanted;
			}
			
		}
		
	}
	
	
	public Color getColor() {
		return myColor;
	}
}