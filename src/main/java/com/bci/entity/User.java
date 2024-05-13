package com.bci.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="created")
	private LocalDateTime created;
	
	@Column(name="modified")
	private LocalDateTime modified;
	
	@Column(name="lastLogin")
	private LocalDateTime lastLogin;
	
	@Column(name="token")
	private String token;
	
	@Builder.Default
	@Column(name="isActive")
	private Boolean isActive = true;
	
	@OneToMany(targetEntity = Phone.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "phone_id", referencedColumnName = "id")

	private List<Phone> phones;

	
	@PreUpdate
	private void onLastLogin() {
		lastLogin = LocalDateTime.now();
	}

	@PrePersist
	private void onCreated() {
		lastLogin = modified = created = LocalDateTime.now();

	}

}
