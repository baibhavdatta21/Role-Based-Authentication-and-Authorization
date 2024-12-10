package demo.example.t4_spb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.example.t4_spb.model.RoleModel;
import demo.example.t4_spb.model.UserModel;
import demo.example.t4_spb.repository.RoleRepository;
import demo.example.t4_spb.repository.UserRepository;
import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@PostConstruct
    public void initData() {
        RoleModel roleModel1=new RoleModel();
        roleModel1.setRolename("Bidder");
        RoleModel roleModel2=new RoleModel();
        roleModel2.setRolename("Approver");
        
        roleRepository.save(roleModel1);
        roleRepository.save(roleModel2);
        
        RoleModel roleBidder = roleRepository.findById(1).orElseThrow(() -> new RuntimeException("Role not found"));
        RoleModel roleApprover = roleRepository.findById(2).orElseThrow(() -> new RuntimeException("Role not found"));
        
        List<RoleModel> roles=new ArrayList<>();//List made to add the roles

        roles.add(roleBidder);
        roles.add(roleApprover);
        
        UserModel userModel1=new UserModel();
        UserModel userModel2=new UserModel();
        UserModel userModel3=new UserModel();
        
        userModel1.setUsername("bidder1");
        userModel1.setCompanyName("companyOne");
        userModel1.setPassword("$2a$12$1Mp13KO7ljA4tiGpkIAY8OZrv4xCGSUhQe/F8gJN65BkIpcdEGMw2");
        userModel1.setEmail("bidderemail@gmail.com");
        userModel1.setRoleModel(roles);
        List<RoleModel> roles1=new ArrayList<>();
        List<RoleModel> roles2=new ArrayList<>();
        roles1.add(roleBidder);
        roles2.add(roleApprover);
          
        userModel2.setUsername("bidder2");
        userModel2.setCompanyName("companyTwo");
        userModel2.setPassword("bidder789$");
        userModel2.setEmail("bidderemail2@gmail.com");
        userModel2.setRoleModel(roles1);
        
        userModel3.setUsername("approver");
        userModel3.setCompanyName("defaultCompany");
        userModel3.setPassword("approver123$");
        userModel3.setEmail("approveremail@gmail.com");
        userModel3.setRoleModel(roles2);
        
        userRepository.save(userModel1);
        userRepository.save(userModel2);
        userRepository.save(userModel3);
        
    }
}
