package com.nsv.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class Rol implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String authority;

	private String authorityDesc;

	public Rol() {
	}

	public Rol(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}
}
