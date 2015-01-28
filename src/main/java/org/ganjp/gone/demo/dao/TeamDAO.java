package org.ganjp.gone.demo.dao;

import java.util.List;

import org.ganjp.gone.demo.model.Team;

public interface TeamDAO {
	
	public void addTeam(Team team);
	public void updateTeam(Team team);
	public Team getTeam(int id);
	public void deleteTeam(int id);
	public List<Team> getTeams();

}