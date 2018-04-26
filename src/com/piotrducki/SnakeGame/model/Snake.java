package com.piotrducki.SnakeGame.model;

import java.awt.Point;
import java.util.LinkedList;

public class Snake
{
	private final int INITAL_SNAKE_SIZE = 10;
	private int direction;
	private int snakeSize;
	private int boardSize;
	LinkedList<Point> snakeParts;

	public Snake(int bSize)
	{
		snakeParts = new LinkedList<Point>();
		boardSize = bSize;
		snakeSize = INITAL_SNAKE_SIZE;
		direction = Direction.UP;
		for (int i = 0; i < INITAL_SNAKE_SIZE; i++)
		{
			Point newSnakePart = new Point(boardSize / 2, boardSize / 2 + i);
			snakeParts.add(newSnakePart);
		}
		snakeSize = snakeParts.size();
	}

	public void changeDirection(int newDirection)
	{
		if (direction - newDirection == 2 || direction - newDirection == -2) // snake can't make 180 degrees turns
			;
		else
			direction = newDirection;
	}

	public void move()
	{
		if (snakeHaveEatenApple())
		{
			addNewSnakeHead();
		} else
		{
			addNewSnakeHead();
			snakeParts.removeLast();
		}
	}

	public Boolean checkSelfCollision()
	{
		Point head = snakeParts.getFirst();
		for (Point snakePart : snakeParts)
		{
			if (snakePart != head && snakePart.getX() == head.getX() && snakePart.getY() == head.getY())
				return true;
		}
		return false;
	}

	public Boolean checkWallCollison()
	{
		Point head = snakeParts.getFirst();
		if (head.getX() == -1 || head.getX() == boardSize || head.getY() == -1|| head.getY() == boardSize )
			return true;
		else
			return false;
	}

	public Boolean checkAppleCollision(Point apple)
	{
		Point head = snakeParts.getFirst();
		if (head.getX() == apple.getX() && head.getY() == apple.getY())
			return true;
		return false;
	}

	public void updateSankeSize(int additonalPoints)
	{
		snakeSize += additonalPoints;
	}

	public LinkedList<Point> getSnakeParts()
	{
		return snakeParts;
	}

	public int getSize()
	{
		return snakeSize;
	}

	private Boolean snakeHaveEatenApple()
	{
		if (snakeSize == snakeParts.size() + 1)
			return true;
		else
			return false;
	}

	private void addNewSnakeHead()
	{
		int headX = (int) snakeParts.getFirst().getX();
		int headY = (int) snakeParts.getFirst().getY();

		if (direction == Direction.UP)
		{
			Point newSnakePart = new Point(headX, headY - 1);
			snakeParts.addFirst(newSnakePart);
		} else if (direction == Direction.DOWN)
		{
			Point newSnakePart = new Point(headX, headY + 1);
			snakeParts.addFirst(newSnakePart);
		} else if (direction == Direction.LEFT)
		{
			Point newSnakePart = new Point(headX - 1, headY);
			snakeParts.addFirst(newSnakePart);
		} else if (direction == Direction.RIGHT)
		{
			Point newSnakePart = new Point(headX + 1, headY);
			snakeParts.addFirst(newSnakePart);
		}
	}

	@Override
	public String toString()
	{
		int i = 0;
		String result = "Snake [direction=" + direction + ", snakeSize=" + snakeSize + ", boardSize=" + boardSize
				+ "\n";
		for (Point snakePart : snakeParts)
		{
			result += "snakeParts" + i + " =" + snakePart + "\n";
			i++;
		}
		return result;

	}

}
