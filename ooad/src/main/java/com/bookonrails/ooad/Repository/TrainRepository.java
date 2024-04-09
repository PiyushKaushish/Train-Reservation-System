// package com.bookonrails.ooad.Repository;

// import com.bookonrails.ooad.Model.Train;
// import org.springframework.stereotype.Repository;

// import java.util.Arrays;
// import java.util.List;

// @Repository
// public class TrainRepository {

//     public List<Train> findAllTrains() {
//         // Sample list of train names (replace with actual data from database)
//         return Arrays.asList(
//             new Train(1L, "Express 101"),
//             new Train(2L, "Superfast 202"),
//             new Train(3L, "Local 303"),
//             new Train(4L, "Bullet Train 404")
//         );
//     }
// }

package com.bookonrails.ooad.Repository;

import com.bookonrails.ooad.Model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train, String> {
    // No custom query methods needed for this example
}
