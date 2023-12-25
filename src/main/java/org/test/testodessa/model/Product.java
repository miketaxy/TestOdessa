package org.test.testodessa.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date entryDate;
    private String itemCode;
    private String itemName;
    private String itemQuantity;
    private String status; //Probably better use enum
}
