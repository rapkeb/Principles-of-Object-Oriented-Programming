package orderSystem;

public class Grower extends Person {

    private Gardener gardener;

    public Grower (){
        super("Grower");
        this.gardener = new Gardener();
    }
    public FlowerBouquet prepare(String flowers, String ws){
        System.out.println(this.getName() + " forwards request to " + gardener.getName());
        FlowerBouquet F2 = this.gardener.prepare(flowers, this.getName());
        System.out.println(this.getName() + " returns flowers to " + ws);
        return F2;
    }
}
