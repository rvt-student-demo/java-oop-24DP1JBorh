package OnlineShop;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Warehouse {
    private Map<String, Integer> warehousePrice;
    private Map<String, Integer> warehouseStock;

    public Warehouse() {
        this.warehousePrice = new HashMap<>();
        this.warehouseStock = new HashMap<>();
    }

    public void addProduct(String product, int price, int stock) {
        warehousePrice.put(product, price);
        warehouseStock.put(product, stock);
    }

    public int price(String product) {
        if (!(warehousePrice.containsKey(product))) {
            return -99;
        }

        return warehousePrice.get(product);
    }

    public int stock(String product) {
        if (!(warehouseStock.containsKey(product))) {
            return 0;
        }

        return warehouseStock.get(product);
    }

    public boolean take(String product) {
        if (!(warehouseStock.containsKey(product)) || warehouseStock.get(product) == 0 ) {
            return false;
        }

        warehouseStock.put(product, stock(product) - 1);
        return true;
    }

    public Set<String> products() {
        return warehouseStock.keySet();
    }

    
}
