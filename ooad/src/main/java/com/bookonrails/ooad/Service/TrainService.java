// package com.bookonrails.ooad.Service;

// import com.bookonrails.ooad.Model.Train;
// import com.bookonrails.ooad.Repository.TrainRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class TrainService {

//     private final TrainRepository trainRepository;

//     @Autowired
//     public TrainService(TrainRepository trainRepository) {
//         this.trainRepository = trainRepository;
//     }

//     public List<Train> getAllTrains() {
//         return trainRepository.findAllTrains();
//     }
// }


// package com.bookonrails.ooad.Service;

// import com.bookonrails.ooad.Model.Train;
// import com.bookonrails.ooad.Repository.TrainRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.Arrays;
// import java.util.List;

// @Service
// public class TrainService {

//     private final TrainRepository trainRepository;

//     @Autowired
//     public TrainService(TrainRepository trainRepository) {
//         this.trainRepository = trainRepository;
//     }

//     public List<Train> getAllTrains() {
//         // Sample trains
//         List<Train> trains = Arrays.asList(
//                 new Train("101", "Express 101", "Express", 200),
//                 new Train("202", "Superfast 202", "Superfast", 150),
//                 new Train("303", "Local 303", "Local", 100),
//                 new Train("404", "Bullet Train 404", "Bullet Train", 300)
//         );

//         // Save sample trains to the database (not necessary for this example)
//         trainRepository.saveAll(trains);

//         return trains;
//     }
// }


// package com.bookonrails.ooad.Service;

// import com.bookonrails.ooad.Model.Train;
// import com.bookonrails.ooad.Repository.TrainRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.ArrayList;
// import java.util.List;

// @Service
// public class TrainService {

//     private final TrainRepository trainRepository;

//     @Autowired
//     public TrainService(TrainRepository trainRepository) {
//         this.trainRepository = trainRepository;
//     }

//     public List<Train> getAllTrains() {
//         // Sample trains
//         List<Train> trains = new ArrayList<>();
//         trains.add(new Train("101", "Express 101", "Express", 200));
//         trains.add(new Train("202", "Superfast 202", "Superfast", 150));
//         trains.add(new Train("303", "Local 303", "Local", 100));
//         trains.add(new Train("404", "Bullet Train 404", "Bullet Train", 300));

//         // Save sample trains to the database (not necessary for this example)
//         trainRepository.saveAll(trains);

//         return trains;
//     }
// }


package com.bookonrails.ooad.Service;

import com.bookonrails.ooad.Model.Train;
import com.bookonrails.ooad.Repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainService {

    private final TrainRepository trainRepository;

    @Autowired
    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    // Method to retrieve all trains
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    // Method to retrieve a train by train number
    public Optional<Train> getTrainByTrainNo(String trainNo) {
        return trainRepository.findById(trainNo);
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
            existingTrain.setTraintype(updatedTrain.getTraintype());
            existingTrain.setTotalSeats(updatedTrain.getTotalSeats());
            // Update other properties as needed

            return trainRepository.save(existingTrain);
        } else {
            // Train with the given trainNo not found
            return null;
        }
    }

    // Method to delete a train by train number
    public void deleteTrain(String trainNo) {
        trainRepository.deleteById(trainNo);
    }
}
