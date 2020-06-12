package com.basicstrong.practice;


import org.h2.tools.Server;

import java.sql.SQLException;

/*
create table TransactionHistory(
        transactionId int,
        accountNumber int,
        name varchar(50),
        transactionType varchar(12),
        amount int);

        jdbc:h2:/home/vagrant/IdeaProjects/Reflection/database/practice1
*/

public class Launcher {
    public static void main(String[] args) throws SQLException {

        Server.main();

    }
}
