package com.smxknife.java.ex4.innerupcast;

public class Host {

	public People getFriend() {
		return new Friend();
	}

	public Animal getBird() {
		return new Bird();
	}

	private class Bird implements Animal {

		public String getPetName() {
			return "bird";
		}
	}

	protected class Friend implements People {

		public String getPeopleName() {
			return "people";
		}
	}
}
