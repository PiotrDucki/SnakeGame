import com.piotrducki.SnakeGame.model.*;

public class Test1
{

	public static void main(String[] args)
	{
		int boardSize = 100;
		Snake snake = new Snake(boardSize);
		System.out.println(snake);
		snake.changeDirection(Direction.LEFT);
		snake.move();
		snake.changeDirection(Direction.DOWN);
		snake.move();
		snake.changeDirection(Direction.RIGHT);
		snake.move();
		System.out.println(snake);
		System.out.println(snake.checkSelfCollision());
		//test
	}

}
