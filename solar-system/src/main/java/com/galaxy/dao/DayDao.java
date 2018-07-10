package com.galaxy.dao;

import com.galaxy.model.Day;

//interface for dayDao, adding getByDay
public interface DayDao extends Dao<Day> {

    public Day getByDay(int day);
}
