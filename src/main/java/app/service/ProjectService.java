package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Project;
import app.exceptions.ProjectIdException;
import app.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	// SAVE/UPDATE
	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} 
		catch (Exception e) {
			throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists." );
		}
	}
	
	// GET (BY ID)
	public Project findProjectByIdentifier (String projectId) {
		
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if (project == null) {
			throw new ProjectIdException("Project ID '" + projectId + "' does NOT exist.");
		}
		return project;
	}
	
	// GET ALL
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	// DELETE
	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if (project == null) {
			throw new ProjectIdException("Cannot delete project with ID '" + projectId + "'.");
		}
		projectRepository.delete(project);
	}
}
