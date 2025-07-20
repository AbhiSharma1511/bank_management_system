package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.entity.Loan;
import com.utils.DBUtil;

public class LoanDAOImpl implements LoanDAO {


    @Override
    public List<Loan> getAllLoans() {
        List<Loan> list = new ArrayList<>();
        String sql = "SELECT * FROM loans";
        try (Connection con = DBUtil.getConnection();PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Loan loan = new Loan();
                loan.setLoanId(rs.getInt("loanId"));
                loan.setCustomerId(rs.getInt("customerId"));
                loan.setCustomerName(rs.getString("customerName"));
                loan.setLoanAmount(rs.getDouble("loanAmount"));
                loan.setStatus(rs.getString("status"));
                loan.setCreatedAt(rs.getTimestamp("createdAt"));
                loan.setUpdatedAt(rs.getTimestamp("updatedAt"));
                list.add(loan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean applyLoan(Loan loan) {
        String sql = "INSERT INTO loans (loanId, customerId, customerName, loanAmount, status, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            ps.setInt(1, generateLoanId());
            ps.setInt(2, loan.getCustomerId());
            ps.setString(3, loan.getCustomerName());
            ps.setDouble(4, loan.getLoanAmount());
            ps.setString(5, "Pending");
            ps.setTimestamp(6, now); // created_at
            ps.setTimestamp(7, now); // updated_at
            if(ps.executeUpdate()>0) {
            	ps.close();
            	con.close();
            	return true;
            } else {
				throw new Exception();
			}

        } catch (Exception e) {
        	System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateLoanStatus(int loanId, String status) {

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE loans SET status = ? WHERE loanId = ?")) {
            ps.setString(1, status);
            ps.setInt(2, loanId);
            if( ps.executeUpdate() > 0) {
            	ps.close();
            	con.close();
            	return true;
            }
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Loan> getLoansByCustomerId(int customerId) {
        List<Loan> list = new ArrayList<>();
        String sql = "SELECT * FROM loans WHERE customerId = ?";
        try (Connection con = DBUtil.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Loan loan = new Loan();
                    loan.setLoanId(rs.getInt("loanId"));
                    loan.setCustomerId(rs.getInt("customerId"));
                    loan.setCustomerName(rs.getString("customerIame"));
                    loan.setLoanAmount(rs.getDouble("amount"));
                    loan.setStatus(rs.getString("status"));
                    loan.setCreatedAt(rs.getTimestamp("createdAt"));
                    loan.setUpdatedAt(rs.getTimestamp("updatedAt"));
                    list.add(loan);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private int generateLoanId() {
	    Random rand = new Random();
	    int id = 10000 + rand.nextInt(9000);
//	    do {
//	        id = 1000 + rand.nextInt(9000); // Generates 10000–99999
//	    } while (isCustomerExists(id));    // Retry until unique
	    return id;
	}

    @Override
    public Loan getLoansById(int loanId) {
        Loan loan = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM loans WHERE loanId = ?")) {

            ps.setInt(1, loanId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                loan = new Loan();
                loan.setLoanId(rs.getInt("loanId"));
                loan.setCustomerId(rs.getInt("customerId"));
                loan.setCustomerName(rs.getString("customerName"));
                loan.setLoanAmount(rs.getDouble("loanAmount"));
                loan.setStatus(rs.getString("status"));
                loan.setCreatedAt(rs.getTimestamp("createdAt")); // Or rs.getDate(), based on your column type
            }
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return loan;
    }

}
