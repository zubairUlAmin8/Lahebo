package Utils;

import org.jboss.aerogear.security.otp.Totp;

public class Two2FActorAuthentication {
    public static String getOptCode(String secretKey) {
        Totp totp = new Totp(secretKey);
        String twofactorcode = totp.now();
        return twofactorcode;
    }
}
