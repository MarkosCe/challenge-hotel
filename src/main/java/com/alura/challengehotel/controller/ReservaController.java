package com.alura.challengehotel.controller;

import com.alura.challengehotel.dao.ReservaDao;
import com.alura.challengehotel.factory.ConnectionFactory;
import com.alura.challengehotel.model.Huesped;
import com.alura.challengehotel.model.Reserva;

import java.util.Map;

public class ReservaController {

    private ReservaDao reservaDao;
    public ReservaController() {
        this.reservaDao = new ReservaDao(new ConnectionFactory().getConnection());
    }

    public void insert(Reserva reserva){
        reservaDao.insert(reserva);
    }

    public Map<Reserva, Huesped> find(String param){
        return reservaDao.find(param);
    }
}
