
package com.cissst.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cissst.dao.IMaintainDao;
import com.cissst.entity.Maintain;
import com.cissst.util.DBUtil;


	public class MaintainDaoImpl implements IMaintainDao{
		public List<Maintain> getAllMaintain() {
			
			String sql = "select id,thing,status,homesnumber,date_format(sdate,'%Y-%m-%d') sdate, date_format(rdate," +
					"'%Y-%m-%d') rdate,tcost,scost,maintainer,smemo from maintain order by sdate";
			Connection conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			List<Maintain> list = new ArrayList<Maintain>();
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next()){
					Maintain a = new Maintain();
					a.setId(rs.getInt("id"));
					a.setThing(rs.getString("thing"));
					a.setStatus(rs.getString("status"));
					a.setHomesnumber(rs.getString("homesnumber"));
					a.setSdate(rs.getString("sdate"));
					a.setRdate(rs.getString("rdate"));
					a.setTcost(rs.getDouble("tcost"));
					a.setScost(rs.getDouble("scost"));
					a.setMaintainer(rs.getString("maintainer"));
					a.setSmemo(rs.getString("smemo"));
					list.add(a);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.close(rs);
				DBUtil.close(stmt);
				DBUtil.close(conn);
			}
			return list;
		}
		
		
		
		
		
		public void save(Maintain a) {
			
			String sql = "insert into Maintain(THING,STATUS,HOMESNUMBER,SDATE,RDATE,TCOST,SCOST,MAINTAINER,SMEMO) " +
								"values(?,?,?,str_to_date(?,'%Y-%m-%d'),str_to_date(?,'%Y-%m-%d'),?,?,?,?)";
			Connection conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, a.getThing());
				stmt.setString(2, a.getStatus());
				stmt.setString(3, a.getHomesnumber());
				stmt.setString(4,  a.getSdate());
				stmt.setString(5, a.getRdate());
				stmt.setDouble(6, (Double) a.getTcost());
				stmt.setDouble(7, (Double) a.getScost());
				stmt.setString(8, a.getMaintainer());
				stmt.setString(9, a.getSmemo());
				stmt.executeUpdate();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				DBUtil.close(stmt);
				DBUtil.close(conn);
			}
		}
		
		
		
		
		public Maintain getMaintainById(String id) {
			
			String sql = "select id,thing,status,homesnumber,date_format(sdate,'%Y-%m-%d') sdate,date_format(rdate,'%Y-%m-%d') rdate,tcost,scost,maintainer,smemo from Maintain where id = ?";
			Connection conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			Maintain a = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				rs = stmt.executeQuery();
				while(rs.next()){
					a = new Maintain();
					a.setId(rs.getInt("id"));
					a.setThing(rs.getString("thing"));
					a.setStatus(rs.getString("status"));
					a.setHomesnumber(rs.getString("homesnumber"));
					a.setSdate(rs.getString("sdate"));
					a.setRdate(rs.getString("rdate"));
					a.setTcost(rs.getDouble("tcost"));
					a.setScost(rs.getDouble("scost"));
					a.setMaintainer(rs.getString("maintainer"));
					a.setSmemo(rs.getString("smemo"));			}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.close(rs);
				DBUtil.close(stmt);
				DBUtil.close(conn);
			}
			return a;
		}
		
		
		
		
		public void update(Maintain a) {
			String sql = " update maintain  set  THING=?,STATUS=?,HOMESNUMBER=?,SDATE=str_to_date(?,'%Y-%m-%d'),RDATE=str_to_date(?,'%Y-%m-%d'),TCOST=?,SCOST=?,MAINTAINER=?,SMEMO=? " +
					"where id = ? ";
			
			Connection conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			
			try {
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, a.getThing());
				stmt.setString(2, a.getStatus());
				stmt.setString(3, a.getHomesnumber());
				stmt.setString(4,  a.getSdate());
				stmt.setString(5, a.getRdate());
				stmt.setDouble(6, (Double) a.getTcost());
				stmt.setDouble(7, (Double) a.getScost());
				stmt.setString(8, a.getMaintainer());
				stmt.setString(9, a.getSmemo());
				stmt.setInt(10, a.getId());
			    stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(stmt);
				DBUtil.close(conn);
			}
		}
		
		
		
		public void delete(String id) {
			String sql ="delete  from Maintain where id= ?";
			Connection conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,id);
				stmt.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(stmt);
				DBUtil.close(conn);
			}
		}





		public List<Maintain> getMaintainByMaintainer(String maintainer) {
			String sql = "select id,thing,status,homesnumber,date_format(sdate,'%Y-%m-%d') sdate,date_format(rdate,'%Y-%m-%d') rdate,tcost,scost,maintainer,smemo from Maintain where maintainer = ? ";
			Connection conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			List<Maintain> list = new ArrayList<Maintain>();
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, maintainer);
				rs = stmt.executeQuery();
				while(rs.next()){
					Maintain a = new Maintain();
					a.setId(rs.getInt("id"));
					a.setThing(rs.getString("thing"));
					a.setStatus(rs.getString("status"));
					a.setHomesnumber(rs.getString("homesnumber"));
					a.setSdate(rs.getString("sdate"));
					a.setRdate(rs.getString("rdate"));
					a.setTcost(rs.getDouble("tcost"));
					a.setScost(rs.getDouble("scost"));
					a.setMaintainer(rs.getString("maintainer"));
					a.setSmemo(rs.getString("smemo"));
					list.add(a);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.close(rs);
				DBUtil.close(stmt);
				DBUtil.close(conn);
			}
			return list;
		}



	}


