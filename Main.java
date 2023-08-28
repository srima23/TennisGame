package tennisMatch;

	public class Main {
		public static void main(String Args[])
		{
			Player p1 = new Player("Fedrer");
			Player p2 = new Player("roger");
			
			int i = 1;
			while(i <= 5)
	        {
			Set.playSets(p1 , p2);
			System.out.println("Games won p1  " + p1.gamesWin);
			System.out.println("Games won p2  " + p2.gamesWin);
			p1.gamesWin = 0;
			p2.gamesWin = 0;
			i++;
			}

			
			System.out.println("\nsets won by " + p1.getname() +" "+ p1.setsWin);
			System.out.println("\nsets won by " + p2.getname() +" "+ p2.setsWin);
			System.out.println("game won by :");
			System.out.println(p1.setsWin > p2.setsWin ? p1.getname() : p2.getname());
		}
	}


