package demo.example.t4_spb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.example.t4_spb.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel,Integer> {

	UserModel findByEmail(String email);

	UserModel findByUsername(String username);

}
