package orderSystem;

public class RobbinFlorist extends Person {

    private FlowerArranger FA;
    private DeliveryPerson DP;
    private WholeSaler WS;

    public RobbinFlorist(){
        super("RobbinFlorist");
        this.DP = new DeliveryPerson();
        this.FA = new FlowerArranger();
        this.WS = new WholeSaler();
    }
    public void prepare (String flower){
        System.out.println(this.getName() + " forwards request to " + WS.getName());
        FlowerBouquet F7  = sendToWS(flower);
        System.out.println(this.getName() + " request flowers arrangement from " +FA.getName());
        FlowerBouquet F8 = sendToFA(F7);
        if (F7.getReady()) {
            System.out.println(this.getName() + " forwards flowers to " +DP.getName());
            sendToDP(flower);
        }
    }
    public FlowerBouquet sendToWS (String flower){
        FlowerBouquet F4 = this.WS.prepare(flower, this. getName());
        return F4;
    }

    public FlowerBouquet sendToFA (FlowerBouquet flower){
        FlowerBouquet F5 = this.FA.prepare(flower, this.getName());
        return F5;
    }

    public void sendToDP (String flowers){
        this.DP.shift(flowers);
    }
}
