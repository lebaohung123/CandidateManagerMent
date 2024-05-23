package app.repository;

import app.model.Candidate;

import java.util.List;

public interface ICandidateRepository {
    boolean checkConnection();
    void addNew(Candidate candidate);
    List<Candidate> getAll();
    Candidate getById(String id);
    void updateById(Candidate candidate);
    StringBuffer getFullName();
    void getNewList();
}
