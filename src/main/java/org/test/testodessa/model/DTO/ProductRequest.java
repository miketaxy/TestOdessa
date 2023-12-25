package org.test.testodessa.model.DTO;


import lombok.Data;
import org.test.testodessa.model.Product;

import java.util.List;

@Data
public class ProductRequest {
    private String table;
    private List<Product> records;
}
