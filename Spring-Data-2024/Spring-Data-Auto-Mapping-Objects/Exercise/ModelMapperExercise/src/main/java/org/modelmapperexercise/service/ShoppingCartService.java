package org.modelmapperexercise.service;

import org.modelmapperexercise.service.dtos.CartItemDto;

public interface ShoppingCartService{
     String addItem(CartItemDto item);
     String removeItem(CartItemDto item);
     String buyItem();
}
