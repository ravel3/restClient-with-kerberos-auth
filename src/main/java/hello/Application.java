package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.kerberos.client.KerberosRestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Value("${app.user-principal}")
	private String userPrincipal;

	@Value("${app.keytab-location}")
	private String keytabLocation;

	@Value("${app.access-url}")
	private String accessUrl;

	@Override
	public void run(String... args) throws Exception {
		KerberosRestTemplate restTemplate =
				new KerberosRestTemplate(keytabLocation, userPrincipal);
		int i = 0;
		do {
			String response = restTemplate.getForObject(accessUrl, String.class);
			System.out.println(response);
			i++;
		} while (i < 2);

	}

    public static void main(String[] args) throws Throwable {
    	new SpringApplicationBuilder(Application.class).web(false).run(args);
    }

}
