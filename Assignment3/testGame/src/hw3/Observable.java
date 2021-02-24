package hw3;

public class Observable {

    protected LinkedList<Observer> obList;

    public Observable(){
        obList = new LinkedList<Observer>();
    }

    public void register(Observer e){
        obList.add(e);
    }

    public  void notifyObservers(String massage){
        for (Observer e : obList){
            e.onEvent(massage);
        }
    }
}
