package example2015;
import org.iq80.leveldb.*;
import org.springframework.web.servlet.ModelAndView;

import example2015.VisitObject;
import static org.iq80.leveldb.impl.Iq80DBFactory.*;
import java.io.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
public class LdbOperater {
	private DB db;
	private String dbFileName="abc";
	private String path = "E/data/leveldb";
	
	private String value;
	private String beforeVisit1;
	private int num=0;
	public LdbOperater(){
		
	}
	
	
	public Map<String,String> displayPeople(String username) throws IOException{
		//Open and close the database
		
		Options options = new Options();
		options.createIfMissing(true);
		DB db = factory.open(new File("example"), options);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		try {
			
			//读取访问者姓名
			//value = asString(db.get(bytes("Tampa")));
			
			Map<String,String> beforeVisit = new HashMap<String,String>();
			
			//遍历结果集
			DBIterator iterator = db.iterator();
			try {
				num=1;
			  for(iterator.seekToFirst(); iterator.hasNext(); iterator.next()) {
			    String key = asString(iterator.peekNext().getKey());
			    String value = asString(iterator.peekNext().getValue());
			    beforeVisit.put(key,value);
			    num++;
			  }
			  //按登陆时间重新排序
			  List <Map.Entry<String,String>> infoIds=new ArrayList<Map.Entry<String,String>>(beforeVisit.entrySet());
			    Collections.sort(infoIds,new Comparator<Map.Entry<String,String>>(){
			    	public int compare(Map.Entry<String, String> o1,Map.Entry<String,String> o2){
			    		 return (o1.getValue()).toString().compareTo(o2.getValue());
			}
			    
			    });
			    Map<String,String> beforeVisit1 = new LinkedHashMap<String,String>();
			    for (int i = 0; i < infoIds.size(); i++) {
			    	beforeVisit1.put( infoIds.get(i).getKey(),infoIds.get(i).getValue());
			        System.out.println(infoIds.get(i).getKey()+":"+infoIds.get(i).getValue());
			        }
			  
			//将访问者姓名写入数据库
			db.put(bytes(username), bytes(dateFormat.format( new Date())));
			  return beforeVisit1;
			} finally {
			  // Make sure you close the iterator to avoid resource leaks.
			  iterator.close();
			  
		}
		}
		
		finally {
			db.close();
			
		}
		
		
	}
	
	
	public int getNum() {
		return num;
	}

	public void create() throws IOException{
		
		try{
			Options options = new Options();
			options.createIfMissing(true);
			db = factory.open(new File(path), options);
			
		}catch(IOException ex){
			System.out.println("数据库创建失败");
		}
		finally{
			db.close();
		}
	}
	public void put(String key,String value)throws IOException{
		try{
			Options options = new Options();
			options.createIfMissing(true);
			db = factory.open(new File("example"), options);
			db.put(bytes(key), bytes(value));
			
		}catch(IOException ex){
			System.out.println("数据库创建失败");
		}
		finally{
			db.close();
		}
		
	}
	
	public void dbIterator ()throws IOException{
		try{
			DBIterator iterator = db.iterator();
			try {
			  for(iterator.seekToFirst(); iterator.hasNext(); iterator.next()) {
			    String key = asString(iterator.peekNext().getKey());
			    String value = asString(iterator.peekNext().getValue());
			    System.out.println(key+" = "+value);
			  }
			} finally {
			  // Make sure you close the iterator to avoid resource leaks.
			  iterator.close();
			}
		}finally{
			db.close();
		}
		
	}
	
	
	public String getDbFileName() {
		return dbFileName;
	}
	public void setDbFileName(String dbFileName) {
		this.dbFileName = dbFileName;
	}
}
