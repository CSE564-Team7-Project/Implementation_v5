package traffic_management;

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
		CarCounter Lane_1carsNum = new CarCounter(); //first lane of north-south
		CarCounter Lane_2carsNum = new CarCounter(); //second lane of north-south
		CarCounter Lane_3carsNum = new CarCounter(); //first lane of east-west
		CarCounter Lane_4carsNum = new CarCounter(); //second lane of east-west
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
		lights.add(eastboundLight);
		lights.add(southboundLight);

		//100 rounds
		for(int i = 0; i < 100; i++) {
			System.out.println("======= ROUND " + (i+1)+ " START =======" );
			cars.clear();
			
			int lane1Num = Lane_1carsNum.getCount();
			int lane2Num = Lane_2carsNum.getCount();
			int lane3Num = Lane_3carsNum.getCount();
			int lane4Num = Lane_4carsNum.getCount();
			System.out.println("--------eastbound--------");
			System.out.println("Lane1 car Number : " +  lane1Num);
			System.out.println("Lane2 car Number : " +  lane2Num);
			System.out.println("-------------------------");
			System.out.println("--------southbound--------");
			System.out.println("Lane3 car Number : " +  lane3Num);
			System.out.println("Lane4 car Number : " +  lane4Num);
			System.out.println("--------------------------");
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
			Lane_1Queue = tempQueue.peek();
			tempQueue.poll();
			Lane_2Queue = tempQueue.peek();
			
			tempQueue = southbountDC.controlDirection(Lane_4Queue, Lane_3Queue, current_color_south);
			Lane_4Queue = tempQueue.peek();
			tempQueue.poll();
			Lane_3Queue = tempQueue.peek();

			
			System.out.println("eastbound Lights Color: " + lights.get(0).getColor());
			System.out.println("southbound lights Color: " + lights.get(1).getColor());
			System.out.println("======= ROUND END =======" );

		}
		
	}

}