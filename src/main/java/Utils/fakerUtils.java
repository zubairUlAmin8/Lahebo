package Utils;

import com.github.javafaker.Faker;

public class fakerUtils {



        public static String generateRandomAustralianPhoneNumber() {
            Faker faker = new Faker();
            String phoneNumber = faker.numerify("######"); // Generate a 10-digit number
            // Format the phone number
            phoneNumber = "+61201"  + " " + phoneNumber.substring(0, 3) + " " + phoneNumber.substring(3);
            return phoneNumber;

        }
}
