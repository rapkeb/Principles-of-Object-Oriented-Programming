package orderSystem;

public class Fred extends Person{

    private RobbinFlorist lf;

    public Fred(){
        super("Fred");
        this.lf = new RobbinFlorist();
    }

    public void prepare (String flower){
        System.out.println(this.getName() + " forwards order to " + lf.getName());
        this.lf.prepare(flower);
    }

}
