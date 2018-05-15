package com.piotrducki.SnakeGame.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

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
	

	/**
	 * Create the panel.
	 */
	public MenuView()
	{
		initComponents();
	}
	
	private void initComponents()
	{
		canvas = new JPanel();
		canvas.setBounds(0, 0, 500, 510);
		canvas.setLayout(null);
		
		buttonStart = new JButton("START");
		buttonStart.setBounds(179, 259, 117, 29);
		canvas.add(buttonStart);
		
		buttonHighscore = new JButton("HIGHSCORE");
		buttonHighscore.setBounds(179, 349, 117, 29);
		canvas.add(buttonHighscore);
		
		buttonExit = new JButton("EXIT");
		buttonExit.setBounds(179, 433, 117, 29);
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
