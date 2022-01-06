package org.perscholas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = "userId"))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userNum", unique=true, nullable=false)
	private Long userNum;

	@Column(name="userId")
	private String userId;
	
	@Column(name="userPw")
	private String userPw;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="userAddress")
	private String userAddress;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable (
			name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userNum"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
	)
	private List<Role> roles;

	@JsonBackReference
	@OneToMany(mappedBy = "users")
	List<Orders> orders;

	public Users(String userId, String userPw, String userName, String userAddress, List<Role> roles) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userAddress = userAddress;
		this.roles = roles;
	}

	public Long getUserNum() {
		return userNum;
	}

	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
}
