package demo.example.t4_spb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.example.t4_spb.model.BiddingModel;

public interface BiddingRepository extends JpaRepository<BiddingModel,Integer>{

	void deleteAllById(Integer id);

}
