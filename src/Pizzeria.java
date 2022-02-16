import java.util.concurrent.*;

public class Pizzeria {

    private ConcurrentLinkedQueue<Client> clients = new ConcurrentLinkedQueue<>();
    private Object lock = new Object();
    private boolean workTime;
    private long start;
    private long end;

    public Pizzeria() {
        new Wagon("Wagon1").start();
        new Wagon("Wagon2").start();
        start = System.currentTimeMillis();
        end = start + 5000;
    }

    public void order(String pizzaName) {
        if (System.currentTimeMillis() < end) {
            clients.add(new Client(System.currentTimeMillis(), pizzaName));
        } else {
            System.out.println("We are closed, dude");
        }
    }

    class Wagon extends Thread{

        long checkTime;

        Wagon(String name) {
            super(name);
        }
        public void run() {
            while (System.currentTimeMillis() < end) {
                if (clients.size() > 0) {
                    cook(clients.poll());
                }
            }
        }

        private void cook(Client client) throws NullPointerException{
            checkTime = System.currentTimeMillis() - client.getStartTime();

            if (checkTime >= 250) {
                System.out.println(client.getPizzaName() + " is NOT delivered");
            } else {
                try {
                    sleep(500);
                    System.out.println(client.getPizzaName() + " is delivered");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
