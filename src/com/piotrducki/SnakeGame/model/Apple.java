package com.piotrducki.SnakeGame.model;

import java.util.Random;
import java.awt.Point;
import java.util.LinkedList;

/**
 * Class represent an apple that can be eaten by snake
 * 
 * @author piotrducki
 *
 */
public class Apple
{
	private int boardSize;
	private Point appleCoordinates;

	/**
	 * Set default apple coordinates x = boardSize / 2, y = boardSize / 4
	 * 
	 * @param bSize
	 *            size of board
	 */

	public Apple(int bSize)
	{
		boardSize = bSize;
		appleCoordinates = new Point(boardSize / 2, boardSize / 4);

	}

	/**
	 * generates new coordinates for apple
	 * 
	 * @param snake
	 *            snake object(points where new apple can't be placed)
	 */

	public void generateNewAppleCoordinates(LinkedList<Point> snake)
	{
		do
		{
			generateNewCoordinatesForApple();

		} while (theAppleIsWhereTheSnakeIs(snake));

	}

	public Point getApple()
	{
		return appleCoordinates;
	}

	/**
	 * set coordinates to default value (boardSize / 2, boardSize / 4)
	 */
	public void restart()
	{
		appleCoordinates = new Point(boardSize / 2, boardSize / 4);
	}

	@Override
	public String toString()
	{
		return "Apple [boardSize=" + boardSize + ", appleCoordinates=" + appleCoordinates + "]";
	}

	/**
	 * check if apple is where snake is
	 * 
	 * @param snake
	 *            snake object
	 * @return true if the apple is where the snake is
	 */

	private Boolean theAppleIsWhereTheSnakeIs(LinkedList<Point> snake)
	{
		for (Point snakePart : snake)
		{
			if (snakePart.getX() == appleCoordinates.getX() & snakePart.getY() == appleCoordinates.getY())
				return true;
		}
		return false;
	}

	/**
	 * generate new random apple coordinates in range within <0, boardSize - 1>
	 */
	
	private void generateNewCoordinatesForApple()
	{
		Random generator = new Random();
		appleCoordinates.x = (generator.nextInt(boardSize));
		appleCoordinates.y = (generator.nextInt(boardSize));
	}

}
