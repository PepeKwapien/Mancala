package gameItems;

public class Pocket {
    private int stones;

    public Pocket(int stones){
        this.stones = stones;
    }

    public int flush(){
        int howManyFlushed = stones;
        stones = 0;
        return howManyFlushed;
    }

    public int getStones() {
        return stones;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }

    public void addStone(){
        this.stones++;
    }
}
