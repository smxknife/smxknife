package com.smxknife.springdatajpa.model.ent;

import com.smxknife.springdatajpa.model.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

/**
 * @author smxknife
 * 2020/11/19
 */
@Getter
@Setter
@MappedSuperclass
public class EntItem extends Item {

}
