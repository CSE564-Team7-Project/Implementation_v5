package com.cse564.team7;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class Main{
	public static void main(String[] args) {
		//declare components
		TrafficController tc = new TrafficController(); //main trafficController
		DirectionControl eastbountDC = new DirectionControl(); //directionControl for lane1&2
		DirectionControl southbountDC = new DirectionControl(); //directionControl for lane3&4	
		TrafficLight eastboundLight = new TrafficLight(); //TrafficLight for lane1&2
		TrafficLight southboundLight = new TrafficLight(); //TrafficLight for lane3&4
		CarCounter Lane_1carsNum = new CarCounter(); //first lane of southbound
		CarCounter Lane_2carsNum = new CarCounter(); //second lane of southbound
		CarCounter Lane_3carsNum = new CarCounter(); //first lane of eastbound
		CarCounter Lane_4carsNum = new CarCounter(); //second lane of eastbound
		Lane Lane_1 = new Lane();
		Lane Lane_2 = new Lane();
		Lane Lane_3 = new Lane();
		Lane Lane_4 = new Lane();
		Queue<Direction> Lane_1Queue = new LinkedList<>();
		Queue<Direction> Lane_2Queue = new LinkedList<>();
		Queue<Direction> Lane_3Queue = new LinkedList<>();
		Queue<Direction> Lane_4Queue = new LinkedList<>();
		Queue<Queue<Direction>> tempQueue = new LinkedList<>();
		
		//create list of each lane's car number: cars.get(0) = lane1, cars.get(1) = lane2....
		List<Integer> cars = new ArrayList<Integer>();
		//create list of two lights: lights.get(0) = lane1&2, lights.get(1) = lane3&4
		List<TrafficLight> lights = new ArrayList<TrafficLight>();
		lights.add(eastboundLight); // initialize =  red
		lights.add(southboundLight); // initialize =  red
		
		//initialize each lane's car number
		int lane1Num = Lane_1carsNum.getCount();
		int lane2Num = Lane_2carsNum.getCount();
		int lane3Num = Lane_3carsNum.getCount();
		int lane4Num = Lane_4carsNum.getCount();
		
		//100 rounds
		for(int i = 0; i < 30; i++) {
			System.out.println("======= ROUND " + (i+1)+ " START =======" );
			//each round add random number of cars to each lane
			if(i > 0) {
				lane1Num = cars.get(0) + Lane_1carsNum.getCount();
				lane2Num = cars.get(1) + Lane_2carsNum.getCount();
				lane3Num = cars.get(2) + Lane_3carsNum.getCount();
				lane4Num = cars.get(3) + Lane_4carsNum.getCount();
				
			}
			
			System.out.println("--------eastbound--------");
			System.out.println("Lane1 car Number : " +  lane1Num);
			System.out.println("Lane2 car Number : " +  lane2Num);
			System.out.println("-------------------------");
			System.out.println("--------southbound--------");
			System.out.println("Lane3 car Number : " +  lane3Num);
			System.out.println("Lane4 car Number : " +  lane4Num);
			System.out.println("--------------------------");	
			
			cars.clear();
			cars.add(lane1Num);
			cars.add(lane2Num);
			cars.add(lane3Num);
			cars.add(lane4Num);
				
			
			Lane_1Queue = Lane_1.getCarsWaiting(lane1Num);
			Lane_2Queue = Lane_2.getCarsWaiting(lane2Num);
			Lane_3Queue = Lane_3.getCarsWaiting(lane3Num);
			Lane_4Queue = Lane_4.getCarsWaiting(lane4Num);
			

			Color current_color_east = lights.get(0).getColor();
			Color current_color_south = lights.get(1).getColor();
			
			tc.decideLights(cars, lights);
			
			tempQueue = eastbountDC.controlDirection(Lane_1Queue, Lane_2Queue, current_color_east); 
			
			//calculate how many cars move
			List<Integer> decrese_east = eastbountDC.decreaseCarNum(Lane_1Queue, Lane_2Queue, current_color_east, cars.get(0), cars.get(1));
			cars.set(0, cars.get(0) - decrese_east.get(0));
			cars.set(1, cars.get(1) - decrese_east.get(1));
			
			Lane_1Queue = tempQueue.peek();//directions of Lane_1
			tempQueue.poll();
			Lane_2Queue = tempQueue.peek();//directions of Lane_2
			
			tempQueue = southbountDC.controlDirection(Lane_4Queue, Lane_3Queue, current_color_south);
			
			//calculate how many cars move
			List<Integer> decrese_south = southbountDC.decreaseCarNum(Lane_4Queue, Lane_3Queue, current_color_south, cars.get(3), cars.get(2));
			cars.set(2, cars.get(2) - decrese_south.get(1));
			cars.set(3, cars.get(3) - decrese_south.get(0));
			
			Lane_4Queue = tempQueue.peek();//directions of Lane_4
			tempQueue.poll();
			Lane_3Queue = tempQueue.peek();//directions of Lane_3

			System.out.println("--------cars move--------");
			System.out.println("Lane1 move : " +  decrese_east.get(0) + " cars");
			System.out.println("Lane2 move : " +  decrese_east.get(1) + " cars");
			System.out.println("Lane3 move : " +  decrese_east.get(1) + " cars");
			System.out.println("Lane4 move : " +  decrese_east.get(0) + " cars");
			System.out.println("--------------------------");
			
			
			Color wanted_color_east = lights.get(0).getColor();
			Color wanted_color_south = lights.get(1).getColor();

			lights.get(0).changeLight(current_color_east, wanted_color_east);
			lights.get(1).changeLight(current_color_south, wanted_color_south);
			
			System.out.println("eastbound Lights Color: " + lights.get(0).getColor());
			System.out.println("southbound lights Color: " + lights.get(1).getColor());	
			
			System.out.println("======= ROUND END =======" );
			// sleep for 1 second
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}