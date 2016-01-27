package com.amacodecode.services;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacodecode.entities.UrlDescriptor;
import com.amacodecode.util.Base62;

@Service
@Transactional
public class ShortenerService implements ShortenerInterface{
	
	@PersistenceContext(type =PersistenceContextType.EXTENDED)
	EntityManager entityManager;
	
	@Override
	public String convertIntbaseTenToBaseSixTwo(int baseTenNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFullUrlFromUsingString(String base) {
		// TODO Auto-generated method stub
		String s =base.substring(base.length()-1);
		System.out.print(s);
		int id =Base62.toBase10(s);
		UrlDescriptor ur =entityManager.find(UrlDescriptor.class, id);
		return ur.getRealUrl();
	}

	@Override
	public int persistUrl(String url) {
		// TODO Auto-generated method stub
		UrlDescriptor u = new UrlDescriptor();
		u.setCreatedDate(new Date());
		u.setRealUrl(url);
		
		entityManager.persist(u);
		entityManager.flush();
		return u.getId();
	}

}
