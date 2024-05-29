package app.utils;

import app.model.Candidate;

import java.util.Comparator;

public class SortByCanTypeASCBirthDSC implements Comparator<Candidate> {
    public SortByCanTypeASCBirthDSC() {
    }
    @Override
    public int compare(Candidate o1, Candidate o2) {
        if (o1.getCandidateType().compareTo(o2.getCandidateType()) != 0)
            return o1.getCandidateType().compareTo(o2.getCandidateType());
        if (o1.getBirthDay() == null)  return -1;
        if (o2.getBirthDay() == null)  return  1;
        return o1.getBirthDay().compareTo(o2.getBirthDay());
    }
}