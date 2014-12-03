package edu.neumont.csc280.lab7.item;

import edu.neumont.csc280.lab7.web.ValidationResult;

public class ModelItemForm {

    private Auction item;
    private ValidationResult validationResult;

    public ModelItemForm() {
        validationResult = new ValidationResult();
    }

    public ModelItemForm(Auction item) {
        this();
        this.item = item;
    }

    public Auction getItem() {
        return item;
    }

    public void setItem(Auction item) {
        this.item = item;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }

    public void addValidationResult(ValidationResult validationResult) {
        this.validationResult.combine(validationResult);
    }
}
