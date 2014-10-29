package edu.neumont.csc280.lab4.item;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    private boolean success;
    private List<String> messages;

    public ValidationResult() {
        success = true;
        messages = new ArrayList<>();
    }

    public boolean getSuccess() {
        return  this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void addMessage(String message){
        this.messages.add(message);
    }

    public void combine(ValidationResult other) {

        // Combine the successes
        if (this.success && other.success) {
            // Both are tru, so leave this one as true
        } else {
            // at least on was false, so set this one to false
            this.setSuccess(false);
        }

        // combine the messages
        for(String m : other.messages) {
            addMessage(m);
        }
    }

    public String toJSON() {
        String retVal = "{ \"success\": " + success + ",";
        retVal += " \"messages\": [ ";
        retVal += (success) ? "succeeded" : "failed";
        for(String m : messages) {
            retVal += ", " + m;
        }
        retVal += " ] }";

        return  retVal;
    }
}
