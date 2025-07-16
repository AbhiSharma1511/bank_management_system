package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.entity.Transaction;
import com.utils.DBUtil;

public class TransactionDAOImpl implements TransactionDAO {

    private Connection conn;

    public TransactionDAOImpl() {
        try {
			conn = DBUtil.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public boolean addTransaction(Transaction t) {
        String sql = "INSERT INTO transactions(sender_account, receiver_account, amount, transaction_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getSenderAccountNo());
            ps.setString(2, t.getReceiverAccountNo());
            ps.setDouble(3, t.getAmount());
            ps.setDate(4, Date.valueOf(t.getTransactionDate()));
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Transaction t = new Transaction();
                t.setTransactionId(rs.getInt("transaction_id"));
                t.setSenderAccountNo(rs.getString("sender_account"));
                t.setReceiverAccountNo(rs.getString("receiver_account"));
                t.setAmount(rs.getDouble("amount"));
                t.setTransactionDate(rs.getDate("transaction_date").toLocalDate());
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Transaction> getTransactionsByAccount(String accountNo) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE sender_account = ? OR receiver_account = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accountNo);
            ps.setString(2, accountNo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction t = new Transaction();
                t.setTransactionId(rs.getInt("transaction_id"));
                t.setSenderAccountNo(rs.getString("sender_account"));
                t.setReceiverAccountNo(rs.getString("receiver_account"));
                t.setAmount(rs.getDouble("amount"));
                t.setTransactionDate(rs.getDate("transaction_date").toLocalDate());
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteTransaction(int transactionId) {
        String sql = "DELETE FROM transactions WHERE transaction_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, transactionId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
