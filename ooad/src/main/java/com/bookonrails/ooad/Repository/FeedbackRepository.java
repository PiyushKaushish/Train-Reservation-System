package com.bookonrails.ooad.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bookonrails.ooad.Model.Feedback;
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{

}
