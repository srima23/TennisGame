package tennisMatch;

	import java.util.ArrayList;
	import java.util.List;
	import java.util.Random;

	public class Round {
		
		public static boolean deuce = false;
		
		static int points[] = {0 , 15 , 30 , 40 , 50};
		static int p1 = 0;
		static int p2 = 0;
		
		static List<Integer> currentRoundScore;
	    static Random random = new Random();

		static Player player1 , player2;
		
		public static List<Integer> playRound(Player temp1 , Player temp2)
		{
			currentRoundScore=new ArrayList<>();		
			player1 = temp1;
			player2 = temp2;

			while(p1 !=4 && p2 !=4 && points[p1] - points[p2] != 20 && points[p2] - points[p1] != 20)
			{
			
				score();
				if(p1 == 3 && p2 == 3)
				{
					reset();
				}

				
			}
			currentRoundScore.add(p1);
			currentRoundScore.add(p2);
			p1 = 0;
			p2 = 0;
			//System.out.println(currentRoundScore);
			deuce = false;
			


			return currentRoundScore;
		}

		private static void reset() {
			
					p1 = 2;
					p2 = 2;
					player1.advantage = false;
					player2.advantage = false;
					deuce = true;
			
			
		}


		private static void score ()
		{
			
			int result = random.nextInt(2);
			
			if(result == 0)
			{
				p1++;
				if(deuce == true)
				{
					player1.advantage=true;
				}
			}
			else
			{
				p2++;
				if(deuce == true)
				{
					player2.advantage=true;
				}
			}
		
		}
	}


