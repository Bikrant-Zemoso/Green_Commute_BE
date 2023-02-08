package com.bootcamp.greencommute.model.enums;

public enum JobType {
    FULL_TIME, INTERNSHIP, CONTRACT, REMOTE;

    public String getJobType() {
        switch(this) {
            case FULL_TIME:
                return "Full Time";

            case INTERNSHIP:
                return "Internship";

            case CONTRACT:
                return "Contract";

            case REMOTE:
                return "Remote";

            default:
                return null;
        }
    }

    public static JobType getEnum(String jobType){
        switch(jobType.toUpperCase()) {
            case "FULL TIME":
                return JobType.FULL_TIME;

            case "INTERNSHIP":
                return JobType.INTERNSHIP;

            case "CONTRACT":
                return JobType.CONTRACT;

            case "REMOTE":
                return JobType.REMOTE;

            default:
                return null;
        }
    }

}

