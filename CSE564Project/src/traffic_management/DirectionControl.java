package traffic_management;

import java.util.LinkedList;
import java.util.Queue;

public class DirectionControl {
	public Queue<Queue<Direction>> controlDirection(Queue<Direction> queue1, Queue<Direction> queue2, Color currentColor) {
		Queue<Direction> Lane1 = new LinkedList<>(queue1);
		Queue<Direction> Lane2 = new LinkedList<>(queue2);
		Queue<Queue<Direction>> result =  new LinkedList<>();
		
		if(currentColor == Color.GREEN) {
			if(Lane1.peek() != Direction.Left && Lane2.peek() != Direction.Left) {
				//1 and 2 can go
				Lane1.poll();
				Lane2.poll();
			}else if(Lane1.peek() != Direction.Left && Lane2.peek() == Direction.Left) {
				//1 and 2 can go
				Lane1.poll();
				Lane2.poll();
			}else if(Lane1.peek() == Direction.Left && Lane2.peek() != Direction.Left) {
				//will crash if both go, if 2 is empty 1 can go
				if(Lane2.isEmpty()) {
					Lane1.poll();
				}
			}else if(Lane1.peek() == Direction.Left && Lane2.peek() == Direction.Left) {
				//1 and 2 can go
				Lane1.poll();
				Lane2.poll();
			}
		}
		result.offer(Lane1);
		result.offer(Lane2);
		return result;
	}
}