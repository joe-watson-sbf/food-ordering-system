package com.vakaks.food.ordering.system.entity;

import com.vakaks.food.ordering.system.domain.entity.AggregateRoot;
import com.vakaks.food.ordering.system.domain.valueobject.*;
import com.vakaks.food.ordering.system.exception.OrderDomainException;
import com.vakaks.food.ordering.system.valueobject.OrderItemId;
import com.vakaks.food.ordering.system.valueobject.StreetAddress;
import com.vakaks.food.ordering.system.valueobject.TranckingId;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress deliveryAddress;
    private final Money price;
    private final List<OrderItem> items;
    private TranckingId tranckingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;


    public void initializeOrder(){
        setId(new OrderId(UUID.randomUUID()));
        tranckingId = new TranckingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        initializeItems();
    }

    public void validateOrder(){
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    private void validateItemsPrice() {
        Money orderItemsTotal = items.stream()
                .map(orderItem -> {
                    validateItemPrice(orderItem);
                    return orderItem.getSubTotal();
                }).reduce(Money.ZERO, Money::add);

        if(!price.equals(orderItemsTotal)){
            throw new OrderDomainException("Total price " + price.getAmount()
            + "is not equal to Order Items total: " + orderItemsTotal.getAmount() + " !");
        }
    }

    private void validateItemPrice(OrderItem orderItem) {
        if(!orderItem.isPriceValid()){
            throw new OrderDomainException("Order item price: " + orderItem.getPrice().getAmount() +
                    " is not a valid for product " + orderItem.getProduct().getId().getValue());
        }
    }

    private void validateTotalPrice() {
        if (price==null || !getPrice().isGreaterThanZero()){
            throw new OrderDomainException("Total price must be greater than zero!");
        }
    }

    private void validateInitialOrder() {
        // CONDITION IN REVIEW
        if (orderStatus == null || getId() ==null){
            throw new OrderDomainException("Order is not in correct state for initialization!");
        }
    }

    private void initializeItems(){
        AtomicLong itemId = new AtomicLong(1);
        items.forEach(orderItem ->
                        orderItem.initializeOrderItem(
                                super.getId(),
                                new OrderItemId(itemId.getAndIncrement())
                        )
        );
    }

    public void pay(){
        if(!orderStatus.equals(OrderStatus.PENDING)){
            throw new OrderDomainException("Order is not in correct state for pay operation!");
        }
        orderStatus = OrderStatus.PAID;
    }

    public void cancel(List<String> failureMessages){
        if (!(orderStatus == OrderStatus.PENDING || orderStatus == OrderStatus.CANCELLING)) {
            throw new OrderDomainException("Order is not in correct state for cancel operation!");
        }
        orderStatus = OrderStatus.CANCELLED;
        updateFailureMessages(failureMessages);
    }


    public void initCancel(List<String> failureMessages){
        if(orderStatus != OrderStatus.PAID){
            throw new OrderDomainException("Order is not in correct state for init cancel operation!");
        }
        orderStatus= OrderStatus.CANCELLING;
        updateFailureMessages(failureMessages);
    }

    public void approve(){
        if(!orderStatus.equals(OrderStatus.PAID)){
            throw new OrderDomainException("Order is not in correct state for approve operation!");
        }
        orderStatus = OrderStatus.APPROVED;
    }


    private void updateFailureMessages(List<String> failureMessages){
        if(this.failureMessages != null && failureMessages != null){
            this.failureMessages.addAll(failureMessages
                    .stream().filter(m -> !(m.isEmpty() && m.isBlank())).toList());
        }
        if(this.failureMessages==null){
            this.failureMessages = failureMessages;
        }
    }




    private Order(Builder builder) {
        super.setId(builder.orderId);
        customerId = builder.customerId;
        restaurantId = builder.restaurantId;
        deliveryAddress = builder.deliveryAddress;
        price = builder.price;
        items = builder.items;
        tranckingId = builder.tranckingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;
    }


    public CustomerId getCustomerId() {
        return customerId;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public StreetAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public Money getPrice() {
        return price;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public TranckingId getTranckingId() {
        return tranckingId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public static final class Builder {
        private OrderId orderId;
        private CustomerId customerId;
        private RestaurantId restaurantId;
        private StreetAddress deliveryAddress;
        private Money price;
        private List<OrderItem> items;
        private TranckingId tranckingId;
        private OrderStatus orderStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder deliveryAddress(StreetAddress val) {
            deliveryAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder tranckingId(TranckingId val) {
            tranckingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
