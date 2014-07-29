package nic.utility;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nic.utility.db.DbUtils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CommonDAO {

	private static Log log = LogFactory.getLog( CommonDAO.class ) ;
	String query="",errorMsg="";
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	@SuppressWarnings("rawtypes")
	List commonList=new ArrayList();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List showFields(String tablenames, String fieldnames, String conditions) {
		log.info("CommonDAO :: showFields() method Start ::: ");
		try {
			String fields = "", tables = "", criteria = "";
			if (tablenames.equals("") || tablenames == null) {
				commonList.add("Error : Table Name Not Provided");
				log.info("Error : Table Name Not Provided");
				log.info("CommonDAO :: showFields() method End ::: ");
				return commonList;
			} else {
				tables = tablenames;
			}

			if (fieldnames.equals("") || fieldnames == null) {
				fields = "*";
			} else {
				fields = fieldnames;
			}
			log.info("Tables : " + tables);
			log.info("Fields : " + fields);
			log.info("Crieteria : " + conditions);
			if (conditions.equals("") || conditions == null) {
				criteria = "";
				query = "select " + fields + " from " + tables + "";
			} else {
				criteria = conditions;
				query = "select " + fields + " from " + tables + " where " + criteria + "";
			}
			log.info("Query : " + query);
			commonList = qrun.query(query, new MapListHandler());
			log.info("CommonList Size : " + commonList.size());
			log.info("CommonDAO :: showFields() method End ::: ");
			return commonList;
		} catch (Exception e) {
			commonList.add("Error : " + e.getMessage());
			log.error(e.getMessage());
			log.info("CommonDAO :: showFields() method End ::: ");
			e.printStackTrace();
			return commonList;
		}
	}
	
	@SuppressWarnings("unchecked")
	public synchronized int getautoGenerateID(String tablename, String fieldname) {
		log.info("CommonDAO :: getautoGenerateID() method Start ::: ");
		try {
			String field = "", table = "";
			int returnresposne = 0;
			table = tablename;
			field = fieldname;
			System.out.println("Table : " + table);
			System.out.println("Field : " + field);

			query = "select nextnum from nextid where tablename = '" + table + "' and fieldname = '" + field + "'";
			System.out.println("Query : " + query);
			returnresposne = (Integer) qrun.query(query, new ScalarHandler());
			System.out.println("Return Resposne getID: " + returnresposne);
			log.info("CommonDAO :: autoGenerateID() method End ::: ");
			return returnresposne;
		} catch (Exception e) {
			commonList.add("Error : " + e.getMessage());
			log.info(e.getMessage());
			log.info("CommonDAO :: getautoGenerateID() method End ::: ");
			e.printStackTrace();
			return 0;
		}
	}
	
	@SuppressWarnings("unused")
	public synchronized int setautoGenerateID(String tablename, String fieldname, int nextautoid) {
		log.info("CommonDAO :: setautoGenerateID() method End ::: ");
		try {
			String field = "", table = "", returnresposne = "";
			long nextautonum = 0;
			table = tablename;
			field = fieldname;
			nextautonum = nextautoid;
			System.out.println("Table : " + table);
			System.out.println("Field : " + field);

			query = "UPDATE nextid SET nextnum = " + nextautonum + " WHERE tablename = '" + table + "' and fieldname = '" + field + "'";
			System.out.println("Query : " + query);
			int affecedrow = qrun.update(query);
			System.out.println("Affecedrow  " + affecedrow);

			if (affecedrow == 1) {
				log.info("CommonDAO :: setautoGenerateID() method End ::: ");
				return 1;
			} else {
				log.info("CommonDAO :: setautoGenerateID() method End ::: ");
				return 0;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			log.info("CommonDAO :: setautoGenerateID() method End ::: ");
			e.printStackTrace();
			return 0;
		}
	}
	
	public static int getCdlId(String custid) throws SQLException {
		log.info("CommonDAO :: getCdlId() method Start ::: ");
		String query = "select cdlgroup_id from customer where customer_id = ?";
		QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
		Object[] cdlid = qrun.query(query, new ArrayHandler(), custid);
		if (cdlid != null) {
			log.info("CommonDAO :: getCdlId() method End ::: ");
			return Integer.parseInt(cdlid[0].toString());
		} else {
			log.info("CommonDAO :: getCdlId() method End ::: ");
			return Integer.parseInt(custid);
		}
	}
	
	public static int insertValueInStockTransaction(String stockId, String productId, String locationId, String transType, int locationQty, String transDate, String lot, String reason, String expiryDate, String userId, String lotId) {
		log.info("CommonDAO :: insertValueInStockTransaction() method Start ::: ");
		QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
		int affectedRow = 0;
		log.info("****************** STOCK TRANSACTION ******************");
		log.info("STOCKID :: "+stockId+" PRODUCTID :: "+productId+" LOCATIONID :: "+locationId+" TRANSTYPE :: "+transType+" QUANTITY :: "+locationQty);
		log.info("TRANSDATE :: "+transDate+" LOT :: "+lot+" REASON :: "+reason+" EXPDATE :: "+expiryDate+" USERID :: "+userId+" LOTID :: "+ lotId);
		String query = "INSERT INTO stocktransaction (stock_id, product_id, location_id, trans_type, quantity, trans_date, lot, reason, exp_date, userid, lot_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			if(locationQty > 0){
				affectedRow = qrun.update(query, stockId, productId, locationId, transType, locationQty, transDate, lot, reason, expiryDate, userId, lotId);
				log.info("Affected Row :: " + affectedRow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		log.info("*******************************************************");
		log.info("CommonDAO :: insertValueInStockTransaction() method End ::: ");
		return affectedRow;
	}
	
	public static String getUserSpecificDepartments(int user_id){
		log.info("CommonDAO :: getUserSpecificDepartments() method Start ::: ");
		String specificDeparment;
		try {
			QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
			specificDeparment = (String) qrun.query("select departments from user_login where userid= ? ",new ScalarHandler(),user_id);
		}catch(SQLException e) {
			e.printStackTrace();
			log.error("CommonDAO :: getUserSpecificDepartments() method End ::: "+e.getMessage());
			return "error";
		}
		log.info("CommonDAO :: getUserSpecificDepartments() method End ::: ");
		return specificDeparment;
	}
	
	public static void main(String args[])
	{
		System.out.println("Common Main Start...");
		//CommonDAO cm = new CommonDAO();
	//	List resultlist = cm.showFields("warehouse_master", "warehouse_id,warehouse_code", "warehouse_code='SDCP'");
		//System.out.println("Result Size : "+resultlist.size());
		//long returnstr = cm.getautoGenerateID("preorder_placement", "preorder_id");
		//System.out.println("Return Str : "+returnstr);
		//returnstr = cm.setautoGenerateID("preorder_placement", "preorder_id",21);
		//System.out.println("Return Str : "+returnstr);
		System.out.println("Common Main End...");
	}
}
