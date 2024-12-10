package demo.example.t4_spb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.example.t4_spb.model.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel,Integer>{

}
