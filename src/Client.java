public class Client {

    private long startTime;
    private String pizzaName;

    public long getStartTime() {
        return startTime;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public Client(long startTime, String pizzaName) {
        this.startTime = startTime;
        this.pizzaName = pizzaName;
    }
}
