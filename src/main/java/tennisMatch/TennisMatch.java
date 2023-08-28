package tennisMatch;


public class TennisMatch {
    private int player1SetsWon;
    private int player2SetsWon;
    private int currentSetPlayer1Games;
    private int currentSetPlayer2Games;
    private int currentGamePlayer1Points;
    private int currentGamePlayer2Points;
    private boolean player1Serving;

    public TennisMatch() {
        player1SetsWon = 0;
        player2SetsWon = 0;
        currentSetPlayer1Games = 0;
        currentSetPlayer2Games = 0;
        currentGamePlayer1Points = 0;
        currentGamePlayer2Points = 0;
        player1Serving = true;
    }

    public void update(boolean player1WinsPoint) {
        if (player1WinsPoint) {
            currentGamePlayer1Points++;
            System.out.println("p1" +"-" + currentGamePlayer1Points);
        } else {
            currentGamePlayer2Points++;
            System.out.println("p2" + "-" + currentGamePlayer2Points);
        }

        if (isDeuce()) {
            if (player1WinsPoint) {
                currentGamePlayer1Points--;
            } else {
                currentGamePlayer2Points--;
            }
        }

        if (hasGameWinner()) {
            if (player1WinsPoint) {
                currentSetPlayer1Games++;
            } else {
                currentSetPlayer2Games++;
            }
            resetCurrentGame();
        }

        if (hasSetWinner()) {
            if (currentSetPlayer1Games > currentSetPlayer2Games) {
                player1SetsWon++;
            } else {
                player2SetsWon++;
            }
            resetCurrentSet();
        }

        player1Serving = !player1Serving;
    }

    private boolean isDeuce() {
        return currentGamePlayer1Points >= 3 && currentGamePlayer2Points >= 3 &&
               currentGamePlayer1Points == currentGamePlayer2Points;
    }

    private boolean hasGameWinner() {
        if (currentGamePlayer1Points >= 4 && currentGamePlayer1Points >= currentGamePlayer2Points + 2) {
            return true;
        } else if (currentGamePlayer2Points >= 4 && currentGamePlayer2Points >= currentGamePlayer1Points + 2) {
            return true;
        }
        return false;
    }

    private boolean hasSetWinner() {
        if (currentSetPlayer1Games >= 6 && currentSetPlayer1Games >= currentSetPlayer2Games + 2) {
            return true;
        } else if (currentSetPlayer2Games >= 6 && currentSetPlayer2Games >= currentSetPlayer1Games + 2) {
            return true;
        }
        return false;
    }

    private void resetCurrentGame() {
        currentGamePlayer1Points = 0;
        currentGamePlayer2Points = 0;
    }

    private void resetCurrentSet() {
        currentSetPlayer1Games = 0;
        currentSetPlayer2Games = 0;
        resetCurrentGame();
    }
    
    public int getPlayer1SetsWon() {
        return player1SetsWon;
    }

    public int getPlayer2SetsWon() {
        return player2SetsWon;
    }

    public int getCurrentSetPlayer1Games() {
        return currentSetPlayer1Games;
    }

    public int getCurrentSetPlayer2Games() {
        return currentSetPlayer2Games;
    }

    public int getCurrentGamePlayer1Points() {
        return currentGamePlayer1Points;
    }

    public int getCurrentGamePlayer2Points() {
        return currentGamePlayer2Points;
    }

    public boolean isPlayer1Serving() {
        return player1Serving;
    }

    public String getCurrentGameScore() {
        if (isDeuce()) {
            return "Deuce";
        } else if (currentGamePlayer1Points == 4 && currentGamePlayer2Points == 3) {
            return "Advantage Player 1";
        } else if (currentGamePlayer1Points == 3 && currentGamePlayer2Points == 4) {
            return "Advantage Player 2";
        } else {
            return formatScore(currentGamePlayer1Points) + "-" + formatScore(currentGamePlayer2Points);
        }
    }

    private String formatScore(int points) {
        switch (points) {
            case 0:
                return "0";
            case 1:
                return "15";
            case 2:
                return "30";
            case 3:
                return "40";
            default:
                return "Game";
        }
    }

    // Additional methods for getting scores, set counts, etc.
    
    public static void main(String[] args) {
		
    	TennisMatch match = new TennisMatch();

        // Simulate a tennis match by updating points
        match.update(true);  // Player 1 wins the point
        match.update(true);
        match.update(false); // Player 2 wins the point
        match.update(false);
        match.update(true);  // Player 1 wins the point
        match.update(true);
        match.update(true);  // Player 1 wins the point
        match.update(true);
        match.update(false); // Player 2 wins the point
        match.update(false);
        match.update(true); 
        match.update(false);
        match.update(false);
        match.update(true); 

        // Print current scores
        System.out.println("Player 1 Sets Won: " + match.getPlayer1SetsWon());
        System.out.println("Player 2 Sets Won: " + match.getPlayer2SetsWon());
        System.out.println("Player 1 Games in Current Set: " + match.getCurrentSetPlayer1Games());
        System.out.println("Player 2 Games in Current Set: " + match.getCurrentSetPlayer2Games());
        System.out.println("Player 1 Points in Current Game: " + match.getCurrentGamePlayer1Points());
        System.out.println("Player 2 Points in Current Game: " + match.getCurrentGamePlayer2Points());
        System.out.println("Is Player 1 Serving: " + match.isPlayer1Serving());
        System.out.println("Current Game Score: " + match.getCurrentGameScore());
    	
	}
}