package com.briup.demo.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiParam;
//表示是应一个实体类
@Entity
//对应数据库中的表t_user
@Table(name = "cms_customer")
public class Customer implements Serializable {
	@Id
	@ApiParam(hidden = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "GoodsId")
	@SequenceGenerator(name = "GoodsId",sequenceName = "GoodsId",allocationSize=1)   
	private Short id;
	
	@Column(unique = true,nullable = false)
	@ApiParam(value = "用户名",required = true)
    private String username;
	
	@Column(unique = false,nullable = false)
	@ApiParam(value = "密码",required = true)
    private String password;

    private static final long serialVersionUID = 1L;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}