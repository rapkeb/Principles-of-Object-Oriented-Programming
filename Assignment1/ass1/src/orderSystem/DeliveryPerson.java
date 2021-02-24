package orderSystem;

public class DeliveryPerson extends Person {

    private Robbin robbin;

    public DeliveryPerson(){
        super("Delivery Person");
        this.robbin = new Robbin();
    }

    public void shift (String flower){
        System.out.println(this.getName() + " delivers flowers to " + robbin.getName());
        this.robbin.receive(flower);
    }
}
