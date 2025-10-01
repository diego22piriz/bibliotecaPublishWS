package configuracion;

import java.util.HashMap;

public class WebServiceConfiguracion {
    private HashMap<String, String> configs;
    
    public WebServiceConfiguracion() {
        configs = new HashMap<>();
        // Configuración hardcodeada
        configs.put("#WS_IP", "localhost");
        configs.put("#WS_PORT", "9129");
    }
    
    public String getConfigOf(String nombre) {
        return configs.get(nombre); 
    }
}