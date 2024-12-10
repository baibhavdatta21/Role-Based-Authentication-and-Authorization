	create table Role_Model(
		id int primary key AUTO_INCREMENT,
		rolename  varchar(50)
	);
	create table User_Model(
		id int primary key AUTO_INCREMENT,
		username varchar(50),
		companyname varchar(50),
		email varchar(50) unique,
		password varchar(50),
		role int ,
		CONSTRAINT fk_bidder
	    FOREIGN KEY (role)
	    REFERENCES Role_Model(id)
	);
	create table Bidding_Model(
	     id int primary key AUTO_INCREMENT,
	     bidddingId int,
	     projectName varchar(50),
	     bidAmount double,
	     yearsToComplete double,
	     dateOfBidding varchar(50),
	     status varchar(50),
	     bidderId int,
	     CONSTRAINT fk_bidder2
	    FOREIGN KEY (bidderId)
	    REFERENCES User_Model(id)
	);
	
