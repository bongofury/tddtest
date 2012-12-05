package it.ebianchi.tdd;

import static org.junit.Assert.*;

import org.junit.Test;
import it.ebianchi.tdd.World;

public class WorldTest {

	@Test
	public void positionShouldBeEqualForValue() {
		assertEquals(World.at(0, 1), World.at(0, 1));
	}
	
	@Test
	public void worldShouldBeEqualsByCells() {
		assertEquals(new World(World.at(0, 1)), new World(World.at(0, 1)));
	}
	
	@Test 
	public void hashCodeShouldBeEqualByAlives() {
		assertEquals(new World(World.at(0, 1)).hashCode(), new World(World.at(0, 1)).hashCode());
	}
	
	@Test
	public void neighboursCountShouldBeZeroWithoutNeighbours() {
		assertEquals(0, new World(World.at(0, 1)).neighbours(World.at(0, 1)));
	}
	
	@Test
	public void neighboursCountShouldCountVertical() {
		assertEquals(1, new World(World.at(0, 1), World.at(0, 0)).neighbours(World.at(0, 0)));
	}
	
	@Test
	public void neighbourCountShouldNotCountItself() {
	  assertEquals(0, new World(World.at(0, 0)).neighbours(World.at(0, 0)));
	}
	
	@Test
	public void neighbourCountShouldCountHorizontalOnes() {
		assertEquals(1, new World(World.at(0, 0), World.at(1, 0)).neighbours(World.at(0, 0)));
	}

	@Test
	public void neighbourCountShouldCountDiagonalOnes() {
	  assertEquals(2, new World(World.at(-1, 1), World.at(1, 0), World.at(0, 1)).neighbours(World.at(0, 1)));
	}
	
	@Test
	public void cellsWithOneNeighbourShouldDie() {
		assertEquals(new World(), new World(World.at(0, 0), World.at(0, 1)).next());
	}

	@Test
	public void livingCellWithTwoNeighboursShouldStayAlive() {
	  assertEquals(new World(World.at(0, 0)), new World(World.at(-1, -1), World.at(0, 0), World.at(1, 1)).next());
	}

	@Test
	public void blinker() {
		assertEquals(new World(World.at(-1, 0),World.at(0, 0),World.at(1, 0)), 
				new World(World.at(0, -1), World.at(0, 0), World.at(0, 1)).next());
	}
	
	@Test
	public void livingCellWithMoreThanThreeNeighboursShouldDie() {
		assertEquals(new World(World.at(-1, 1),World.at(1, 1),World.at(1, 0),
				World.at(-1, 0), World.at(0, -1)),
				new World(World.at(-1, 0), World.at(0, 0), World.at(1, 0), 
						World.at(0, 1), World.at(1, 1)).next());
	}
	
	@Test
	public void finalTest() {
		assertEquals(new World(World.at(-1, -1),World.at(0, -1),World.at(1, -1),
				World.at(-1, 1), World.at(0, 1),World.at(1, 1),World.at(-1, 0),World.at(1, 0)),
				new World(World.at(-1, 0), World.at(0, 0), World.at(1, 0), 
						World.at(0, 1), World.at(0, -1)).next());
	}

}
