package com.geekcap.javaworld.springbatchexample.simple.writer;

import com.geekcap.javaworld.springbatchexample.simple.model.Product;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Writes products to a database
 */
public class ProductItemWriter implements ItemWriter<Product>
{
    private static final String GET_PRODUCT = "select * from PRODUCT where id = ?";
    private static final String INSERT_PRODUCT = "insert into PRODUCT (id,name,description,quantity) values (?,?,?,?)";
    private static final String UPDATE_PRODUCT = "update PRODUCT set name = ?, description = ?,quantity = ? where id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void write(List<? extends Product> products) throws Exception
    {
        for( Product product : products )
        {
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
                jdbcTemplate.update( UPDATE_PRODUCT, product.getName(), product.getDescription(), product.getQuantity(), product.getId() );
            }
            else
            {
                jdbcTemplate.update( INSERT_PRODUCT, product.getId(), product.getName(), product.getDescription(), product.getQuantity() );
            }
        }
    }
}
