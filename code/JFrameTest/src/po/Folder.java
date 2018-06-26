package po;

public class Folder {
	private int id;
	private String name;
	private int ownerID;		//文件夹所属用户ID
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOwnerID() {
		return ownerID;
	}
	public void setuID(int uID) {
		this.ownerID = uID;
	}
	public Folder(int id, String name, int ownerID) {
		super();
		this.id = id;
		this.name = name;
		this.ownerID = ownerID;
	}
	
	public Folder(){}
}
