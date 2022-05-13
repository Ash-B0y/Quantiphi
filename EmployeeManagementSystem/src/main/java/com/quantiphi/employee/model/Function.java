package com.quantiphi.employee.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name="functions")
public class Function implements Serializable{
	
	@Id
	@Column(name = "function_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int functionId;
	@Column(name = "function_name",unique=true)
	private String functionName;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "functions")
	private Set<Role> roles;
	
	public Function() {
		super();
	}

	public int getFunctionId() {
		return functionId;
	}

	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Function [functionId=" + functionId + ", functionName=" + functionName + ", roles=" + roles + "]";
	}
	
	

}
