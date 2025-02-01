package battleship;

public class Player {
    private String username;
    private int score;
    private Board playerBoard;

    Player(String username){
        this.username = username;
        score = 0;
        this.playerBoard = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlayerBoard(Board board){
        this.playerBoard = board;
    }

    public Board getPlayerBoard(){
        return this.playerBoard;
    }

    public void updateScore(){
        score++;
        System.out.println(username+"- New score is: "+score);
    }
}
