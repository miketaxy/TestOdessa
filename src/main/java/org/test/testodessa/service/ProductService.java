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
        entityManager.createNativeQuery("DROP TABLE IF EXISTS   " + tableName).executeUpdate(); //IF I UNDERSTOOD TASK CORRECTLY
        entityManager.createNativeQuery("CREATE TABLE " + tableName + " (id INT AUTO_INCREMENT PRIMARY KEY, " +
                " entry_date VARCHAR(255)," +
                " item_code VARCHAR(255)," +
                " item_name VARCHAR(255)," +
                " item_quantity VARCHAR(255)," +
                " status VARCHAR(255))").executeUpdate();

        for (Product record : records) {
            entityManager.persist(record);
        }
    }

    public List<Product> getAllProducts() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }
}
