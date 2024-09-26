package com.auutomate.src.backoffice.product.application.update;

import com.auutomate.src.shared.domain.Command;

public class UpdateProductDescriptionCommand implements Command {
    private final String reference;
    private final String description;

    public UpdateProductDescriptionCommand(String reference, String description) {
        this.reference = reference;
        this.description = description;
    }

    public String reference() {
        return reference;
    }

    public String description() {
        return description;
    }
}
