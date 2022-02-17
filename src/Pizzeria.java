import java.util.Map;
import java.util.concurrent.*;

public class Pizzeria {

    private ConcurrentLinkedQueue<Client> clients = new ConcurrentLinkedQueue<>();
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
        }
    }

    class Wagon extends Thread {

        long checkTime;

        Wagon(String name) {
            super(name);
        }

        public void run() {
            while (System.currentTimeMillis() < end) {
                if (clients.size() > 0) {
                    try {
                        if (clients.peek() != null) {
                            cook(clients.poll());
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private void cook(Client client) {
            checkTime = System.currentTimeMillis() - client.getStartTime();
            //System.out.println(client.getPizzaName() + " CheckTime :" + checkTime);
            if (checkTime > 249) {
                System.out.println(client.getPizzaName() + " is NOT delivered (");
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
