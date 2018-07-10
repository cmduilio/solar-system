package com.galaxy.service;

import java.util.List;

import com.galaxy.dao.DayDao;
import com.galaxy.dao.DayDaoImpl;
import com.galaxy.model.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

//implementation of dayService interface using dayDao
@Service
public class DayServiceImpl implements DayService{
	
	@Autowired
	private DayDao dayDao;

	public void setDayDao(DayDaoImpl DayDao) {
		this.dayDao = DayDao;
	}

	@Transactional
	public void add(Day p) {
		this.dayDao.add(p);
	}

	@Transactional
	public void update(Day p) {
		this.dayDao.update(p);
	}

	@Transactional
	public List<Day> getList() {
		return this.dayDao.getList();
	}

	@Transactional
	public Day getById(Long id) {
		return this.dayDao.getById(id);
	}

	@Transactional
	public void remove(int id) {
		this.dayDao.remove(id);
	}

	@Transactional
	public Day getByDay(int day) {
		return this.dayDao.getByDay(day);
	}
}
