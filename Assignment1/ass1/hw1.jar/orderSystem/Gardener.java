package orderSystem;

public class Gardener extends Person {

    public Gardener(){
        super("Gardener");
    }

    public FlowerBouquet prepare (String flowers, String growerName){
        System.out.println(this.getName() + " prepares flowers");
        FlowerBouquet F1 = new FlowerBouquet(flowers);
        System.out.println(this.getName() + " returns flowers to " + growerName);
        return F1;
    }
}
