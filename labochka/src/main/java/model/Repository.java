package model;

import java.sql.*;
import java.util.ArrayList;

public class Repository {

    Connection connection = null;

    void setConnection() {
        //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
        String url = "jdbc:postgresql://localhost:5432/olga";
        String name = "postgres";
        String password = "123456789";

        ArrayList<String> dataWord = new ArrayList<>();
        try {
            //Загружаем драйвер
            Class.forName("org.postgresql.Driver");
            System.out.println("Драйвер подключен");
            //Создаём соединение
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("Соединение установлено");
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }

    void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error");
            }
        }
    }

    public ArrayList<String> getData(String text, Boolean isStrong) {

        String sqlRequest = "";
        String sqlParam = "";
        ArrayList<String> dataWord = new ArrayList<>();
        setConnection();
        if (isStrong) {
            sqlRequest = "select dict.word, dict.mean from dict where dict.word = ?";
            sqlParam = text;
        } else {
            sqlRequest = "select dict.word, dict.mean from dict where dict.word like ?";
            sqlParam = "%" + text + "%";
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
            //Устанавливаем в нужную позицию значения определённого типа
            preparedStatement.setString(1, sqlParam);
            //выполняем запрос
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                String data = result.getString(1) + " - " + result.getString(2) + "\n";
                dataWord.add(data);
            }
        } catch (Exception ex) {
            ArrayList<String> err = new ArrayList<>();
            err.add("Ошибка!!!");
            return err;
        } finally {
            closeConnection();
        }
        return dataWord;
    }

    public String setData(String word, String mean) {
        setConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO dict(word, mean) VALUES (?, ?)");
            //Устанавливаем в нужную позицию значения определённого типа
            preparedStatement.setString(1, word);
            preparedStatement.setString(2, mean);
            //выполняем запрос
            preparedStatement.executeUpdate();
            return "Данные успешно добавлены!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Произошла ошибка, попробуйте позже!";
        } finally {
            closeConnection();
        }
    }


}
