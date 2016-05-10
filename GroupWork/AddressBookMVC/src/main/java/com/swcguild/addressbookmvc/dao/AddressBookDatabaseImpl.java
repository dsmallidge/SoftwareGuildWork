package com.swcguild.addressbookmvc.dao;

import com.swcguild.addressbookmvc.dto.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jake
 */
public class AddressBookDatabaseImpl implements AddressBookable
{

    private static final String SQL_INSERT_ADDRESS
            = "insert into addressbook (first_name, last_name, street, city, state, zip) values(?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_ADDRESS
            = "delete from addressbook where address_id = ?";
    private static final String SQL_SELECT_ADDRESS
            = "select * from addressbook where address_id = ?";
    private static final String SQL_UPDATE_ADDRESS
            = "update addressbook set first_name = ?, last_name = ?, street = ?, city  =  ?, state =  ?, zip = ? where  address_id =  ?";
    private static final String SQL_SELECT_ALL_ADDRESS
            = "select * from addressbook";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getSize()
    {
        return -1;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void addAddress(Address a)
    {
        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                a.getFirstName(),
                a.getLastName(),
                a.getStreet(),
                a.getCity(),
                a.getState(),
                a.getZip());
        a.setId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
    }

    @Override
    public void updateAddress(Address a)
    {
        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                a.getFirstName(),
                a.getLastName(),
                a.getStreet(),
                a.getCity(),
                a.getState(),
                a.getZip(),
                a.getId());
    }

    @Override
    public void removeAddress(int id)
    {
        jdbcTemplate.update(SQL_DELETE_ADDRESS, id);
    }

    @Override
    public List<Address> getList()
    {
        return jdbcTemplate.query(SQL_SELECT_ALL_ADDRESS, new AddressMapper()).stream()
                .sorted((s1,s2) -> s1.getLastName().compareToIgnoreCase(s2.getLastName()))
                .collect(Collectors.toList());
    }


    @Override
    public int getEmptyId()
    {
        return 0;
    }

    @Override
    public Address getAddressById(int id)
    {
        try
        {
            return jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS,
                    new AddressMapper(), id);
        } catch (EmptyResultDataAccessException ex)
        {
            
            return null;
        }
    }
    
    @Override
    public List<Address> getFilteredList(Map<SearchTerm, String> criteria) {
        if (criteria.size() == 0) {
            return getList();
        } else {
            StringBuilder sQuery = new StringBuilder("select * from addressbook where ");
            
            // build the where clause
            int numParams = criteria.size();
            int paramPosition = 0;
            String[] paramVals = new String[numParams];
            
            Set<SearchTerm> keySet = criteria.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();
            while(iter.hasNext()) {
                SearchTerm currentKey = iter.next();
                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }
                
                sQuery.append(currentKey);
                sQuery.append(" = ? ");
                
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }
            
            return jdbcTemplate.query(sQuery.toString(), new AddressMapper(), paramVals);
        }
    }

    private static final class AddressMapper implements RowMapper<Address>
    {

        @Override
        public Address mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            Address address = new Address();
            address.setId(rs.getInt("address_id"));
            address.setFirstName(rs.getString("first_name"));
            address.setLastName(rs.getString("last_name"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city"));
            address.setState(rs.getString("state"));
            address.setZip(rs.getString("zip"));
            return address;
        }
    }
}
