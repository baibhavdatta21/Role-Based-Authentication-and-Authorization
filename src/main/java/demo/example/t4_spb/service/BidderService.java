package demo.example.t4_spb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.example.t4_spb.model.BiddingModel;
import demo.example.t4_spb.model.UserModel;
import demo.example.t4_spb.repository.BiddingRepository;
import demo.example.t4_spb.repository.UserRepository;

@Service
public class BidderService {
	@Autowired
	BiddingRepository bidderRepository;
	@Autowired
	UserRepository userRepository;

	public BiddingModel check(String username,BiddingModel b) {
		UserModel u=userRepository.findByEmail(username);
		b.setBidderId(u.getId());
		bidderRepository.save(b);
		return b;
	}

	public BiddingModel check2(String id, BiddingModel b) {
		Optional<BiddingModel> b2=bidderRepository.findById(Integer.parseInt(id));
		System.out.println(b2.get().getBidderId());
		b2.get().setStatus("approved");
		return b2.get();
	}
	
	
	
}
