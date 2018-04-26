package com.piotrducki.SnakeGame.App;

import com.piotrducki.SnakeGame.controller.Controller;
import com.piotrducki.SnakeGame.model.Apple;
import com.piotrducki.SnakeGame.model.Snake;
import com.piotrducki.SnakeGame.view.View;

import javax.swing.*;
import java.awt.*;

public class SnakeGame implements Runnable
{
	
	private static final int BOARD_SIZE = 50;
	private View gameView;
	private Controller gameController;
	private Snake snake;
	private Apple apple;

	public static void main(String[] args)
	{
		SnakeGame snakeGame = new SnakeGame();
        snakeGame.run();
	}

	@Override
	public void run()
	{
		snake = new Snake(BOARD_SIZE);
		apple = new Apple(BOARD_SIZE);
		gameView = new View(BOARD_SIZE, snake, apple);
		gameController = new Controller(BOARD_SIZE, snake, apple, gameView);
		
		JFrame jframe = new JFrame("Snake Game");         
		Container contentPane = jframe.getContentPane();
        contentPane.add(gameView.getCanvas(), BorderLayout.CENTER);

		jframe.setSize(BOARD_SIZE * gameView.getSizeOfPoint() ,BOARD_SIZE * gameView.getSizeOfPoint() );
		jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        
        jframe.addKeyListener(gameController);
        new Thread(gameController).start();
	}

}
