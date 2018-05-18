package com.piotrducki.SnakeGame.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Highscores
{

	private static final int NUBER_OF_HIGHSCORES = 3;

	// first score is the lowest

	private LinkedList<Integer> highscoresValues = new LinkedList<Integer>();
	private LinkedList<String> highscoresUsers = new LinkedList<String>();

	public Highscores()
	{
		if (!loadHighcoresfromFile())
			initHighscores();

	}

	public boolean checkIfNewHighscore(int newScore)
	{
		if (newScore > highscoresValues.getFirst())
			return true;
		else
			return false;
	}

	public void addNewHighscore(int newScore, String userName)
	{

		highscoresValues.removeFirst();
		highscoresUsers.removeFirst();

		if (newScore > highscoresValues.getLast())
		{
			highscoresValues.addLast(newScore);
			highscoresUsers.addLast(userName);
		} else if (newScore > highscoresValues.getFirst())
		{
			highscoresValues.add(1, newScore);
			highscoresUsers.add(1, userName);
		} else
		{
			highscoresValues.addFirst(newScore);
			highscoresUsers.addFirst(userName);
		}

		saveHighcoresToFile();
	}

	// data in file is saved like this
	// amadeusz 12
	// arek 22
	// antek 29

	private void saveHighcoresToFile()
	{
		File scoreFile = new File("highscore.dat");
		if (!scoreFile.exists())
		{
			try
			{
				scoreFile.createNewFile();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		FileWriter fileWriter = null;
		BufferedWriter bufferdWriter = null;
		try
		{
			fileWriter = new FileWriter(scoreFile);
			bufferdWriter = new BufferedWriter(fileWriter);

			for (int i = 0; i < NUBER_OF_HIGHSCORES; i++)
				bufferdWriter.write(generateHighscoreString(i));

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (bufferdWriter != null)
					bufferdWriter.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		}
	}

	private boolean loadHighcoresfromFile()
	{
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try
		{
			fileReader = new FileReader("highscore.dat");
			bufferedReader = new BufferedReader(fileReader);
			for (int i = 0; i < NUBER_OF_HIGHSCORES; i++)
			{
				String highscoreReadFromFile = bufferedReader.readLine();
				addHighscore(highscoreReadFromFile);
			}
		} catch (Exception e)
		{
			return false;
		} finally
		{
			try
			{
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		}

		return true;
	}

	private void addHighscore(String highscoreString)
	{
		String[] arr = highscoreString.split(" ");

		highscoresUsers.addLast(arr[0]);
		highscoresValues.addLast(Integer.parseInt(arr[1]));
	}

	private String generateHighscoreString(int index)
	{
		String stringToWrite = highscoresUsers.get(index) + " " + highscoresValues.get(index).toString() + "\n";
		return stringToWrite;
	}

	private void initHighscores()
	{
		for (int i = 0; i < NUBER_OF_HIGHSCORES; i++)
		{
			highscoresValues.add(0);
			highscoresUsers.add(".");
		}

	}

	@Override
	public String toString()
	{
		return highscoresValues.get(2).toString() +"  "+  highscoresUsers.get(2).toString() + "\n"
				+ highscoresValues.get(1).toString() + "  "+ highscoresUsers.get(1).toString() + "\n"
				+ highscoresValues.get(0).toString() + "  "+ highscoresUsers.get(0).toString();
	}
}
