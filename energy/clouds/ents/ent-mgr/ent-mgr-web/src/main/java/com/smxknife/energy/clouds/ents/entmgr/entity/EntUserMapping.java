package com.smxknife.energy.clouds.ents.entmgr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author smxknife
 * 2021/5/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_ent_user_mapping")
public class EntUserMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String entName;
	private String entCode;
	private String account;
	private String username;
	private String mail;
	private String tel;
}
