public class Item implements Comparable<Item> {
    private final String id;
    private final String name;
    private final String category;
    private int quantity;

    public Item(String id, String name, String category, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(Item other) {
        return Integer.compare(other.quantity, this.quantity);
    }

    @Override
    public String toString() {
        return "Item{" +
                "category='" + category + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
