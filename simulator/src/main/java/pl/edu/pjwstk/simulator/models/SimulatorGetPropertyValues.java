package pl.edu.pjwstk.simulator.models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SimulatorGetPropertyValues {
    Map<String, Integer> result = new HashMap<>();
    InputStream inputStream;
    public Map<String, Integer> getPropValues() throws IOException {
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream!=null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file" + propFileName + "not found");
            }


            int train_count = Integer.parseInt(prop.getProperty("train_count"));
            int compartments = Integer.parseInt(prop.getProperty("compartments"));
            int compartment_size = Integer.parseInt(prop.getProperty("compartment_size"));

            result.put("train_count", train_count);
            result.put("compartments", compartments);
            result.put("compartment_size", compartment_size);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}
