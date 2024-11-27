import java.util.*;

public class CategoryManager {
    private final Map<String, PriorityQueue<Item>> categoryMap;

    public CategoryManager() {
        categoryMap = new HashMap<>();
    }

    // Add an item
    public void addItemToCategory(Item item) {
        categoryMap.computeIfAbsent(item.getCategory(), k -> new PriorityQueue<>()).add(item);
    }

    // Remove an item
    public void removeItemFromCategory(Item item) {
        PriorityQueue<Item> items = categoryMap.get(item.getCategory());
        if (items != null) {
            items.remove(item);
            if (items.isEmpty()) {
                categoryMap.remove(item.getCategory());
            }
        }
    }

    // Retrieve all items
    public List<Item> getItemsByCategory(String category) {
        PriorityQueue<Item> items = categoryMap.get(category);
        return items == null ? Collections.emptyList() : new ArrayList<>(items);
    }
}
