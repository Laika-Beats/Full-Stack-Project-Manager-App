package app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.entity.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

	Project findByProjectIdentifier(String projectId);
}
