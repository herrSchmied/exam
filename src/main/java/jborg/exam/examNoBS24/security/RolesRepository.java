package jborg.exam.examNoBS24.security;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface RolesRepository extends JpaRepository<Roles, String>
{

}
