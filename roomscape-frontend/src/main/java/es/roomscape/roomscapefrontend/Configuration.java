package es.roomscape.roomscapefrontend;

import lombok.Data;
import org.springframework.core.env.Environment;

@Data
public class Configuration {

    private String backendURL;
    private String imageFolder;

    public void loadConfiguration(Environment environment) {
        backendURL = environment.getProperty("roomscape.backend");
        imageFolder = environment.getProperty("images");
    }
}
