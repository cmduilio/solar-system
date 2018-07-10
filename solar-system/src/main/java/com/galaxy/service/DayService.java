package com.galaxy.service;

import com.galaxy.model.Day;

//interface for dayService, adding getByDay
public interface DayService extends Service<Day> {

    public Day getByDay(int day);
}
