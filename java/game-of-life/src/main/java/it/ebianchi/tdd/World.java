package it.ebianchi.tdd;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class World {
	
	private final Set<Point> alives;
	
	public World(Point... points) {
		alives = new HashSet<Point>(Arrays.asList(points));
	}
	
	private World(Set<Point> points) {
		alives = points;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) return true;
		if (obj == null || this.getClass() != obj.getClass()) return false;
		
		World world = (World) obj;
		
		 if (alives != null ? !alives.equals(world.alives) : world.alives != null) 
			 return false;

		 return true;
		
	}
	
	@Override
	public int hashCode() {
		return (alives != null) ? alives.hashCode() : 0;
	}
	
	
	public static Point at(int x, int y) {
		return new Point(x, y);
	}
	
	public int neighbours(Point point) {
		int neighboursCount = 0;
		for (int deltaY = -1; deltaY < 2; deltaY++) {
			for (int deltaX = -1; deltaX < 2; deltaX++) {
				if (!(deltaX == 0 && deltaY == 0)) {
					System.out.println("position: " + (point.x+deltaX) + " - " + (point.y+deltaY));
					if (alives.contains(at(point.x+deltaX, point.y+deltaY)))
						neighboursCount++;
				}
			}
		}
		return neighboursCount;
	}
	
	public World next() {
		  Set<Point> aliveInNext = new HashSet<Point>();
		  for (Point cell : alives) {
		    for (int yDelta = -1; yDelta <= 1; yDelta++) {
		      for (int xDelta = -1; xDelta <= 1; xDelta++) {
		        Point testingCell = at(cell.x + xDelta, cell.y + yDelta);
		        if ((alives.contains(testingCell) && neighbours(testingCell) == 2) || neighbours(testingCell) == 3) {
		          aliveInNext.add(testingCell);
		        }
		      }
		    }
		  }
		  return new World(aliveInNext);
		}


}
