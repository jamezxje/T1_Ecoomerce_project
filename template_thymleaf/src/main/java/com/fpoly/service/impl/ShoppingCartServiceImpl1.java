//package com.fpoly.service.impl;
//
//import com.fpoly.model.*;
//import com.fpoly.repository.ShoppingCartDetailRepository;
//import com.fpoly.repository.ShoppingCartRepository;
//import com.fpoly.service.ShoppingCartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class ShoppingCartServiceImpl1 implements ShoppingCartService {
//
//    @Autowired
//    ShoppingCartRepository shoppingCartRepository;
//
//    @Autowired
//    ShoppingCartDetailRepository shoppingCartDetailRepository;
//
//    @Override
//    public ShoppingCart addToCart(Product product, Size size, Integer quantity, Users user) {
//
//        ShoppingCart cart = user.getShoppingCart();
//
//        // Create a new shopping cart if it doesn't exist
//        if (cart == null) {
//            cart = new ShoppingCart();
//            cart.setUser(user);
//            cart.setTotalItem(0);
//            cart.setTotalPrice((double) 0);
//        }
//
//        // Initialize cart details set if null
//        Set<ShoppingCartDetail> cartDetails = cart.getShoppingCartDetails();
//        if (cartDetails == null) {
//            cartDetails = new HashSet<>();
//            cart.setShoppingCartDetails(cartDetails);
//        }
//
//        // Check if the product already exists in the cart details
//        ShoppingCartDetail existingDetail = cartDetails.stream()
//                .filter(detail -> detail.getProduct().getId().equals(product.getId()))
//                .findFirst()
//                .orElse(null);
//
//        // If the product doesn't exist in the cart, create a new detail
//        if (existingDetail == null) {
//            existingDetail = new ShoppingCartDetail();
//            existingDetail.setProduct(product);
//            existingDetail.setQuantity(quantity);
//            existingDetail.setTotalPrice(quantity * product.getPrice());
//            existingDetail.setCart(cart);
//            cartDetails.add(existingDetail);
//        } else {
//            // If the product already exists in the cart, update its quantity and total price
//            existingDetail.setQuantity(existingDetail.getQuantity() + quantity);
//            existingDetail.setTotalPrice(existingDetail.getTotalPrice() + (quantity * product.getPrice()));
//        }
//
//        // Save the shopping cart detail
//        shoppingCartDetailRepository.save(existingDetail);
//
//        // Update total item count and total price in the shopping cart
//        cart.setTotalItem(calculateTotalItems(cartDetails));
//        cart.setTotalPrice(calculateTotalPrice(cartDetails));
//
//        // Save the shopping cart
//        return shoppingCartRepository.save(cart);
//    }
//
//    // Helper method to calculate total items in the cart
//    private int calculateTotalItems(Set<ShoppingCartDetail> cartDetails) {
//        return cartDetails.stream().mapToInt(ShoppingCartDetail::getQuantity).sum();
//    }
//
//    // Helper method to calculate total price of items in the cart
//    private double calculateTotalPrice(Set<ShoppingCartDetail> cartDetails) {
//        return cartDetails.stream().mapToDouble(detail -> detail.getTotalPrice()).sum();
//
//    }
//
//
//
//
//
//    private ShoppingCartDetail findCartDetail(Set<ShoppingCartDetail> cartDetails, Integer productId) {
//        if (cartDetails == null) {
//            return null;
//        }
//        ShoppingCartDetail cartDetail = null;
//
//        for (ShoppingCartDetail item : cartDetails) {
//            if (item.getProduct().getId() == productId) {
//                cartDetail = item;
//            }
//        }
//        return cartDetail;
//    }
//
//    private int totalItems(Set<ShoppingCartDetail> cartDetails) {
//        int totalItems = 0;
//        for (ShoppingCartDetail item : cartDetails) {
//            totalItems += item.getQuantity();
//        }
//        return totalItems;
//    }
//
//    private double totalPrice(Set<ShoppingCartDetail> cartDetails) {
//        double totalPrice = 0.0;
//
//        for (ShoppingCartDetail item : cartDetails) {
//            totalPrice += item.getTotalPrice();
//        }
//
//        return totalPrice;
//    }
//}