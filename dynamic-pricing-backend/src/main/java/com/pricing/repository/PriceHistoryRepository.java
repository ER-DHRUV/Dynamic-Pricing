package com.pricing.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pricing.model.PriceHistory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PriceHistoryRepository
        extends JpaRepository<PriceHistory, Long> {

    List<PriceHistory> findByProductId(Long productId);
}
