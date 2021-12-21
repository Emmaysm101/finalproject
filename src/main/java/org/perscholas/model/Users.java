package org.perscholas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
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
@Data
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

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable (
			name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userNum"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
	)
	private List<Role> roles;

	@OneToMany(mappedBy = "users")
	List<Orders> orders;

	public Users(String userId, String userPw, String userName, String userAddress, List<Role> roles) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userAddress = userAddress;
		this.roles = roles;
	}
}
