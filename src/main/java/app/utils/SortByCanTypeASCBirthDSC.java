package app.utils;

import app.model.Candidate;

import java.util.Comparator;

public class SortByCanTypeASCBirthDSC implements Comparator<Candidate> {
    public SortByCanTypeASCBirthDSC() {
    }
    @Override
    public int compare(Candidate o1, Candidate o2) {
        int candidateTypeCompare = o1.getCandidateType().compareTo(o2.getCandidateType());
        int candidateCompare = o1.getBirthDay().compareTo(o2.getBirthDay());
        return (candidateTypeCompare == 0) ? candidateCompare : candidateTypeCompare;
    }
}
