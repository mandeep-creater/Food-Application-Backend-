package com.foodapp.serviceImpl;

import com.foodapp.DTO.CartItemDTO;
import com.foodapp.entity.CartItem;
import com.foodapp.entity.MenuItem;
import com.foodapp.entity.User;
import com.foodapp.exception.GlobalExceptionHandler;
import com.foodapp.helper.CartItemMapper;
import com.foodapp.repo.CartItemRepo;
import com.foodapp.repo.MenuItemRepo;
import com.foodapp.repo.UserRepository;
import com.foodapp.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.foodapp.helper.CartItemMapper.toDTO;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MenuItemRepo menuItemRepo;


    @Override
    public CartItemDTO addCartItem(Long menuItemId, int quantity, String email) {
        //check user exist
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> GlobalExceptionHandler.userNotExists(User.class, email));
        //check menu item exist
        MenuItem menuItem = menuItemRepo.findById(menuItemId)
                .orElseThrow(() -> GlobalExceptionHandler.menuItemNotExists(menuItemId));

        //check cart item exist
        Optional<CartItem> cartItemOptional = cartItemRepo.findByUserAndMenuItem(user, menuItem);

        CartItem cartItem;

        if (cartItemOptional.isPresent()) {
            // Item already in cart: update quantity
            cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            // Item not in cart: create new
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setMenuItem(menuItem);
            cartItem.setQuantity(quantity);
            cartItem.setAddedAt(LocalDateTime.now());
        }
        CartItem saved = cartItemRepo.save(cartItem);
        return toDTO(saved);

    }

    @Override
    public List<CartItemDTO> getCartByUser(String email) {

        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw GlobalExceptionHandler.userNotExists(User.class, email);
        }
        List<CartItem> cartItems = cartItemRepo.findAll();
        return cartItems.stream().map(CartItemMapper::toDTO).collect(java.util.stream.Collectors.toList());
    }


    @Override
    public void removeCartItem(Long cartItemId,boolean forceDelete)  {
        Optional<CartItem> cartItemOptional = cartItemRepo.findById(cartItemId);
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            if (forceDelete || cartItem.getQuantity() <= 1) {
                cartItemRepo.delete(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItemRepo.save(cartItem);
            }
        }else
        {
             GlobalExceptionHandler.cartItemNotExists(cartItemId);
        }
    }



    @Override
    public void emptyCart(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw GlobalExceptionHandler.userNotExists(User.class,email);
        }
        List<CartItem> cartItems = cartItemRepo.findAllByUser(user.get());
        cartItems.forEach(cartItem -> cartItemRepo.delete(cartItem));

    }

    @Override
    public CartItemDTO getCartItemById(Long cartItemId) {

        Optional<CartItem> cartItemOptional = cartItemRepo.findById(cartItemId);
        if (!cartItemOptional.isPresent()) {
             GlobalExceptionHandler.cartItemNotExists(cartItemId);
        }
        CartItem items = cartItemOptional.get();
        return toDTO(items);

    }

    @Override
    public double calculateCartTotal(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw GlobalExceptionHandler.userNotExists(User.class,email);
        }
        List<CartItem> cartItems = cartItemRepo.findAllByUser(user.get());
        double totalPrice = 0.0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getMenuItem().getPrice() * cartItem.getQuantity();
        }
        return totalPrice;

    }

    @Override
    public int calculateCartQuantity(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw GlobalExceptionHandler.userNotExists(User.class,email);
        }
        List<CartItem> cartItems = cartItemRepo.findAllByUser(user.get());
        int totalQuantity = 0;
        for (CartItem cartItem : cartItems) {
            totalQuantity += cartItem.getQuantity();
        }
        return totalQuantity;

    }

    @Override
    public CartItemDTO getLastAddedItem(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw GlobalExceptionHandler.userNotExists(User.class,email);
        }
        CartItem cartItem = cartItemRepo.findFirstByUserOrderByAddedAtDesc(user.get());
        if (cartItem== null) {
                GlobalExceptionHandler.cartItemNotExists(0L);
        }
            return toDTO(cartItem);

    }

    @Override
    public CartItemDTO getFirstAddedItem(String email)  {
        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw GlobalExceptionHandler.userNotExists(User.class,email);
        }
        CartItem cartItem = cartItemRepo.findFirstByUserOrderByAddedAtAsc(user.get());
        if (cartItem== null) {
             GlobalExceptionHandler.cartItemNotExists(0L);
        }
        return toDTO(cartItem);
    }

    @Override
    public int getCartItemCount(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw GlobalExceptionHandler.userNotExists(User.class,email);
        }
        List<CartItem> cartItems = cartItemRepo.findAllByUser(user.get());
        return cartItems.size();
    }

    @Override
    public double getAveragePrice(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw GlobalExceptionHandler.userNotExists(User.class,email);
        }

        List<CartItem> cartItems = cartItemRepo.findAllByUser(user.get());
        double totalPrice = 0.0;
        int totalQuantity = 0;

        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getMenuItem().getPrice() * cartItem.getQuantity();
            totalQuantity += cartItem.getQuantity();
        }

        if (totalQuantity > 0) {
            return totalPrice / totalQuantity;
        } else {
            return 0.0;
        }
    }

    @Override
    public int getMaxQuantity(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw GlobalExceptionHandler.userNotExists(User.class,email);
        }
        List<CartItem> cartItems = cartItemRepo.findAllByUser(user.get());
        int maxQuantity = 0;
        for (CartItem cartItem : cartItems) {
            maxQuantity = Math.max(maxQuantity, cartItem.getQuantity());
        }
        return maxQuantity;
    }

    @Override
    public int getMinQuantity(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw GlobalExceptionHandler.userNotExists(User.class,email);
        }
        List<CartItem> cartItems = cartItemRepo.findAllByUser(user.get());
        int minQuantity = Integer.MAX_VALUE;
        for (CartItem cartItem : cartItems) {
            minQuantity = Math.min(minQuantity, cartItem.getQuantity());
        }
        return minQuantity;
    }

    @Override
    public double getTotalPriceInRange(String email, double minPrice, double maxPrice) {
     return 0;
    }

    @Override
    public double getAveragePriceInRange(String email, double minPrice, double maxPrice) {
        return 0;
    }

    @Override
    public int getMaxQuantityInRange(String email, int minQuantity, int maxQuantity) {
        return 0;
    }

    @Override
    public int getMinQuantityInRange(String email, int minQuantity, int maxQuantity) {
        return 0;
    }

    @Override
    public double getTotalPriceInQuantityRange(String email, int minQuantity, int maxQuantity) {
        return 0;
    }

    @Override
    public double getAveragePriceInQuantityRange(String email, int minQuantity, int maxQuantity) {
        return 0;
    }

    @Override
    public double getMaxPrice(String email) {
        return 0;
    }

    @Override
    public double getMinPrice(String email) {
        return 0;
    }

    @Override
    public double getTotalPriceInRangeAndQuantityRange(String email, double minPrice, double maxPrice, int minQuantity, int maxQuantity) {
        return 0;
    }

    @Override
    public double getAveragePriceInRangeAndQuantityRange(String email, double minPrice, double maxPrice, int minQuantity, int maxQuantity) {
        return 0;
    }

    @Override
    public double getMaxPriceInRangeAndQuantityRange(String email, double minPrice, double maxPrice, int minQuantity, int maxQuantity) {
        return 0;
    }

    @Override
    public double getMinPriceInRangeAndQuantityRange(String email, double minPrice, double maxPrice, int minQuantity, int maxQuantity) {
        return 0;
    }

    @Override
    public double getTotalPriceInRangeAndQuantityRangeAndCategoryRange(String email, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String category) {
        return 0;
    }

    @Override
    public double getAveragePriceInRangeAndQuantityRangeAndCategoryRange(String email, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String category) {
        return 0;
    }

    @Override
    public double getMaxPriceInRangeAndQuantityRangeAndCategoryRange(String email, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String category) {
        return 0;
    }


}
