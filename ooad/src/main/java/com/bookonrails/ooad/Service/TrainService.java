package com.bookonrails.ooad.Service;

import com.bookonrails.ooad.Model.*;
import com.bookonrails.ooad.Repository.RouteRepository;
import com.bookonrails.ooad.Repository.TrainRepository;
import org.springframework.transaction.annotation.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private RouteRepository routeRepository;

    

    // Method to retrieve all trains
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    // Method to retrieve a train by train number
    public Train getTrainByTrainNo(String trainNo) {
        return trainRepository.findByTrainNo(trainNo);
    }

    // Method to save a train
    public Train saveTrain(Train train) {
        return trainRepository.save(train);
    }

    // Method to update a train
    public Train updateTrain(String trainNo, Train updatedTrain) {
        Optional<Train> existingTrainOptional = trainRepository.findById(trainNo);

        if (existingTrainOptional.isPresent()) {
            Train existingTrain = existingTrainOptional.get();
            existingTrain.setTrainName(updatedTrain.getTrainName());
            existingTrain.setTrainType(updatedTrain.getTrainType());

            return trainRepository.save(existingTrain);
        } 
        else{
            return null;
        }
    }

    // Method to delete a train by train number
    public void deleteTrain(String trainNo) {
        trainRepository.deleteById(trainNo);
    }

    @Transactional
    public List<Train> searchTrainBySrcAndDest(String SRC,String DEST){
        List<Train> t = new ArrayList<>();
        List<String> r= routeRepository.findRouteBetweenStation(SRC,DEST );
        for(String s:r){
            Route route= routeRepository.findByRouteCode(s);
            t.add(trainRepository.findByRoute(route));
            System.out.println(route.getRouteCode());

        }
        return t;
    }

    @Transactional
    public List<SeatAvailability> searchTrain(String SRC,String DEST, ClassType classes, Date date){
        List<Train> t=searchTrainBySrcAndDest(SRC,DEST);
        // now we need to filter trains based on if they run on the given date
        // and then filter them based on class type
        List<SeatAvailability> newList= new ArrayList<>();
        for(int i=0;i<t.size();i++){
            if(t.get(i).doesDateAndClassExist(date,classes)){
                newList.add(t.get(i).getSeatAvailabilityBasedOnClassAndDate(date, classes));
            }
        }
        return newList;
    }




}
