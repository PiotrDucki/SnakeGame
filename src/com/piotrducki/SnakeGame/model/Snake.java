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

	/**
	 * initializes snake with default size
	 * 
	 * @param bSize
	 *            board size
	 */

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

	/**
	 * resetting snake to default values and size
	 */
	public void restart()
	{
		snakeParts.removeAll(getSnakeParts());
		snakeSize = INITAL_SNAKE_SIZE;
		direction = Direction.UP;
		for (int i = 0; i < INITAL_SNAKE_SIZE; i++)
		{
			Point newSnakePart = new Point(boardSize / 2, boardSize / 2 + i);
			snakeParts.add(newSnakePart);
		}
		snakeSize = snakeParts.size();
	}

	/**
	 * changes the direction of a snake (snake can't make 180 degrees turns)
	 * 
	 * @param newDirection
	 *            new direction
	 */
	public void changeDirection(int newDirection)
	{
		if (direction - newDirection == 2 || direction - newDirection == -2)
			;
		else
			direction = newDirection;
	}

	/**
	 * moves snake by one point in current direction, if snake have eaten apple
	 * method doesn't pop last element of snake body
	 */
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

	/**
	 * 
	 * 
	 * @return returns true if head of snake is in the same place as other element
	 *         of snake body
	 */
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

	/**
	 * 
	 * @return returns true if head of snake is out of bounds of the game board
	 */

	public Boolean checkWallCollison()
	{
		Point head = snakeParts.getFirst();
		if (head.getX() == -1 || head.getX() == boardSize || head.getY() == -1 || head.getY() == boardSize)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @param apple
	 *            current apple position
	 * @return true if head of snake is in the same place as apple
	 */

	public Boolean checkAppleCollision(Point apple)
	{
		Point head = snakeParts.getFirst();
		if (head.getX() == apple.getX() && head.getY() == apple.getY())
			return true;
		return false;
	}

	/**
	 * 
	 * @return score
	 */

	public int getScore()
	{
		return getSize() - INITAL_SNAKE_SIZE;
	}

	/**
	 * 
	 * @param additonalPoints
	 *            point that need to be added to score
	 */

	public void updateSankeSize(int additonalPoints)
	{
		snakeSize += additonalPoints;
	}

	/**
	 * 
	 * @return sanke parts as linked list of points
	 */

	public LinkedList<Point> getSnakeParts()
	{
		return snakeParts;
	}

	/**
	 * 
	 * @return size of sanke
	 */
	public int getSize()
	{
		return snakeSize;
	}

	/**
	 * 
	 * @return true if snake have eaten apple
	 */

	private Boolean snakeHaveEatenApple()
	{
		if (snakeSize == snakeParts.size() + 1)
			return true;
		else
			return false;
	}

	/**
	 * adds new element do snakes body parts in direction which the snake is point
	 * at
	 */

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
