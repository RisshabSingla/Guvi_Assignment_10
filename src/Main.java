public class Main {
    public static void main(String[] args) {
        InventoryManager ims = new InventoryManager(10);

        // Adding items
        ims.addItem("101", "Laptop", "Electronics", 50);
        ims.addItem("102", "Mouse", "Electronics", 5);
        ims.addItem("103", "Keyboard", "Electronics", 100);
        ims.addItem("201", "Chair", "Furniture", 20);
        ims.addItem("202", "Table", "Furniture", 8);

        // Listing all items
        System.out.println("All Items in Inventory:");
        ims.listAllItems().forEach(System.out::println);

        // Category-wise sorting and retrieval
        System.out.println("\nItems in Electronics Category:");
        ims.getItemsByCategory("Electronics").forEach(System.out::println);

        // Restock notification
        System.out.println("\nRestock Notifications:");
        ims.addItem("202", "Table", "Furniture", -5);

        // Top k items
        System.out.println("\nTop 3 Items by Quantity:");
        ims.getTopKItems(3).forEach(System.out::println);

        // Merging inventories
        InventoryManager ims2 = new InventoryManager(10);
        ims2.addItem("103", "Keyboard", "Electronics", 120); // Higher quantity
        ims2.addItem("301", "Apple", "Groceries", 30);
        ims.mergeInventory(ims2);

        System.out.println("\nAfter Merging:");
        ims.listAllItems().forEach(System.out::println);
    }
}
