package com.cse564.team7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

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
	
	public List<Integer> decreaseCarNum(Queue<Direction> queue1, Queue<Direction> queue2, Color currentColor,int currentNum1, int currentNum2) {
		Queue<Direction> Lane1 = new LinkedList<>(queue1);
		Queue<Direction> Lane2 = new LinkedList<>(queue2);
		boolean Lane1dec = false;
		boolean Lane2dec = false;
		List<Integer> resultList = new ArrayList<Integer>();;
		int lane1Numdec = 0;
		int lane2Numdec = 0;
		if(currentColor == Color.GREEN) {
			if(Lane1.peek() != Direction.Left && Lane2.peek() != Direction.Left) {
				//1 and 2 can go
				Lane1dec = true;
				Lane2dec = true;
			}else if(Lane1.peek() != Direction.Left && Lane2.peek() == Direction.Left) {
				//1 and 2 can go
				Lane1dec = true;
				Lane2dec = true;
			}else if(Lane1.peek() == Direction.Left && Lane2.peek() != Direction.Left) {
				//will crash if both go, if 2 is empty 1 can go
				if(Lane2.isEmpty()) {
					Lane1dec = true;
				}
			}else if(Lane1.peek() == Direction.Left && Lane2.peek() == Direction.Left) {
				//1 and 2 can go
				Lane1dec = true;
				Lane2dec = true;
			}
		
			Random random1 = new Random();
			Random random2 = new Random();
			lane1Numdec = random1.nextInt(currentNum1+1);
			lane2Numdec = random2.nextInt(currentNum2+1);

		}

		resultList.add(lane1Numdec);
		resultList.add(lane2Numdec);
		return resultList;
		
	}

}