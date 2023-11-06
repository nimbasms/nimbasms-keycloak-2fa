package nimbasms.keycloak.authenticator.gateway;

/**
 * @author Harouna Diallo, @hadpro24
 */
public interface SmsService {

	void send(String phoneNumber, String message);

}
