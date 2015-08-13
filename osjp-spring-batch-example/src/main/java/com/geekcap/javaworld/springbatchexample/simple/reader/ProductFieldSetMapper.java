package com.geekcap.javaworld.springbatchexample.simple.reader;

import com.geekcap.javaworld.springbatchexample.simple.model.Product;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * Builds a Product from a row in the CSV file (as a set of fields)
 */
public class ProductFieldSetMapper implements FieldSetMapper<Product>
{
    @Override
    public Product mapFieldSet(FieldSet fieldSet) throws BindException {
        Product product = new Product();
        product.setId( fieldSet.readInt( "id" ) );
        product.setName( fieldSet.readString( "name" ) );
        product.setDescription( fieldSet.readString( "description" ) );
        product.setQuantity( fieldSet.readInt( "quantity" ) );
        return product;
    }
}
