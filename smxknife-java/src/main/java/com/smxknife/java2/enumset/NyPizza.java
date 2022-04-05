package com.smxknife.java2.enumset;

import java.util.Objects;

/**
 * @author smxknife
 * 2020/1/30
 */
public class NyPizza extends Pizza {

	public static void main(String[] args) {
		Pizza pizza = new NyBuilder(Size.SMALL)
				.addTopping(Topping.SAUSAGE)
				.addTopping(Topping.ONION)
				.build();
	}

	public enum Size {SMALL, MEDIUM, LARGE}
	private final Size size;

	private NyPizza(NyBuilder builder) {
		super(builder);
		this.size = builder.size;
	}

	public static class NyBuilder extends Builder<NyBuilder> {

		private final Size size;

		public NyBuilder(Size size) {
			this.size = Objects.requireNonNull(size);
		}

		@Override
		Pizza build() {
			return new NyPizza(this);
		}

		@Override
		protected NyBuilder self() {
			return this;
		}
	}
}
