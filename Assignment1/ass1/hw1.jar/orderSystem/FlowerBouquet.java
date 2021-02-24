package orderSystem;

public class FlowerBouquet {

    private String flowers;
    private boolean isArranged;

    public FlowerBouquet (String flowers){
        this.flowers = flowers;
        this.isArranged = false;
    }
    public void setFlowers (){
        this.isArranged = true;
    }

    public boolean getReady(){
        return this.isArranged;
    }
}
