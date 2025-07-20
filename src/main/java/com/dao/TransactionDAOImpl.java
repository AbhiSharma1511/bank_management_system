package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Transaction;
import com.utils.DBUtil;

public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public boolean addTransaction(Transaction t) {
    	String sql = "INSERT INTO transactions(transactionId, senderAccountNo, receiverAccountNo, amount, transactionDate) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, t.getTransactionId());
        	ps.setString(2, t.getSenderAccountNo());
            ps.setString(3, t.getReceiverAccountNo());
            ps.setDouble(4, t.getAmount());
            ps.setDate(5, Date.valueOf(t.getTransactionDate()));
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Transaction> getAllTransactions() {

        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Transaction t = new Transaction();
                t.setTransactionId(rs.getInt("transaction_id"));
                t.setSenderAccountNo(rs.getString("sender_account"));
                t.setReceiverAccountNo(rs.getString("receiver_account"));
                t.setAmount(rs.getDouble("amount"));
                t.setTransactionDate(rs.getDate("transaction_date").toLocalDate());
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Transaction> getTransactionsByAccount(String accountNo) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE senderAccountNo = ? OR receiverAccountNo = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accountNo);
            ps.setString(2, accountNo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction t = new Transaction();
                t.setTransactionId(rs.getInt("transactionId"));
                t.setSenderAccountNo(rs.getString("senderAccountNo"));
                t.setReceiverAccountNo(rs.getString("receiverAccountNo"));
                t.setAmount(rs.getDouble("amount"));
                t.setTransactionDate(rs.getDate("transactionDate").toLocalDate());
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteTransaction(int transactionId) {
        String sql = "DELETE FROM transactions WHERE transactionId = ?";
        try (Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, transactionId);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
