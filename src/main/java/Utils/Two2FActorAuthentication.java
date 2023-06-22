package Utils;

import org.jboss.aerogear.security.otp.Totp;

public class Two2FActorAuthentication {
    public Totp totp;
    String twofactorcode;
    public String getOptCode(String secretKey) {
         totp = new Totp(secretKey);
         twofactorcode = totp.now();
        return twofactorcode;
    }
}
