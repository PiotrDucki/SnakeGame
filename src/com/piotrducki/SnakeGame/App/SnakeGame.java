package com.piotrducki.SnakeGame.App;

import com.piotrducki.SnakeGame.controller.Controller;
import com.piotrducki.SnakeGame.model.Apple;
import com.piotrducki.SnakeGame.model.Snake;
import com.piotrducki.SnakeGame.view.GameView;

import javax.swing.*;
import java.awt.*;

public class SnakeGame implements Runnable
{
	
	private static final int SPACE_FOR_TOP_BAR = 20;
	private static final int SPACE_FOR_SCORE_BAR = 20;
	private static final int BOARD_SIZE = 50;
	private GameView gameView;
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
		gameView = new GameView(BOARD_SIZE, SPACE_FOR_SCORE_BAR );
		gameController = new Controller(BOARD_SIZE, snake, apple, gameView);
		
		JFrame jframe = new JFrame("Snake Game");         
		Container contentPane = jframe.getContentPane();
		
        contentPane.add(gameView.getLableScore(), BorderLayout.NORTH );
        contentPane.add(gameView.getCanvas(), BorderLayout.CENTER);


		jframe.setSize(BOARD_SIZE * gameView.getSizeOfPoint() ,BOARD_SIZE * gameView.getSizeOfPoint()  + SPACE_FOR_TOP_BAR + SPACE_FOR_SCORE_BAR);
		jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        
        jframe.addKeyListener(gameController);
        new Thread(gameController).start();
	}

}
