package com.alura.challengehotel.dao;

import com.alura.challengehotel.model.Huesped;
import com.alura.challengehotel.model.Reserva;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ReservaDao {

    private final Connection con;

    public ReservaDao(Connection con) {
        this.con = con;
    }

    public void insert(Reserva reserva) {
        try {
            String query = "INSERT INTO reservas(fecha_entrada, fecha_salida, valor, forma_pago) " +
                    "values(?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            try(statement) {
                statement.setDate(1, reserva.getFechaEntrada());
                statement.setDate(2, reserva.getFechaSalida());
                statement.setFloat(3, reserva.getValor());
                statement.setString(4, reserva.getFormaPago());
                statement.execute();

                ResultSet resultSet =  statement.getGeneratedKeys();
                try(resultSet) {
                    if (resultSet.next()){
                        reserva.setId(resultSet.getInt(1));
                        //System.out.println(resultSet.getInt(1));
                    } else {
                        System.out.println("No hay llave generada");
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algo salio mal :(");
            throw new RuntimeException(e);
        }
    }

    public Map<Reserva, Huesped> find(String param){
        Map<Reserva, Huesped> datos = new HashMap<>();
        try {
            String query = "SELECT * FROM " +
                    "(HUESPEDES H INNER JOIN RESERVAS R ON H.ID_RESERVA = R.ID) " +
                    "WHERE (H.APELLIDO = ? OR R.ID = ?)";
            PreparedStatement statement = con.prepareStatement(query);
            try(statement) {
                statement.setString(1, param);
                statement.setString(2, param);

                ResultSet resultSet = statement.executeQuery();
                try(resultSet) {
                    while (resultSet.next()) {
                        Reserva reserva = new Reserva(resultSet.getInt("R.ID"),
                                resultSet.getDate("R.FECHA_ENTRADA"),
                                resultSet.getDate("R.FECHA_SALIDA"),
                                resultSet.getFloat("R.VALOR"),
                                resultSet.getString("R.FORMA_PAGO"));
                        Huesped huesped =  new Huesped(resultSet.getInt("H.ID"),
                                resultSet.getString("H.NOMBRE"),
                                resultSet.getString("H.APELLIDO"),
                                resultSet.getDate("H.FECHA_NACIMIENTO"),
                                resultSet.getString("H.NACIONALIDAD"),
                                resultSet.getString("H.TELEFONO"),
                                resultSet.getInt("H.ID_RESERVA"));
                        datos.put(reserva, huesped);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algo salio mal :(");
            throw new RuntimeException(e);
        }
        return datos;
    }
}
