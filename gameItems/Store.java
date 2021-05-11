package gameItems;

public class Store {
    private int stones;

    public Store(){
        stones = 0;
    }

    public int getStones() {
        return stones;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }

    public void addStones(int stones){
        this.stones += stones;
    }
}
