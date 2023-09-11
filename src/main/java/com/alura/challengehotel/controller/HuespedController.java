package com.alura.challengehotel.controller;

import com.alura.challengehotel.dao.HuespedDao;
import com.alura.challengehotel.factory.ConnectionFactory;
import com.alura.challengehotel.model.Huesped;
import com.alura.challengehotel.model.Reserva;

import java.sql.Connection;
import java.util.List;

public class HuespedController {
    private final HuespedDao huespedDao;
    public HuespedController() {
        this.huespedDao = new HuespedDao(new ConnectionFactory().getConnection());
    }
    public void save(Huesped huesped){
        huespedDao.save(huesped);
    }

    public List<Huesped> toList(){
        return huespedDao.toList();
    }
}
