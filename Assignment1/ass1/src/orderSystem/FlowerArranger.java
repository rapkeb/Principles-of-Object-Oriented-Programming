package orderSystem;

public class FlowerArranger extends Person {

    public FlowerArranger(){
        super("Flower Arranger");
    }

    public FlowerBouquet prepare (FlowerBouquet flower, String fl){
        System.out.println(this.getName() + " arranges flowers");
        flower.setFlowers();
        System.out.println(this.getName() + " returns arranged flowers to " + fl);
        return flower;
    }
}
