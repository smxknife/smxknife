package com.smxknife.springdatajpa.model.building;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author smxknife
 * 2020/11/19
 */
@Entity
@DiscriminatorValue("r")
public class Room extends StructureItem {
}
