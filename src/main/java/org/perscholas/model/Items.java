package org.perscholas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="items")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="itemId")
	private Long itemId;
	
	@Column(name="itemName")
	private String itemName;
	
	@Column(name="itemPrice")
	private double itemPrice;
	
	@Column(name="image")
	private String image;

	@JsonBackReference
	@OneToMany(mappedBy = "items")
	List<Cart> carts;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
}
