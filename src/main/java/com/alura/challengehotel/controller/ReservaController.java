package com.alura.challengehotel.controller;

import com.alura.challengehotel.dao.ReservaDao;
import com.alura.challengehotel.factory.ConnectionFactory;
import com.alura.challengehotel.model.Reserva;

public class ReservaController {

    private ReservaDao reservaDao;
    public ReservaController() {
        this.reservaDao = new ReservaDao(new ConnectionFactory().getConnection());
    }

    public void insert(Reserva reserva){
        reservaDao.insert(reserva);
    }

    public void find(String param){
        reservaDao.find(param);
    }
}
