package com.Finsure.Finsure.repository;

import com.Finsure.Finsure.entity.Contact;
import com.Finsure.Finsure.entity.RequestMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestMoneyRepository extends JpaRepository<RequestMoney , Long> {
//    List<RequestMoney> findByRequestSender_UserIdOrRequestReceiver_UserId(long userId);



    RequestMoney findByRequestMoneyId(Long requestMoneyId);

    List<RequestMoney> findByRequestSender_UserIdOrRequestReceiver_UserId(Long senderId , Long receiveId);
}
