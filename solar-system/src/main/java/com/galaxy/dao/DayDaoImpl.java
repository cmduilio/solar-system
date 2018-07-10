package com.galaxy.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.galaxy.model.Day;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//implementation of daydao interface using entityManager
@Repository
public class DayDaoImpl implements DayDao {

	private static final Logger logger = LoggerFactory.getLogger(DayDaoImpl.class);

	@Autowired
//	@PersistenceContext
	private EntityManager entityManager;

	public void add(Day p) {
		entityManager.persist(p);
		logger.info("Day saved successfully, Day Details=" + p);
	}

	public void update(Day p) {
		entityManager.merge(p);
		logger.info("Day updated successfully, Day Details=" + p);
	}

	@SuppressWarnings("unchecked")
	public List<Day> getList() {
		List<Day> DaysList = entityManager.createQuery("from Day").getResultList();
		for (Day p : DaysList) {
			logger.info("Day List::" + p);
		}
		return DaysList;
	}

	public Day getById(Long id) {
		Day p = (Day) entityManager.find(Day.class, id);
		logger.info("Day loaded successfully, Day details=" + p);
		return p;
	}

	public void remove(int id) {
		Day p = (Day) entityManager.find(Day.class, new Integer(id));
		if (null != p) {
			entityManager.remove(p);
		}
		logger.info("Day deleted successfully, Day details=" + p);
	}

	public Day getByDay(int day) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Day> criteria = cb.createQuery(Day.class);
		Root<Day> root = criteria.from(Day.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(root.get("day"), day));
		criteria.where(predicates.toArray(new Predicate[predicates.size()])).distinct(true);

		Day Day = (Day) entityManager.createQuery(criteria).getSingleResult();
		logger.info("Day ::" + Day);
		return Day;
	}
}
