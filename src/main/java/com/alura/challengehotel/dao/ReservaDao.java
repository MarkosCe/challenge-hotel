package com.alura.challengehotel.dao;

import com.alura.challengehotel.model.Reserva;

import java.sql.*;

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
                        System.out.println(resultSet.getInt(1));
                    } else {
                        System.out.println("No hay llave generada");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
