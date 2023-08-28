package tennisMatch;

public class Player {

		private String name;
		public boolean Toss = false;
		public boolean advantage = false;
		public int point;
		public int roundsWin = 0;
		public int setsWin = 0;
		public int gamesWin = 0;

		public Player(String name)
		{
			this.name = name;
		}

		public String getname()
		{
			return this.name;
		}

		public void incrementRoundsWin()
		{
			roundsWin++;
		}

		public void incrementSetsWin()
		{
			setsWin++;
		}

		public void incrementGamesWin()
		{
			gamesWin++;
		}
	}


