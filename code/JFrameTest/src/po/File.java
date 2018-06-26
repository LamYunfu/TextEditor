package po;

import java.sql.Timestamp;

public class File {
	private int id;
	private int fId;
	private String title;
	private String tag;
	private Timestamp createTime;
	private Timestamp modifiedTime;
	private String size;
	
	
	public File(int id, int fId, String title, String tag, Timestamp createTime, Timestamp modifiedTime, String size) {
		super();
		this.id = id;
		this.fId = fId;
		this.title = title;
		this.tag = tag;
		this.createTime = createTime;
		this.modifiedTime = modifiedTime;
		this.size = size;
	}
	
	public File(){}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getfId() {
		return fId;
	}


	public void setfId(int fId) {
		this.fId = fId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public Timestamp getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}


	public Timestamp getModifiedTime() {
		return modifiedTime;
	}


	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
