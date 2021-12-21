package org.perscholas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="items")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="itemId")
	private Long itemId;
	
	@Column(name="itemName")
	private String itemName;
	
	@Column(name="itemPrice")
	private double itemPrice;
	
	@Column(name="availableQuantity")
	private int availableQuantity;
	
	@Column(name="image")
	private String image;

	@OneToMany(mappedBy = "items")
	List<Cart> carts;

}
