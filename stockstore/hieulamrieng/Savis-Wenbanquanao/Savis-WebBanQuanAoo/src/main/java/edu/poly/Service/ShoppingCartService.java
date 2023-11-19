package edu.poly.Service;



import edu.poly.model.CartItem;
import edu.poly.model.OrderDetail;

import java.util.Collection;

public interface ShoppingCartService {


    void add(CartItem item);

    void remove(int id);

    Collection<CartItem> getCartItems();

    void clear();

    void update(int id, int quantity);

    double getAmount();

    int getCount();
}
