package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.entity.Loan;
import com.utils.DBUtil;

public class LoanDAOImpl implements LoanDAO {

    private Connection conn;

    public LoanDAOImpl() {
        try {
			conn = DBUtil.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Replace with your DB utility
    }

    @Override
    public List<Loan> getAllLoans() {
        List<Loan> list = new ArrayList<>();
        String sql = "SELECT * FROM loans";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Loan loan = new Loan();
                loan.setLoanId(rs.getInt("loan_id"));
                loan.setCustomerId(rs.getInt("customer_id"));
                loan.setCustomerName(rs.getString("customer_name"));
                loan.setLoanAmount(rs.getDouble("amount"));
                loan.setStatus(rs.getString("status"));
                list.add(loan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean applyLoan(Loan loan) {
        String sql = "INSERT INTO loans (customer_id, customer_name, amount, status) VALUES (?, ?, ?, 'Pending')";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, loan.getCustomerId());
            ps.setString(2, loan.getCustomerName());
            ps.setDouble(3, loan.getLoanAmount());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateLoanStatus(int loanId, String status) {
        String sql = "UPDATE loans SET status = ? WHERE loan_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, loanId);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Loan> getLoansByCustomerId(int customerId) {
        List<Loan> list = new ArrayList<>();
        String sql = "SELECT * FROM loans WHERE customer_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Loan loan = new Loan();
                    loan.setLoanId(rs.getInt("loan_id"));
                    loan.setCustomerId(rs.getInt("customer_id"));
                    loan.setCustomerName(rs.getString("customer_name"));
                    loan.setLoanAmount(rs.getDouble("amount"));
                    loan.setStatus(rs.getString("status"));
                    list.add(loan);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

