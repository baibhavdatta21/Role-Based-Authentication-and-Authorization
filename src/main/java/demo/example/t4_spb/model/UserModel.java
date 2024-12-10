package demo.example.t4_spb.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="User_Model")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="username")
	private String username;
	@Column(name="companyName")
	private String companyName;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="dummy", joinColumns = @JoinColumn(name = "role"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<RoleModel> roleModel=new ArrayList<>();
	
	

	public UserModel(Integer id, String username, String companyName, String email, String password,
			List<RoleModel> roleModel) {
		super();
		this.id = id;
		this.username = username;
		this.companyName = companyName;
		this.email = email;
		this.password = password;
		this.roleModel = roleModel;
	}



	public List<RoleModel> getRoleModel() {
		return roleModel;
	}



	public void setRoleModel(List<RoleModel> roleModel) {
		this.roleModel = roleModel;
	}



	public UserModel() {
		
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
