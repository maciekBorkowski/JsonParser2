package pl.maciejborkowski.jsonparser2;

public class Main {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Vehicle v = new Vehicle("Daewoo", "Model", 546);
                v.setRealMileage(200000);
                String[] accessories = {"ABS", "4 air bags"};
                v.accessories = accessories;
		System.out.println(JsonParser.toJson(v));
	}
}