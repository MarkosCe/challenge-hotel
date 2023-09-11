package com.alura.challengehotel.dao;

import com.alura.challengehotel.model.Huesped;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HuespedDao {

    private Connection connection;
    public HuespedDao(Connection connection) {
        this.connection = connection;
    }

    public void save(Huesped huesped){
        try {
            String query = "INSERT INTO HUESPEDES(" +
                    "NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO, ID_RESERVA) " +
                    "VALUES(?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            try(statement) {
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setDate(3, huesped.getFecha_nacimiento());
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(6, huesped.getId_reserva());
                statement.execute();
                ResultSet resultSet = statement.getGeneratedKeys();
                try(resultSet) {
                    if (resultSet.next()){
                        //System.out.println(resultSet.getInt(1));
                        huesped.setId(resultSet.getInt(1));
                    } else {
                        System.out.println("No hay llave generada");
                    }
                }
            }
        }catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, "Ocurrio un error.");
            throw new RuntimeException(sqlException);
        }
    }
}
