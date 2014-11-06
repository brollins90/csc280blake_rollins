package edu.neumont.csc280.lab4.item;

import edu.neumont.csc280.lab4.ValidationResult;

/**
 * Created by blakerollins on 11/5/14.
 */
public class UpdateItemModel {

    private AuctionItem item;
    private ValidationResult validationResult;

    public UpdateItemModel() {
        validationResult = new ValidationResult();
    }

    public UpdateItemModel(AuctionItem item) {
        this();
        this.item = item;
    }

    public AuctionItem getItem() {
        return item;
    }

    public void setItem(AuctionItem item) {
        this.item = item;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }

    public void addValidationResult(ValidationResult validationResult) {
        this.validationResult.combine(validationResult);
    }
}
