package Utils;

import com.github.javafaker.Faker;

public class fakerUtils {
    static Faker faker = new Faker();


        public static String generateRandomAustralianPhoneNumber() {
            String phoneNumber = faker.numerify("######"); // Generate a 10-digit number
            // Format the phone number
            phoneNumber = "+61201"  + " " + phoneNumber.substring(0, 3) + " " + phoneNumber.substring(3);
            LogUtils.info("Random Phone Number for Australia is Generated: "+phoneNumber);
            return phoneNumber;

        }

    public static int generateRandomInt() {
        int number = faker.number().numberBetween(1, 999);
        return number;
    }
    public static int generateRandomInt(int uperBound) {
        int number = faker.number().numberBetween(1, uperBound);
        return number;
    }
    public static String generateDummyData(int size) {
        Faker faker = new Faker();
        String dummyData = faker.lorem().characters(size);
        return dummyData;
    }
    public static String generateRandomPassword() {
        Faker faker = new Faker();

        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialCharacters = "!@$%&*";

        StringBuilder password = new StringBuilder();

        // Generate at least one character from each category
        password.append(faker.random().nextInt(uppercaseLetters.length())).append(faker.random().nextInt(lowercaseLetters.length()));
        password.append(faker.random().nextInt(numbers.length())).append(faker.random().nextInt(specialCharacters.length()));

        // Generate remaining characters
        for (int i = 0; i < 4; i++) {
//            int category = faker.random().nextInt(4);

            switch (i) {
                case 0:
                    password.append(uppercaseLetters.charAt(faker.random().nextInt(uppercaseLetters.length())));
                    break;
                case 1:
                    password.append(lowercaseLetters.charAt(faker.random().nextInt(lowercaseLetters.length())));
                    break;
                case 2:
                    password.append(numbers.charAt(faker.random().nextInt(numbers.length())));
                    break;
                case 3:
                    password.append(specialCharacters.charAt(faker.random().nextInt(specialCharacters.length())));
                    break;
            }
        }
        // Shuffle the characters in the password
        for (int i = 0; i < password.length(); i++) {
            int j = faker.random().nextInt(password.length());
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(j));
            password.setCharAt(j, temp);
        }
        LogUtils.info("Random Password is Generated: "+password.toString());
        return password.toString();
    }
    public static String generateRandomDepartment() {
        Faker faker = new Faker();
        return faker.job().field();
    }
}

