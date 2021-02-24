package orderSystem;

public class Robbin extends Person {

    public Robbin(){
        super("Robbin");
    }

    public void receive (String flower){
        System.out.println(this.getName() + " accepts the flowers: " + flower);
    }
}
