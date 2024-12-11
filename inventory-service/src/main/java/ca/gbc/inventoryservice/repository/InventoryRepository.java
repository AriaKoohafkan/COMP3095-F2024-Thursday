
package ca.gbc.inventoryservice.repository;

import ca.gbc.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    boolean existsBySkuCodeAndQuantityGreaterThan(String skuCode, int quantity);
}
