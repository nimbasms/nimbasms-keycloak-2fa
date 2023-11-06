package nimbasms.keycloak.authenticator.gateway;

import nimbasms.keycloak.authenticator.SmsConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author Harouna Diallo, @hadpro24
 */
@Slf4j
public class SmsServiceFactory {

	public static SmsService get(Map<String, String> config) {
		if (Boolean.parseBoolean(config.getOrDefault(SmsConstants.SIMULATION_MODE, "false"))) {
			return (phoneNumber, message) -> log.warn(String
					.format("***** SIMULATION MODE ***** Would send SMS to %s with text: %s", phoneNumber, message));
		} else {
			return new NimbaSmsService(config);
		}
	}

}
