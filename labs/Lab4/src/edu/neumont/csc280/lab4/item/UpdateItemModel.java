package edu.neumont.csc280.lab4.item;


/**
 * Created by blakerollins on 10/29/14.
 */
public class UpdateItemModel {

    public AuctionItem item;
    public ValidationResult validationResult;

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

    public void addValidationResult(ValidationResult result) {
        this.validationResult.combine(result);
    }
}


