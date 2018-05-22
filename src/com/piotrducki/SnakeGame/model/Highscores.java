package com.piotrducki.SnakeGame.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * class responsible for dealing with highscores highcores are saved as
 * linkedLists one with the name of the player and second with the score
 * 
 * @author piotrducki
 *
 */
public class Highscores
{

	private static final int NUBER_OF_HIGHSCORES = 3;

	// first score is the lowest

	private LinkedList<Integer> highscoresValues = new LinkedList<Integer>();
	private LinkedList<String> highscoresUsers = new LinkedList<String>();

	/**
	 * read highscores from file, if file can't be found create new file and
	 * initialize all highscores with "." 0
	 */
	public Highscores()
	{
		if (!loadHighcoresfromFile())
			initHighscores();

	}

	/**
	 * 
	 * @param newScore
	 *            new score
	 * @return true if score is higher then the lowest highscore
	 */

	public boolean checkIfNewHighscore(int newScore)
	{
		if (newScore > highscoresValues.getFirst())
			return true;
		else
			return false;
	}

	/**
	 * checks where to put new score in highscore list and delete the previous
	 * lowest highscore
	 * 
	 * @param newScore
	 *            new score
	 * @param userName
	 *            user name
	 */

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

	/**
	 * save data to file "highscore.dat"
	 */

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

	/**
	 * load data form "highscore.dat"
	 * 
	 * @return true if data loaded properly
	 */

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

	/**
	 * add highscore to highscore list
	 * 
	 * @param highscoreString
	 *            string containing "user" highscoreValue
	 */

	private void addHighscore(String highscoreString)
	{
		String[] arr = highscoreString.split(" ");

		highscoresUsers.addLast(arr[0]);
		highscoresValues.addLast(Integer.parseInt(arr[1]));
	}

	/**
	 * return string that are use to save data to file
	 * @param index index at highscore list
	 * @return string to be saved to file
	 */
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
		return highscoresValues.get(2).toString() + "  " + highscoresUsers.get(2).toString() + "\n"
				+ highscoresValues.get(1).toString() + "  " + highscoresUsers.get(1).toString() + "\n"
				+ highscoresValues.get(0).toString() + "  " + highscoresUsers.get(0).toString();
	}
}
