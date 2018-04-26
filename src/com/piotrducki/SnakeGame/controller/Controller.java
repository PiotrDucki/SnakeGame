package com.piotrducki.SnakeGame.controller;

import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ViewportLayout;

import com.piotrducki.SnakeGame.model.Apple;
import com.piotrducki.SnakeGame.model.Direction;
import com.piotrducki.SnakeGame.model.Snake;
import com.piotrducki.SnakeGame.view.View;

public class Controller implements Runnable, KeyListener
{
	private static final int FRAMES_PER_SECOND = 18;
	private static final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
	private static final int POINTS_PER_APPLE = 1;
	private static boolean gameIsRunning = true;
	
	private int boardSize;
	private Snake snake;
	private Apple apple;
	private View view;
	
	
	public Controller(int bSize, Snake s, Apple a, View v)
	{
		boardSize = bSize;
		snake = s;
		apple =a;
		view = v;
		
	}
	
	@Override
	public void run()
	{

		long nextGameTick = System.currentTimeMillis();
		long sleepTime = 0;
		

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
		System.out.println("The End");
		
	}
	private void displayGame()
	{
		view.draw();
		System.out.println(snake.getSize());
	}

	private void updateGame()
	{
		snake.move();
		if (snake.checkSelfCollision() || snake.checkWallCollison())
			gameIsRunning = false;
		if (snake.checkAppleCollision(apple.getApple()))
		{
			snake.updateSankeSize(POINTS_PER_APPLE);
			apple.generateNewAppleCoordinates(snake.getSnakeParts());
		}
	}


	@Override
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		switch (keyCode) {
        case KeyEvent.VK_UP :
            snake.changeDirection(Direction.UP);
            break;
        case KeyEvent.VK_RIGHT :
            snake.changeDirection(Direction.RIGHT);
            break;
        case KeyEvent.VK_DOWN :
            snake.changeDirection(Direction.DOWN);
            break;
        case KeyEvent.VK_LEFT :
            snake.changeDirection(Direction.LEFT);
            break;
        case KeyEvent.VK_Q :
            gameIsRunning = false;
            break;
        case KeyEvent.VK_SPACE :
            break;
    }
	}
@Override
	public void keyTyped(KeyEvent e)
	{
	}
	@Override
	public void keyReleased(KeyEvent e)
	{
	}
	
}
