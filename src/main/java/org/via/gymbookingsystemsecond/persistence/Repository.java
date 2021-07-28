package org.via.gymbookingsystemsecond.persistence;

import java.util.List;

@org.springframework.stereotype.Repository
public interface Repository<T> {

	T add(T entity);

	List<T> findAll();

	T update(T entity);

	void remove(T entity);
}
