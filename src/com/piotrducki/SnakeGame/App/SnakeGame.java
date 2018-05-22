package com.piotrducki.SnakeGame.App;

import com.piotrducki.SnakeGame.controller.Controller;

import com.piotrducki.SnakeGame.model.Apple;
import com.piotrducki.SnakeGame.model.Highscores;
import com.piotrducki.SnakeGame.model.Snake;
import com.piotrducki.SnakeGame.view.GameView;
import com.piotrducki.SnakeGame.view.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

/**
 * class responsible for creating all objects and opening game menu
 * 
 * @author piotrducki
 *
 */

public class SnakeGame implements Runnable
{

	private static final int NOTHING_IS_CLICKED = 0;
	private static final int START_IS_CLICKED = 1;
	private static final int HIGHSCORE_IS_CLICKED = 2;
	private static final int EXIT_IS_CLICKED = 3;

	private static final int SPACE_FOR_TOP_BAR = 20;
	private static final int SPACE_FOR_SCORE_BAR = 20;
	private static final int BOARD_SIZE = 50;

	private MenuView menuView;

	private GameView gameView;
	private Controller gameController;
	private Snake snake;
	private Apple apple;
	private Highscores highscores;

	private enum State
	{
		MENU, GAME
	}

	private static State state;

	public static void main(String[] args)
	{
		state = State.MENU;
		SnakeGame snakeGame = new SnakeGame();
		snakeGame.run();
	}

	/**
	 * Inits all objects needed for program to run, handles user input in menu is
	 * responsible for creating new game threads
	 */

	@Override
	public void run()
	{

		boolean programIsRuning = true;

		initObjects();

		Thread gameThread = new Thread(gameController);

		JFrame jframe = new JFrame("Snake Game");
		Container contentPane = jframe.getContentPane();
		jframe.setSize(BOARD_SIZE * gameView.getSizeOfPoint(),
				BOARD_SIZE * gameView.getSizeOfPoint() + SPACE_FOR_TOP_BAR + SPACE_FOR_SCORE_BAR);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane.add(menuView.getCanvas());
		contentPane.add(gameView.getLableScore(), BorderLayout.NORTH);
		contentPane.add(gameView.getCanvas(), BorderLayout.CENTER);

		setVisibleMenu();

		jframe.setVisible(true);

		while (programIsRuning)
		{
			if (state == State.MENU)
			{
				switch (menuView.getMenuInput())
				{
				case NOTHING_IS_CLICKED:
					break;
				case START_IS_CLICKED:
					state = State.GAME;
					setVisibleGame();
					gameThread = new Thread(gameController);
					gameThread.start();
					break;
				case HIGHSCORE_IS_CLICKED:
					JOptionPane.showMessageDialog(menuView.getCanvas(), highscores, "Highscores",
							JOptionPane.PLAIN_MESSAGE);
					break;
				case EXIT_IS_CLICKED:
					programIsRuning = false;
					break;
				default:
					break;
				}
			} else if (state == State.GAME && !gameThread.isAlive()) // the game is over
			{
				try
				{
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}

				state = State.MENU;
				setVisibleMenu();
			}

			try
			{
				TimeUnit.MILLISECONDS.sleep(20);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

		}

		jframe.dispatchEvent(new WindowEvent(jframe, WindowEvent.WINDOW_CLOSING));
	}

	/**
	 * Inits all objects needed for program to run
	 */

	private void initObjects()
	{
		snake = new Snake(BOARD_SIZE);
		apple = new Apple(BOARD_SIZE);
		highscores = new Highscores();
		gameView = new GameView(BOARD_SIZE, SPACE_FOR_SCORE_BAR);
		gameController = new Controller(BOARD_SIZE, snake, apple, gameView, highscores);
		menuView = new MenuView();

	}

	/**
	 * set visibility on all game components to false and all menu components to
	 * true
	 */

	private void setVisibleMenu()
	{
		gameView.getCanvas().setVisible(false);
		gameView.getLableScore().setVisible(false);
		menuView.getCanvas().setVisible(true);
	}

	/**
	 * set visibility on all menu components to false and all menu components to
	 * false
	 */

	private void setVisibleGame()
	{
		gameView.getCanvas().setVisible(true);
		gameView.getLableScore().setVisible(true);
		menuView.getCanvas().setVisible(false);
	}

}
