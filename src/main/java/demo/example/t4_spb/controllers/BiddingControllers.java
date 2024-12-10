package demo.example.t4_spb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import demo.example.t4_spb.model.BiddingModel;
import demo.example.t4_spb.model.UserModel;
import demo.example.t4_spb.repository.BiddingRepository;
import demo.example.t4_spb.security.Jwtutil;
import demo.example.t4_spb.service.BidderService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("bidding")
public class BiddingControllers {
	@Autowired
	Jwtutil jwtUtil;
	@Autowired
	BidderService bidderService;
	@Autowired
	BiddingRepository bidrep;
	@PostMapping("/add")
	public ResponseEntity<BiddingModel> check(@RequestBody BiddingModel b, HttpServletRequest request) {
		System.out.println("Inside biddding controller");
//		System.out.println("inside postmaping /add");
		String auth=request.getHeader("Authorization");
		String token=auth.substring(7);
		String username=jwtUtil.extractUserName(token);
//		System.out.println(username);
//		System.out.println("in bidding controller");
		return ResponseEntity.status(HttpStatus.OK).body(bidderService.check(username,b));
		
	}
	@PostMapping("/update/{id}")
	public ResponseEntity<BiddingModel> check2(@PathVariable String id,@RequestBody BiddingModel b){
		System.out.println("Here!");
		return ResponseEntity.status(HttpStatus.OK).body(bidderService.check2(id,b));
		
	}
	@GetMapping("/delete/{id}")
	public String check2(@PathVariable Integer id) {
		bidrep.deleteById(id);
		return "Deleted";
	}
	
	
}
