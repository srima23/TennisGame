package tennisMatch;


	import java.util.*;

	public class Game {

		static List <Integer> player1Score = new ArrayList<>();
		static List <Integer> player2Score = new ArrayList<>();
		List <Integer> roundWinners = new ArrayList<>();
		
		public static void startGame(Player p1 , Player p2) {
			
			
			
			while(p1.gamesWin != 6 && p2.gamesWin != 6)
			{
				List <Integer> roundScores = new ArrayList<> (Round.playRound(p1 , p2));
				
				player1Score.add(roundScores.get(0));
				player2Score.add(roundScores.get(1));

				if(roundScores.get(0)==4)
				{
					p1.incrementGamesWin();
				}

				else
				{
					p2.incrementGamesWin();

				}
			}


			
		}



	}


