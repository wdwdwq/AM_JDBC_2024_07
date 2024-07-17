package org.example.system;

import org.example.controller.ArticleController;
import org.example.controller.MemberController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public void run() {
        System.out.println("==Article Start==");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("command ) ");
            String cmd = sc.nextLine().trim();

            Connection conn = null;

            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String url = "jdbc:mariadb://127.0.0.1:3306/AM_JDBC_2024_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";

            try {
                conn = DriverManager.getConnection(url, "root", "");

                int actionResult = action(conn, sc, cmd);


                if (actionResult == 1) {
                    System.out.println("==프로그램 종료==");
                    sc.close();
                    break;
                }
            } catch (SQLException e) {
                System.out.println("에러 1 : " + e);
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private int action(Connection conn, Scanner sc, String cmd) {

        if (cmd.equals("exit")) {
            return -1;
        }

        MemberController memberController = new MemberController(sc, conn);
        ArticleController articleController = new ArticleController(conn, sc);
        if(cmd.equals("member login")){
            memberController.login();
        }else if (cmd.equals("member join")) {
            memberController.doJoin();
        } else if (cmd.equals("article write")) {
            articleController.doWrite();
        } else if (cmd.equals("article list")) {
            articleController.showList();
        } else if (cmd.startsWith("article modify")) {
            articleController.doModify(cmd);
        } else if (cmd.startsWith("article detail")) {
            articleController.showDetail(cmd);
        } else if (cmd.startsWith("article delete")) {
            articleController.doDelete(cmd);
        }
        return 0;
    }

}