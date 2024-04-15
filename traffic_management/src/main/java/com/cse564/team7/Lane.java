package com.cse564.team7;

import java.util.LinkedList;
import java.util.Queue;

public class Lane {
	Queue<Direction> cars_waiting = new LinkedList<>();
	Car Car = new Car();
	
	public void add_car() {
		cars_waiting.offer(Car.getCarDirect());
	}
	
	public void remove_car() {
		cars_waiting.poll();
	}
	
	public Queue<Direction> getCarsWaiting(int i){
		for(int j = 0; j < i; j++) {
			cars_waiting.offer(Car.getCarDirect());
		}
		
		return cars_waiting;
	}
	
}