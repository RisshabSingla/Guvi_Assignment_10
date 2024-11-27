import java.util.Random;

public class InventoryPerformanceTest {
    public static void main(String[] args) {
        InventoryManager ims = new InventoryManager(1);

        int totalItems = 1_000_000;
        Random random = new Random();
        System.out.println("Adding " + totalItems + " items to inventory...");

        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= totalItems; i++) {
            String id = "ID" + i;
            String name = "Item" + i;
            String category = "Category" + (i % 100);
            int quantity = random.nextInt(100000) + 1;
            ims.addItem(id, name, category, quantity);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Finished adding items in " + (endTime - startTime) + " ms.");

        // Retrieve items in a random category
        String testCategory = "Category50";
        System.out.println("\nRetrieving items in " + testCategory + "...");
        startTime = System.currentTimeMillis();
        System.out.println("Items in " + testCategory + ": " + ims.getItemsByCategory(testCategory).size());
        endTime = System.currentTimeMillis();
        System.out.println("Retrieved in " + (endTime - startTime) + " ms.");

        // Retrieve top 10 items by quantity
        System.out.println("\nRetrieving top 10 items by quantity...");
        startTime = System.currentTimeMillis();
        ims.getTopKItems(10).forEach(System.out::println);
        endTime = System.currentTimeMillis();
        System.out.println("Retrieved in " + (endTime - startTime) + " ms.");

        // Merge with another large inventory
        InventoryManager ims2 = new InventoryManager(1);
        System.out.println("\nAdding another mil items to second inventory...");
        startTime = System.currentTimeMillis();
        for (int i = totalItems/2; i <= totalItems+totalItems/2; i++) {
            String id = "ID" + i;
            String name = "Item" + i;
            String category = "Category" + (i % 100); // 100 unique categories
            int quantity = random.nextInt(1000) + 1; // Random quantity between 1 and 1000
            ims2.addItem(id, name, category, quantity);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Finished adding items in " + (endTime - startTime) + " ms.");

        System.out.println("\nMerging inventories...");
        startTime = System.currentTimeMillis();
        ims.mergeInventory(ims2);
        endTime = System.currentTimeMillis();
        System.out.println("Merged inventories in " + (endTime - startTime) + " ms.");

        // Verify total item count
        System.out.println("\nTotal items in inventory after merging: " + ims.listAllItems().size());
    }
}
