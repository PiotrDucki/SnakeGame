package com.piotrducki.SnakeGame.model;

import java.util.Random;
import java.awt.Point;
import java.util.LinkedList;

public class Apple
{
	private int boardSize;
	private Point appleCoordinates;

	public Apple(int bSize)
	{
		boardSize = bSize;
		appleCoordinates = new Point(boardSize/2, boardSize/4);
		
	}

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
	

	@Override
	public String toString()
	{
		return "Apple [boardSize=" + boardSize + ", appleCoordinates=" + appleCoordinates + "]";
	}

	private Boolean theAppleIsWhereTheSnakeIs(LinkedList<Point> snake)
	{
		for (Point snakePart : snake)
		{
			if (snakePart.getX() == appleCoordinates.getX() & snakePart.getY() == appleCoordinates.getY())
				return true;
		}
		return false;
	}

	private void generateNewCoordinatesForApple()
	{
		Random generator = new Random();
		appleCoordinates.x =(generator.nextInt(boardSize) );
		appleCoordinates.y =(generator.nextInt(boardSize) );
	}
	

}
