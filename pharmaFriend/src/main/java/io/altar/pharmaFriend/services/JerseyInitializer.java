package  io.altar.pharmaFriend.services;



import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyInitializer extends ResourceConfig {
	public JerseyInitializer() {
		registerEndpoints();
	}

	private void registerEndpoints() {
		register(MedicineService.class);
		register(PharmacyService.class);
		register(UserRequestService.class);
	}
}
