package com.smxknife.energy.services.user.infras.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author smxknife
 * 2021/5/19
 */
@Data
@Entity
@Table(name = "tb_user_meta")
public class UserMeta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String account;
	private String username;
	private String mail;
	private String tel;
	private String password = "111111";
}
