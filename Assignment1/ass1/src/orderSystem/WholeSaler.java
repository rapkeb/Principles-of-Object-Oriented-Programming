package orderSystem;

public class WholeSaler extends Person {

    private Grower grower;

    public WholeSaler(){
        super("Wholesaler");
        this.grower = new Grower();
    }
    public FlowerBouquet prepare(String flowers, String rs){
        System.out.println(this.getName() + " forwards request to " + grower.getName());
        FlowerBouquet F3 = this.grower.prepare(flowers, this.getName());
        System.out.println(this.getName() + " returns flowers to " + rs);
        return  F3;
    }
}
