package com.geekcap.javaworld.springbatchexample.simple.processor;

import com.geekcap.javaworld.springbatchexample.simple.model.Product;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Processor that finds existing products and updates a product quantity appropriately
 */
public class ProductItemProcessor implements ItemProcessor<Product,Product>
{
    private static final String GET_PRODUCT = "select * from PRODUCT where id = ?";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Product process(Product product) throws Exception
    {
        // Retrieve the product from the database
        List<Product> productList = jdbcTemplate.query(GET_PRODUCT, new Object[] {product.getId()}, new RowMapper<Product>() {
            @Override
            public Product mapRow( ResultSet resultSet, int rowNum ) throws SQLException {
                Product p = new Product();
                p.setId( resultSet.getInt( 1 ) );
                p.setName( resultSet.getString( 2 ) );
                p.setDescription( resultSet.getString( 3 ) );
                p.setQuantity( resultSet.getInt( 4 ) );
                return p;
            }
        });

        if( productList.size() > 0 )
        {
            // Add the new quantity to the existing quantity
            Product existingProduct = productList.get( 0 );
            product.setQuantity( existingProduct.getQuantity() + product.getQuantity() );
        }

        // Return the (possibly) update prduct
        return product;
    }
}
