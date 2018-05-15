package com.piotrducki.SnakeGame.view;

import javax.swing.*;

import com.piotrducki.SnakeGame.model.Apple;
import com.piotrducki.SnakeGame.model.Direction;
import com.piotrducki.SnakeGame.model.Snake;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.LinkedList;

public class GameView
{
	private JLabel lableScore;
	private JPanel canvas;
	
	
	private final int SIZE_OF_POINT = 10;
	private int spaceForScoreBar;
	private int boardSize;
	private LinkedList<Point> snakeParts;
	private Point apple;

	public GameView(int boardSize, int spaceForScoreBar)
	{
		this.boardSize = boardSize;
		this.spaceForScoreBar = spaceForScoreBar;
		
		lableScore = new JLabel();

		canvas = new JPanel()
		{
			@Override
			public void paintComponent(Graphics graphics)
			{
				setBounds(0, spaceForScoreBar, SIZE_OF_POINT * boardSize, SIZE_OF_POINT * boardSize);
				drawBoard(graphics);
				drawSnake(graphics);
				drawApple(graphics);
			}
		};
	
	
	}

	public void draw(LinkedList<Point> snakeParts, Point apple, int score)
	{
		this.snakeParts = snakeParts;
		this.apple = apple;
		lableScore.setText("                                                      score:" + score);
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
	
	public JLabel getLableScore()
	{
		return lableScore;
	}

	private void drawBoard(Graphics g)
	{
		Color green = new Color(1666073);

		g.setColor(green);
		g.fillRect(0, 0, SIZE_OF_POINT * boardSize, SIZE_OF_POINT * boardSize);

	}

	private void drawApple(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect((int) (apple.getX()) * SIZE_OF_POINT, (int) (apple.getY()) * SIZE_OF_POINT, SIZE_OF_POINT,
				SIZE_OF_POINT);
	}

	private void drawSnake(Graphics g)
	{
		g.setColor(Color.BLACK);
		for (Point snakePart : snakeParts)
		{
			g.fillRect((int) snakePart.getX() * SIZE_OF_POINT, (int) (snakePart.getY() * SIZE_OF_POINT), SIZE_OF_POINT,
					SIZE_OF_POINT);
		}

	}
}
