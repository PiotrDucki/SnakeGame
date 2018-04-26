package com.piotrducki.SnakeGame.view;

import javax.swing.*;

import com.piotrducki.SnakeGame.model.Apple;
import com.piotrducki.SnakeGame.model.Snake;
import java.awt.Point;
import java.awt.*;
import java.util.LinkedList;

public class View
{
	private final int SIZE_OF_POINT = 10;
	private int boardSize;
	private JPanel canvas;
	private Snake snake;
	private Apple apple;

	public View(int bSize, Snake s, Apple a)
	{
		boardSize = bSize;
		snake = s;
		apple = a;

		canvas = new JPanel()
		{
			@Override
			public void paintComponent(Graphics graphics)
			{
				drawBoard(graphics);
				drawSnake(graphics);
				drawApple(graphics);
			}
		};
	}

	public void draw()
	{
		canvas.repaint();
	}

	public int getSizeOfPoint()
	{
		return SIZE_OF_POINT;
	}

	public JPanel getCanvas()
	{
		return canvas;
	}

	public void drawBoard(Graphics g)
	{
		  Color green = new Color(1666073);

		g.setColor(green);
		g.fillRect(0, 0,  SIZE_OF_POINT* boardSize,  SIZE_OF_POINT*boardSize);

	}

	public void drawApple(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect((int)(apple.getApple().getX())* SIZE_OF_POINT, (int)(apple.getApple().getY())* SIZE_OF_POINT,  SIZE_OF_POINT,  SIZE_OF_POINT);
	}

	public void drawSnake(Graphics g)
	{
		g.setColor(Color.BLACK);
		for (Point snakePart : snake.getSnakeParts())
		{
			g.fillRect((int )snakePart.getX()* SIZE_OF_POINT, (int) (snakePart.getY()* SIZE_OF_POINT),  SIZE_OF_POINT,  SIZE_OF_POINT);
		}

	}
}
