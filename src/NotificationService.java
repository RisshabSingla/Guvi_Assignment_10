public class NotificationService {
    private final int restockThreshold;

    public NotificationService(int restockThreshold) {
        this.restockThreshold = restockThreshold;
    }

    // Check and notify
    public void checkRestock(Item item) {
        if (item.getQuantity() < restockThreshold) {
            System.out.println("Restock Notification: Item " + item.getName() + " is below the threshold.");
        }
    }
}
