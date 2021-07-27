package com.chaitanya.repository.specs;

import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.chaitanya.domain.entity.Book;
import com.chaitanya.domain.entity.Tag;
import com.chaitanya.domain.model.SearchCriteria;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookSpecification implements Specification<Book> {

	private static final long serialVersionUID = 1L;
	
	private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder builder) { 
        if (!criteria.getOperation().equalsIgnoreCase(":"))
        	return null;
        else if(criteria.getKey().contains("."))
        	return toNestedPredicate(root, query, builder);
        else
        	return createPredicate(root.get(criteria.getKey()), query, builder);
    }
    
    private Predicate toNestedPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder builder) { 
    	String[] params = criteria.getKey().split(Pattern.quote("."));    	
    	switch(params[0]) {
    		case "tags":
    			Join<Book, Tag> join = root.join("tags");
    			return createPredicate(join.<String>get(params[1]), query, builder);
    		default: 
    			return null;
    	}
    }
    
	@SuppressWarnings("unchecked")
	private Predicate createPredicate(Path<?> path, CriteriaQuery<?> query, CriteriaBuilder builder) {
    	if (path.getJavaType() == String.class)
            return builder.like((Expression<String>) path, "%" + criteria.getValue() + "%");
        else 
            return builder.equal(path, criteria.getValue());
    }
    
}