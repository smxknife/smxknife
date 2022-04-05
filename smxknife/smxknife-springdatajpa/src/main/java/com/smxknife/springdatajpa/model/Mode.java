package com.smxknife.springdatajpa.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2020/11/19
 */
@Getter
@Setter
@MappedSuperclass
public class Mode  implements Serializable {
	public static final long serialVersionUID = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@CreationTimestamp
	private LocalDateTime whenCreate;

	@UpdateTimestamp
	private LocalDateTime whenUpdate;
}
