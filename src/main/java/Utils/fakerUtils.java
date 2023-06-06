package Utils;

import com.github.javafaker.Faker;

public class fakerUtils {
    static Faker faker = new Faker();


        public static String generateRandomAustralianPhoneNumber() {
            String phoneNumber = faker.numerify("######"); // Generate a 10-digit number
            // Format the phone number
            phoneNumber = "+61201"  + " " + phoneNumber.substring(0, 3) + " " + phoneNumber.substring(3);
            return phoneNumber;

        }

    public static int generateRandomInt() {
        int number = faker.number().numberBetween(1, 100);
        return number;
    }

}
