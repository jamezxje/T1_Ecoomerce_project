package com.fpoly.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Table(name = "Shopping_Cart")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_item")
    private Integer totalItem;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "cart")
    private Set<ShoppingCartDetail> shoppingCartDetails;

}
