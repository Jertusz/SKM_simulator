package pl.edu.pjwstk.simulator.models;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SimulatorGetPropertyValues {
    Map<String, Integer> result = new HashMap<>();
    InputStream inputStream;
    int train_count;
    int compartments;
    int compartmentSize;

    public Map<String, Integer> getPropValues() throws IOException {
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
                train_count = Integer.parseInt(prop.getProperty("train_count"));
                compartments = Integer.parseInt(prop.getProperty("compartments"));
                compartmentSize = Integer.parseInt(prop.getProperty("compartment_size"));

                result.put("train_count", train_count);
                result.put("compartments", compartments);
                result.put("compartment_size", compartmentSize);
            }
        } catch (Exception e) {
            train_count = 5;
            compartments = 7;
            compartmentSize = 6;
        } finally {
            inputStream.close();
        }
        return result;
    }
}
