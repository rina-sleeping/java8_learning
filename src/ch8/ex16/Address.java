package ch8.ex16;

public class Address {
	private String city;
	private String state;
	private String zipCode;

	Address(String city, String state, String zipCode) {
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return String.join(",", "city:" + city, "state:" + state, "zipCode:"
				+ zipCode);
	}
}
