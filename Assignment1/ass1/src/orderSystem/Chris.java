package orderSystem;

public class Chris extends Person {
    private String flowers;
    private Fred florist;

    public Chris(String flowers, String name){
        super(name);
        this.flowers = flowers;
        this.florist = new Fred();
    }
    public void prepare(){
        System.out.println(this.getName() + " orders flowers from " + this.florist.getName() + " : " + flowers);
        this.florist.prepare(flowers);
    }
}
