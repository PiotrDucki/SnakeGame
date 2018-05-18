package com.piotrducki.SnakeGame.view;

import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MenuView
{
	private static final int NOTHING_IS_CLICKED = 0;
	private static final int START_IS_CLICKED = 1;
	private static final int HIGHSCORE_IS_CLICKED = 2;
	private static final int EXIT_IS_CLICKED = 3;

	private int menuInput = NOTHING_IS_CLICKED;

	private JPanel canvas;
	private JButton buttonStart;
	private JButton buttonHighscore;
	private JButton buttonExit;

	private BufferedImage snakeImage;

	/**
	 * Create the panel.
	 */
	public MenuView()
	{
		initComponents();
	}

	private void initComponents()
	{
		
		Color green = new Color(1668377);

		canvas = new JPanel();
		canvas.setBounds(0, 0, 500, 520);
		canvas.setBackground(Color.BLACK);
		canvas.setLayout(null);
		
		
		java.net.URL imageURL = GameView.class.getResource("images/Snake.png");
		if (imageURL != null)
		{
			ImageIcon icon = new ImageIcon(imageURL);
			JLabel picLabel = new JLabel(icon);
			picLabel.setBounds(46, 60, 407, 81);
			canvas.add(picLabel);
			
		}
		

		buttonStart = new JButton("START");
		buttonStart.setBounds(150, 240, 200, 50);
		buttonStart.setBackground(Color.BLACK);
		buttonStart.setBorder(BorderFactory.createLineBorder(green, 5));
		buttonStart.setForeground(Color.WHITE);
		buttonStart.setFont(new Font("Arial", Font.BOLD, 26));
		buttonStart.setFocusPainted(false);
		canvas.add(buttonStart);

		buttonHighscore = new JButton("HIGHSCORE");
		buttonHighscore.setBounds(150, 330, 200, 50);
		buttonHighscore.setBackground(Color.BLACK);
		buttonHighscore.setBorder(BorderFactory.createLineBorder(green, 5));
		buttonHighscore.setForeground(Color.WHITE);
		buttonHighscore.setFont(new Font("Arial", Font.BOLD, 26));
		buttonHighscore.setFocusPainted(false);
		canvas.add(buttonHighscore);

		buttonExit = new JButton("EXIT");
		buttonExit.setBounds(150, 420, 200, 50);
		buttonExit.setBackground(Color.BLACK);
		buttonExit.setBorder(BorderFactory.createLineBorder(green, 5));
		buttonExit.setForeground(Color.WHITE);
		buttonExit.setFont(new Font("Arial", Font.BOLD, 26));
		buttonExit.setFocusPainted(false);
		canvas.add(buttonExit);

		buttonStart.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				menuInput = START_IS_CLICKED;
			}
		});

		buttonHighscore.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				menuInput = HIGHSCORE_IS_CLICKED;
			}
		});

		buttonExit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				menuInput = EXIT_IS_CLICKED;
			}
		});

	}

	public int getMenuInput()
	{
		int tempMenuImput = menuInput;
		menuInput = NOTHING_IS_CLICKED;
		return tempMenuImput;
	}

	public JPanel getCanvas()
	{
		return canvas;
	}

}
