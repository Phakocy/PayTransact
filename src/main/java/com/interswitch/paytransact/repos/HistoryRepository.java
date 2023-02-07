package com.interswitch.paytransact.repos;

import com.interswitch.paytransact.entities.History;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Id> {
    List<History> findHistoriesByAccount_Id(Integer id);
}
