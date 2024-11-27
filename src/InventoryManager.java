import java.util.*;
import java.util.stream.Collectors;

public class InventoryManager {
    private final Map<String, Item> inventory;
    private final CategoryManager categoryManager;
    private final NotificationService notificationService;

    public InventoryManager(int restockThreshold) {
        inventory = new HashMap<>();
        categoryManager = new CategoryManager();
        notificationService = new NotificationService(restockThreshold);
    }

    // Add or update an item
    public void addItem(String id, String name, String category, int quantity) {
        if (inventory.containsKey(id)) {
            Item existingItem = inventory.get(id);
            categoryManager.removeItemFromCategory(existingItem);
            existingItem.addQuantity(quantity);
        } else {
            Item newItem = new Item(id, name, category, quantity);
            inventory.put(id, newItem);
        }
        categoryManager.addItemToCategory(inventory.get(id));
        notificationService.checkRestock(inventory.get(id));
    }

    // Remove item
    public void removeItem(String id) {
        if (!inventory.containsKey(id)) {
            throw new NoSuchElementException("Item with ID " + id + " not found.");
        }
        Item item = inventory.remove(id);
        categoryManager.removeItemFromCategory(item);
    }

    // Get all items
    public List<Item> getItemsByCategory(String category) {
        return categoryManager.getItemsByCategory(category);
    }

    // Merge
    public void mergeInventory(InventoryManager other) {
        for (Item item : other.inventory.values()) {
            if (this.inventory.containsKey(item.getId())) {
                Item existingItem = this.inventory.get(item.getId());
                if (item.getQuantity() > existingItem.getQuantity()) {
                    categoryManager.removeItemFromCategory(existingItem);
                    existingItem.setQuantity(item.getQuantity());
                }
            } else {
                this.addItem(item.getId(), item.getName(), item.getCategory(), item.getQuantity());
            }
        }
    }

    // Get top k items
    public List<Item> getTopKItems(int k) {
        return inventory.values().stream()
                .sorted(Comparator.comparingInt(Item::getQuantity).reversed())
                .limit(k)
                .collect(Collectors.toList());
    }

    // Return all items
    public List<Item> listAllItems() {
        return new ArrayList<>(inventory.values());
    }
}
