package com.vimebedwars.game.mysql;

import com.vimebedwars.game.object.player.PlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.Color;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class MySQL {

    private final String url = "jdbc:mysql://localhost:3306/bw";
    private final String username = "root";
    private final String password = "";

    public void saveStats(PlayerInfo info) {
        try {


                Connection connection = getConnection();

                if (!connection.isClosed()) {
                    Statement statement = connection.createStatement();

                    String name = info.getName();
                    int kills = info.getKills();
                    int deaths = info.getDeaths();
                    int blockBreaks = info.getBlockBreaks();
                    String team = info.getTeam().getColor() == Color.RED ? "red" : "blue";

                    String query = String.format("UPDATE `stats` SET `username`='%s',`kills`='%d',`deaths`='%d',`blockbreak`='%d',`team`='%s' WHERE `username`='%s'",name,kills,deaths,blockBreaks,team,name);

                    statement.executeUpdate(query);
                    statement.close();
                    connection.close();

                    Bukkit.getLogger().log(Level.SEVERE,"Player is save in database");
                }else {
                    connection.close();
                }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }
}
