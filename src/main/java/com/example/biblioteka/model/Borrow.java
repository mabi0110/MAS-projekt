package com.example.biblioteka.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Borrow {
    private int id;
    private Integer userId;
    private Integer bookId;
    private Date borrowDate;

    public Borrow(Integer userId, Integer bookId, Date borrowDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
    }

    public Borrow(int id, Integer userId, Integer bookId, Date borrowDate) {
        this(userId, bookId, borrowDate);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", borrowDate=" + borrowDate +
                '}';
    }
}
