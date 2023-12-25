package org.test.testodessa.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.test.testodessa.model.Product;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @PersistenceContext
    private EntityManager entityManager;

    public void createTableFromJson(String tableName, List<Product> records) {
        // Dynamically create table using Hibernate
        entityManager.createNativeQuery("CREATE TABLE " + tableName + " (id INT AUTO_INCREMENT PRIMARY KEY)").executeUpdate();

        // Create columns based on Product entity fields
        for (Product record : records) {
            entityManager.createNativeQuery(
                    "ALTER TABLE " + tableName +
                            " ADD COLUMN entry_date VARCHAR(255), " +
                            " ADD COLUMN item_code VARCHAR(255), " +
                            " ADD COLUMN item_name VARCHAR(255), " +
                            " ADD COLUMN item_quantity VARCHAR(255), " +
                            " ADD COLUMN status VARCHAR(255)"
            ).executeUpdate();

            // Save records to the dynamically created table
            entityManager.persist(record);
        }
    }

    public List<Product> getAllProducts() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }
}
