package com.piotrducki.SnakeGame.controller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


import com.piotrducki.SnakeGame.model.Apple;
import com.piotrducki.SnakeGame.model.Direction;
import com.piotrducki.SnakeGame.model.Highscores;
import com.piotrducki.SnakeGame.model.Snake;
import com.piotrducki.SnakeGame.view.GameView;

public class Controller implements Runnable
{
	private static final int FRAMES_PER_SECOND = 20;
	private static final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
	private static final int POINTS_PER_APPLE = 1;
	private static boolean gameIsRunning;
	private int direction;

	private int boardSize;
	private Snake snake;
	private Apple apple;
	private GameView view;
	private Highscores highscores;


	public Controller(int bSize, Snake s, Apple a, GameView v, Highscores h)
	{
		boardSize = bSize;
		snake = s;
		apple = a;
		view = v;
		highscores = h;
		
		addKeyBindings();
	}

	@Override
	public void run()
	{
		long nextGameTick = System.currentTimeMillis();
		long sleepTime = 0;
		
		snake.restart();
		apple.restart();
		
		direction = Direction.UP;
		gameIsRunning = true;
				
		while (gameIsRunning)
		{
			updateGame();
			displayGame();
			nextGameTick += SKIP_TICKS;
			sleepTime = nextGameTick - System.currentTimeMillis();
			if (sleepTime >= 0)
				try
				{
					Thread.sleep(sleepTime);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		int score = snake.getScore();
		if(highscores.checkIfNewHighscore(score))
		{
			String  user = JOptionPane.showInputDialog(view.getCanvas(), "You have new highscore! Enter you nick:", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
			if(user == null)
				user = "UNKNOWN";
			highscores.addNewHighscore(score, user);
		}
		
	}

	private void displayGame()
	{
		view.draw(snake.getSnakeParts(), apple.getApple(),snake.getScore());
	}

	private void updateGame()
	{
		snake.changeDirection(direction);
		snake.move();
		if (snake.checkSelfCollision() || snake.checkWallCollison())
			gameIsRunning = false;
		if (snake.checkAppleCollision(apple.getApple()))
		{
			snake.updateSankeSize(POINTS_PER_APPLE);
			apple.generateNewAppleCoordinates(snake.getSnakeParts());
		}

	}
	
	private void addKeyBindings()
	{
		InputMap im = view.getLableScore().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0,  false), "go up");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0,false), "go down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0,false), "go left");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0,false), "go right");
		
		ActionMap am = view.getLableScore().getActionMap();
		
		am.put("go up", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				direction = Direction.UP;
			}
		});
		
		am.put("go down", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				direction = Direction.DOWN;
			}
		});
 
		am.put("go left", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				direction = Direction.LEFT;
			}
		});

		am.put("go right", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				direction = Direction.RIGHT;
			}
		});
	}
	

}
