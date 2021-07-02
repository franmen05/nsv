package com.nsv.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_roles", uniqueConstraints= {@UniqueConstraint(columnNames= {"user_id", "rol"})})
public class UserRol implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "rol")
	private Rol rol;

	public UserRol() {}

	public UserRol(Rol r) {
		this.rol = r;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
