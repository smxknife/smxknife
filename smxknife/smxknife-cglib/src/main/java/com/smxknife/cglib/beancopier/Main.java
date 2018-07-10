package com.smxknife.cglib.beancopier;

import net.sf.cglib.beans.BeanCopier;

public class Main {
  public static void main(String[] args) {
    Entity entity = new Entity();
    entity.setName("test");
    entity.setPassword("12346");

    Model model = new Model();

    BeanCopier beanCopier = BeanCopier.create(Entity.class, Model.class, false);
    beanCopier.copy(entity, model, null);
    System.out.println(model);
  }
}