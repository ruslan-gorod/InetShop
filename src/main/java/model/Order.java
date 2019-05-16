package model;

import java.util.Objects;

public class Order {
    private int id;
    private int goodId;
    private int userId;
    private int code;

    public Order(int goodId, int userId, int code) {
        this.goodId = goodId;
        this.userId = userId;
        this.code = code;
    }

    public Order(int id, int goodId, int userId, int code) {
        this.id = id;
        this.goodId = goodId;
        this.userId = userId;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                goodId == order.goodId &&
                userId == order.userId &&
                code == order.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodId, userId, code);
    }
}
