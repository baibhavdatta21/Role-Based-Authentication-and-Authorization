package demo.example.t4_spb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Bidding_Model")
public class BiddingModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="biddingId")
	private Integer biddingId;
	@Column(name="projectName")
	private String 	projectName="Metro Phase V 2024";
	@Column(name="bidAmount")
	private Double  bidAmount;
	@Column(name="dateOfBidding")
	private String  dateOfBidding;
	@Column(name="status")
	private String  status="pending";
	@Column(name="bidderId")
	private Integer bidderId;
	
	public BiddingModel() {
		
	}
	
	public BiddingModel(Integer id, Integer biddingId, String projectName, Double bidAmount, String dateOfBidding,
			String status, Integer bidderId) {
		super();
		this.id = id;
		this.biddingId = biddingId;
		this.projectName = projectName;
		this.bidAmount = bidAmount;
		this.dateOfBidding = dateOfBidding;
		this.status = status;
		this.bidderId = bidderId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBiddingId() {
		return biddingId;
	}
	public void setBiddingId(Integer biddingId) {
		this.biddingId = biddingId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Double getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(Double bidAmount) {
		this.bidAmount = bidAmount;
	}
	public String getDateOfBidding() {
		return dateOfBidding;
	}
	public void setDateOfBidding(String dateOfBidding) {
		this.dateOfBidding = dateOfBidding;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getBidderId() {
		return bidderId;
	}
	public void setBidderId(Integer bidderId) {
		this.bidderId = bidderId;
	}
	
}
