package roomscape.es.roomscapefrontend.utils;

import lombok.Data;
import org.springframework.core.env.Environment;

@Data
public class Configuration {

    private String backendURL;
    private String imageFolder;
    private String iconScapeRoom;
    private int timeOut;

    public void loadConfiguration(Environment environment) {
        backendURL = environment.getProperty("roomscape.backend");
        imageFolder = environment.getProperty("images");
        iconScapeRoom = imageFolder + "IconoApp_1.png";
        timeOut = 10;
    }
}
