package com.etiqa.onlinestore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Book title is required")
    @Column(name = "book_title", nullable = false)
    private String bookTitle;

    @NotNull(message = "Book price must be provided")
    @PositiveOrZero(message = "Book price must be zero or positive")
    @Column(name = "book_price")
    private Double bookPrice;

    @NotNull(message = "Book quantity must be provided")
    @Min(value = 0, message = "Book quantity must be zero or positive")
    @Column(name = "book_quantity")
    
    private Integer bookQuantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public Double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public Integer getBookQuantity() {
		return bookQuantity;
	}

	public void setBookQuantity(Integer bookQuantity) {
		this.bookQuantity = bookQuantity;
	}

    
	public ProductEntity(String bookTitle, double bookPrice, int bookQuantity) {
	    this.bookTitle = bookTitle;
	    this.bookPrice = bookPrice;
	    this.bookQuantity = bookQuantity;
	}
}
