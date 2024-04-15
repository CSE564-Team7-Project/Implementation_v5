package com.cse564.team7;

import java.util.Random;

public class Car {
	Random random = new Random();
	Direction[] directions = Direction.values();

	
	public Direction getCarDirect() {
		int randomIndex = random.nextInt(directions.length);	
		Direction randomDirection = directions[randomIndex];
		//System.out.println("randomDirection: " + randomDirection);
		return randomDirection;
	}
}