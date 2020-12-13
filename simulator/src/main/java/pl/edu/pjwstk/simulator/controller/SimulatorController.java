package pl.edu.pjwstk.simulator.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.simulator.models.Compartment;
import pl.edu.pjwstk.simulator.models.Simulator;
import pl.edu.pjwstk.simulator.models.Train;
import pl.edu.pjwstk.simulator.service.TrainService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("/trains")
public class SimulatorController extends CrudController<Train>{

    public SimulatorController(TrainService service){
        super(service);
    }

    @Override
    public Function<Train, Map<String, Object>> transformToDTO() {
        return train -> {
            var payload = new LinkedHashMap<String, Object>();
            payload.put("id", train.getId());
            payload.put("station", train.getStation().getName());
            payload.put("direction", train.getDirection());
            payload.put("cooldown", train.getCooldown());
            payload.put("compartments", train.getCompartmentList().stream().map(Compartment::getId));

            return payload;
        };
    }

//    @PostMapping("/move")
//    public void moveSimulator() {
//        simulator.move();
//    }
//
//    @GetMapping("/trains")
//    public ArrayList<Long> getTrains() {
//        ArrayList<Long> res = new ArrayList<>();
//        for (Train train : simulator.getTrains()) {
//            res.add(train.getId());
//        }
//        return res;
//    }
//
//    @GetMapping("/trains/{id}")
//    public HashMap<String, Long> getTrainDetails(@PathVariable("id") int id) {
//        Train queryTrain = null;
//        HashMap<String, Long> queryResult = new HashMap<>();
//        for (Train train : simulator.getTrains()) {
//            if (id == train.getId()) {
//                queryTrain = train;
//            }
//        }
//        queryResult.put("Id", queryTrain.getId());
////        queryResult.put("Zapelnienie przedzialow", queryTrain.getFillPercentage());
////        queryResult.put("Ilosc pasazerow", queryTrain.getTotalPassengers());
//        return queryResult;
//    }
//
////    @GetMapping("/trains/{id}/{compartment}")
////    public Compartment getCompartmentDetails(@PathVariable("id") int id, @PathVariable("compartment") int compartment) {
////        Train queryTrain = null;
////        for (Train train : simulator.getTrains()) {
////            if (id == train.getId()) {
////                queryTrain = train;
////            }
////        }
////        return queryTrain.getCompartments().get(compartment);
////    }
}
